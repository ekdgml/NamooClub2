package dom;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
	//
	private static PersonRepository instance = new PersonRepository();
	
	private List<SocialPerson> people;

	
	
	//---------------------------------------------------------------
	
	private PersonRepository() {
		//
		this.people = new ArrayList<>();
	}
	
	public static PersonRepository getInstance() {
		//
		return instance;
	}	
	//----------------------------------------------------------------------

    public void addPerson(SocialPerson person) {
	// 
	this.people.add(person);
    }

    
	
	
  

}
