package com.projectOfAdmin.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.projectOfAdmin.model.Userinfo;

public class RolesDao 
{
	private String jdbcURL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
	private String userName = "root";
	private String password = "root";
	private String insertUserinfo = "INSERT INTO  testdb.userinfo (roleId,name,phoneno,password)  "
			+ "values(?,?,?,?);";
	private String checkLogin = "select * from testdb.userinfo ui,testdb.roles r  where r.roleId=ui.roleId and ui.name=? and ui.password=?;";
	private String selectAlluser = "select ui.userId,ui.roleId,ui.name,ui.phoneno,ui.password,r.roleName from testdb.userinfo ui,testdb.roles r where r.roleId=ui.roleId;";
	private String selectOneuser = "select ui.userId,ui.roleId,ui.name,ui.phoneno,ui.password,r.roleName from testdb.userinfo ui,testdb.roles r where r.roleId=ui.roleId  and ui.name=? and ui.password=? and ui.userId = ?;";
	private String deleteUser = "delete from testdb.userinfo where userId = ?";
	private String selectUserbyid = "select ui.userId,ui.roleId,ui.name,ui.phoneno,ui.password,r.roleName from testdb.userinfo ui,testdb.roles r where r.roleId=ui.roleId  and ui.userId = ?";
	private String updateUser = "update testdb.userinfo set name = ?,phoneno = ?,password = ? where userId=?";
	int result = 0;

	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void registerUser(Userinfo ui) {
		try (Connection connecion = getConnection();
			PreparedStatement ps = connecion.prepareStatement(insertUserinfo)) {
			ps.setInt(1, ui.getRoleid());
			ps.setString(2, ui.getName());
			ps.setString(3, ui.getPhoneno());
			ps.setString(4, ui.getPassword());
			ps.executeUpdate();
			System.out.println("ps " + ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Userinfo selectUserinfo(String name, String password) {
		boolean result = false;
		Userinfo userinfo = new Userinfo();
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(checkLogin)) {
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			result = rs.next();
			System.out.println(result);
			if (result == true) 
			{
				userinfo.setUserid(Integer.parseInt(rs.getString("userId")));
				userinfo.setRoleid(Integer.parseInt(rs.getString("roleId")));
				userinfo.setName(rs.getString("name"));
				userinfo.setPhoneno(rs.getString("phoneno"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setRoleName(rs.getString("roleName"));
			} 
			else 
			{
				userinfo.setUserid(-1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userinfo;
	}

	public List<Userinfo> listAllUserinfo() {
		List<Userinfo> ui = new ArrayList<Userinfo>();
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(selectAlluser)) {
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("userId");
				int roleId = rs.getInt("roleId");
				String name = rs.getString("name");
				String phoneno = rs.getString("phoneno");
				String password = rs.getString("password");
				String roleName = rs.getString("roleName");
				ui.add(new Userinfo(userId, roleId, name, phoneno, password,roleName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ui;
	}
	
	public Userinfo selectOneUser(String name,String password,int id) {
		Userinfo ui = null;
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(selectOneuser)) {
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String roleName = rs.getString("roleName");
				name = rs.getString("name");
				String phoneno = rs.getString("phoneno");
				password = rs.getString("password");
				int userId = rs.getInt("userId");
				int roleId = rs.getInt("roleId");
				ui = new Userinfo(userId, roleId, name, phoneno, password, roleName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ui;
	}
	
	public boolean deleteUserinfo(int id) {
		boolean rowdeleted=false;
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(deleteUser)) {
			ps.setInt(1, id);
			rowdeleted = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowdeleted;
	}
	
	public Userinfo selectUserById(int id) {
		Userinfo userinfo = null;
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(selectUserbyid)) {
			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				String name = rs.getString("name");
				String phoneno = rs.getString("phoneno");
				String password = rs.getString("password");
				String roleName = rs.getString("roleName");
				int roleId = Integer.parseInt(rs.getString("roleid"));
				userinfo = new Userinfo(id, roleId, name, phoneno, password,roleName);
				System.out.println("selectUserbyid userof one " +  userinfo);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return userinfo;
	}
	
	public boolean updateUserinfo(Userinfo userinfo) {
		boolean rowupdated = false;
		try (Connection connecion = getConnection(); PreparedStatement ps = connecion.prepareStatement(updateUser)) {
			ps.setString(1, userinfo.getName());
			ps.setString(2, userinfo.getPhoneno());
			ps.setString(3, userinfo.getPassword());
			ps.setInt(4, userinfo.getUserid());
			System.out.println(ps);
			rowupdated = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowupdated;
	}
}

