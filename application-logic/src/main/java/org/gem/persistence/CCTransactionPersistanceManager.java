package org.gem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CCTransactionPersistanceManager {

	private static final String DB_PATH = "C:\\Dev\\clownfish\\application-logic\\expensedb\\expensedb";
	private static String expenseDbUrl = null; // "jdbc:derby:"+DB_PATH+";";
												// //create=true";

	public CCTransactionPersistanceManager() {
		loadDrivers();
		setJdbcUrl();
	}

	private void setJdbcUrl() {
		String dbPath = System.getProperty("derby.system.home", DB_PATH);
		expenseDbUrl = "jdbc:derby:" + dbPath + ";"; // create=true";
	}

	private void loadDrivers() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List executeQuery(String query) {

		// TODO: determine how to create a connection to the expensedb derby
		// database.
		// application is likely missing the path for the expensedb database.
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClownFishDS");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery(query);
		q.executeUpdate();
		List<Object> myResults  = q.getResultList();
		return myResults;
		
		/*ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(expenseDbUrl);
				Statement sqlStmt = conn.createStatement();) {

		
				sqlStmt.execute(query);
				rs = sqlStmt.getResultSet();
				if(rs!=null){
					while (rs.next()) {
						showRowValues(rs);
					}
					rs.close();
				}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
//		return rs;

	}

	private void showRowValues(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
	
//		String columns = "";
//		String name =null;
//		for (int x = 1; x <= md.getColumnCount(); x++) {
//			 name =md.getColumnName(x);
//		}
//		columns+=columns+"\t|\t"+name;
//		System.out.println(columns);
		
		String record = ">"; 
		for (int x = 1; x <= md.getColumnCount(); x++) {
			int type = md.getColumnType(x);
			
			String result = null;
			
			switch (type) {
			case Types.VARCHAR:
				result = rs.getString(x);
				break;
			case Types.INTEGER:
				result =Integer.toString(rs.getInt(x));
				break;
			case Types.DATE:
				result = (rs.getDate(x)==null)?"":rs.getDate(x).toString();
				break;
			default:
				System.out.println(rs.getObject(x));
				break;
			}

			record=record+result+"\t|\t";
		}
		
		System.out.println(record);
	}
}
