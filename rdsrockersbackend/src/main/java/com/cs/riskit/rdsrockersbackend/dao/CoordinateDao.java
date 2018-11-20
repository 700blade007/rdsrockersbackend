package com.cs.riskit.rdsrockersbackend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.stereotype.Repository;

import com.cs.riskit.rdsrockersbackend.model.Axis;
import com.cs.riskit.rdsrockersbackend.model.Coordinate;

@Repository
public class CoordinateDao {

	public List<Coordinate> getCoordinates(Axis a) {
		JdbcDataSource h2Datasource = new JdbcDataSource();
		h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
		h2Datasource.setUser("sa");

		DataSource ds = h2Datasource;

		List<Coordinate> coordinateList = new ArrayList<Coordinate>();

		try {
			Connection con = ds.getConnection();
			//String sql = "SELECT "+a.getXaxis()+", "+a.getYaxis()+" FROM TEST1";
			String sql = "SELECT "+a.getXaxis()+", "+a.getFunc()+"("+a.getYaxis()+") FROM TEST1 group by "+a.getXaxis()+";";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coordinateList.add(new Coordinate(rs.getString(1), rs.getLong(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coordinateList;
	}

	public List<String> getXColumnNames() {
		JdbcDataSource h2Datasource = new JdbcDataSource();
		h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
		h2Datasource.setUser("sa");

		DataSource ds = h2Datasource;

		List<String> columnList = new ArrayList<String>();

		try {
			Connection con = ds.getConnection();
			String sql = "SELECT  c.column_name,c.type_name FROM INFORMATION_SCHEMA.COLUMNS c WHERE  c.table_name = 'TEST1' and c.type_name in ('VARCHAR','DATE');";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				columnList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnList;
	}
	
	public List<String> getYColumnNames() {
		JdbcDataSource h2Datasource = new JdbcDataSource();
		h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
		h2Datasource.setUser("sa");

		DataSource ds = h2Datasource;

		List<String> columnList = new ArrayList<String>();

		try {
			Connection con = ds.getConnection();
			String sql = "SELECT  c.column_name,c.type_name FROM INFORMATION_SCHEMA.COLUMNS c WHERE  c.table_name = 'TEST1' and c.type_name not in ('VARCHAR','DATE');";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				columnList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnList;
	}

}
