package dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityRepository {
	
	private static CommunityRepository instance = new CommunityRepository();
	
	private Map<String, Community> communities;
	
	//-------------------------------------------------------------------------
	
	private CommunityRepository() {
		//
		this.communities = new HashMap<String, Community>();
	}
	
	public static CommunityRepository getInstance() {
		//
		return instance;
	}
	
//------------------------------------------------------------------------------
	
	public void addCommunity(Community community) {
		//
		this.communities.put(community.getName(), community);
	}

	public List<Community> readAllCommunities() {
		//
		List<Community> communities = new ArrayList<Community>();
		communities.addAll(this.communities.values());
		
		return communities;
	}
	
	  public Community findCommunity(String communityName) {
	    	//
	    	return communities.get(communityName);
	    }
}
