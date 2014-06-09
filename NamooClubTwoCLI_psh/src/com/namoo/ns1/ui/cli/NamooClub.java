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

	// 메뉴보여주기
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
			System.out.println(" 1. 주민으로 가입");
			System.out.println(" 2. 로그인");
			System.out.println(" 3. 로그아웃");
			System.out.println(" 4. 커뮤니티 등록");
			System.out.println(" 5. 커뮤니티 가입");
			System.out.println(" 6. 커뮤니티 회원 수 조회");
			System.out.println(" 7. 회원 조회");
			System.out.println(" 8. 커뮤니티 목록 조회");
			System.out.println(" 0. 종료");
			System.out.println("=================================");
			System.out.println("선택 > ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // 한줄건너띄기 없애기(버퍼에 남은 엔터키 제거)

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
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.out.println("잘목입력하셨습니다.");
				break;
			}
		}
	}

	

	// 주민으로 가입하기
	private void showJoinAsTownerMenu() throws NamooClubException {
		//
		System.out.println("이름을 입력하세요.");
		String name = scanner.nextLine();
		System.out.println("이메일을 입력하세요.");
		String email = scanner.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String password = scanner.nextLine();
		personService.registPerson(name, email, password);
		System.out.println("입력하신 정보는 " + name + "/" + email + "/" + password);

	}

	// 로그인하기
	private void showLoginMenu() {
		//
		while (true) {
			System.out.println("이메일을 입력하세요.");
			String email = scanner.nextLine();
			System.out.println("비밀번호를 입력하세요.");
			String password = scanner.nextLine();

			try {
				personService.loginUser(email, password);
				System.out.println("로그인되었습니다.");
				this.loginPerson = personService.findPerson(email);

				System.out.println("사용자의 이름은 : " + this.loginPerson.getName()
						+ "입니다.");
				break;
			} catch (NamooClubException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	private void showLogoutMenu() {
		//
		this.loginPerson = null;
		System.out.println("로그아웃되었습니다.");
		
	}

	// 커뮤니티 생성하기
	private void showRegistCommunityMenu() {
		//
		String communityName;
		if (this.loginPerson != null) {
			while (true) {
				System.out.println("커뮤니티 이름 : ");
				communityName = scanner.nextLine();
				if (service.findCommunity(communityName) != null) {
					System.out.println("이미 등록되어 있는 이름입니다. 다시입력해주세요.");

				} else {
					String name = this.loginPerson.getName();
					String email = this.loginPerson.getEmail();
					String password = this.loginPerson.getPassword();
					service.registCommunity(communityName, name, email,
							password);
					System.out.println("생성하신 커뮤니티 이름은 : " + communityName
							+ " 입니다.");
					break;
				}
			}
		} else {
			System.out.println("먼저 로그인하세요.");
		}
	}

	// 커뮤니티 가입하기
	private void showJoinAsMemeberMenu() {
		//
		// 커뮤니티 찾기
		while (true) {
			if (this.loginPerson != null) {
				//
				System.out.println("원하는 커뮤니티이름을 입력하세요.");
				String communityName = scanner.nextLine();
				Community community = service.findCommunity(communityName);

				if (community == null) {
					System.out.println("커뮤니티가 없습니다.");
				} else {
					if (community.getName().equals(communityName)) {
						System.out
								.println("본인이 만든 커뮤니티 입니다.Manager로 이미 가입되어있습니다.");
						break;
					} else {
						System.out.println(community.getName()
								+ " 커뮤니티에 가입되셨습니다.");
						String name = this.loginPerson.getName();
						String email = this.loginPerson.getEmail();
						String password = this.loginPerson.getPassword();
						service.joinAsMemeber(communityName, name, email,
								password);
					}
				}

			} else {
				System.out.println("로그인을 먼저 하세요.");
			}
		}
	}

	// 커뮤니티의 멤버 수 조회하기
	private void showCountMemberMenu() {
		//
		System.out.println("원하는 커뮤니티이름을 입력하세요.");
		String communityName = scanner.nextLine();

		Community community = service.findCommunity(communityName);

		if (community == null) {
			System.out.println("커뮤니티가 없습니다.");
		} else {
			System.out.println(community.getName() + "의 멤버 수는 "
					+ community.getCount() + "명 입니다.");
		}

	}

	// 정보 확인하기
	private void showCheckInforMenu() {
		//
		String communityName;

		while (true) {
			System.out.println("커뮤니티 이름을 입력하세요.");
			communityName = scanner.nextLine();
			Community community = service.findCommunity(communityName);
			if (community == null) {
				System.out.println("커뮤니티가 없습니다. 다시 입력하세요.");
			} else {
				while (true) {
					System.out.println("이메일을 입력하세요.");
					String email = scanner.nextLine();
					if (email == null) {
						System.out.println("이메일이 없습니다. 다시 입력하세요.");
					} else {
						System.out.println("이름 : "
								+ community.findMember(email).getName());
						break;
					}
					break;

				}
				break;
			}
		}
	}

	// 커뮤니티 목록 조회하기
	private void showCheckCommunityMenu() {
		//
		System.out.println(service.findAllCommunities());
	}

}
