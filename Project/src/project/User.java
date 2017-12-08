package project;

import java.sql.*;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class User {
	static String name;		//not null
	static String id;		//not null
	static char sex;
	static String bdate;
	static String rgtdate;
	static int unumber;		//not null
	static int mgrnum;		
		
	public User(){
		
	}
	public static void insert(Connection con) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Insert");
		System.out.println("--------------------------");
		System.out.print("Name: ");
		name = scanner.next();
		try {
			System.out.print("Id: ");
			id = scanner.next();
			String sql="select * from user where id='"+id+"'";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ERROR: Id already exists");
				System.out.print("Id: ");
				id=scanner.next();
				sql="select * from user where id='"+id+"'";
				rs=stmt.executeQuery(sql);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		scanner.nextLine();
		System.out.print("Sex: ");
		String tmp = scanner.nextLine();
		if(tmp.equals((String)""))
			sex = ' ';
		else {
			sex=tmp.charAt(0);
			while(sex!='F'&&sex!='M') {
				System.out.println("You must insert sex either 'M' or 'F'");
				System.out.print("Sex: ");
				sex=scanner.next().charAt(0);
				scanner.nextLine();
			}
		}
		System.out.print("Birth Date: ");
		bdate = scanner.nextLine();
		if(!bdate.equals((String)"")) {
			while(!bdate.matches("^....-..-..$")) {
				System.out.println("You must insert birth date like: 'yyyy-mm-dd'");
				System.out.print("Birth Date: ");
				bdate=scanner.next();
				scanner.nextLine();
			}
		}
		System.out.print("Register Date: ");
		rgtdate = scanner.nextLine();
		if(!rgtdate.equals((String)"")) {
			while(!rgtdate.matches("^....-..-..$")) {
				System.out.println("You must insert register date like: 'yyyy-mm-dd'");
				System.out.print("Register Date: ");
				rgtdate=scanner.next();
			}
		}
	
		System.out.println("--------------------------");
		System.out.println();
		
	}
	
	public static void update(Connection con) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Update");
		System.out.println("--------------------------");
		System.out.print("Name: ");
		name = scanner.nextLine();
		try {
			System.out.print("Id: ");
			id = scanner.nextLine();
			if(!(id.equals((String)""))) {
				String sql="select * from user where id='"+id+"'";
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println("ERROR: Id already exists");
					System.out.print("Id: ");
					id=scanner.next();
					sql="select * from user where id='"+id+"'";
					rs=stmt.executeQuery(sql);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("Sex: ");
		String tmp = scanner.nextLine();
		if(tmp.equals((String)""))
			sex = ' ';
		else {
			sex=tmp.charAt(0);
			while(sex!='F'&&sex!='M') {
				System.out.println("You must insert sex either 'M' or 'F'");
				System.out.print("Sex: ");
				sex=scanner.next().charAt(0);
				scanner.nextLine();
			}
		}
		System.out.print("Birth Date: ");
		bdate = scanner.nextLine();
		if(!bdate.equals((String)"")) {
			while(!bdate.matches("^....-..-..$")) {
				System.out.println("You must insert birth date like: 'yyyy-mm-dd'");
				System.out.print("Birth Date: ");
				bdate=scanner.next();
				scanner.nextLine();
			}
		}
		System.out.print("Register Date: ");
		rgtdate = scanner.nextLine();
		if(!rgtdate.equals((String)"")) {
			while(!rgtdate.matches("^....-..-..$")) {
				System.out.println("You must insert register date like: 'yyyy-mm-dd'");
				System.out.print("Register Date: ");
				rgtdate=scanner.next();
				scanner.nextLine();
			}
		}
		
		System.out.print("Manager Number: ");
		tmp=scanner.nextLine();
		if(tmp.equals((String)""))
			mgrnum=0;
		else { 
			try {
				mgrnum=Integer.parseInt(tmp);
				String sql="select * from manager where mgrnumber="+mgrnum;
				Statement stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(!rs.next()) {
					System.out.println("ERROR: Wrong Manager Number");
					System.out.print("Manager Number: ");
					mgrnum=scanner.nextInt();
					sql="select * from manager where mgrnumber="+mgrnum;
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
		System.out.println("1. Name");
		System.out.println("2. Id");
		System.out.println("3. User Number");
		System.out.print("Delete User by : ");
		int num= sc.nextInt();
		
		while(num!=1 && num!=2 && num!=3) {
			System.out.println("ERROR: Please write another number.");
			System.out.print("Delete User by : ");
			num=sc.nextInt();
		}
		
		String sql = null;
		
		switch(num) {
			case 1:{				//delete by name
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("User name: ");
				String username=sc.next();
				
				String query="select * from user where name='"+username+"'";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					System.out.println();
					view(rs);
					System.out.println();
					System.out.print("Select User number: ");
					int usernum=sc.nextInt();
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(6)==usernum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong User Number");
							System.out.print("Select Singer Number: ");
							usernum=sc.nextInt();
						}
					}
					
					sql="unumber="+usernum;	
					
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			case 2:{				//delete by id
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("User id: ");
				String userid=sc.next();
				
				sql="id='"+userid+"'";
				
				break;
			}
			case 3:{				//delete by unumber
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("User number: ");
				int usernumber=sc.nextInt();
				
				sql="unumber="+usernumber;
				
				break;
			}
		}
		
		return sql;
	}
	
	public static void select(Connection con) {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. Select Name");
		System.out.println("2. Select Id");
		System.out.println("3. Select Sex");
		System.out.println("4. Select Birth Date");
		System.out.println("5. Select Register Date");
		System.out.println("6. Select User Number");
		System.out.println("7. Select Manager Number");
		System.out.print("Input: ");
		int num=scanner.nextInt();
		
		System.out.println();
		try {
			switch(num) {
				case 1:{					
					System.out.print("User Name: ");
					String username=scanner.next();
					
					String sql="select * from user where name like '%"+username+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 2:{
					System.out.print("User Id: ");
					String userid=scanner.next();
					
					String sql="select * from user where id like '%"+userid+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 3:{					
					System.out.println("1. Male");
					System.out.println("2. Female");
					System.out.print("Sex: ");
					int usersex=scanner.nextInt();
					
					while(usersex!=1 && usersex!=2) {
						System.out.println("ERROR: Please write another number.");
						usersex=scanner.nextInt();
					}
					
					String sql;
					if(usersex==1) {
						sql="select * from user where sex='M'";			
					}
					else{
						sql="select * from user where sex='F'";
					}
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);		
					break;
				}
				case 4:{
					System.out.println("1. Year");
					System.out.println("2. Month");
					System.out.println("3. Year-Month");
					System.out.println("4. Specific Date");
					System.out.print("Birth Date Search: ");
					int birthdate=scanner.nextInt();
					
					while(!(birthdate>=1&&birthdate<=4)) {
						System.out.println("ERROR: Please write another number.");
						birthdate=scanner.nextInt();
					}
					
					String date;
					System.out.println();
					if(birthdate==1) {
						System.out.print("Year: ");
						date=scanner.next();
						while(!date.matches("^....$")) {
							System.out.println("You must insert year like: 'yyyy'");
							System.out.print("Year: ");
							date=scanner.next();
						}
						
						String sql="select * from user where bdate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(birthdate==2) {
						System.out.print("Month: ");
						date=scanner.next();
						while(!date.matches("^..$")) {
							System.out.println("You must insert month like: 'mm'");
							System.out.print("Month: ");
							date=scanner.next();
						}
						
						String sql="select * from user where bdate like '_____"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(birthdate==3) {
						System.out.print("Year-Month: ");
						date=scanner.next();
						while(!date.matches("^....-..$")) {
							System.out.println("You must insert year-month like: 'yyyy-mm'");
							System.out.print("Year-Month: ");
							date=scanner.next();
						}
						
						String sql="select * from user where bdate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else{
						System.out.print("Birth Date: ");
						date=scanner.next();
						while(!date.matches("^....-..-..$")) {
							System.out.println("You must insert birth date like: 'yyyy-mm-dd'");
							System.out.print("Birth Date: ");
							date=scanner.next();
						}
						
						String sql="select * from user where bdate='" +date+"'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}
					break;
				}
				case 5:{
					System.out.println("1. Year");
					System.out.println("2. Month");
					System.out.println("3. Year-Month");
					System.out.println("4. Specific Date");
					System.out.print("Register Date Search: ");
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
						
						String sql="select * from user where rgtdate like '"+date+"%'";
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
						
						String sql="select * from user where rgtdate like '_____"+date+"%'";
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
						
						String sql="select * from user where rgtdate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else{
						System.out.print("Register Date: ");
						date=scanner.next();
						while(!date.matches("^....-..-..$")) {
							System.out.println("You must insert rigster date like: 'yyyy-mm-dd'");
							System.out.print("Register Date: ");
							date=scanner.next();
						}
						
						String sql="select * from user where rgtdate='" +date+"'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}
					break;
				}
				case 6:{
					System.out.print("User Number: ");
					int usernum=scanner.nextInt();
					
					String sql="select * from user where unumber="+usernum;
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 7:{
					System.out.print("Manager Number: ");
					int mnum=scanner.nextInt();
					
					String sql="select * from user where mgrnum="+mnum;
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
			String name= rs.getString("name");
			String id=rs.getString("id");
			String sex=rs.getString("sex");
			String bdate=rs.getString("bdate");
			String rgtdate=rs.getString("rgtdate");
			int unumber=rs.getInt(6);
			int mgrnum=rs.getInt(7);
			System.out.println("(1) user name: "+name+" (2) user id: "+id+" (3) sex: "+sex+" (4) birth date: "
			+bdate+" (5) register date: "+rgtdate+" (6) user number: "+unumber+" (7) manager num: "+mgrnum);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}
	
}
