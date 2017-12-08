package project;

import java.sql.*;
import java.util.Scanner;

public class Singer {
	
	static String sname;			//not null
	static String debutdate;
	static int snumber;			//not null
	static int sex;				//1. male 2. female 3. both
	static int groupn;			//1. group 2. solo
	
	public static void insert() {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Insert");
		System.out.println("--------------------------");
		System.out.print("Singer Name: ");
		sname=scanner.next();
		scanner.nextLine();
		System.out.print("Debut Date: ");
		debutdate=scanner.nextLine();
		if(!debutdate.equals((String)"")) {
			while(!debutdate.matches("^....-..-..$")) {
				System.out.println("You must insert debut date like: 'yyyy-mm-dd'");
				System.out.print("Debut Date: ");
				debutdate=scanner.next();
				scanner.nextLine();
			}
		}
		System.out.println("1. Male");
		System.out.println("2. Female");
		System.out.println("3. Both");
		System.out.print("Sex: ");
		String tmp=scanner.nextLine();
		if(!tmp.equals((String)"")) {
			sex=Integer.parseInt(tmp);
			while(sex!=1&&sex!=2&&sex!=3) {
				System.out.println("You must insert sex among 1 to 3");
				System.out.print("sex: ");
				sex=scanner.nextInt();
			}
		}else 
			sex=0;
		System.out.println("1. Group");
		System.out.println("2. Solo");
		System.out.print("Group: ");
		tmp=scanner.nextLine();
		if(!tmp.equals((String)"")) {
			groupn=Integer.parseInt(tmp);

			while(groupn!=1&&groupn!=2) {
				System.out.println("You must insert group between 1, 2");
				System.out.print("group: ");
				groupn=scanner.nextInt();
			}
		}else
			groupn=0;
		System.out.println("--------------------------");
		System.out.println();
	}
	
	public static void update() {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Update");
		System.out.println("--------------------------");
		System.out.print("Name: ");
		sname = scanner.nextLine();
		System.out.print("Debut Date: ");
		debutdate = scanner.nextLine();
		if(!debutdate.equals((String)"")) {
			while(!debutdate.matches("^....-..-..$")) {
				System.out.println("You must insert debut date like: 'yyyy-mm-dd'");
				System.out.print("Debut Date: ");
				debutdate=scanner.next();
				scanner.nextLine();
			}
		}
		System.out.println("1. Male");
		System.out.println("2. Female");
		System.out.println("3. Both");
		System.out.print("Sex: ");
		String tmp=scanner.nextLine();
		if(!tmp.equals((String)"")) {
			sex=Integer.parseInt(tmp);
			while(sex!=1&&sex!=2&&sex!=3) {
				System.out.println("You must insert sex among 1 to 3");
				System.out.print("sex: ");
				sex=scanner.nextInt();
			}
		}else
			sex=0;
		System.out.println("1. Group");
		System.out.println("2. Solo");
		System.out.print("Group: ");
		tmp=scanner.nextLine();
		if(!tmp.equals((String)"")) {
			groupn=Integer.parseInt(tmp);
			while(groupn!=1&&groupn!=2) {
				System.out.println("You must insert group between 1, 2");
				System.out.print("group: ");
				groupn=scanner.nextInt();
			}
		}else
			groupn=0;
		
		System.out.println("--------------------------");
		System.out.println();

	}
	
	public static String delete(Connection con) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Delete");
		System.out.println("-------------------------------------------");
		System.out.println("1. Singer Name");
		System.out.println("2. Singer Number");
		System.out.print("Delete Singer by : ");
		int num= sc.nextInt();
		
		while(num!=1 && num!=2) {
			System.out.println("ERROR: Please write another number.");
			System.out.println("Delete Singer by : ");
			num=sc.nextInt();
		}
		
		String sql = null;
		
		switch(num) {
			case 1:{				//delete by name
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Singer name: ");
				String singername=sc.next();
				
				String query="select * from singer where sname='"+singername+"'";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					System.out.println();
					view(rs);
					System.out.println();
					System.out.print("Select Singer number: ");
					int singernum=sc.nextInt();
					
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(3)==singernum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong Singer Number");
							System.out.print("Select Singer Number: ");
							singernum=sc.nextInt();
						}
					}
										
