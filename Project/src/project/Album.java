package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Album {

	static String aname;		//not null
	static String rldate;
	static int anumber;			//not null
	
	public static void insert() {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Insert");
		System.out.println("--------------------------");
		System.out.print("Album Name: ");
		aname=scanner.next();
		scanner.nextLine();
		System.out.print("Release Date: ");
		rldate=scanner.nextLine();
		if(!rldate.equals((String)"")) {
			while(!rldate.matches("^....-..-..$")) {
				System.out.println("You must insert release date like: 'yyyy-mm-dd'");
				System.out.print("Release Date: ");
				rldate=scanner.next();
				scanner.nextLine();
			}
		}
		
		System.out.println("--------------------------");
		System.out.println();
	}
	
	public static void update() {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Update");
		System.out.println("--------------------------");
		System.out.print("Album Name: ");
		aname = scanner.nextLine();
		System.out.print("Release Date: ");
		rldate = scanner.nextLine();
		if(!rldate.equals((String)"")) {
			while(!rldate.matches("^....-..-..$")) {
				System.out.println("You must insert release date like: 'yyyy-mm-dd'");
				System.out.print("Release Date: ");
				rldate=scanner.next();
				scanner.nextLine();
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
		System.out.println("1. Album Name");
		System.out.println("2. Album Number");
		System.out.print("Delete Album by : ");
		int num= sc.nextInt();
		
		while(num!=1 && num!=2) {
			System.out.println("ERROR: Please write another number.");
			System.out.print("Delete Album by : ");
			num=sc.nextInt();
		}
		
		String sql = null;
		
		switch(num) {
			case 1:{				//delete by name
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Album name: ");
				String albumname=sc.next();
				
				String query="select * from album where aname like '%"+albumname+"%'";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					System.out.println();
					view(rs);
					System.out.println();
					System.out.print("Select Album number: ");
					int albumnum=sc.nextInt();
					
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(3)==albumnum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong Album Number");
							System.out.print("Select Album Number: ");
							albumnum=sc.nextInt();
						}
					}					
					sql="anumber="+albumnum;	
					
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			case 2:{				//delete by number
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Album number: ");
				int albumnumber=sc.nextInt();
				
				sql="anumber="+albumnumber;
				
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
		System.out.println("3. Select Release Date");
		System.out.print("Input: ");
		int num=scanner.nextInt();
		
		System.out.println();
		try {
			switch(num) {
				case 1:{					
					System.out.print("Album Name: ");
					String albumname=scanner.next();
					
					String sql="select * from album where aname like '%"+albumname+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 2:{
					System.out.print("Album Number: ");
					int albumnum=scanner.nextInt();
					
					String sql="select * from album where anumber="+albumnum;
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 3:{					
					System.out.println("1. Year");
					System.out.println("2. Month");
					System.out.println("3. Year-Month");
					System.out.println("4. Specific Date");
					System.out.print("Release Date Search: ");
					int rdate=scanner.nextInt();
					
					while(!(rdate>=1&&rdate<=4)) {
						System.out.println("ERROR: Please write another number.");
						rdate=scanner.nextInt();
					}
					
					String date;
					System.out.println();
					if(rdate==1) {
						System.out.print("Year: ");
						date=scanner.next();
						while(!date.matches("^....$")) {
							System.out.println("You must insert year like: 'yyyy'");
							System.out.print("Year: ");
							date=scanner.next();
						}
						
						String sql="select * from album where rldate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(rdate==2) {
						System.out.print("Month: ");
						date=scanner.next();
						while(!date.matches("^..$")) {
							System.out.println("You must insert month like: 'mm'");
							System.out.print("Month: ");
							date=scanner.next();
						}
						
						String sql="select * from album where rldate like '_____"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(rdate==3) {
						System.out.print("Year-Month: ");
						date=scanner.next();
						while(!date.matches("^....-..$")) {
							System.out.println("You must insert year-month like: 'yyyy-mm'");
							System.out.print("Year-Month: ");
							date=scanner.next();
						}
						
						String sql="select * from album where rldate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else{
						System.out.print("Release Date: ");
						date=scanner.next();
						while(!date.matches("^....-..-..$")) {
							System.out.println("You must insert release date like: 'yyyy-mm-dd'");
							System.out.print("Release Date: ");
							date=scanner.next();
						}
						
						String sql="select * from album where rldate='" +date+"'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}
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
			String aname= rs.getString("aname");
			String rldate=rs.getString("rldate");
			int anumber=rs.getInt(3);
			System.out.println("(1) album name: "+aname+" (2) release date: "+rldate+" (3) album number: "+anumber);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}
}
