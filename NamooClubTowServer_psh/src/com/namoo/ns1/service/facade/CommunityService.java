package com.namoo.ns1.service.facade;

import java.util.List;
import com.namoo.ns1.service.exception.NamooClubException;

import dom.Community;
import dom.CommunityMember;

public interface CommunityService {
	//
	public Community registCommunity(
			String communityName, 
			String name,
			String email, 
			String password);

	public Community findCommunity(String communityName);

	public void joinAsMemeber(
			String communityName, 
			String name, 
			String email,
			String password);

    public List<Community> findAllCommunities();
    	
    public Community loginRegistCom(String communityName, String email, String descript) throws NamooClubException;
    
    public void loginJoinCom(String communityName, String email) throws NamooClubException;
    
    public List<CommunityMember> findComMembers(String selectedCommunity) throws NamooClubException;
    
    public void removeCommunity(String communityName);
    
    public void withdrawalCommunity(String communityName, String email);
}
    	
    