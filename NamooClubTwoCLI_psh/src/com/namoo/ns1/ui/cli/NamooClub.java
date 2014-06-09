package com.namoo.ns1.ui.cli;

import java.util.Scanner;

import com.namoo.ns1.service.exception.NamooClubException;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.SocialPersonService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.Community;
import dom.SocialPerson;

public class NamooClub {
	//
	private CommunityService service;
	private SocialPersonService personService;
	private Scanner scanner = new Scanner(System.in);
	private SocialPerson loginPerson;

	public NamooClub() {
		//
		this.service = NamooClubServiceFactory.getInstance()
				.getCommunityService();
		this.personService = NamooClubServiceFactory.getInstance()
				.getSocialPersonService();
	}

	// -----------------------------------------------------------------------------------

	public static void main(String[] args) throws NamooClubException {
		//
		new NamooClub().show();
	}

	// �޴������ֱ�
	public void show() throws NamooClubException {
		//
		while (true) {
			System.out.println("=================================");
			if (loginPerson != null) {
				System.out.println("      Menu   [" + loginPerson.getName()
						+ "]");
			} else {
				System.out.println("          Menu         ");
			}
			System.out.println("=================================");
			System.out.println(" 1. �ֹ����� ����");
			System.out.println(" 2. �α���");
			System.out.println(" 3. �α׾ƿ�");
			System.out.println(" 4. Ŀ�´�Ƽ ���");
			System.out.println(" 5. Ŀ�´�Ƽ ����");
			System.out.println(" 6. Ŀ�´�Ƽ ȸ�� �� ��ȸ");
			System.out.println(" 7. ȸ�� ��ȸ");
			System.out.println(" 8. Ŀ�´�Ƽ ��� ��ȸ");
			System.out.println(" 0. ����");
			System.out.println("=================================");
			System.out.println("���� > ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // ���ٰǳʶ�� ���ֱ�(���ۿ� ���� ����Ű ����)

			switch (choice) {
			case 1:
				showJoinAsTownerMenu();
				break;
			case 2:
				showLoginMenu();
				break;
			case 3:
				showLogoutMenu();
				break;
			case 4:
				showRegistCommunityMenu();
				break;
			case 5:
				showJoinAsMemeberMenu();
				break;
			case 6:
				showCountMemberMenu();
				break;
			case 7:
				showCheckInforMenu();
				break;
			case 8:
				showCheckCommunityMenu();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
				break;
			default:
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}

	

	// �ֹ����� �����ϱ�
	private void showJoinAsTownerMenu() throws NamooClubException {
		//
		System.out.println("�̸��� �Է��ϼ���.");
		String name = scanner.nextLine();
		System.out.println("�̸����� �Է��ϼ���.");
		String email = scanner.nextLine();
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		String password = scanner.nextLine();
		personService.registPerson(name, email, password);
		System.out.println("�Է��Ͻ� ������ " + name + "/" + email + "/" + password);

	}

	// �α����ϱ�
	private void showLoginMenu() {
		//
		while (true) {
			System.out.println("�̸����� �Է��ϼ���.");
			String email = scanner.nextLine();
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			String password = scanner.nextLine();

			try {
				personService.loginUser(email, password);
				System.out.println("�α��εǾ����ϴ�.");
				this.loginPerson = personService.findPerson(email);

				System.out.println("������� �̸��� : " + this.loginPerson.getName()
						+ "�Դϴ�.");
				break;
			} catch (NamooClubException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	private void showLogoutMenu() {
		//
		this.loginPerson = null;
		System.out.println("�α׾ƿ��Ǿ����ϴ�.");
		
	}

	// Ŀ�´�Ƽ �����ϱ�
	private void showRegistCommunityMenu() {
		//
		String communityName;
		if (this.loginPerson != null) {
			while (true) {
				System.out.println("Ŀ�´�Ƽ �̸� : ");
				communityName = scanner.nextLine();
				if (service.findCommunity(communityName) != null) {
					System.out.println("�̹� ��ϵǾ� �ִ� �̸��Դϴ�. �ٽ��Է����ּ���.");

				} else {
					String name = this.loginPerson.getName();
					String email = this.loginPerson.getEmail();
					String password = this.loginPerson.getPassword();
					service.registCommunity(communityName, name, email,
							password);
					System.out.println("�����Ͻ� Ŀ�´�Ƽ �̸��� : " + communityName
							+ " �Դϴ�.");
					break;
				}
			}
		} else {
			System.out.println("���� �α����ϼ���.");
		}
	}

	// Ŀ�´�Ƽ �����ϱ�
	private void showJoinAsMemeberMenu() {
		//
		// Ŀ�´�Ƽ ã��
		while (true) {
			if (this.loginPerson != null) {
				//
				System.out.println("���ϴ� Ŀ�´�Ƽ�̸��� �Է��ϼ���.");
				String communityName = scanner.nextLine();
				Community community = service.findCommunity(communityName);

				if (community == null) {
					System.out.println("Ŀ�´�Ƽ�� �����ϴ�.");
				} else {
					if (community.getName().equals(communityName)) {
						System.out
								.println("������ ���� Ŀ�´�Ƽ �Դϴ�.Manager�� �̹� ���ԵǾ��ֽ��ϴ�.");
						break;
					} else {
						System.out.println(community.getName()
								+ " Ŀ�´�Ƽ�� ���ԵǼ̽��ϴ�.");
						String name = this.loginPerson.getName();
						String email = this.loginPerson.getEmail();
						String password = this.loginPerson.getPassword();
						service.joinAsMemeber(communityName, name, email,
								password);
					}
				}

			} else {
				System.out.println("�α����� ���� �ϼ���.");
			}
		}
	}

	// Ŀ�´�Ƽ�� ��� �� ��ȸ�ϱ�
	private void showCountMemberMenu() {
		//
		System.out.println("���ϴ� Ŀ�´�Ƽ�̸��� �Է��ϼ���.");
		String communityName = scanner.nextLine();

		Community community = service.findCommunity(communityName);

		if (community == null) {
			System.out.println("Ŀ�´�Ƽ�� �����ϴ�.");
		} else {
			System.out.println(community.getName() + "�� ��� ���� "
					+ community.getCount() + "�� �Դϴ�.");
		}

	}

	// ���� Ȯ���ϱ�
	private void showCheckInforMenu() {
		//
		String communityName;

		while (true) {
			System.out.println("Ŀ�´�Ƽ �̸��� �Է��ϼ���.");
			communityName = scanner.nextLine();
			Community community = service.findCommunity(communityName);
			if (community == null) {
				System.out.println("Ŀ�´�Ƽ�� �����ϴ�. �ٽ� �Է��ϼ���.");
			} else {
				while (true) {
					System.out.println("�̸����� �Է��ϼ���.");
					String email = scanner.nextLine();
					if (email == null) {
						System.out.println("�̸����� �����ϴ�. �ٽ� �Է��ϼ���.");
					} else {
						System.out.println("�̸� : "
								+ community.findMember(email).getName());
						break;
					}
					break;

				}
				break;
			}
		}
	}

	// Ŀ�´�Ƽ ��� ��ȸ�ϱ�
	private void showCheckCommunityMenu() {
		//
		System.out.println(service.findAllCommunities());
	}

}
