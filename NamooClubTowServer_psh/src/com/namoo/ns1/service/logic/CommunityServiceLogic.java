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
		// 1.Socialperson ��ü ���� + ����
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);

		// 2.Community ��ü ���� + ����
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
		em.store(community); // �ٲ�� community! community�ȿ��� �ٲ���̹Ƿ�!
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
			throw new RuntimeException("�ش��ϴ� email�� ���� ����� �����ϴ�.");
		} 
		
		// �����ϴ� Ŀ�´�Ƽ ���� Ȯ��
		if (em.find(Community.class, communityName) != null) {
			throw new NamooClubException("�̹� �����ϴ� Ŀ�´�Ƽ�Դϴ�.");
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
			throw new RuntimeException("�ش��ϴ� �̸����� ���� ����� �����ϴ�.");
		} 
			
		Community community = em.find(Community.class, communityName);
		if (community != null && community.findMember(email) != null) {
			throw new NamooClubException("�̹� �ش� Ŀ�´�Ƽ�� ���ԵǾ� �ֽ��ϴ�.");
		} else if (community == null) {
			throw new NamooClubException("�ش� Ŀ�´�Ƽ�� �����ϴ�.");
		}
		community.addMember(person);
		em.store(community);
	}

	@Override
	public List<CommunityMember> findComMembers(String communityName) throws NamooClubException {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("�ش��ϴ� Ŀ�´�Ƽ�� �����ϴ�.");
		}
		return community.getMembers();
	}

	@Override
	public void removeCommunity(String communityName)  {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("�ش��ϴ� Ŀ�´�Ƽ�� �����ϴ�.");
		} 
			em.remove(community);
		}
	
	@Override
	public void withdrawalCommunity(String communityName, String email) {
		//
		Community community = em.find(Community.class, communityName);
		if (community == null) {
			throw new NamooClubException("Ŀ�´�Ƽ�� �������� �ʽ��ϴ�.");
		} else if (community != null && community.findMember(email) == null) {
			throw new NamooClubException("Ŀ�´�Ƽ�� ���ԵǾ����� �ʽ��ϴ�.");
		}
		community.removeMember(email);
		em.store(community);
	}
	
}



