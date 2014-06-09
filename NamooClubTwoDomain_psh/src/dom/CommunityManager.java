package dom;

import java.io.Serializable;

/**
 * @author kosta-19
 * @version 1.0
 * @created 18-2-2014 ¿ÀÈÄ 12:48:42
 */
public class CommunityManager implements Serializable {
	private static final long serialVersionUID = -713187695251906804L;
	
	private SocialPerson rolePerson;

	public CommunityManager(){

	}

	public CommunityManager(SocialPerson rolePerson)
	{
		this.rolePerson = rolePerson;
	}

	public String getName() {
		return rolePerson.getName(); 
	}

	public String getEmail() {
		return rolePerson.getEmail();
	}
}