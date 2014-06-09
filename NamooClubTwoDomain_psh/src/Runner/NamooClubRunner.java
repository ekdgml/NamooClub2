package Runner;

import java.util.List;
import dom.Community;
import dom.CommunityAssistant;
import dom.CommunityRepository;
import dom.SocialPerson;

public class NamooClubRunner {
//Todo: 멤버카운트
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		CommunityAssistant assistant = new CommunityAssistant();
		createCommunity(assistant);
		createPerson(assistant);
		joinAsMember(assistant);
		
		//커뮤니티 목록 조회
		List<Community> communities = assistant.findAllCommunities();
		
		/*for(Community community : communities)
		{
			System.out.println(community);
		}*/

	}
	//시나리오 1.커뮤니티 생성하기
	private static void createCommunity(CommunityAssistant assistant)
	{
		String CommunityName = "java 커뮤니티";
		String adminName = "상희";
		String email = "ekdgml@naver.com";
		String password = "12345";
		
		assistant = new CommunityAssistant();
		Community community = assistant.createcommunity(
				CommunityName,
				adminName,
				email, 
				password);
		
		// 생성된 커뮤니티 정보 확인
		System.out.println("커뮤니티 이름 : " + community.getName());
		
	}
	
	public List<Community> findAllCommunities()
	{
		CommunityRepository repo = CommunityRepository.getInstance();
		return repo.readAllCommunities();
	}

    //시나리오 2.사람생성
	public static void createPerson(CommunityAssistant assistant) {
		//
		
		String Name = "홍길동";
		String email = "abc@naver.com";
		String password = "abc";
		
		assistant = new CommunityAssistant();
		
		SocialPerson person = assistant.createPerson(Name, email, password);
		
		System.out.println("등록한 이름 : " + person.getName());
		System.out.println("등록한 이메일 : " + person.getEmail());
	}
	
	//시나리오 3.커뮤니티 멤버로 가입
	public static void joinAsMember(CommunityAssistant assistant) {
		//
		String communityName = "java 커뮤니티";
		
		System.out.println("찾으려는 커뮤니티 이름 : " + communityName);
		
		CommunityRepository repo = CommunityRepository.getInstance();
		Community community = repo.findCommunity(communityName);
		
		System.out.println("찾은 커뮤니티 이름 : " + community.getName());
		
		SocialPerson person = assistant.createPerson(" 홍길동", "abc@naver.com", "abc");
		
		System.out.println("내 정보 :" + person.getName() + "\n" + person.getEmail() + "\n" + person.getPassword());
		
		community.addMember(person);
		
		System.out.println("멤버 수 : " + community.getCount());

	}
}
