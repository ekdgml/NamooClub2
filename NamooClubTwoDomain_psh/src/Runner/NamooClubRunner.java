package Runner;

import java.util.List;
import dom.Community;
import dom.CommunityAssistant;
import dom.CommunityRepository;
import dom.SocialPerson;

public class NamooClubRunner {
//Todo: ���ī��Ʈ
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		CommunityAssistant assistant = new CommunityAssistant();
		createCommunity(assistant);
		createPerson(assistant);
		joinAsMember(assistant);
		
		//Ŀ�´�Ƽ ��� ��ȸ
		List<Community> communities = assistant.findAllCommunities();
		
		/*for(Community community : communities)
		{
			System.out.println(community);
		}*/

	}
	//�ó����� 1.Ŀ�´�Ƽ �����ϱ�
	private static void createCommunity(CommunityAssistant assistant)
	{
		String CommunityName = "java Ŀ�´�Ƽ";
		String adminName = "����";
		String email = "ekdgml@naver.com";
		String password = "12345";
		
		assistant = new CommunityAssistant();
		Community community = assistant.createcommunity(
				CommunityName,
				adminName,
				email, 
				password);
		
		// ������ Ŀ�´�Ƽ ���� Ȯ��
		System.out.println("Ŀ�´�Ƽ �̸� : " + community.getName());
		
	}
	
	public List<Community> findAllCommunities()
	{
		CommunityRepository repo = CommunityRepository.getInstance();
		return repo.readAllCommunities();
	}

    //�ó����� 2.�������
	public static void createPerson(CommunityAssistant assistant) {
		//
		
		String Name = "ȫ�浿";
		String email = "abc@naver.com";
		String password = "abc";
		
		assistant = new CommunityAssistant();
		
		SocialPerson person = assistant.createPerson(Name, email, password);
		
		System.out.println("����� �̸� : " + person.getName());
		System.out.println("����� �̸��� : " + person.getEmail());
	}
	
	//�ó����� 3.Ŀ�´�Ƽ ����� ����
	public static void joinAsMember(CommunityAssistant assistant) {
		//
		String communityName = "java Ŀ�´�Ƽ";
		
		System.out.println("ã������ Ŀ�´�Ƽ �̸� : " + communityName);
		
		CommunityRepository repo = CommunityRepository.getInstance();
		Community community = repo.findCommunity(communityName);
		
		System.out.println("ã�� Ŀ�´�Ƽ �̸� : " + community.getName());
		
		SocialPerson person = assistant.createPerson(" ȫ�浿", "abc@naver.com", "abc");
		
		System.out.println("�� ���� :" + person.getName() + "\n" + person.getEmail() + "\n" + person.getPassword());
		
		community.addMember(person);
		
		System.out.println("��� �� : " + community.getCount());

	}
}
