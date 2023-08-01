package com.projectOfAdmin.model;

public class Userinfo 
{
	private int userid;
	private int roleid;
	private String name;
	private String phoneno;
	private String password;
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Userinfo() {
		super();
	}
	
	@Override
	public String toString() {
		return "userinfo [userid=" + userid + ", roleid=" + roleid + ", name=" + name + ", phoneno=" + phoneno
				+ ", password=" + password + ", roleName=" + roleName + "]";
	}
	
	public Userinfo(String roleName) {
		super();
		this.roleName = roleName;
	}
	
	public Userinfo(int userid, int roleid, String name, String phoneno, String password, String roleName) {
		super();
		this.userid = userid;
		this.roleid = roleid;
		this.name = name;
		this.phoneno = phoneno;
		this.password = password;
		this.roleName = roleName;
	}
	public Userinfo( String name, String phoneno, String password,int userid) {
		super();
		this.userid = userid;
		this.name = name;
		this.phoneno = phoneno;
		this.password = password;
	}
	public Userinfo(int roleid, String name, String phoneno, String password) {
		super();
		this.roleid = roleid;
		this.name = name;
		this.phoneno = phoneno;
		this.password = password;
	}
}
