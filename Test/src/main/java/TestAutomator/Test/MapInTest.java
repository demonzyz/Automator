package TestAutomator.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapInTest {
	
	public Map<String, Map<String, String>> con_mysql(String database,String table){
		List<String> list = new ArrayList<String>();
		Map<String, Map<String,String>> mix_map = new LinkedHashMap<String, Map<String,String>>();
		Map<String, String> number_map = new LinkedHashMap<String, String>();
		Map<String, String> value_map = new LinkedHashMap<String, String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String Ulr = "jdbc:mysql://127.0.0.1:3306/" + database;
			String Username = "root";
			String Password = "12345678";
			Connection connection = DriverManager.getConnection(Ulr,Username,Password);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from " + table);
			
			while (result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String value = result.getString("value");
				number_map.put(name, value);
				mix_map.put(id, number_map);
			}

			for (String nu : number_map.keySet()) {
				System.out.println(nu + " | " + number_map.get(nu));
			}
			
			for (String mi : mix_map.keySet()) {
				System.out.println(mi + " | " + number_map.get(mi));
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
		return mix_map;
		
	}
		
	public static void main(String[] args) {
		MapInTest test = new MapInTest();
		Map<String, Map<String, String>> map = test.con_mysql("myweb", "test_ini");
		Set<String> map_set = map.keySet();
		for (String set : map_set) {
			System.out.println(set);
			System.out.println(map.get(set));
//			HashMap<String, String> inHashMap = map.get(set);
//			for (String string : map_set) {
//				
//			}
		}
		
	}
		

}
