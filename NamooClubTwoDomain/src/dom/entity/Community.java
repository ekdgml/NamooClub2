package dom.entity;

import java.util.ArrayList;
import java.util.List;

import com.namoo.ns1.common.Identifiable;

public class Community implements Identifiable {

	private static final long serialVersionUID = -1649818789572216203L;
	
	private String name;
	private CommunityManager manager;
	private List<CommunityMember> members;

	//--------------------------------------------------------------------------
	// constructors
	
	/**
	 * 
	 * @param communityName
	 * @param admin
	 */
	public Community(String communityName, SocialPerson admin){
		//
		this.name = communityName;
		this.members = new ArrayList<CommunityMember>();
		
		setManager(admin);
		addMember(admin);
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommunityManager getManager() {
		return manager;
	}

	public List<CommunityMember> getMembers() {
		return members;
	}

	//--------------------------------------------------------------------------
	// public methods
	
	/**
	 * 
	 * @param rolePerson
	 */
	public void setManager(SocialPerson rolePerson){
		//
		CommunityManager manager = new CommunityManager(name, rolePerson);
		this.manager = manager;
	}

	/**
	 * 
	 * @param rolePerson
	 */
	public void addMember(SocialPerson rolePerson){
		//
		CommunityMember member = new CommunityMember(name, rolePerson);
		this.members.add(member);
	}

	@Override
	public String getOId() {
		// 
		return name;
	}
}