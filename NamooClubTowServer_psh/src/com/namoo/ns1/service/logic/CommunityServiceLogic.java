package com.namoo.ns1.service.logic;

import java.util.List;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.exception.NamooClubException;
import com.namoo.ns1.service.facade.CommunityService;

import dom.Community;
import dom.CommunityMember;
import dom.SocialPerson;

public class CommunityServiceLogic implements CommunityService {

	private EntityManager em;

	public CommunityServiceLogic() {
		//
		this.em = EntityManager.getInstance();
	}

	public Community registCommunity(String communityName, String name,
			String email, String password) {
		//
		// 1.Socialperson 객체 생성 + 저장
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);

		// 2.Community 객체 생성 + 저장
		Community community = new Community(communityName, person);
		em.store(community);

		return em.find(Community.class, communityName);
	}

	public Community findCommunity(String communityName) {
		//
		return em.find(Community.class, communityName);
	}

	public void joinAsMemeber(String communityName, String name, String email,
			String password) {
		//
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);

		Community community = em.find(Community.class, communityName);
		community.addMember(person);
		em.store(community); // 바뀐건 community! community안에서 바뀐것이므로!
	}

	@Override
	public List<Community> findAllCommunities() {
		//
		return em.findAll(Community.class);
	}

	@Override
	public Community loginRegistCom(String communityName, String email, String descript) throws NamooClubException {
		//
		SocialPerson person = em.find(SocialPerson.class, email);
		if (person == null) {
			throw new RuntimeException("해당하는 email을 가진 사람이 없습니다.");
		} 
		
		// 존재하는 커뮤니티 인지 확인
		if (em.find(Community.class, communityName) != null) {
			throw new NamooClubException("이미 존재하는 커뮤니티입니다.");
		}
		
		Community community = new Community(communityName, person);
		community.setDescript(descript);
		community.addManager(person);
		em.store(community);
		
		return em.find(Community.class, communityName);
	}
	
	@Override
	public void loginJoinCom(String communityName, String email) throws NamooClubException {
		//
		SocialPerson person = em.find(SocialPerson.class, email);
		if (person == null) {
			throw new RuntimeException("해당하는 이메일을 가진 사람이 없습니다.");
		} 
			
		Community community = em.find(Community.class, communityName);
		if (community != null && community.findMember(email) != null) {
			throw new NamooClubException("이미 해당 커뮤니티에 가입되어 있습니다.");
		} else if (community == null) {
			throw new NamooClubException("해당 커뮤니티가 없습니다.");
		}
		community.addMember(person);
		em.store(community);
	}

	@Override
	public List<CommunityMember> findComMembers(String communityName) throws NamooClubException {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("해당하는 커뮤니티가 없습니다.");
		}
		return community.getMembers();
	}

	@Override
	public void removeCommunity(String communityName)  {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("해당하는 커뮤니티가 없습니다.");
		} 
			em.remove(community);
		}
	
	@Override
	public void withdrawalCommunity(String communityName, String email) {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("커뮤니티가 존재하지 않습니다.");
		} else if (community != null && community.findMember(email) == null) {
			throw new NamooClubException("커뮤니티에 가입되어있지 않습니다.");
		}
		community.removeMember(email);
		em.store(community);
	}
	
}



