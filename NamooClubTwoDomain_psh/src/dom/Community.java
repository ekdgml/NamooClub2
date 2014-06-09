package dom;

import java.util.ArrayList;
import java.util.List;
import com.namoo.ns1.common.Identifiable;

public class Community implements Identifiable{

	private static final long serialVersionUID = 7038230122488561443L;
	private CommunityManager manager;
	private List<CommunityMember> members;
	private String name;
	private String descript;

	public CommunityManager getManager() {
		return manager;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Community()
	{
		this.members = new ArrayList<CommunityMember>();
	}

	public Community(String communityName, SocialPerson admin) {
		//
		this();
		this.name = communityName;
		
		addManager(admin);
		addMember(admin);
	}

	public void addManager(SocialPerson manager) {
		//
		this.manager = new CommunityManager(manager);
	}

	public void addMember(SocialPerson person) {
		//
		this.members.add(new CommunityMember(person));

	}

	public String getName() {
		//
		return name;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		builder.append("커뮤니티 이름: " + name);
		builder.append(", Manager 이름: " + manager.getName());
		builder.append(", 회원 수: " + members.size());
		
		return builder.toString();
	}

	public int getCount() {
		//
		int counter = members.size();
		return counter;
	}
	
	
	
	public  CommunityMember findMember(String email) {
		// 
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				return member; 
			}
		}
		return null;
	}
	
	public CommunityManager findManager(String email) {
		//
		if (manager.getEmail().equals(email)) {
			return manager;
		} return null;
	}
	
	public List<CommunityMember> getMembers() {
		return members;
	}

	@Override
	public String getOId() {
		//
		return name;
	}
	
	public void removeMember(String email) {
		// 
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}
}