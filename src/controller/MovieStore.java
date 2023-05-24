package controller;


import java.util.Scanner;

public class MovieStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("Welcome to movie store!");	
		Scanner sc = new Scanner(System.in);
		System.out.println("Option : 1.User 2.Admin");
		int input = sc.nextInt();
		if(input == 1)
			UserControl.main(args);
		else {
			AdminControl.checkAdmin(sc);
		}
		
	}

}
