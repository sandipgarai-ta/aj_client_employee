package com.mib.users.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "path")
public class Path {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="path_id")
	private int id;
	@Column(name="path")
	private String path;
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Path [id=" + id + ", path=" + path + ", role=" + role + "]";
	}
}
