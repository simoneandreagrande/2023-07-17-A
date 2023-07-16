package it.polito.tdp.gosales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.gosales.model.DailySale;
import it.polito.tdp.gosales.model.Products;
import it.polito.tdp.gosales.model.Retailers;

public class GOsalesDAO {
	
	
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
	
	
	
	public List<String> getColori(){
		String query = "SELECT DISTINCT Product_color from go_products";
		List<String> result = new ArrayList<String>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("Product_color"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public List<Products> getVertici(String colore){
		String query = "SELECT * from go_products WHERE Product_color = ?";
		List<Products> result = new ArrayList<Products>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, colore);
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
	
	
	public List<Integer> getRetailersFromProduct(Products p, Integer anno){
		String query = "SELECT DISTINCT s.Retailer_code "
				+ "FROM go_products p, go_daily_sales s "
				+ "WHERE p.Product_number = s.Product_number AND p.Product_Number = ? "
				+ "AND YEAR(s.Date) = ?";
		List<Integer> result = new ArrayList<Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, p.getNumber());
			st.setInt(2, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getInt("Retailer_code"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public void countDaysSalesInCommonFromRetailer(Products p1, Products p2, Integer anno, Integer retID, Set<LocalDate> events) {
		String query = "SELECT DISTINCT s1.Date "
				+ "FROM go_daily_sales s1, go_daily_sales s2 "
				+ "WHERE s1.Date = s2.Date  AND s1.Retailer_code = s2.Retailer_code "
				+ "AND s1.Product_Number = ? AND s2.Product_Number = ? "
				+ "AND YEAR(s1.Date) = ? AND s1.Retailer_code = ?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, p1.getNumber());
			st.setInt(2, p2.getNumber());
			st.setInt(3, anno);
			st.setInt(4, retID);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				events.add(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
			}
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public Integer countDaysSalesInCommonFromRetailerv2(Products p1, Products p2, Integer anno) {
		String query = "SELECT COUNT(DISTINCT s1.Date) as N "
				+ "FROM go_daily_sales s1, go_daily_sales s2 "
				+ "WHERE s1.Date = s2.Date  AND s1.Retailer_code = s2.Retailer_code "
				+ "AND s1.Product_Number = ? AND s2.Product_Number = ? "
				+ "AND YEAR(s1.Date) = ? ";
		Integer count = 0;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, p1.getNumber());
			st.setInt(2, p2.getNumber());
			st.setInt(3, anno);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				count = rs.getInt("N");
			}
			conn.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
}
