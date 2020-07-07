package project14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

	
	public static void main(String[] args) throws SQLException {
		// selectDemo();
		// insertDemo();
		// updateDemo();
		deleteDemo();
	}
	
	public static void deleteDemo() throws SQLException {
		Connection connection=null;
		DbHelper helper=new DbHelper();
		PreparedStatement statement=null;
		try {
			connection=helper.getConnection();
			/* direkt verilmek istenirse 
			String sql="delete from city where id=4082";
			*/
			// dynamic olarak
			String sql="delete from city where id=?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, 4082);
			int result=statement.executeUpdate();
			System.out.println("Kayit silindi");
		} catch(SQLException exception) {
			helper.showErrorMessage(exception);
		} finally {
			statement.close();
			connection.close();
		}
	}
	
	public static void updateDemo() throws SQLException {
		Connection connection=null;
		DbHelper helper=new DbHelper();
		PreparedStatement statement=null;
		try {
			connection=helper.getConnection();
			/* direkt verilmek istenirse 
			String sql="update city set population=70000 where id=4082";
			*/
			// dynamic olarak
			String sql="update city set population=80000 where id=?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, 4082);
			int result=statement.executeUpdate();
			System.out.println("Kayit guncellendi");
		} catch(SQLException exception) {
			helper.showErrorMessage(exception);
		} finally {
			statement.close();
			connection.close();
		}
	}
	
	public static void insertDemo() throws SQLException {
		Connection connection=null;
		DbHelper helper=new DbHelper();
		PreparedStatement statement=null;
		try {
			connection=helper.getConnection();
			/* direkt verilmek istenirse 
			statement=connection.prepareStatement("insert into city(name,countrycode,district,population) values ('Duzce','TUR','Duzce',50000)");
			*/
			// eklenecek parametreler kullanýcý tarafýndan verilecek ise
			String sql="insert into city(name,countrycode,district,population) values(?,?,?,?)";
			statement=connection.prepareStatement(sql);
			statement.setString(1,"Duzce");
			statement.setString(2, "TUR");
			statement.setString(3,"Duzce");
			statement.setInt(4, 50000);
			int result=statement.executeUpdate();
			System.out.println("Kayit eklendi");
		} catch(SQLException exception) {
			helper.showErrorMessage(exception);
		} finally {
			statement.close();
			connection.close();
		}
	}
	
	public static void selectDemo() throws SQLException  {
		Connection connection=null;
		DbHelper helper=new DbHelper();
		Statement statement=null;
		ResultSet resultSet;
		try {
			connection=helper.getConnection();
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select code,name,continent,region from country");
			ArrayList<Country> countries=new ArrayList<Country>();
			while(resultSet.next()) {
				countries.add(new Country(resultSet.getString("code"),resultSet.getString("name"),resultSet.getString("continent"),resultSet.getString("region")));
			}
			for(Country country:countries) {
				System.out.println(country.getCode()+" "+country.getName());
			}
			
		} catch(SQLException exception) {
			helper.showErrorMessage(exception);
		} finally {
			connection.close();
		}
	}
	
}
