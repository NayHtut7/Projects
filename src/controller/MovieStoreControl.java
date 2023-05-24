package controller;

import java.util.Optional;
import java.util.Scanner;


import model.Movie;
import model.MovieDAO;

public class MovieStoreControl {

	private static final MovieDAO MOVIE_DAO = new MovieDAO();
//	private static final AdminDAO ADMIN_DAO = new AdminDAO();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    //    Scanner sc = new Scanner(System.in);
	//	addMovie(sc);
    //    editMovie(sc);
	//	searchMovie(sc);
    //  removeMovie(sc);
    //    recoverMoviesFromRB(sc);
    //    showAdminList();
    //    addToDownloadList(sc);
    //    showDownloadList();
       
	}
	
	
	static void showMovieList(Scanner input) {
		System.out.println("Movie List : ");
		 MOVIE_DAO.showAllMovies().forEach(System.out :: println);
		 System.out.print("Do you want to exit?(Type Yes) :");
	  		String decision = input.next();
	  		if(decision.equals("Yes"))
	  		  AdminControl.main(null);
	}
	static void movieList() {
		System.out.println("Movie List : ");
		 MOVIE_DAO.showAllMovies().forEach(System.out :: println);
	}
	static void addMovie(Scanner movieInput) {
		 boolean start = true;
	   	  while(start) {
		System.out.println("Now, you can add movie!");
		movieList();
		System.out.println();
		System.out.print("Movie Id : ");
    	int id = movieInput.nextInt();
		
		System.out.print("Movie Title : ");
		String title = movieInput.next();
		
		System.out.print("Released Year : ");
		int releasedYear = movieInput.nextInt();
		
		System.out.print("Genre : ");
		String genre = movieInput.next();
		
		System.out.print("Duration : ");
		String duration = movieInput.next();
		
		System.out.print("Rating : ");
		double rating = movieInput.nextDouble();
		
		Movie movie = new Movie(id, title, releasedYear, genre, duration, rating);
		int createdRow = MOVIE_DAO.createMovie(movie);
		if(createdRow>0) {
			System.out.println("Movie has been successfully added!");
			movieList();
		}
		else
			System.err.println("The process is failed!");
		
		
		 System.out.print("Do you want to add another movie?(Yes/No) : ");
 		  String decision = movieInput.next();
 		  if(decision.equalsIgnoreCase("No"))
 		  start = false;
	   	  }
	   	  AdminControl.main(null);
	}
    static void editMovie(Scanner movieInput) {
    	 boolean start = true;
	   	  while(start) {
    	System.out.println("Now, you can edit movie!");
    	movieList();
    	System.out.print("Movie Id : ");
    	int id = movieInput.nextInt();
    	
		System.out.print("Movie Title : ");
		String title = movieInput.next();
		
		System.out.print("Released Year : ");
		int releasedYear = movieInput.nextInt();
		
		System.out.print("Genre : ");
		String genre = movieInput.next();
		
		System.out.print("Duration : ");
		String duration = movieInput.next();
		
		System.out.print("Rating : ");
		double rating = movieInput.nextDouble();
		
		Movie movie = new Movie(id, title, releasedYear, genre, duration, rating);
		int createdRow = MOVIE_DAO.changeInfo(movie);
		if(createdRow>0) {
			System.out.println("Movie Id = "+id+" has been successfully updated!");
			movieList();
		}
		else
			System.err.println("The process is failed!");
		
		 System.out.print("Do you want to keep editing?(Yes/No) : ");
 		  String decision = movieInput.next();
 		  if(decision.equalsIgnoreCase("No"))
 		  start = false;
	   	  }
	   	  AdminControl.main(null);
    }
    static void searchMovie(Scanner movieInput) {
    	  boolean start = true;
    	  while(start) {
    	System.out.println("Now, you can search movie from the list!");
    	
    	System.out.print("Movie Title : ");
    	String title = movieInput.next();
    	
    	Optional<Movie> movieOptional = MOVIE_DAO.getMovieWithTitle(title);
    	if(movieOptional.isPresent()) {
    		System.out.println(movieOptional.get());
    		
		
    	}
    	else
    		System.err.println("Movie Title: "+title+" is not found");
    	

 		 System.out.print("Do you want to search again?(Yes/No) : ");
 		  String decision = movieInput.next();
 		  if(decision.equalsIgnoreCase("No"))
 		  start = false;
    	  }
    	  UserControl.main(null);
    }
    
	static void removeMovie(Scanner idInput) {
		 boolean start = true;
	   	  while(start) {

		movieList();
		System.out.println("Now, you can remove movies!");
		System.out.print("Movie Id : ");
		int id = idInput.nextInt();
		System.out.println("Are you sure that you want to delete?(Yes/No)");
		String decision = idInput.next();
		
		if(decision.equalsIgnoreCase("Yes")) {
			Optional<Movie> movieOptional = MOVIE_DAO.deleteMovie(id);
			String result = movieOptional.isPresent()? "Movie Id= "+id+" is deleted!" : "Deleting process is failed!";
			System.out.println(result);
			Movie movie = movieOptional.get();
			Optional<Movie> removeMovie = MOVIE_DAO.getMovie(movie);
			String receive = removeMovie.isEmpty()? "Your deleted movie is in recycle bin!" : "Recycle bin is empty!";
			System.out.println(receive);
			moviesFromRB();
		}else {
			System.out.println("Deleting process is cancelled");
			
	  		
		}
		 System.out.print("Do you want to remove another movie?(Yes/No) : ");
 		  String input = idInput.next();
 		  if(input.equalsIgnoreCase("No"))
 		  start = false;
		}
			
		AdminControl.main(null);
		
		
	}
	
	static void moviesFromRB() {
		System.out.println("Movie list from reccycle bin: ");
		 MOVIE_DAO.showAllMoviesFromRB().forEach(System.out :: println);
	}
	static void recoverMoviesFromRB(Scanner idInput) {
		boolean start = true;
		while(start) {
		moviesFromRB();
		System.out.println("Now, you can recover movies!");
		System.out.print("Movie Id : ");
		int id = idInput.nextInt();
		System.out.println("Are you sure that you want to recover?(Yes/No)");
		String decision = idInput.next();
		
		if(decision.equalsIgnoreCase("Yes")) {
			Optional<Movie> movieOptional = MOVIE_DAO.deleteMovieFromRB(id);
		
			Movie movie = movieOptional.get();
			Optional<Movie> recoverMovie = MOVIE_DAO.getMovieFromRB(movie);
			String receive = recoverMovie.isEmpty()? "Your recovered movie is in movie list!" : "Movie is still in recycle bin!";
			System.out.println(receive);
			movieList();
		}else {
			System.out.println("Recovery process is cancelled");
			
		  }

 		 System.out.print("Do you want to recover another movie?(Yes/No) : ");
 		  String input = idInput.next();
 		  if(input.equalsIgnoreCase("No"))
 		  start = false;
		}
		AdminControl.main(null);
			
	}
	static void permanentDelete(Scanner idInput) {
		boolean start = true;
		while(start) {
		moviesFromRB();
		System.out.println("Now, You can delete movies from recycle bin!");
		System.out.print("Movie Id : ");
		int id = idInput.nextInt();
		System.out.println("Are you sure that you want to delete permanently?(Yes/No)");
		String decision = idInput.next();
		
		if(decision.equalsIgnoreCase("Yes")) {
			 MOVIE_DAO.deleteMovieFromRB(id);
			 moviesFromRB();
		}else {
			System.out.println("Deleting process is cancelled");
		   
			
		  }
 		 System.out.print("Do you want to delete another movie?(Yes/No) : ");
 		  String input = idInput.next();
 		  if(input.equalsIgnoreCase("No"))
 		  start = false;
		}
		AdminControl.main(null);
	}
	
	static void addToDownloadList(Scanner idInput) {
		movieList();
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
	}
	static void showDownloadList() {
		System.out.println("Your downloaded movies : ");
		MOVIE_DAO.showDownload().forEach(System.out :: println);
	}
	
}
