package TestAutomator.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMysql {
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException {
		//��һ��������MySQL��JDBC������
		Class.forName("com.mysql.jdbc.Driver"); 
		//ȡ�����ӵ�url,�ܷ���MySQL���ݿ���û���,���룻myweb�����ݿ���
		String url = "jdbc:mysql://127.0.0.1:3306/myweb";
		String username = "root";
		String password = "12345678";
		//�ڶ�����������MySQL���ݿ���������ʵ��
		Connection con = DriverManager.getConnection(url, username, password);		
		return con;
	}

	public static void main(String[] args) {
		try {
			//����������ȡ������ʵ��con����con����Statement������ʵ�� sql_statement
			Connection conn  = getConnection();
			Statement sql_statement = conn.createStatement();
			/** *//************ �����ݿ������ز��� ************/
			//���ͬ�����ݿ���ڣ�ɾ��
			//sql_statement.executeUpdate("drop table if exists student");
			//ִ����һ��sql���������һ����Ϊstudent�ı�
			//sql_statement.executeUpdate("create table student (id int not null auto_increment, name varchar(20) not null default 'name', math int not null default 60, primary key (id) ); ");
			//����в�������
			//sql_statement.executeUpdate("insert student values(1, 'liying', 98)");
			//sql_statement.executeUpdate("insert student values(2, 'jiangshan', 88)");
			//sql_statement.executeUpdate("insert student values(3, 'wangjiawu', 78)");
			//sql_statement.executeUpdate("insert student values(4, 'duchangfeng', 100)");
			//---���ϲ�����ʵ�ã������г�����Ϊ�ο�--- 
			
			//���Ĳ���ִ�в�ѯ����ResultSet��Ķ��󣬷��ز�ѯ�Ľ��
			String query = "select * from test_ini";
			ResultSet result = sql_statement.executeQuery(query);
		
			/** *//************ �����ݿ������ز��� ************/ 
			System.out.println("test_ini���е���������:");
			System.out.println("------------------------");
			System.out.println("id" + "    " + "name" + "             " + "value");
			System.out.println("------------------------"); 
			
			//�Ի�õĲ�ѯ������д�����Result��Ķ�����в���
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String value = result.getString("value");
				//ȡ�����ݿ��е�����
				System.out.println(id + " | " + name + " | " + value);		
			}
			
			//�ر����Ӻ�����
			sql_statement.close();
			conn.close(); 

			} catch(java.lang.ClassNotFoundException e) {
			//����JDBC����,��Ҫ�õ�����û���ҵ�
			System.err.print("ClassNotFoundException");
			//��������
			System.err.println(e.getMessage());
			} catch (SQLException ex) {
			//��ʾ���ݿ����Ӵ�����ѯ����
			System.err.println("SQLException: " + ex.getMessage());
			}
	}
}
