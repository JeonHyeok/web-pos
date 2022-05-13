package com.web.pos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.web.pos.entity.UserInfo;

public class UserInfoJdbcDao implements UserInfoInterfacce {
	private String driver;
	private String url;
	private String userName;
	private String password;
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public UserInfoJdbcDao (String driver, String url, String userName, String password) {
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, userName, password);
	}
	
	private void disconnect() throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
			rs = null;
		}
		if (stmt != null && !stmt.isClosed()) {
			stmt.close();
			stmt = null;
		}
		if (conn != null && !conn.isClosed()) {
			conn.close();
			conn = null;
		}
	}

	@Override
	public UserInfo getUserInfo(String id, String pwd) {
		UserInfo ui = null;
		
		String sql = "SELECT * FROM userinfo WHERE user_id = ? AND user_pw = ?";
		
		try {
			connect();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				ui = new UserInfo();
				ui.setUser_id(rs.getString("user_id"));
				ui.setUser_pw(rs.getString("user_pw"));
				ui.setUser_name(rs.getString("user_name"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ui;
	}

	@Override
	public int insertUserInfo(String id, String pwd, String name) {
		int result = 0;
		
		String sql = "INSERT INTO userinfo (user_id, user_pw, user_name) VALUES (?, ?, ?)";
		
		try {
			connect();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			stmt.setString(3, name);
			
			result = stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int deleteUserInfo(String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

}
