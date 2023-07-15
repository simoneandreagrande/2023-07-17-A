package it.polito.tdp.gosales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.DailySale;
import it.polito.tdp.gosales.model.Products;
import it.polito.tdp.gosales.model.Retailers;

public class GOsalesDAO {

	public List<Products> getNodes(String color) {

		String query = "select * from go_products where Product_color = ? ";

		List<Products> result = new ArrayList<Products>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,color);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Products(rs.getInt("Product_number"),
						rs.getString("Product_line"),
						rs.getString("Product_type"),
						rs.getString("Product"),
						rs.getString("Product_brand"),
						rs.getString("Product_color"),
						rs.getDouble("Unit_cost"),
						rs.getDouble("Unit_price")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Arco> getEdges(String type, Integer year){

		String query = "select " +
				"  gp1.Product_number as p1, " +
				"  gp2.Product_number as p2, " +
				"  count(distinct gds2.Date) as n " +
				"from " +
				"  go_products gp1, " +
				"  go_products gp2, " +
				"  go_daily_sales gds1, " +
				"  go_daily_sales gds2 " +
				"where " +
				"  gp1.Product_color = ? " +
				"  and gp1.Product_color = gp2.Product_color " +
				"  and year(gds1.Date )= ? " +
				"  and gds1.Date = gds2.Date " +
				"  and gds1.Retailer_code = gds2.Retailer_code " +
				"  and gds1.Product_number = gp1.Product_number " +
				"  and gds2.Product_number = gp2.Product_number " +
				"  and gp1.Product_number > gp2.Product_number " +
				"group by " +
				"  gp1.Product_number , " +
				"  gp2.Product_number " +
				"order by n desc ";

		List<Arco> result = new ArrayList<Arco>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,type);
			st.setInt(2,year);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add( new Arco(rs.getInt("p1"), rs.getInt("p2"), rs.getInt("n")) );
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	
	/**
	 * Metodo per leggere la lista di tutti i rivenditori dal database
	 * @return
	 */

	public List<Retailers> getAllRetailers(){
		String query = "SELECT * from go_retailers";
		List<Retailers> result = new ArrayList<Retailers>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Retailers(rs.getInt("Retailer_code"), 
						rs.getString("Retailer_name"),
						rs.getString("Type"), 
						rs.getString("Country")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	
	/**
	 * Metodo per leggere la lista di tutti i prodotti dal database
	 * @return
	 */
	public List<Products> getAllProducts(){
		String query = "SELECT * from go_products";
		List<Products> result = new ArrayList<Products>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Products(rs.getInt("Product_number"), 
						rs.getString("Product_line"), 
						rs.getString("Product_type"), 
						rs.getString("Product"), 
						rs.getString("Product_brand"), 
						rs.getString("Product_color"),
						rs.getDouble("Unit_cost"), 
						rs.getDouble("Unit_price")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}

	
	/**
	 * Metodo per leggere la lista di tutte le vendite nel database
	 * @return
	 */
	public List<DailySale> getAllSales(){
		String query = "SELECT * from go_daily_sales";
		List<DailySale> result = new ArrayList<DailySale>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new DailySale(rs.getInt("retailer_code"),
				rs.getInt("product_number"),
				rs.getInt("order_method_code"),
				rs.getTimestamp("date").toLocalDateTime().toLocalDate(),
				rs.getInt("quantity"),
				rs.getDouble("unit_price"),
				rs.getDouble("unit_sale_price")  ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<String> getAllColors() {
		String query = "select distinct(Product_color) as c from go_products";

		List<String> result = new ArrayList<String>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("c"));
			}
			conn.close();
			Collections.sort(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Integer> getAllYears() {
		String query = "select distinct (year(go_daily_sales.date)) as n from go_daily_sales";

		List<Integer> result = new ArrayList<Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getInt("n"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}
}
