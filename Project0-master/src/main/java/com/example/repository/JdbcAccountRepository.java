package com.example.repository;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import com.example.database.FirstConnection;
import com.example.entity.Account;
import com.example.entity.TDetails;
import com.example.entity.AccDetails;

public class JdbcAccountRepository implements AccountRepository {
	Scanner input = new Scanner(System.in);
	public void save(AccDetails accdetails) {
			Connection con = null;
		try {
			con = FirstConnection.getConnection();

			String sql = "insert into accountdetails (id,user_name,phone_no,address,balance,type,age) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,accdetails.getId());
			ps.setString(2,accdetails.getUser_name());
			ps.setLong(3,accdetails.getPhone_no());
			ps.setString(4,accdetails.getAddress());
			ps.setDouble(5,accdetails.getBalance());
			ps.setString(6,accdetails.getType());
			ps.setInt(7,accdetails.getAge());

			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				System.out.println("Account Created");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Account load(String accNumber) {	

		Account account = null;

		Connection connection = null;
		try {
			connection = FirstConnection.getConnection();

			String sql = "select * from accountdetails where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, accNumber);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setUser_name(rs.getString(2));
				account.setPhone_no(rs.getLong(3));
				account.setAddress(rs.getString(4));
				account.setBalance(rs.getDouble(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return account;
	}

	public void update(Account account) {

		Connection connection = null;
		try {
			connection = FirstConnection.getConnection();

			String sql = "update accountdetails set balance=? where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getId());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public List<AccDetails> getAccDetails(int n1) {
	
	Connection con = null;

	List<AccDetails> accDetails1 = new ArrayList<AccDetails>();

	try {
		con = FirstConnection.getConnection();
		String sql;
		if(n1==0) {
		sql="select * from accountdetails;";
		}
		else {
		sql="select * from accountdetails where id="+n1+";";
		}
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			AccDetails accDetails = new AccDetails();
			accDetails.setId(rs.getInt(1));
			accDetails.setUser_name(rs.getString(2));
			accDetails.setPhone_no(rs.getInt(3));
			accDetails.setAddress(rs.getString(4));
			accDetails.setBalance(rs.getDouble(5));
			accDetails1.add(accDetails);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return accDetails1;

}
	
	public void edit(int n1) {

		Connection con = null;
		try {
			con = FirstConnection.getConnection();
			String sql;
			System.out.println("Select 1 to change amount");
			System.out.println("Select 2 to change name");
			System.out.println("Select 3 to change phone number");
			System.out.println("Select 4 to change address");
			int c=input.nextInt();
			System.out.println("Enter the value");
			switch (c) {
			case 1:{
				double am=input.nextDouble();
				sql = "update accountdetails set balance="+am+" where id="+n1+"";
				PreparedStatement ps = con.prepareStatement(sql);
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Account edited.");
				}
				break;
			}
			case 2:{
				String val=input.next();
				sql = "update accountdetails set user_name='"+val+"' where id="+n1+"";
				PreparedStatement ps = con.prepareStatement(sql);
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Account edited.");
				}
				break;
			}
			case 3:{
				String val=input.next();
				sql = "update accountdetails set phone_no='"+val+"' where id="+n1+"";
				PreparedStatement ps = con.prepareStatement(sql);
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Account edited.");
				}
				break;
			}
			case 4:{
				String val=input.next();
				sql = "update accountdetails set address='"+val+"' where id="+n1+"";
				PreparedStatement ps = con.prepareStatement(sql);
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Account edited.");
				}
				break;
			}

			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int countAcc() {

		Connection con = null;
		try {
			con = FirstConnection.getConnection();
			String sql = "select count(*) from accountdetails;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int c = rs.getInt(1);
			return c;
			// step-7 : close connection
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
	public List<TDetails> getTransfer() {
	
	Connection con = null;

	List<TDetails> tdetails1 = new ArrayList<TDetails>();

	try {
		con = FirstConnection.getConnection();

		String sql="select * from trans;";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			TDetails tdetails = new TDetails();
			tdetails.setFromid(rs.getString(1));
			tdetails.setToid(rs.getString(2));
			tdetails.setTransamont(rs.getDouble(3));
			tdetails.setDate(rs.getString(4));
			tdetails1.add(tdetails);
		}

		// step-7 : close connection
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return tdetails1;
	}


	public void tsave(TDetails tdetails) {
		Connection con = null;
		try {
			con = FirstConnection.getConnection();
			String sql = "insert into trans (fromid,toid,transamount,date) values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			Date utilDate = new Date();
			LocalDate localDate = LocalDate.now();
			ps.setString(1, tdetails.getFromid());
			ps.setString(2, tdetails.getToid());
			ps.setDouble(3, tdetails.getTransamont());
			ps.setDate(4, new java.sql.Date(utilDate.getTime()));
			//ps.setDate(4, new java.sql.Date(utilDate.getTime()));

			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				System.out.println("Transfer updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public void delete(int id) {

		Connection con = null;
		try {
			con = FirstConnection.getConnection();
			String sql = "delete from accountdetails where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			// step-4 : execute jdbc-statements & process result-set
			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				System.out.println("Account deleted.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int count() {

		Connection con = null;
		try {
			System.out.println("Enter Id (0 for all)");
			int n1=input.nextInt();
			con = FirstConnection.getConnection();
			String sql;
			if(n1==0) {
			sql = "select count(*) from trans";
			}
			else {
				sql = "select count(*) from trans where fromid="+n1+" or toid="+n1;
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int c = rs.getInt(1);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}