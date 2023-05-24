package controller;

import java.util.Optional;
import java.util.Scanner;

import model.Admin;
import model.AdminDAO;


public class AdminControl {
//	private static final MovieDAO MOVIE_DAO = new MovieDAO();
	private static final AdminDAO ADMIN_DAO = new AdminDAO();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Scanner sc = new Scanner(System.in);
     System.out.println("You've been verified as an admin!");
     System.out.println("Option : 1.Movie List  2.Upload Movie  3.Remove Movie  4.Edit Movie  5.Recover Movie From RecycleBin   6.Remove Movie From RecycleBin  7.Add Admin  8.Admin List  9.Main Menu");
     int number = sc.nextInt();
     switch (number) {
	case 1 -> MovieStoreControl.showMovieList(sc);
	case 2 -> MovieStoreControl.addMovie(sc);
	case 3 -> MovieStoreControl.removeMovie(sc);
	case 4 -> MovieStoreControl.editMovie(sc);
	case 5 -> MovieStoreControl.recoverMoviesFromRB(sc);
	case 6 -> MovieStoreControl.permanentDelete(sc);
	case 7 -> addAdmin(sc);
	case 8 -> showAdminList(sc);
	case 9 -> MovieStore.main(args);
	
	
	default ->{ System.err.println("Invalid Option!");
	         
	             main(args);
	   }
	  }
	}
	static void showAdminList(Scanner input) {
		System.out.println("Admin : ");
		ADMIN_DAO.showAdmin().forEach(System.out :: println);
		System.out.print("Do you want to exit?(Type Yes) :");
  		String decision = input.next();
  		if(decision.equals("Yes"))
  			main(null);
	}
    
	static void addAdmin(Scanner adminInput) {
		 boolean start = true;
   	  while(start) {
		System.out.print("Name : ");
		String name = adminInput.next();
		
		System.out.print("Password : ");
		int password = adminInput.nextInt();
		Admin admin = new Admin(name, password);
		Optional<Admin> newAdmin = ADMIN_DAO.addAdmin(admin);
		if(newAdmin.isEmpty())
			System.out.println("New admin has successfully added to admin list!");
		else
			System.err.println("The process is failed!");
		
		 System.out.print("Do you want to add next one?(Yes/No) : ");
 		  String decision = adminInput.next();
 		  if(decision.equalsIgnoreCase("No"))
 		  start = false;
   	  }
   	     main(null);
	}
	static void checkAdmin(Scanner passwordInput) {
		System.out.print("Name : ");
		String name = passwordInput.next();
		System.out.print("Password : ");
		int password = passwordInput.nextInt();
		System.out.println("Name : "+name);
		System.out.println("Password : "+password);
		Optional<Admin> adminInfo = ADMIN_DAO.searchAdmin(password);
		if(adminInfo.isPresent()) {
			System.out.println(adminInfo.get());
			System.out.println("You can control movie store!");
			main(null);
		}
		else {
			System.err.println("Wrong password!");
			
			MovieStore.main(null);
		}
	}
}
