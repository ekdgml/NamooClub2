package dom;

import com.namoo.ns1.common.Identifiable;

public class SocialPerson implements Identifiable {
	
	private static final long serialVersionUID = 4393376958377507064L;
	
	private String email;
	private String name;
	private String password;

	public SocialPerson(String name, String email, String password)
	{
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() 
	{	
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getOId() {
		//
		return email;
	}

}