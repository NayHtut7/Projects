package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import database.DBConnection;

public class MovieDAO {
     private Connection connection;
     private Statement stmt;
     private PreparedStatement pStmt;
     private ResultSet rs;
     
     private void close() {
    	 if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
     }
     
     public List<Movie>showAllMovies(){
    	 List<Movie> movieList = new ArrayList<>();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from movie;");
			
			while(rs.next()) {
				movieList.add(new Movie(
						rs.getInt("id"),
						rs.getString("title"), 
						rs.getInt("releasedyear"),
						rs.getString("genre"), 
						rs.getString("duration"), 
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieList;
     }
     
     public int createMovie(Movie movie) {
    	 int createdRow = 0;
    	 connection = DBConnection.getConnection();
    	 try {
    		 pStmt =connection.prepareStatement("INSERT INTO `movie` "
     		 		+ "(`id`, `title`, `releasedyear`, `genre`, `duration`, `rating`)"
     		 		+ " VALUES (?, ?, ?, ?, ?, ?);");
     			pStmt.setInt(1, movie.getId());
     			pStmt.setString(2, movie.getTitle());
     			pStmt.setInt(3, movie.getReleasedYear());
     			pStmt.setString(4, movie.getGenre());
     			pStmt.setString(5, movie.getDuration());
     			pStmt.setDouble(6, movie.getRating());
     		   createdRow = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return createdRow;
     }
     
     public int changeInfo(Movie movie) {
    	 int createdRow = 0;
    	 if(movie.getId() != 0 ) {
    		 connection = DBConnection.getConnection();
    		 
    		 try {
				pStmt = connection.prepareStatement("UPDATE `movie` SET "
						+ "`title` = ?, "
						+ "`releasedyear` = ?, "
						+ "`genre` = ?, "
						+ "`duration` = ?, "
						+ "`rating` = ? WHERE (`id` = ?);");
				pStmt.setString(1, movie.getTitle());
				pStmt.setInt(2, movie.getReleasedYear());
				pStmt.setString(3, movie.getGenre());
				pStmt.setString(4, movie.getDuration());
				pStmt.setDouble(5, movie.getRating());
				pStmt.setInt(6, movie.getId());
				createdRow = pStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
    	 }
		return createdRow;
    	 
     }
     public Optional<Movie> deleteMovie(int id) {
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			
//		    stmt.executeUpdate("delete from movie where id='"+id+"';");
		    rs = stmt.executeQuery("select*from movie where id='"+id+"';");
			while(rs.next()) {
				movieOptional = Optional.of(new Movie(
								rs.getInt("id"),
								rs.getString("title"), 
								rs.getInt("releasedyear"),
								rs.getString("genre"), 
								rs.getString("duration"), 
								rs.getDouble("rating")));
					}
			 stmt.executeUpdate("delete from movie where id='"+id+"';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     
     public Optional<Movie> getMovieWithTitle(String title){
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from movie where title='"+title+"';");
			
			while(rs.next()) {
		movieOptional = Optional.of(new Movie(
						rs.getInt("id"),
						rs.getString("title"), 
						rs.getInt("releasedyear"),
						rs.getString("genre"), 
						rs.getString("duration"), 
						rs.getDouble("rating")));
			}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     
     public Optional<Movie> getMovie(Movie movie) {
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 
    	 try {
			pStmt = connection.prepareStatement("INSERT INTO `recyclebin` "
					+ "(`id`, `title`, `releasedyear`, `genre`, `duration`, `rating`) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
			pStmt.setInt(1, movie.getId());
			pStmt.setString(2, movie.getTitle());
			pStmt.setInt(3, movie.getReleasedYear());
			pStmt.setString(4, movie.getGenre());
			pStmt.setString(5, movie.getDuration());
			pStmt.setDouble(6, movie.getRating());
		    pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     
     public List<Movie>showAllMoviesFromRB(){
    	 List<Movie> movieList = new ArrayList<>();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from recyclebin;");
			
			while(rs.next()) {
				movieList.add(new Movie(
						rs.getInt("id"),
						rs.getString("title"), 
						rs.getInt("releasedyear"),
						rs.getString("genre"), 
						rs.getString("duration"), 
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieList;
     }
     
     public Optional<Movie> deleteMovieFromRB(int id){
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			   rs = stmt.executeQuery("select*from recyclebin where id='"+id+"';");
				while(rs.next()) {
					movieOptional = Optional.of(new Movie(
									rs.getInt("id"),
									rs.getString("title"), 
									rs.getInt("releasedyear"),
									rs.getString("genre"), 
									rs.getString("duration"), 
									rs.getDouble("rating")));
						}
				 stmt.executeUpdate("delete from recyclebin where id='"+id+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     
     public Optional<Movie> getMovieFromRB(Movie movie){
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
    		 pStmt =connection.prepareStatement("INSERT INTO `movie` "
    		 		+ "(`id`, `title`, `releasedyear`, `genre`, `duration`, `rating`)"
    		 		+ " VALUES (?, ?, ?, ?, ?, ?);");
    			pStmt.setInt(1, movie.getId());
    			pStmt.setString(2, movie.getTitle());
    			pStmt.setInt(3, movie.getReleasedYear());
    			pStmt.setString(4, movie.getGenre());
    			pStmt.setString(5, movie.getDuration());
    			pStmt.setDouble(6, movie.getRating());
    		    pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     public Optional<Movie> getDownload(int id){
    	 Optional<Movie> downloadMovie = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select*from movie where id='"+id+"';");
			while(rs.next()) {
				downloadMovie = Optional.of(new Movie(
								rs.getInt("id"),
								rs.getString("title"), 
								rs.getInt("releasedyear"),
								rs.getString("genre"), 
								rs.getString("duration"), 
								rs.getDouble("rating")));
					}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return downloadMovie;
     }
     public Optional<Movie> addToDownload(Movie movie){
    	 Optional<Movie> movieOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 try {
    		 pStmt =connection.prepareStatement("INSERT INTO `download` "
    		 		+ "(`id`, `title`, `releasedyear`, `genre`, `duration`, `rating`)"
    		 		+ " VALUES (?, ?, ?, ?, ?, ?);");
    			pStmt.setInt(1, movie.getId());
    			pStmt.setString(2, movie.getTitle());
    			pStmt.setInt(3, movie.getReleasedYear());
    			pStmt.setString(4, movie.getGenre());
    			pStmt.setString(5, movie.getDuration());
    			pStmt.setDouble(6, movie.getRating());
    		    pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieOptional;
     }
     
     public List<Movie>showDownload(){
    	 List<Movie> movieList = new ArrayList<>();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from download;");
			
			while(rs.next()) {
				movieList.add(new Movie(
						rs.getInt("id"),
						rs.getString("title"), 
						rs.getInt("releasedyear"),
						rs.getString("genre"), 
						rs.getString("duration"), 
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return movieList;
     }
    
}
