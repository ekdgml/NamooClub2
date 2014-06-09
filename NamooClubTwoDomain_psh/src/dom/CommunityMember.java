package dom;

import java.io.Serializable;

/**
 * @author kosta-19
 * @version 1.0
 * @created 18-2-2014 ���� 12:48:42
 */
public class CommunityMember implements Serializable {  //member�� ����� ���� ���� socialperson�� ������ ����.

	private static final long serialVersionUID = -9112571068036190717L;
	
	private SocialPerson rolePerson;

	public CommunityMember(SocialPerson rolePerson)
	{
		this.rolePerson = rolePerson;
	}

	public String getEmail() {
		// 
		return rolePerson.getEmail();
	}
	
	public String getName() {
		//
		return rolePerson.getName();
	}

}