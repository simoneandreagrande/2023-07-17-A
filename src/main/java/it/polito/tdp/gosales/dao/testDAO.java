package it.polito.tdp.gosales.dao;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.gosales.model.DailySale;
import it.polito.tdp.gosales.model.Products;
import it.polito.tdp.gosales.model.Retailers;

public class testDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GOsalesDAO dao = new GOsalesDAO();
		List<Retailers> listR = new ArrayList<>();
		List<Products> listP = new ArrayList<>();
		List<DailySale> listS = new ArrayList<>();
		
		
//		listR = dao.getAllRetailers();
//		System.out.println(listR.size());
//		
//		listP = dao.getAllProducts();
//		System.out.println(listP.size());
//		
//		listS = dao.getAllSales();
//		System.out.println(listS.size());
		
		
		
		Integer product1 = 109110;
        Integer product2 = 112110;
        int year = 2015;

        // Esegui il metodo calculateNumberOfDays
//        int numberOfDays = dao.calculateNumberOfDays(product1, product2, year);

//        System.out.println(numberOfDays);
        // Verifica il risultato atteso
        int expectedNumberOfDays = 10; // Assumi che il numero di giorni atteso sia 10
    
    
		
		
	}

}
