package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
			Connection con = null; 
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/foundmanagement", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			
			return con; 
		 } 
		
		
		public String readFunds()
		{ 
			String output = ""; 
			
			try
			{ 
				
				Connection con = connect(); 
				
				if (con == null) 
				{ 
					return "Error while connecting to the database for reading."; 
				} 
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project Name</th>"
						+ "<th>Receiver ID</th>" 
						+ "<th>TimeRange</th>" 
						+ "<th>Amount </th>" 
						+ "<th>Status</th>"
						+ "<th>Update</th><th>Remove</th></tr>"; 
				
				String query = "select * from fundmanagment"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String FundID = Integer.toString(rs.getInt("FundID")); 
					String ProjectID = Integer.toString(rs.getInt("ProjectID"));
					String ProjectName = rs.getString("ProjectName"); 
					String ReceiverID = Integer.toString(rs.getInt("ReceiverID"));
					String TimeRange = rs.getString("TimeRange"); 
					String TotalAmount = Double.toString(rs.getDouble("TotalAmount")); 
					String Status = rs.getString("Status"); 
					
					// Add into the html table
					output += "<tr><td>" +  ProjectID + "</td>"; 
					output += "<td>" + ProjectName + "</td>"; 
					output += "<td>" + ReceiverID + "</td>"; 
					output += "<td>" + TimeRange + "</td>"; 
					output += "<td>" + TotalAmount + "</td>";
					output += "<td>" + Status + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-FundID='" + FundID + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-FundID='" + FundID + "'></td></tr>"; 
				} 
				
				con.close(); 
				
				// Complete the html table
				output += "</table>"; 
				
		 } 
			
		catch (Exception e)  { 
			
			output = "Error while reading the fund."; 
			System.err.println(e.getMessage()); 
		 } 
			
			return output; 
		}


		
		public String insertFund(String ProjectID, String ProjectName, String ReceiverID, String TimeRange, String TotalAmount, String Status) 
		 { 
			
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 { 
					 
						 return "Error while connecting to the database for inserting."; 
				 
					 } 
				 
					 // create a prepared statement
					 String query = " insert into fundmanagment(`FundID`,`ProjectID`,`ProjectName`,`RecieverID`,`TimeRange`,`TotalAmount`,`Status`)"+ " values (?, ?, ?, ?, ?,?,?)";
				
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setInt(2, Integer.parseInt(ProjectID));
					 preparedStmt.setString(3, ProjectName); 
					 preparedStmt.setInt(4, Integer.parseInt(ReceiverID));
					 preparedStmt.setString(5, TimeRange); 
					 preparedStmt.setDouble(6, Double.parseDouble(TotalAmount)); 
				 	preparedStmt.setString(7, Status); 
				
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 
				 	String newFunds = readFunds(); 
				 	output = "{\"status\":\"success\", \"data\": \"" +  newFunds + "\"}"; 
				
				 } 
				 
				 catch (Exception e) { 
					 output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund.\"}"; 
					 System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
				 
			} 
		
		
		
		public String updateFund(String FundID, String ProjectID, String ProjectName, String ReceiverID, String TimeRange, String TotalAmount, String Status) 
		{ 
			
			String output = ""; 
			
			try{ 
				
				 Connection con = connect(); 
				 
				 if (con == null) 
				 { 
					 
					 return "Error while connecting to the database for updating."; 
				
				 } 
				 
				 // create a prepared statement
				
				 String query = "UPDATE `fundmanagment` SET `ProjectID`=?,`ProjectName`=?,`ReceiverID=?,`TimeRange`=?,`TotalAmount`=?,`Status`=? WHERE `FundID`=?";
				 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(ProjectID)); 
				 preparedStmt.setString(2, ProjectName); 
				 preparedStmt.setInt(3, Integer.parseInt(ReceiverID)); 
				 preparedStmt.setString(4, TimeRange); 
				 preparedStmt.setDouble(5, Double.parseDouble(TotalAmount)); 
				 preparedStmt.setString(6, Status); 

				
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newFunds = readFunds(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}"; 
				
				} 
			
				 catch (Exception e) 
				 { 
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the fund.\"}"; 
					 System.err.println(e.getMessage()); 
					 
				 } 
			
				 return output; 
				 
			 } 
		
		
		public String deleteFund(String FundID) 
		{ 
			
			 String output = ""; 
			 
			 try
			 { 
				 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 { 
					 
					 return "Error while connecting  to the database for deleting."; 
				
				 } 
				 
				 // create a prepared statement
				 String query = "delete from fundmanagment where FundID=?"; 
				
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(FundID)); 
				
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newFunds = readFunds(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}"; 
			} 
			 
			catch (Exception e) 
			 { 
				
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the fund.\"}"; 
				 System.err.println(e.getMessage()); 
				 
			 } 
			 
				 return output; 
		 } 
}
