package com.namoo.ns1.service.facade;

import java.util.List;
import com.namoo.ns1.service.exception.NamooClubException;

import dom.SocialPerson;

public interface SocialPersonService {

	public SocialPerson registPerson(
			String name,
			String email, 
			String password) throws NamooClubException;

	public SocialPerson findPerson(String email);
	
	public boolean loginUser(String email, String password);

	public List<SocialPerson> findAllTowners();
	
}
