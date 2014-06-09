package com.namoo.ns1.service.facade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.namoo.ns1.service.logic.CommunityServiceLogic;

import dom.Community;

public class CommunityServiceTest {
	
	//¡Úsetup teardown¡Ú
	
	private CommunityService service;
	
	@Before
	public void setUp() {
		//
		this.service = new CommunityServiceLogic(); 
	}
			
	@Test
	public void testRegistCommunity() {
		//
		String communityName = "java Ä¿¹Â´ÏÆ¼";
		String adminName = "¹Ú»óÈñ";
		String email = "ekdgml@naver.com";
		String password = "1234";
		
		service.registCommunity(communityName, adminName, email, password);
		
		Community community = service.findCommunity(communityName);
		assertEquals("java Ä¿¹Â´ÏÆ¼", community.getName());
	}

	@Test
	public void testFindCommunity() {
		//
		String communityName = "java Ä¿¹Â´ÏÆ¼";
		Community community = service.findCommunity(communityName);
		assertEquals("java Ä¿¹Â´ÏÆ¼", community.getName());
	}

	@Test
	public void testJoinAsMemeber() {
		//
		String communityName = "java Ä¿¹Â´ÏÆ¼";
		String name = "È«±æµ¿";
		String email = "abcd@naver.com";
		String password = "abcd";
		
		service.joinAsMemeber(communityName, name, email, password);
		
		//TODO È®ÀÎÇÊ¿ä!
	}

}
