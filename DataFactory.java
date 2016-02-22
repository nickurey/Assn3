/*******************************************************************************
 *  CSCI Assignment 
 *  Filename: ${filename}
 *  Author: Nguyen Van Thanh Huy (Nick Nguyen)
 *  UOW Student Number: 5216746
 *  Description:
 *  
 *******************************************************************************/

import java.io.*;
import java.util.*;

import Basic.Member;
import Common.DBQuery;
//import Game.Card;

public class DataFactory {

	public static ArrayList<Member> getAllMembers() {

		ArrayList<Member> MemberList = new ArrayList<Member>();

		// if the storage of Members' data is changed to a xml file
		// or a database, we just need to change this part of the code.
		// Concept of Information Hiding
		// In computer science, information hiding is the principle of
		// segregation of the design decisions in a computer program that
		// are most likely to change, thus protecting other parts of the program
		// from extensive modification if the design decision is changed.

//		try {
//			Scanner fileScanner = new Scanner(new File("Members.dat"));
//
//			while (fileScanner.hasNextLine()) {
//				String data = fileScanner.nextLine();
//				String[] dataArray = data.split("\\|");
//				String loginName = dataArray[0];
//				String hashedPassword = dataArray[1];
//				String chipsString = dataArray[2];
//				int chips = Integer.parseInt(chipsString);
//
//				Member p = new Member(loginName, hashedPassword, chips);
//				MemberList.add(p);
//			}
//
//			fileScanner.close();
//
//		} catch (FileNotFoundException ex) {
//			System.out.println("Members.dat not found.");
//
//		}

		DBQuery query = new DBQuery();
       
        MemberList = query.runGetMemberListQuery("Select * from User");
		
		return MemberList;

	}

	
}
