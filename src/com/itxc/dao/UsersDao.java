package com.itxc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.itxc.model.User;
import com.itxc.utils.C3p0Utils;

public class UsersDao {
	// 添加用户的操作 【注册操作】
	public boolean insert(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthday = sdf.format(user.getBirthday());
			String sql = "INSERT INTO users(id,name,password,email,birthday) " + "VALUES(" + user.getId() + ",'"
					+ user.getName() + "','" + user.getPassword() + "','" + user.getEmail() + "','" + birthday + "')";
			System.out.println(sql);// 自查sql
			int num = stmt.executeUpdate(sql);
			if (num >= 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//C3p0Utils.release(rs, stmt, conn);
		}
		return false;
	}

	// 查询所有的User对象
	public ArrayList<User> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// * 将数据库的表数据 按照 我们自己定义【数据结构】 存储在内存
		ArrayList<User> list = new ArrayList<User>();
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM users";// sql脚本
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {// 一次循环 取出一行 数据
				User user = new User();
				// 取出了一行数据存储到用户对象
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				// 将用户对象放在集合中
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//C3p0Utils.release(rs, stmt, conn);
		}
		return null;
	}

	// 根据id查找指定的user
	public User find(int id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM users WHERE id=" + id;
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	// 根据id查找指定的user
	public User findUserByNameAndPwd(String name,String pwd) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM user"
					+ " WHERE username='" + name+"' and PASSWORD='"+pwd+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				//user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
   
	// 删除用户
	public boolean delete(int id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "DELETE FROM users WHERE id=" + id;
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}

	// 修改用户
	public boolean update(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = C3p0Utils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthday = sdf.format(user.getBirthday());
			String sql = "UPDATE users set name='" + user.getName() + "',password='" + user.getPassword() + "',email='"
					+ user.getEmail() + "',birthday='" + birthday + "' WHERE id=" + user.getId();
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
}
