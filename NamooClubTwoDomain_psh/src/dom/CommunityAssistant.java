package dom;

import java.util.List;

/**
 * @author kosta-19
 * @version 1.0
 * @created 18-2-2014 坷饶 12:48:42
 */ 
public class CommunityAssistant 
{

	public CommunityAssistant() {
		//
	}

	/**
	 * @param communityname
	 * 
	 * @param communityName
	 * @param adminName
	 * @param email
	 * @param password    password
	 */
	public Community createcommunity(String communityName, String adminName, String email, String password) {
		//
		
		//1.Socialperson 按眉 积己
		SocialPerson person = new SocialPerson(adminName, email, password);
		
		//2.Community 按眉积己
		Community community = new Community(communityName, person);
		
		//3.Repository俊 历厘
		CommunityRepository repo = CommunityRepository.getInstance();
		repo.addCommunity(community);
		
		return community;
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param password    password
	 */
	public SocialPerson createPerson(String name, String email, String password){
		//
		
		SocialPerson person = new SocialPerson(name, email, password);
		
		PersonRepository tory = PersonRepository.getInstance();
		tory.addPerson(person);
		
		return person;
	}

	/**
	 * 
	 * @param communityName
	 * @param member    member
	 */
	public void joinAsMember(String communityName, SocialPerson person){
		//
		CommunityRepository repo = CommunityRepository.getInstance();
		Community community = repo.findCommunity(communityName);
		
		community.addMember(person);

	}
	
	public List<Community> findAllCommunities() {
		//
		CommunityRepository repo = CommunityRepository.getInstance();
		
		return repo.readAllCommunities();
	}

	public Community findCommunity(String communityName) {
		// 
		CommunityRepository repo = CommunityRepository.getInstance();
		return repo.findCommunity(communityName);
	}



	
	

}