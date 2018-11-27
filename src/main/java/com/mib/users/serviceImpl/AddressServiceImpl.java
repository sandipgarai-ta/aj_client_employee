package com.mib.users.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mib.users.models.Address;
import com.mib.users.repository.AddressRepository;

@Service
public class AddressServiceImpl {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Value(value = "${store.location.radius}")
	Double radius;
	
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
	public List<Address> getAddressInRadius(Double lat,Double lng) {
		List<Address> allAddresses = addressRepository.findAll();
		List<Address> filteredAddresses = new ArrayList<>();
		double lat1 = lat;
		double lon1 = lng;
		
		for (Iterator iterator = allAddresses.iterator(); iterator.hasNext();) {
			Address address = (Address) iterator.next();
			if(inRadius(lat1, lon1, address.getLatitude(), address.getLongitude())) {
				filteredAddresses.add(address);
			}
		}
		
		return filteredAddresses;
	}
	public boolean inRadius(double lat1,double lon1,double lat2,double lon2) {
		
		boolean test = false;
		
		/*double lat1=22.5713942800;
		double lat2=22.5720184200;
		double lon1=88.3505618700;
		double lon2=88.3489847300;*/

		double R = 6371; // radius of earth in KM
		double f1 = lat1*Math.PI/180;//lat1.toRadians(); //to radian = 1deg*Math.PI/180
		double f2 = lat2*Math.PI/180;
		double dlatr = (lat2-lat1)*Math.PI/180;
		double dlonr = (lon2-lon1)*Math.PI/180;

		double a = Math.sin(dlatr /2) * Math.sin(dlatr /2) +
		        Math.cos(f1) * Math.cos(f2) *
		        Math.sin(dlonr/2) * Math.sin(dlonr/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c;
		
		System.out.println("Distance = "+d);
		
		if(d<=radius) {
			test = true;
		}
		
		return test;
	}
}
