package cn.gaowenbonum1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC工具类:
 * 	* 加载驱动
 *  * 获得连接
 *  * 释放资源
 *  * 代码都重复.
 * 
 *
 */
public class JDBCUtils {
	private static DataSource dataSource = null;
	
	// 获得连接池:
	public static DataSource getDataSource(){
		try {
			Properties p = new Properties();
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			dataSource = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dataSource;
	}
	
	// 获得连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
