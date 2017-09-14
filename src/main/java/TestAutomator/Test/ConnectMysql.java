package TestAutomator.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMysql {
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException {
		//第一步：加载MySQL的JDBC的驱动
		Class.forName("com.mysql.jdbc.Driver"); 
		//取得连接的url,能访问MySQL数据库的用户名,密码；myweb：数据库名
		String url = "jdbc:mysql://127.0.0.1:3306/myweb";
		String username = "root";
		String password = "12345678";
		//第二步：创建与MySQL数据库的连接类的实例
		Connection con = DriverManager.getConnection(url, username, password);		
		return con;
	}

	public static void main(String[] args) {
		try {
			//第三步：获取连接类实例con，用con创建Statement对象类实例 sql_statement
			Connection conn  = getConnection();
			Statement sql_statement = conn.createStatement();
			/** *//************ 对数据库进行相关操作 ************/
			//如果同名数据库存在，删除
			//sql_statement.executeUpdate("drop table if exists student");
			//执行了一个sql语句生成了一个名为student的表
			//sql_statement.executeUpdate("create table student (id int not null auto_increment, name varchar(20) not null default 'name', math int not null default 60, primary key (id) ); ");
			//向表中插入数据
			//sql_statement.executeUpdate("insert student values(1, 'liying', 98)");
			//sql_statement.executeUpdate("insert student values(2, 'jiangshan', 88)");
			//sql_statement.executeUpdate("insert student values(3, 'wangjiawu', 78)");
			//sql_statement.executeUpdate("insert student values(4, 'duchangfeng', 100)");
			//---以上操作不实用，但是列出来作为参考--- 
			
			//第四步：执行查询，用ResultSet类的对象，返回查询的结果
			String query = "select * from test_ini";
			ResultSet result = sql_statement.executeQuery(query);
		
			/** *//************ 对数据库进行相关操作 ************/ 
			System.out.println("test_ini表中的数据如下:");
			System.out.println("------------------------");
			System.out.println("id" + "    " + "name" + "             " + "value");
			System.out.println("------------------------"); 
			
			//对获得的查询结果进行处理，对Result类的对象进行操作
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String value = result.getString("value");
				//取得数据库中的数据
				System.out.println(id + " | " + name + " | " + value);		
			}
			
			//关闭连接和声明
			sql_statement.close();
			conn.close(); 

			} catch(java.lang.ClassNotFoundException e) {
			//加载JDBC错误,所要用的驱动没有找到
			System.err.print("ClassNotFoundException");
			//其他错误
			System.err.println(e.getMessage());
			} catch (SQLException ex) {
			//显示数据库连接错误或查询错误
			System.err.println("SQLException: " + ex.getMessage());
			}
	}
}
