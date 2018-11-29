package com.mib.users.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mib.users.models.Path;
import com.mib.users.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<Path> matchers=userService.findPaths();
		http.csrf().disable();
		//.authorizeRequests()
        //.antMatchers("/").permitAll()
        //.antMatchers(HttpMethod.POST, "/login").permitAll();
		
		for(Path m : matchers) {
			System.out.println("Path = "+m.getPath()+" role = "+m.getRole());
			if(m.getRole().getId()>0) {
				http.authorizeRequests().antMatchers(m.getPath()).hasAuthority(m.getRole().getRole());
			} else {
				http.authorizeRequests().antMatchers(m.getPath()).permitAll();
			}
			
		      /*http.authorizeRequests().antMatchers(m.getPath().toString())
		        .access("hasRole('"+m.getRole().toString()+"')")
		        ;*/
		}
		
		http.
		authorizeRequests()
				.anyRequest()
				.authenticated().and()
		        // We filter the api/login requests
		        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
		                UsernamePasswordAuthenticationFilter.class)
		        // And filter other requests to check the presence of JWT in header
		        .addFilterBefore(new JWTAuthenticationFilter(),
		                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}