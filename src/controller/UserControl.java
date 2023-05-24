package controller;

import java.util.Optional;
import java.util.Scanner;

import model.Movie;
import model.MovieDAO;

public class UserControl {
	
	private static final MovieDAO MOVIE_DAO = new MovieDAO();
      public static void main(String[] args) {
    	
    		  Scanner sc = new Scanner(System.in);
    		
    	  System.out.println("User's Option : 1.Search Movies 2.Download Movies 3.Downloadlist 4.Main Menu");
    	  int num = sc.nextInt();
    	  switch (num) {
		case 1 -> MovieStoreControl.searchMovie(sc);
		case 2 -> addToDownloadList(sc);
		case 3 -> showDownloadList(sc);
		case 4 -> MovieStore.main(args);
		
		
		default -> {System.err.println("Invalid Option!");
					main(args);
					
		}
		
		}
      }
	
      static void addToDownloadList(Scanner idInput) {
    	  boolean start = true;
    	  while(start) {
  	    MovieStoreControl.movieList();;
  		System.out.println("Now, you can download movies!");
  		System.out.print("Movie Id : ");
  		int id = idInput.nextInt();
  		Optional<Movie> movieOptional = MOVIE_DAO.getDownload(id);
  		Movie movie = movieOptional.get();
  		Optional<Movie> downloadMovie = MOVIE_DAO.addToDownload(movie);
  		if(downloadMovie.isEmpty())
  			System.out.println("Movie has successfully downloaded!");
  		else
  			System.err.println("The process is failed!");
  		
  		 System.out.print("Do you want to download another movie?(Yes/No) : ");
  		  String decision = idInput.next();
  		  if(decision.equalsIgnoreCase("No"))
  		  start = false;
    	  }
    	  UserControl.main(null);
  	}
  	static void showDownloadList(Scanner input) {
  		System.out.println("Your downloaded movies : ");
  		MOVIE_DAO.showDownload().forEach(System.out :: println);
  		System.out.print("Do you want to exit?(Type Yes) :");
  		String decision = input.next();
  		if(decision.equals("Yes"))
  			main(null);
  	}
}
