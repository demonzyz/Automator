package TestAutomator.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sun.nio.fs.MacOSXFileSystemProvider;

public class TestInTest {
	
	public List<String> con_mysql(String database,String table){
		List<String> list = new ArrayList<String>();
		Map<String, Map<String,String>> mix_map = new HashMap<String, Map<String,String>>();
		Map<String, String> number_map = new HashMap<String, String>();
		Map<String, String> value_map = new HashMap<String, String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String Ulr = "jdbc:mysql://127.0.0.1:3306/" + database;
			String Username = "root";
			String Password = "12345678";
			Connection connection = DriverManager.getConnection(Ulr,Username,Password);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from " + table);
			System.out.println("-----------------");
			System.out.println(result);
			System.out.println("-----------------");
			while (result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String value = result.getString("value");
				//取得数据库中的数据
				System.out.println(id + " | " + name + " | " + value);
				list.add(id);
				list.add(name);
				list.add(value);
			}
			statement.close();
			connection.close(); 
			
		} catch(ClassNotFoundException e) {
		//加载JDBC错误,所要用的驱动没有找到
		System.err.print("ClassNotFoundException");
		//其他错误
		System.err.println(e.getMessage());
		} catch (SQLException ex) {
		//显示数据库连接错误或查询错误
		System.err.println("SQLException: " + ex.getMessage());
		}
		return list;
		
	}
		
	public static void main(String[] args) {
		TestInTest test = new TestInTest();
		List<String> list = test.con_mysql("myweb", "test_ini");
		Iterator<String> iterator = list.iterator();
		System.out.println(list.get(1));
		while (iterator.hasNext()) {
			String value = iterator.next();
			System.out.println(value);	
		}
	}
		

}
