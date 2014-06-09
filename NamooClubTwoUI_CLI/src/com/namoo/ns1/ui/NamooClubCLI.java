package com.namoo.ns1.ui;

import java.util.Scanner;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

public class NamooClubCLI {
    
    private CommunityService communityService;
    private TownerService townerService;
    private Scanner scanner;
    
    public NamooClubCLI() {
        //
        communityService = NamooClubServiceFactory.getInstance().getCommunityService();
        townerService = NamooClubServiceFactory.getInstance().getTownerService();
        scanner = new Scanner(System.in);
    }
    
    public void show() {
        //
        while(true){
            System.out.println();
            System.out.println("==============================================");
            System.out.println("Menu");
            System.out.println("==============================================");
            System.out.println(" 1. Create a community");
            System.out.println(" 2. Join as a member");
            System.out.println(" 3. Login as a towner");
            System.out.println(" 4. Member count");
            System.out.println(" 0. Program exit");
            System.out.println("==============================================");
            System.out.print("Select number: ");

            int inputNumber = scanner.nextInt();
            scanner.nextLine(); // 
            switch(inputNumber){
                case 1: // 
                    createCommunity(); 
                    break;
                case 2: 
                    joinsAsMember(); 
                    break;
                case 3: 
                    loginAsTowner(); 
                    break;
                case 4: // 
                    countMembers(); 
                    break;
                case 0: 
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Choose again!");
            }
        }
    }

    public void createCommunity(){
        //
        System.out.println("================================");
        System.out.println("1. Create a community ");
        System.out.println("================================");

        String communityName = getInput("Community name");
        String name = getInput("Manager's name");
        String email = getInput("Manager's email");
        String password = getInput("Manager's password");
         
        communityService.registCommunity(communityName, name, email, password);
    }
    
    private void joinsAsMember() {
        // 
        System.out.println("================================");
        System.out.println("2. Join as a member ");
        System.out.println("================================");

        String communityName = getInput("Community name");
        String name = getInput("Member's name");
        String email = getInput("Member's email");
        String password = getInput("Member's password");

        communityService.joinAsMember(communityName, name, email, password);    
    }

    private void loginAsTowner() {
        // 
        System.out.println("================================");
        System.out.println("3. Login as a member");
        System.out.println("================================");
        
        String email = getInput("login id");
        String password = getInput("password");

        if(townerService.loginAsTowner(email, password)) {
        	System.out.println("Login success!"); 
        } else {
        	System.out.println("Login fail!"); 
        }
    }    
    
    private void countMembers() {
        // 
        System.out.println("================================");
        System.out.println("4. Count members");
        System.out.println("================================");
        
        String communityName = getInput("community name");
        int count = communityService.countMembers(communityName); 
        System.out.println("Community members are " + count + ", please enter."); 
        scanner.nextLine();
     }

    private String getInput(String title) {
    	// 
    	String inputText; 
    	System.out.print(title + ":");
    	inputText = scanner.nextLine();
    	
    	return inputText; 
    }

    public static void main(String[] args) {
        //
        new NamooClubCLI().show();
        System.exit(0); 
    }
}
