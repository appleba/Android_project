package com.itxc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.itxc.model.Products;
import com.itxc.utils.C3p0Utils;

public class ProductsDao extends BaseDao{

	// 查询所有的User对象
	public ArrayList<Products> findAll() {
		// * 将数据库的表数据 按照 我们自己定义【数据结构】 存储在内存
		try {
			ArrayList<Products> list = new ArrayList<Products>();
			String sql = "SELECT * FROM Products";// sql脚本
			list = (ArrayList<Products>)this.query(sql, new BeanListHandler<>(Products.class));
			return list;
		} catch (SQLException e) {
			//e.printStackTrace();
			//记录异常日志 日志框架
			return null;
		}
	}
    
	public static void main(String[] args) {
		//alt + shift + L
	    List<Products> findAll = new ProductsDao().findAll();
		//System.out.println(findAll);
	}
}
