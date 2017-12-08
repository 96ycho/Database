package project;

import java.util.Scanner;
import java.sql.*;

public class Manager {
	
	static int mgrnumber;			//not null
	static String mgrname;			//not null
	static String mid;				//not  null
	
	public static void insert(Connection con) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Insert");
		System.out.println("--------------------------");
		System.out.print("Manager Name: ");
		mgrname=scanner.next();
		scanner.nextLine();
		System.out.print("Manager Id: ");
		mid=scanner.next();
		
		try {
			String sql="select * from manager where mid='"+mid+"'";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ERROR: Manager Id already exists");
				System.out.print("Manager Id: ");
				mid=scanner.next();
				sql="select * from manager where mid='"+mid+"'";
				rs=stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		System.out.println("--------------------------");
		System.out.println();
	}
	
	public static void update(Connection con) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Update");
		System.out.println("--------------------------");
		System.out.print("Manager Name: ");
		mgrname = scanner.nextLine();
		System.out.print("Manager Id: ");
		mid = scanner.nextLine();
		if(!(mid.equals((String)""))){
			try {
				String sql="select * from manager where mid='"+mid+"'";
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println("ERROR: Manager Id already exists");
					System.out.print("Manager Id: ");
					mid=scanner.next();
					sql="select * from manager where mid='"+mid+"'";
					rs=stmt.executeQuery(sql);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("--------------------------");
		System.out.println();
	}
	
	public static String delete(Connection con) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Delete");
		System.out.println("-------------------------------------------");
		System.out.println("1. Manager Name");
		System.out.println("2. Manager Number");
		System.out.print("Delete Manager by : ");
		int num= sc.nextInt();
		
		while(num!=1 && num!=2) {
			System.out.println("ERROR: Please write another number.");
			System.out.print("Delete Manager by : ");
			num=sc.nextInt();
		}
		
		String sql = null;
		
		switch(num) {
			case 1:{				//delete by name
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Manager name: ");
				String managername=sc.next();
				
				String query="select * from manager where mgrname like '%"+managername+"%'";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					System.out.println();
					view(rs);
					System.out.println();
					System.out.print("Select Manager number: ");
					int managernum=sc.nextInt();
					
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(1)==managernum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong Manager Number");
							System.out.print("Select Manager Number: ");
							managernum=sc.nextInt();
						}
					}					
					sql="mgrnumber="+managernum;	
					
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			case 2:{				//delete by number
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Manager number: ");
				int managernumber=sc.nextInt();
				
				sql="mgrnumber="+managernumber;
				
				break;
			}
		}
		
		return sql;
	}
	
	public static void select(Connection con) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. Select Name");
		System.out.println("2. Select Number");
		System.out.println("3. Select Id");
		System.out.print("Input: ");
		int num=scanner.nextInt();
		
		System.out.println();
		try {
			switch(num) {
				case 1:{					
					System.out.print("Manager Name: ");
					String mname=scanner.next();
					
					String sql="select * from manager where mgrname like '%"+mname+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 2:{
					System.out.print("Manager Number: ");
					int mnum=scanner.nextInt();
					
					String sql="select * from manager where mgrnumber="+mnum;
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 3:{					
					System.out.print("Manager Id: ");
					String mid=scanner.next();
					
					String sql="select * from manager where mid like '%"+mid+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void view(ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			String mgrname= rs.getString("mgrname");
			String mid=rs.getString("mid");
			int mgrnumber=rs.getInt(1);
			System.out.println("(1) manager name: "+mgrname+" (2) manager id: "+mid+" (3) manager number: "+mgrnumber);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	
	}
	
}
