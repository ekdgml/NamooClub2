package com.namoo.ns1.service.logic;
import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.CommunityService;

import dom.entity.Community;
import dom.entity.SocialPerson;

public class CommunityServiceLogic implements CommunityService {

	private EntityManager em;
	
	public CommunityServiceLogic() {
		//
		em = EntityManager.getInstance();
	}
	
	@Override
	public void registCommunity(String communityName, String adminName, String email, String password){
		//
		SocialPerson admin = createPerson(adminName, email, password);
		Community community = new Community(communityName, admin);
		
		em.store(community);
	}

	private SocialPerson createPerson(String name, String email, String password) {
		// 
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);
		
		return person;
	}

	@Override
	public Community findCommunity(String communityName){
		//
		return em.find(Community.class, communityName);
	}

	@Override
	public void joinAsMember(String communityName, String name, String email, String password){
		//
		Community community = em.find(Community.class, communityName);
		
		SocialPerson towner = createPerson(name, email, password);
		community.addMember(towner);
		
		em.store(community);
	}

	@Override
	public int countMembers(String communityName){
		//
		Community community = em.find(Community.class, communityName);
		if (community != null) {
			return community.getMembers().size();
		}
		
		return 0;
	}

	@Override
	public void removeCommunity(String communityName) {
		// 
		em.remove(Community.class, communityName);
	}

}