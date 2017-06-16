import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	

		
		/*
		public static void main(String[] args) {
			try {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "select * from customers";
		
				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/montgomery College?user=root&password=password");
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					System.out.print(rs.getString("FirstName") + "\t");
					System.out.print(rs.getString("LastName"));
					System.out.println();
					
					
					rs.close();
					
						stmt.close();
			}		
					con.close();
				} catch (Exception e) {
						e.printStackTrace();
					}
			
		}
		*/
		
		public void deleteRecord(String table, String whereColumn, String toDelete) {
			
			
			try {
				Connection con = null;
				PreparedStatement statement = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/montgomerycollege?user=root&password=password");
				String sql = String.format("delete from %s where %s = ?", table, whereColumn);
				statement = con.prepareStatement(sql);
				statement.setString(1, toDelete);
				statement.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			
		}
		
		public void addRecord(String table, String column1, String column2, String toAdd1, String toAdd2) {
			try {
				Connection con = null;
				PreparedStatement statement = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/robo_resume?user=root&password=password");
				String sql = String.format("insert into %s (%s,%s) values (?,?)", table, column1, column2, toAdd1, toAdd2);
				statement = con.prepareStatement(sql);
				statement.setString(1, toAdd1);
				statement.setString(2, toAdd2);
				System.out.println(sql);
				statement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		public void updateRecord(String table, String column, String whereColumn, String columnEquals, String toUpdate){
			try{
				Connection con = null;
				PreparedStatement statement = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/montgomerycollege?user=root&password=password");
				String sql = String.format("update %s set %s = ? where %s = '%s'",table, column, whereColumn, columnEquals);
				statement = con.prepareStatement(sql);
				statement.setString(1, toUpdate);
				statement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		public void getData(String table, String whereColumn, String equals, String... columns){
			try {
				String colString = "";
				for(String col: columns) colString += col + ",";
				colString = colString.substring(0, colString.length() -1);
				Connection con = null;
				PreparedStatement statement = null;
				ResultSet rs = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/montgomerycollege?user=root&password=password");
				
				String sql = String.format("select %s from %s where %s = %s", colString, table, whereColumn, equals);
				
				System.out.println(sql);
				statement = con.prepareStatement("");
				rs = statement.executeQuery(sql);
				
				
				
				while(rs.next()){
					
					for(String col: columns) System.out.println(rs.getString(col));
					
				}		
			
				} catch (Exception e) {
						e.printStackTrace();
				}
			
		}

			
		}

