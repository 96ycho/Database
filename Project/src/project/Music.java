package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Music {
	static String mname;			//not null
	static int mnumber;			//not null
	static String playtime;
	static String rldate;
	static int snum;				//not null
	static int anum;				//not null
	static int mgrnum;			
	
	public Music(){
		
	}
	public static boolean insert(Connection con) {
		try {
			Scanner scanner=new Scanner(System.in);
			
			System.out.println();
			System.out.println("Insert");
			System.out.println("--------------------------");
			System.out.print("Music Name: ");
			mname = scanner.next();                
			scanner.nextLine();
			System.out.print("Play time: ");
			playtime = scanner.nextLine();
			if(!playtime.equals((String)"")) {
				while(!playtime.matches("^.:..$")) {
					System.out.println("You must insert play time like: '_:__'");
					System.out.print("Play time: ");
					playtime=scanner.next();
					scanner.nextLine();
				}
			}
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
			System.out.print("Singer: ");
			String singer;
			singer = scanner.next();
			String sql="select * from singer where sname='"+singer+"'";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.next()) {			 
				System.out.println("ERROR: No Singer.");
				System.out.println();
				return false;
			}
			rs.beforeFirst();
			Singer s=null;
			System.out.println();
			s.view(rs);
			System.out.println();
			System.out.print("Select Singer Number: ");
			snum=scanner.nextInt();
			int check=0;
			while(check==0) {		
				rs.beforeFirst();
				while(rs.next()) {
					if(rs.getInt(3)==snum)
						check=1;
				}
				if(check==0) {
					System.out.println("ERROR: Wrong Singer Number");
					System.out.print("Select Singer Number: ");
					snum=scanner.nextInt();
				}
			}
		
			System.out.print("Album: ");
			String album;
			album = scanner.next();
			sql="select * from album where aname='"+album+"'";
			stmt=con.createStatement();
			rs = stmt.executeQuery(sql);
			if(!rs.next()) {
				System.out.println("ERROR: No Album.");
				System.out.println();
				return false; 
			}
			rs.beforeFirst();
			Album a=null;
			System.out.println();
			a.view(rs);
			System.out.println();
			System.out.print("Select Album Number: ");
			anum=scanner.nextInt();
			check=0;
			while(check==0) {
				rs.beforeFirst();
				while(rs.next()) {
					if(rs.getInt(3)==anum)
						check=1;
				}
				if(check==0) {
					System.out.println("ERROR: Wrong Album Number");
					System.out.print("Select Album Number: ");
					anum=scanner.nextInt();
				}
			}
			
			System.out.println("--------------------------");
			System.out.println();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void update(Connection con) {
		try {
			Scanner scanner=new Scanner(System.in);
			
			System.out.println();
			System.out.println("Update");
			System.out.println("--------------------------");
			System.out.print("Music Name: ");
			mname = scanner.nextLine();
			System.out.print("Play time: ");
			playtime = scanner.nextLine();
			if(!playtime.equals((String)"")) {
				while(!playtime.matches("^*:*$")) {
					System.out.println("You must insert play time like: '_:__'");
					System.out.print("Play time: ");
					playtime=scanner.next();
					scanner.nextLine();
				}
			}
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
			System.out.print("Singer: ");
			String singer;
			singer = scanner.nextLine();
			if(!singer.equals((String)"")) {
				String sql="select * from singer where sname='"+singer+"'";
				Statement stmt=con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(!rs.next()) {
					System.out.println("ERROR: No Singer.");
					System.out.println();
					System.out.print("Singer: ");
					singer = scanner.next();
					sql="select * from singer where sname='"+singer+"'";
					rs = stmt.executeQuery(sql);
				}
				rs.beforeFirst();				
				Singer s=null;
				System.out.println();
				s.view(rs);
				System.out.println();
				System.out.print("Select Singer Number: ");
				snum=scanner.nextInt();
				int check=0;
				while(check==0) {
					rs.beforeFirst();
					while(rs.next()) {
						if(rs.getInt(3)==snum)
							check=1;
					}
					if(check==0) {
						System.out.println("ERROR: Wrong Singer Number");
						System.out.print("Select Singer Number: ");
						snum=scanner.nextInt();
					}
				}
				scanner.nextLine();
			}else {
				snum=0;
			}
			System.out.print("Album: ");
			String album;
			album = scanner.nextLine();
			if(!album.equals((String)"")) {
				String sql="select * from album where aname='"+album+"'";
				Statement stmt=con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(!rs.next()) {
					System.out.println("ERROR: No Album.");
					System.out.println();
					System.out.print("Album: ");
					album = scanner.next();
					sql="select * from album where aname='"+album+"'";
					rs = stmt.executeQuery(sql);
				}
				rs.beforeFirst();
				Album a=null;
				System.out.println();
				a.view(rs);
				System.out.println();
				System.out.print("Select Album Number: ");
				anum=scanner.nextInt();
				int check=0;
				while(check==0) {
					rs.beforeFirst();
					while(rs.next()) {
						if(rs.getInt(3)==anum)
							check=1;
					}
					if(check==0) {
						System.out.println("ERROR: Wrong Album Number");
						System.out.print("Select Album Number: ");
						anum=scanner.nextInt();
					}
				}			
				scanner.nextLine();
			}else {
				anum=0;
			}
			
			System.out.print("Manager Number: ");
			String tmp=scanner.nextLine();
			if(tmp.equals((String)""))
				mgrnum=0;
			else { 
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
			}
			
			System.out.println("--------------------------");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String delete(Connection con) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Delete");
		System.out.println("-------------------------------------------");
		System.out.println("1. Music Name");
		System.out.println("2. Music Number");
		System.out.print("Delete Music by : ");
		int num= sc.nextInt();
		
		while(num!=1 && num!=2) {
			System.out.println("ERROR: Please write another number.");
			num=sc.nextInt();
		}
		
		String sql = null;
		
		switch(num) {
			case 1:{				//delete by name
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Music name: ");
				String musicname=sc.next();
				
				String query="select * from music where mname='"+musicname+"'";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					System.out.println();
					view(rs);
					System.out.println();
					System.out.print("Select Music number: ");
					int musicnum=sc.nextInt();
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(2)==musicnum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong Music Number");
							System.out.print("Select Music Number: ");
							musicnum=sc.nextInt();
						}
					}
					
					sql="mnumber="+musicnum;	
					
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			case 2:{				//delete by number
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.print("Music number: ");
				int musicnumber=sc.nextInt();
				
				sql="mnumber="+musicnumber;
				
				break;
			}
		}
		
		return sql;
	}
	
	public static void select(Connection con) {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. Select Music Name");
		System.out.println("2. Select Music Number");
		System.out.println("3. Select Release Date");
		System.out.println("4. Select Singer");
		System.out.println("5. Select Album");
		System.out.println("6. Select Manager Number");
		System.out.print("Input: ");
		int num=scanner.nextInt();
		
		System.out.println();
		try {
			switch(num) {
				case 1:{					
					System.out.print("Music Name: ");
					String musicname=scanner.next();
					
					String sql="select * from music where mname like '%"+musicname+"%'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 2:{
					System.out.print("Music number: ");
					int musicnum=scanner.nextInt();
					
					String sql="select * from music where mnumber="+musicnum;
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
					int reldate=scanner.nextInt();
					
					while(!(reldate>=1&&reldate<=4)) {
						System.out.println("ERROR: Please write another number.");
						reldate=scanner.nextInt();
					}
					
					String date;
					System.out.println();
					if(reldate==1) {
						System.out.print("Year: ");
						date=scanner.next();
						while(!date.matches("^....$")) {
							System.out.println("You must insert year like: 'yyyy'");
							System.out.print("Year: ");
							date=scanner.next();
						}
						
						String sql="select * from music where rldate like '"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(reldate==2) {
						System.out.print("Month: ");
						date=scanner.next();
						while(!date.matches("^..$")) {
							System.out.println("You must insert month like: 'mm'");
							System.out.print("Month: ");
							date=scanner.next();
						}
						
						String sql="select * from music where rldate like '_____"+date+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}else if(reldate==3) {
						System.out.print("Year-Month: ");
						date=scanner.next();
						while(!date.matches("^....-..$")) {
							System.out.println("You must insert year-month like: 'yyyy-mm'");
							System.out.print("Year-Month: ");
							date=scanner.next();
						}
						
						String sql="select * from music where rldate like '"+date+"%'";
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
						
						String sql="select * from music where rldate='" +date+"'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						view(rs);
					}
					break;
				}
				case 4:{
					System.out.print("Singer: ");
					String singer=scanner.next();
					
					
					String sql="select * from music inner join singer on music.snum=singer.snumber where sname='"+singer+"'";
					//sql="select * from music, singer where snum=snumber and sname='"+singer"'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 5:{
					System.out.print("Album: ");
					String album=scanner.next();
					
					
					String sql="select * from music inner join album on music.anum=album.anumber where aname='"+album+"'";
					//sql="select * from music, album where anum=anumber and aname='"+album"'";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println();
					
					view(rs);
					break;
				}
				case 6:{
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
			String mname= rs.getString("mname");
			int mnumber=rs.getInt(2);
			String playtime=rs.getString("playtime");
			String rldate=rs.getString("rldate");
			int snum=rs.getInt(5);
			int anum=rs.getInt(6);
			int mgrnum=rs.getInt(7);
			System.out.println("(1) music name: "+mname+" (2) music number: "+mnumber+" (3) playtime: "+playtime+" "
					+ "(4) release date: "+rldate+" (5) singer num: "+snum+" (6) aalbum num: "+anum+" (7) manager num: "+mgrnum);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}
	
}