					sql="snumber="+singernum;	
					
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			case 2:{				//delete by number
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Singer number: ");
				int singernumber=sc.nextInt();
				
				sql="snumber="+singernumber;
				
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
		System.out.println("3. Select Debut Date");
		System.out.println("4. Select Sex");
		System.out.println("5. Select Group");
		System.out.print("Input: ");
		int num=scanner.nextInt();
		
		System.out.println();
		try {
			switch(num) {
				case 1:{					
					System.out.print("Singer Name: ");
					String singername=scanner.next();
					
					String sql="select * from singer where sname like '%"+singername+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 2:{
					System.out.print("Singer Number: ");
					int singernum=scanner.nextInt();
					
					String sql="select * from singer where snumber="+singernum;
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
					System.out.print("Debut Date Search: ");
					int ddate=scanner.nextInt();
					
					while(!(ddate>=1&&ddate<=4)) {
						System.out.println("ERROR: Please write another number.");
						ddate=scanner.nextInt();
					}
					
					String date;
					System.out.println();
					if(ddate==1) {
						System.out.print("Year: ");
						date=scanner.next();
						while(!date.matches("^....$")) {
							System.out.println("You must insert year like: 'yyyy'");
							System.out.print("Year: ");
							date=scanner.next();
						}
						
						String sql="select * from singer where debutdate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(ddate==2) {
						System.out.print("Month: ");
						date=scanner.next();
						while(!date.matches("^..$")) {
							System.out.println("You must insert month like: 'mm'");
							System.out.print("Month: ");
							date=scanner.next();
						}
						
						String sql="select * from singer where debutdate like '_____"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(ddate==3) {
						System.out.print("Year-Month: ");
						date=scanner.next();
						while(!date.matches("^....-..$")) {
							System.out.println("You must insert year-month like: 'yyyy-mm'");
							System.out.print("Year-Month: ");
							date=scanner.next();
						}
						
						String sql="select * from singer where debutdate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else{
						System.out.print("Debut Date: ");
						date=scanner.next();
						while(!date.matches("^....-..-..$")) {
							System.out.println("You must insert debut date like: 'yyyy-mm-dd'");
							System.out.print("Debut Date: ");
							date=scanner.next();
						}
						
						String sql="select * from singer where debutdate='" +date+"'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}
					break;
				}
				case 4:{
					System.out.println("1. Male");
					System.out.println("2. Female");
					System.out.print("Sex: ");
					int sex=scanner.nextInt();
					
					while(sex!=1 && sex!=2) {
						System.out.println("ERROR: Please write another number.");
						sex=scanner.nextInt();
					}
				
					String sql="select * from singer where sex="+sex;
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);					
	
					break;
				}
				case 5:{
					System.out.println("1. Group");
					System.out.println("2. Solo");
					System.out.print("Group: ");
					int sgroup=scanner.nextInt();
					
					while(sgroup!=1 && sgroup!=2) {
						System.out.println("ERROR: Please write another number.");
						sgroup=scanner.nextInt();
					}
				
					String sql="select * from singer where groupn="+sgroup;
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
			String sname= rs.getString("sname");
			String debutdate=rs.getString("debutdate");
			int snumber=rs.getInt(3);
			int sex=rs.getInt(4);
			int groupn=rs.getInt(5);
			System.out.print("(1) singer name: "+sname+" (2) debut date: "+debutdate+" (3) singer number: "+snumber);
			if(sex==1)
				System.out.print(" (4) sex: M");
			else if(sex==2) System.out.print(" (4) sex: F");
			else if(sex==3) System.out.print(" (4) sex: Both");
			else System.out.print(" (4) sex: null");
			if(groupn==1)
				System.out.print(" (5) group: Group");
			else if(groupn==2) System.out.print(" (5) group: Solo");
			else System.out.print(" (5) group: null");
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}
	
}
