package com.namoo.ns1.service.facade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.namoo.ns1.service.logic.CommunityServiceLogic;

import dom.Community;

public class CommunityServiceTest {
	
	//��setup teardown��
	
	private CommunityService service;
	
	@Before
	public void setUp() {
		//
		this.service = new CommunityServiceLogic(); 
	}
			
	@Test
	public void testRegistCommunity() {
		//
		String communityName = "java Ŀ�´�Ƽ";
		String adminName = "�ڻ���";
		String email = "ekdgml@naver.com";
		String password = "1234";
		
		service.registCommunity(communityName, adminName, email, password);
		
		Community community = service.findCommunity(communityName);
		assertEquals("java Ŀ�´�Ƽ", community.getName());
	}

	@Test
	public void testFindCommunity() {
		//
		String communityName = "java Ŀ�´�Ƽ";
		Community community = service.findCommunity(communityName);
		assertEquals("java Ŀ�´�Ƽ", community.getName());
	}

	@Test
	public void testJoinAsMemeber() {
		//
		String communityName = "java Ŀ�´�Ƽ";
		String name = "ȫ�浿";
		String email = "abcd@naver.com";
		String password = "abcd";
		
		service.joinAsMemeber(communityName, name, email, password);
		
		//TODO Ȯ���ʿ�!
	}

}
