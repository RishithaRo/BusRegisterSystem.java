package Jdbc;
import java.util.*;
import java.sql.*;
class BusRegisterSystem 
{
	void create_BusManagement(String url,String un,String pw) throws SQLException
	{
		Connection con = DriverManager.getConnection(url,un,pw);
		String query = "create database BusManagement";
		Statement st = con.createStatement();
		System.out.println(st.executeUpdate(query));
	}
	
	void create_busDetails(String url,String un,String pw) throws SQLException
	{
		Connection con = DriverManager.getConnection(url,un,pw);
		//String query = "(create table BusDetails('Serial_no int primary key','Bus_no varchar(20) unique','Bus_name varchar(30)','Bus_route varchar(500)','Timings varchar(50)')";
		String query = "CREATE TABLE BusDetails (Serial_no int primary key ,Bus_no varchar(20) unique , Bus_name varchar(30), Bus_route varchar(500), Timings varchar(50))";
		Statement st = con.createStatement();
		System.out.println(st.executeUpdate(query));
	}
	
	void registerBus(String url,String un,String pw,int Serial_no,String Bus_no,String Bus_name,String Bus_route,String Timings) throws SQLException
	{
		Connection con = DriverManager.getConnection(url,un,pw);
		String query = "insert into busDetails values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, Serial_no);
		ps.setString(2,Bus_no);
		ps.setString(3,Bus_name);
		ps.setString(4, Bus_route);
		ps.setString(5,Timings);
		System.out.println(ps.executeUpdate());
		
	}
	void viewBuses(String url,String un,String pw) throws SQLException
	{
		Connection con = DriverManager.getConnection(url,un,pw);
		String query = "select * from busDetails";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			System.out.print(rs.getInt(1));
			System.out.print("\t"+rs.getString(2));
			System.out.print("\t"+rs.getString(3));
			System.out.print("\t"+rs.getString(4));
			System.out.print("\t"+rs.getString(5));
			System.out.println();
		}
		
	}
}
class BusManagement 
{
	public static void main(String[] args) throws SQLException
	{
		
		String url = "jdbc:mysql://localhost:3306/";
		String un = "root";
		String pw = "rohith@123";
		BusRegisterSystem ob = new BusRegisterSystem();
		// ob.create_BusManagement(url,un,pw); // Creating database (returns 1)
		// ob.create_busDetails("jdbc:mysql://localhost:3306/BusManagement",un,pw); // creating table(returns 0)
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Register New Bus\t2.View Buses\nEnter your choice:");
		int ch = sc.nextInt();
		if(ch==1)
		{
			System.out.println("Enter bus details to register:");
			int Serial_no = sc.nextInt();
			String Bus_no = sc.next();
			String Bus_name = sc.next();
			String Bus_route = sc.next();
			String Timings = sc.next();
			ob.registerBus("jdbc:mysql://localhost:3306/BusManagement",un,pw,Serial_no,Bus_no,Bus_name,Bus_route,Timings);
		}
		else
		{
			ob.viewBuses("jdbc:mysql://localhost:3306/BusManagement",un,pw);
			
		}	
	}
}
