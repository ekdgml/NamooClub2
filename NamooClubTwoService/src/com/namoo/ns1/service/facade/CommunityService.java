package com.namoo.ns1.service.facade;

import dom.entity.Community;

public interface CommunityService {

	/**
	 * 
	 * @param communityName
	 * @param adminName
	 * @param email
	 * @param password
	 */
	public void registCommunity(String communityName, String adminName, String email, String password);

	/**
	 * 
	 * @param communityName
	 */
	public Community findCommunity(String communityName);

	/**
	 * 
	 * @param communityName
	 * @param name
	 * @param email
	 * @param password
	 */
	public void joinAsMember(String communityName, String name, String email, String password);

	/**
	 * 
	 * @param communityName
	 */
	public int countMembers(String communityName);
	
	/**
	 * @param communityName
	 */
	public void removeCommunity(String communityName);

}