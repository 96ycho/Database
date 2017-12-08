package project;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

	public static Connection con=null;
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String pass="eunji415";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		while(true) {
			int num;
			
			System.out.println();
			System.out.println("0. Exit");
			System.out.println("1. Manager Mode");
			System.out.println("2. User Mode");
			System.out.print("Input: ");
			
			Scanner scanner=new Scanner(System.in);
			num=scanner.nextInt();
			
			switch(num) {
				case 0:
					return;
				case 1:{			//manager mode
				
					System.out.println();
					System.out.println("--------------------------");
					System.out.print("Manager ID: ");
					String Id = scanner.next();
					int mnum;
					String sql="select mgrnumber from manager where mid='"+Id+"'";
					Statement stmt = con.createStatement();	
					ResultSet rs = stmt.executeQuery(sql);
					while(!rs.next()) {
						System.out.println("ERROR: Wrong Manager Id");
						System.out.println();
						System.out.print("Manager ID: ");
						Id = scanner.next();
						
						sql="select mgrnumber from manager where mid='"+Id+"'";
						stmt = con.createStatement();	
						rs = stmt.executeQuery(sql);
					}
					mnum=rs.getInt(1);
						
					while(true) {
						int num1;
						int back=0;
						
						System.out.println();
						System.out.println("0. Return to Previous Menu");
						System.out.println("1. User");
						System.out.println("2. Music");
						System.out.println("3. Singer");
						System.out.println("4. Album");
						System.out.println("5. Manager");
						System.out.print("Input: ");
						
						num1=scanner.nextInt();
						switch(num1) {
							case 0:{		//return
								back=1;
								break;
							}
							case 1:{
								manageuser(mnum);
								break;
							}
							case 2:{
								managemusic(mnum);
								break;
							}
							case 3:{
								managesinger();
								break;
							}
							case 4:{
								managealbum();
								break;
							}
							case 5:{
								managemanager();
								break;
							}
							default:{
								System.out.println("ERROR: Please write another number.");
								break;
							}
						}
						if(back==1)
							break;
					}		//while
					break;
				}				//manager mode
				
				case 2:{			//user mode
					
					while(true) {
						int num1;
						int back=0; 
						
						System.out.println();
						System.out.println("--------------------------");
						System.out.println("0. Return to Previous Menu");
						System.out.println("1. Sign In");
						System.out.println("2. Sign Up");
						System.out.print("Input: ");
						
						num1=scanner.nextInt();
						switch(num1) {
							case 0:{
								back=1;
								break;
							}
							case 1:{						//User Sign in
								System.out.println();
								System.out.print("User Id: ");
								String Id=scanner.next();
								
								int unum;
								String sql="select unumber from user where id='"+Id+"'";
								Statement stmt = con.createStatement();	
								ResultSet rs = stmt.executeQuery(sql);
								while(!rs.next()) {
									System.out.println("ERROR: Wrong User Id");
									System.out.println();
									System.out.print("User ID: ");
									Id = scanner.next();
									
									sql="select unumber from user where id='"+Id+"'";
									stmt = con.createStatement();	
									rs = stmt.executeQuery(sql);
								}
								unum=rs.getInt(1);
								
								user(unum);
								break;
							}							//user sign in
							case 2:{						//User Sign up
								String name, id, bdate, rgtdate;
								char sex;
								System.out.println();
								System.out.println("Register");
								System.out.println("--------------------------");
								
								System.out.print("Name: ");
								name = scanner.next();
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
								Date d=new Date();
								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
								rgtdate=sf.format(d);
																
								sql="insert into user(name, id";
								if(sex!=' ')
									sql+=", sex";
								if(!bdate.equals((String)""))
									sql+=", bdate";
								if(!rgtdate.equals((String)""))
									sql+=", rgtdate";
								
								sql +=") values('" +name+"', '"+id+"'";
								if(sex!=' ')
									sql+=", '"+sex+"'";
								if(!bdate.equals((String)""))
									sql+=", '"+bdate+"'";
								if(!rgtdate.equals((String)""))
									sql+=", '"+rgtdate+"'";
								sql+=")";
								
								stmt = con.createStatement();
								stmt.executeUpdate(sql);
								System.out.println("--------------------------");
								System.out.println("Register Done!");
								
								break;
							}				//user sign up
							default:{
								System.out.println("ERROR: Please write another number.");
								break;
							}
						}
						if(back==1)
							break;
					}
					break;
				}			//user mode
				
				default: {
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
			
	}
	
	public static void manageuser(int mnum) {					//user insert, update, delete, select
		
		while(true) {
			int numuser;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Insert User");
			System.out.println("2. Updatae User");
			System.out.println("3. Delete User");
			System.out.println("4. Select User");
			System.out.println("5. View User");
			System.out.print("Input: ");
			
			numuser=scanner.nextInt();
			
			switch(numuser) {
				case 0:
					return;
				case 1:{					//insert
					try {
						User tmp = null;
						tmp.insert(con);
						tmp.mgrnum=mnum;
						String sql="insert into user(name, id, mgrnum";
						if(tmp.sex!=' ')
							sql+=", sex";
						if(!tmp.bdate.equals((String)""))
							sql+=", bdate";
						if(!tmp.rgtdate.equals((String)""))
							sql+=", rgtdate";
						
						sql +=") values('" +tmp.name+"', '"+tmp.id+"', "+tmp.mgrnum;
						if(tmp.sex!=' ')
							sql+=", '"+tmp.sex+"'";
						if(!tmp.bdate.equals((String)""))
							sql+=", '"+tmp.bdate+"'";
						if(!tmp.rgtdate.equals((String)""))
							sql+=", '"+tmp.rgtdate+"'";
						sql+=")";
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
						System.out.println("Insert Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2:{					//update
					try {
						
						System.out.println();
						System.out.println("-------------------------------------------");
						System.out.print("User id: ");
						String userid=scanner.next();
						scanner.nextLine();
						
						User tmp = null;
						tmp.update(con);
						
						String sql="update user set ";
						
						int cng=0;
						if(!tmp.name.equals((String)"")) {
							sql +="name='"+tmp.name+"'";
							cng++;
						}
						if(!tmp.id.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="id='"+tmp.id+"'";
							cng++;
						}
						if(!(tmp.sex==' ')) {
							if(cng!=0)
								sql+=", ";
							sql +="sex='"+tmp.sex+"'";
							cng++;
						}
						if(!tmp.bdate.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="bdate='"+tmp.bdate+"'";
							cng++;
						}
						if(!tmp.rgtdate.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="rgtdate='"+tmp.rgtdate+"'";
							cng++;
						}

						if(!(tmp.mgrnum==0)) {
							if(cng!=0)
								sql+=", ";
							sql +="mgrnum="+tmp.mgrnum;
							cng++;
						}
							
						if(cng==0) {
							System.out.println("Nothing Updated");
							break;
						}
						
						sql+=" where id='"+userid+"'";
						Statement stmt = con.createStatement();
						int rt=stmt.executeUpdate(sql);
						if(rt==0)
							System.out.println("Nothing Updated: Wrong user id");
						else System.out.println("Update Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{					//delete
					try {
						
						User tmp = null;
						String query= tmp.delete(con);
						
						String sql="delete from user where "+query;

						Statement stmt = con.createStatement();
						int rt = stmt.executeUpdate(sql);
						
						if(rt==0)
							System.out.println("Nothing Deleted");
						else System.out.println("Delete Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{					//select
					//User search
					
					User tmp = null;
					tmp.select(con);
					
					break;
				}
				case 5:{					//view
					String sql="select * from user";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						System.out.println("View");
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String name= rs.getString("name");
							String id=rs.getString("id");
							String sex=rs.getString("sex");
							String bdate=rs.getString("bdate");
							String rgtdate=rs.getString("rgtdate");
							int unumber=rs.getInt(6);
							int mgrnum=rs.getInt(7);
							System.out.println("(1) user name: "+name+" (2) user id: "+id+" (3) sex: "+sex+" (4) bitrh date: "
							+bdate+" (5) resgister date: "+rgtdate+" (6) user number: "+unumber+" (7) manager num: "+mgrnum);
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
	}
	
	public static void managemusic(int mnum) {
		
		while(true) {
			int nummusic;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Insert Music");
			System.out.println("2. Updatae Music");
			System.out.println("3. Delete Music");
			System.out.println("4. Select Music");
			System.out.println("5. View Music");
			System.out.print("Input: ");
			
			nummusic=scanner.nextInt();
			
			switch(nummusic) {
				case 0:
					return;
				case 1:{					//insert
					try {
						Music tmp = null;
						if(!tmp.insert(con))
							break;
						tmp.mgrnum=mnum;
						String sql="insert into music(mname, snum, anum, mgrnum";
						if(!tmp.playtime.equals((String)""))
							sql+=", playtime";
						if(!tmp.rldate.equals((String)""))
							sql+=", rldate";
						
						sql +=") values('" +tmp.mname+"', "+tmp.snum+", "+tmp.anum+", "+tmp.mgrnum;
						if(!tmp.playtime.equals((String)""))
							sql+=", '"+tmp.playtime+"'";
						if(!tmp.rldate.equals((String)""))
							sql+=", '"+tmp.rldate+"'";
						sql+=")";
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
						System.out.println("Insert Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2:{					//update
					try {
						
						System.out.println();
						System.out.println("-------------------------------------------");
						System.out.print("Music number: ");
						int musicnum=scanner.nextInt();
						
						Music tmp = null;
						tmp.update(con);
						
						String sql="update music set ";
						
						int cng=0;
						if(!tmp.mname.equals((String)"")) {
							sql +="mname='"+tmp.mname+"'";
							cng++;
						}
						if(!tmp.playtime.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="playtime='"+tmp.playtime+"'";
							cng++;
						}
						if(!tmp.rldate.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="rldate='"+tmp.rldate+"'";
							cng++;
						}
						if(!(tmp.snum==0)) {
							if(cng!=0)
								sql+=", ";
							sql +="snum="+tmp.snum;
							cng++;
						}
						if(!(tmp.anum==0)) {
							if(cng!=0)
								sql+=", ";
							sql +="anum="+tmp.anum;
							cng++;
						}
						if(!(tmp.mgrnum==0)) {
							if(cng!=0)
								sql+=", ";
							sql +="mgrnum="+tmp.mgrnum;
							cng++;
						}
							
						if(cng==0) {
							System.out.println("Nothing Updated");
							break;
						}
						
						sql+=" where mnumber="+musicnum;
						Statement stmt = con.createStatement();
						int rt=stmt.executeUpdate(sql);
						if(rt==0)
							System.out.println("Nothing Updated: Wrong music number");
						else System.out.println("Update Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{					//delete
					try {
						Music tmp = null;
						String query= tmp.delete(con);
						
						String sql="delete from music where "+query;

						Statement stmt = con.createStatement();
						int rt = stmt.executeUpdate(sql);
						
						if(rt==0)
							System.out.println("Nothing Deleted");
						else System.out.println("Delete Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{					//select
					//Music search
					
					Music tmp = null;
					tmp.select(con);
					
					break;
				}
				case 5:{					//view
					String sql="select * from music";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						System.out.println("View");
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String mname= rs.getString("mname");
							int mnumber=rs.getInt(2);
							String playtime=rs.getString("playtime");
							String rldate=rs.getString("rldate");
							int snum=rs.getShort(5);
							int anum=rs.getInt(6);
							int mgrnum=rs.getInt(7);
							System.out.println("(1) music name: "+mname+" (2) music number: "+mnumber+" (3) playtime: "+playtime+" "
									+ "(4) release date: "+rldate+" (5) singer num: "+snum+" (6) album num: "+anum+" (7) manager num: "+mgrnum);
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
	}

	public static void managesinger() {
		while(true) {
			int numsinger;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Insert Singer");
			System.out.println("2. Updatae Singer");
			System.out.println("3. Delete Singer");
			System.out.println("4. Select Singer");
			System.out.println("5. View Singer");
			System.out.print("Input: ");
			
			numsinger=scanner.nextInt();
			
			switch(numsinger) {
				case 0:
					return;
				case 1:{					//insert
					try {
						Singer tmp = null;
						tmp.insert();
						String sql="insert into singer(sname";
						if(!tmp.debutdate.equals((String)""))
							sql+=", debutdate";
						if(tmp.sex!=0)
							sql+=", sex";
						if(tmp.groupn!=0)
							sql+=", groupn";
						
						sql +=") values('" +tmp.sname+"'";
						if(!tmp.debutdate.equals((String)""))
							sql+=", '"+tmp.debutdate+"'";
						if(tmp.sex!=0)
							sql+=", "+tmp.sex;
						if(tmp.groupn!=0)
							sql+=", "+tmp.groupn;
						sql+=")";
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
						System.out.println("Insert Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2:{					//update
					try {
						
						System.out.println();
						System.out.println("-------------------------------------------");
						System.out.print("Singer number: ");
						int singernum=scanner.nextInt();
						
						Singer tmp = null;
						tmp.update();
						
						String sql="update singer set ";
						
						int cng=0;
						if(!tmp.sname.equals((String)"")) {
							sql +="sname='"+tmp.sname+"'";
							cng++;
						}
						if(!tmp.debutdate.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="debutdate='"+tmp.debutdate+"'";
							cng++;
						}
						if(tmp.sex!=0) {
							if(cng!=0)
								sql+=", ";
							sql+="sex= "+tmp.sex;
							cng++;
						}
						if(tmp.groupn!=0) {
							if(cng!=0)
								sql+=", ";
							sql+="groupn= "+tmp.groupn;
							cng++;
						}
							
						if(cng==0) {
							System.out.println("Nothing Updated");
							break;
						}
						
						sql+=" where snumber="+singernum;
						Statement stmt = con.createStatement();
						int rt=stmt.executeUpdate(sql);
						if(rt==0)
							System.out.println("Nothing Updated: Wrong singer number");
						else System.out.println("Update Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{					//delete
					try {
						
						Singer tmp = null;
						String query= tmp.delete(con);
						
						String sql="delete from singer where "+query;

						Statement stmt = con.createStatement();
						int rt = stmt.executeUpdate(sql);
						
						if(rt==0)
							System.out.println("Nothing Deleted");
						else System.out.println("Delete Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{					//select
					//Singer search
					
					Singer tmp = null;
					tmp.select(con);
					
					break;
				}
				case 5:{					//view
					String sql="select * from singer";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						System.out.println("View");
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String sname= rs.getString("sname");
							String debutdate=rs.getString("debutdate");
							int snumber=rs.getInt(3);
							System.out.print("(1) singer name: "+sname+" (2) debut date: "+debutdate+" (3) singer number: "+snumber);
							int sex=rs.getInt(4), groupn=rs.getInt(5);
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
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
	}
	
	public static void managealbum() {
		while(true) {
			int numalbum;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Insert Album");
			System.out.println("2. Updatae Album");
			System.out.println("3. Delete Album");
			System.out.println("4. Select Album");
			System.out.println("5. View Album");
			System.out.print("Input: ");
			
			numalbum=scanner.nextInt();
			
			switch(numalbum) {
				case 0:
					return;
				case 1:{					//insert
					try {
						Album tmp = null;
						tmp.insert();
						String sql="insert into album(aname";
						if(!tmp.rldate.equals((String)""))
							sql+=", rldate";
						
						sql +=") values('" +tmp.aname+"'";
						if(!tmp.rldate.equals((String)""))
							sql+=", '"+tmp.rldate+"'";
						sql+=")";
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
						System.out.println("Insert Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2:{					//update
					try {
						
						System.out.println();
						System.out.println("-------------------------------------------");
						System.out.print("Album number: ");
						int albumnum=scanner.nextInt();
						
						Album tmp = null;
						tmp.update();
						
						String sql="update album set ";
						
						int cng=0;
						if(!tmp.aname.equals((String)"")) {
							sql +="aname='"+tmp.aname+"'";
							cng++;
						}
						if(!tmp.rldate.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="rldate='"+tmp.rldate+"'";
							cng++;
						}
						
						if(cng==0) {
							System.out.println("Nothing Updated");
							break;
						}
							
						sql+=" where anumber="+albumnum;
						Statement stmt = con.createStatement();
						int rt=stmt.executeUpdate(sql);
						if(rt==0)
							System.out.println("Nothing Updated: Wrong album number");
						else System.out.println("Update Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{					//delete
					try {
						
						Album tmp = null;
						String query= tmp.delete(con);
						
						String sql="delete from album where "+query;

						Statement stmt = con.createStatement();
						int rt = stmt.executeUpdate(sql);
						
						if(rt==0)
							System.out.println("Nothing Deleted");
						else System.out.println("Delete Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{					//select
					//Singer search
					
					Album tmp = null;
					tmp.select(con);
					
					break;
				}
				case 5:{					//view
					String sql="select * from album";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						System.out.println("View");
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String aname= rs.getString("aname");
							String rldate=rs.getString("rldate");
							int anumber=rs.getInt(3);
							System.out.println("(1) album name: "+aname+" (2) release date: "+rldate+" (3) album number: "+anumber);
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
	}
	
	public static void managemanager() {
		while(true) {
			int nummanager;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Insert Manager");
			System.out.println("2. Updatae Manager");
			System.out.println("3. Delete Manager");
			System.out.println("4. Select Manager");
			System.out.println("5. View Manager");
			System.out.print("Input: ");
			
			nummanager=scanner.nextInt();
			
			switch(nummanager) {
				case 0:
					return;
				case 1:{					//insert
					try {
						Manager tmp = null;
						tmp.insert(con);
						String sql="insert into manager(mgrname, mid) values('"+tmp.mgrname+"', '"+tmp.mid+"')";
		
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
						System.out.println("Insert Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2:{					//update
					try {
						
						System.out.println();
						System.out.println("-------------------------------------------");
						System.out.print("Manager number: ");
						int managernum=scanner.nextInt();
						
						Manager tmp = null;
						tmp.update(con);
						
						String sql="update manager set ";
						
						int cng=0;
						if(!tmp.mgrname.equals((String)"")) {
							sql +="mgrname='"+tmp.mgrname+"'";
							cng++;
						}
						if(!tmp.mid.equals((String)"")) {
							if(cng!=0)
								sql+=", ";
							sql +="mid='"+tmp.mid+"'";
							cng++;
						}
						
						if(cng==0) {
							System.out.println("Nothing Updated");
							break;
						}
							
						sql+=" where mgrnumber="+managernum;
						Statement stmt = con.createStatement();
						int rt=stmt.executeUpdate(sql);
						if(rt==0)
							System.out.println("Nothing Updated: Wrong manager number");
						else System.out.println("Update Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{					//delete
					try {
						
						Manager tmp = null;
						String query= tmp.delete(con);
						
						String sql="delete from manager where "+query;

						Statement stmt = con.createStatement();
						int rt = stmt.executeUpdate(sql);
						
						if(rt==0)
							System.out.println("Nothing Deleted");
						else System.out.println("Delete Done!");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{					//select
					//Singer search 
					
					Manager tmp = null;
					tmp.select(con);
					
					break;
				}
				case 5:{					//view
					String sql="select * from manager";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						System.out.println("View");
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							int mgrnumber=rs.getInt(1);
							String mgrname= rs.getString("mgrname");
							String mid=rs.getString("mid");
							System.out.println("(1) manager name: "+mgrname+" (2) manager id: "+mid+" (3) manager number: "+mgrnumber);
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
		}
	}
	
	public static void user(int usernum) {
		while(true) {
			Scanner scanner=new Scanner(System.in);
			System.out.println();
			
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Search Music");
			System.out.println("2. Search Singer");
			System.out.println("3. Search Album");
			System.out.println("4. Playlist");
			System.out.print("Input: ");
			int num=scanner.nextInt();
			System.out.println();
			
			try {
				switch(num) {
					case 0:
						return;
						
					case 1:{
						while(true) {
							int back=0;
							System.out.println();
							System.out.println("0. Return to Previous Menu");
							System.out.println("1. Search by Name");
							System.out.println("2. Search by Singer");
							System.out.println("3. Search by Album");
							System.out.print("Input: ");
							int num1=scanner.nextInt();
							System.out.println();
							
							switch(num1) {
								case 0:{
									back=1;
									break;
								}
								case 1:{
									System.out.print("Music: ");
									String musicname=scanner.next();
									
									String sql="select * from music where mname like '%"+musicname+"%'";
									Statement stmt=con.createStatement();
									ResultSet rs;
									rs = stmt.executeQuery(sql);
									System.out.println();
									
									view(rs);
									break;
								}
								case 2:{
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
								case 3:{
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
								default:{
									System.out.println("ERROR: Please write another number.");
									break;
								}
							}
							if(back==1)
								break;
						}					
						break;
					}
					
					case 2:{
						System.out.print("Singer Name: ");
						String singername=scanner.next();
						
						String sql="select * from singer where sname like '%"+singername+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String sname= rs.getString("sname");
							String debutdate=rs.getString("debutdate");
							int sex=rs.getInt(4);
							int groupn=rs.getInt(5);
							System.out.print("(1) singer name: "+sname+" (2) debut date: "+debutdate);
							if(sex==1)
								System.out.print(" (3) sex: M");
							else if(sex==2) System.out.print(" (3) sex: F");
							else if(sex==3) System.out.print(" (3) sex: Both");
							else System.out.print(" (3) sex: null");
							if(groupn==1)
								System.out.print(" (4) group: Group");
							else if(groupn==2) System.out.print(" (4) group: Solo");
							else System.out.print(" (4) group: null");
							System.out.println();
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
						
						break;
					}
					
					case 3:{
						System.out.print("Album Name: ");
						String albumname=scanner.next();
						
						String sql="select * from album where aname like '%"+albumname+"%'";
						Statement stmt=con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println();
						
						while(rs.next()) {
							System.out.println("----------------------------------------------------------------------------------------------------------------------");
							String aname= rs.getString("aname");
							String rldate=rs.getString("rldate");
							System.out.println("(1) album name: "+aname+" (2) release date: "+rldate);
						}
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
					
						break;
					}
					
					case 4:{
						playlist(usernum);
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
	}
	
	public static void view(ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			String mname= rs.getString("mname");
			String playtime=rs.getString("playtime");
			String rldate=rs.getString("rldate");
			int snum=rs.getInt(5);
			int anum=rs.getInt(6);
			String sql="select sname from singer where snumber="+snum;
			Statement stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery(sql);
			rs1.next();
			String sname=rs1.getString("sname");
			sql="select aname from album where anumber="+anum;
			rs1=stmt.executeQuery(sql);
			rs1.next();
			String aname=rs1.getString("aname");
			
			System.out.println("(1) music: "+mname+" (2) singer: "+sname+" (3) album: "+aname
					+ "(4) play time: "+playtime+" (5) release date: "+rldate);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
	}
	
	public static void playlist(int usernum) throws SQLException{
		Scanner scanner= new Scanner(System.in);
		while(true) {
			System.out.println();
			System.out.println("0. Return to Previous Menu");
			System.out.println("1. Create Playlist");
			System.out.println("2. Remove Playlist");
			System.out.println("3. Show Playlist");
			System.out.print("Input: ");
			int num=scanner.nextInt();
			scanner.nextLine();
			System.out.println();
			
			int back=0;
			switch(num) {
				case 0:
					return;
				case 1:{
					System.out.println("Create");
					System.out.println("--------------------------");
					System.out.print("Playlist name: ");
					String pname=scanner.next();
					
					Date d=new Date();
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String createdate=sf.format(d);
					
					String sql="insert into playlist(unum, pname, createdate, totalnum) values("+usernum
							+", '"+pname+"', '"+createdate+"', 0)";
					
					Statement stmt=con.createStatement();
					stmt.executeUpdate(sql);
					System.out.println("Create done!");
					break;
				}
				case 2:{
					System.out.println("Remove");
					System.out.println("--------------------------");
					System.out.print("Playlist name: ");
					String pname=scanner.next();
					
					String sql="delete from playlist where unum="+usernum+" and pname='"+pname+"'";
					Statement stmt=con.createStatement();
					stmt.executeUpdate(sql);
					break;
				}
				case 3:{
					String sql="select * from playlist where unum="+usernum;
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()) {
						System.out.println("----------------------------------------------------------------------------------------------------------------------");
						String pname=rs.getString("pname");
						String createdate=rs.getString("createdate");
						int totalnum=rs.getInt(4);
						System.out.println("(1) playlist name: "+pname+" (2) create date: "+createdate+" (3) total number: "+totalnum);
					}
					System.out.println("----------------------------------------------------------------------------------------------------------------------");
					System.out.print("Select Playlist: ");
					String playli=scanner.nextLine();
					
					Playlist play=new Playlist();
					if(!playli.equals((String)"")) {
						sql="select * from playlist where unum='"+usernum+"' and pname='"+playli+"'";
						rs=stmt.executeQuery(sql);
						while(!rs.next()){
							System.out.println("ERROR: Wrong Playlist Name");
							System.out.print("Select Playlist: ");
							playli=scanner.nextLine();
							if(playli.equals((String)""))
								break;
							sql="select * from playlist where unum='"+usernum+"' and pname='"+playli+"'";
							rs=stmt.executeQuery(sql);
						}
						if(playli.equals((String)""))
							break;
						play.list(con, usernum, playli);
					}
					break;
				}
				default:{
					System.out.println("ERROR: Please write another number.");
					break;
				}
			}
			if(back==1)
				break;
		}
	}
	
}
