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

public class AdminDAO {
       
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
     public List<Admin> showAdmin(){
    	 List<Admin> adminList = new ArrayList<>();
    	 connection = DBConnection.getConnection();
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * from admin");
			while(rs.next()){
				adminList.add(new Admin(
				rs.getInt("id"),
				rs.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return adminList;
    	 
    	 
     }
     
     public Optional<Admin> addAdmin(Admin admin){
    	 Optional<Admin> adminOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 
    	 try {
			pStmt = connection.prepareStatement("INSERT INTO `admin` "
					+ "(`id`, `name`, `password`) "
					+ "VALUES (?, ?, ?);");
			pStmt.setInt(1, admin.getId());
			pStmt.setString(2, admin.getName());
			pStmt.setInt(3, admin.getPassword());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminOptional;
     }
     public Optional<Admin> searchAdmin(int password){
    	 Optional<Admin> adminOptional = Optional.empty();
    	 connection = DBConnection.getConnection();
    	 
    	 try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from admin where password='"+password+"';");
			while(rs.next()) {
				adminOptional = Optional.of(new Admin(
						rs.getInt("id"),
						rs.getString("name")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return adminOptional;
     }
}
