package project;

import java.util.Scanner;
import java.sql.*;

public class Playlist {
	int unum;
	String pname;
	String createdate; 
	int totalnum;
	
	public Playlist(){
		
	}
	
	public void list(Connection con, int unum, String pname) {
		
		Scanner scanner=new Scanner(System.in);
		try {
			
			while(true) {
				
				String sql="select mname, mnumber, snum, anum, playtime, rldate from music, playon "
						+ "where playon.mnum=music.mnumber and playon.unum="+unum+" and playon.pname='"+pname+"'";
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				System.out.println();
				view(con, rs);
				
				System.out.println("0. Return to Previous Menu");
				System.out.println("1. Add Music");
				System.out.println("2. Delete Music");
				System.out.print("Input: ");
				int num=scanner.nextInt();
				while(num!=0&&num!=1&&num!=2) {
					System.out.println("ERROR: Please write another number.");
					System.out.print("Input: ");
				}
				
				if(num==0)
					return;
				else if(num==1)
					add(con, unum, pname);
				else if(num==2)
					delete(con, unum, pname, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Connection con, int unum, String pname) {
		Scanner scanner=new Scanner(System.in);
		
		try {
			System.out.println();
			System.out.println("Add");
			System.out.println("--------------------------");
			System.out.print("Music Name: ");
			String musicname=scanner.next();
			scanner.nextLine();
			
			String sql="select mname, mnumber, snum, anum, playtime, rldate from music where mname like '%"+musicname+"%'";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println();
			view(con, rs);
			while(true) {
				System.out.print("Add music number: ");
				String tmp=scanner.nextLine();
				if(tmp.equals((String)""))
					break;
				else {
					int mnum=Integer.parseInt(tmp);
					int check=0;
					while(check==0) {
						rs.beforeFirst();
						while(rs.next()) {
							if(rs.getInt(2)==mnum)
								check=1;
						}
						if(check==0) {
							System.out.println("ERROR: Wrong Music Number");
							System.out.print("Select Music Number: ");
							mnum=scanner.nextInt();
						}
					}
					sql="select * from playon where mnum="+mnum+" and unum="+unum+" and pname='"+pname+"'";
					rs=stmt.executeQuery(sql);
					if(rs.next()) {
						System.out.println("ERROR: Music is already in playlist "+pname);
						break;
					}

					sql="insert into playon(mnum, unum, pname) values("+mnum+", "+unum+", '"+pname+"')";
					stmt.executeUpdate(sql);
					sql="update playlist set totalnum=totalnum+1 where unum='"+unum+"' and pname='"+pname+"'";
					stmt.executeUpdate(sql);
					System.out.println("Insert Done!");
					System.out.println();
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Connection con, int unum, String pname, ResultSet rs) {
		Scanner scanner=new Scanner(System.in);
		
		try {
			rs.beforeFirst();
			System.out.println();
			System.out.print("Delete music number: ");
			String tmp=scanner.nextLine();
			if(tmp.equals((String)""))
				return;
			else {
				int mnum=Integer.parseInt(tmp);
				int check=0;
				while(check==0) {
					rs.beforeFirst();
					while(rs.next()) {
						if(rs.getInt(2)==mnum)
							check=1;
					}
					if(check==0) {
						System.out.println("ERROR: Wrong Music Number");
						System.out.print("Select Music Number: ");
						mnum=scanner.nextInt();
					}
				}
				String sql="delete from playon where mnum="+mnum+" and unum="+unum+" and pname='"+pname+"'";
				Statement stmt=con.createStatement();
				stmt.executeUpdate(sql);
				sql="update playlist set totalnum=totalnum-1 where unum='"+unum+"' and pname='"+pname+"'";
				stmt.executeUpdate(sql);				
				System.out.println("Delete Done!");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void view(Connection con, ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			String mname= rs.getString("mname");
			String playtime=rs.getString("playtime");
			String rldate=rs.getString("rldate");
			int mnum=rs.getInt(2);
			int snum=rs.getInt(3);
			int anum=rs.getInt(4);
			String sql="select sname from singer where snumber="+snum;
			Statement stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery(sql);
			rs1.next();
			String sname=rs1.getString("sname");
			sql="select aname from album where anumber="+anum;
			rs1=stmt.executeQuery(sql);
			rs1.next();
			String aname=rs1.getString("aname");
			
			System.out.println("(1) music: "+mname+" (2) music number: "+mnum+" (3) singer: "+sname+" (4) album: "+aname
					+ "(5) play time: "+playtime+" (6) release date: "+rldate);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
	}
}
