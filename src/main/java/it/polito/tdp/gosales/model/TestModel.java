//package it.polito.tdp.gosales.model;
//
//import org.jgrapht.Graph;
//import org.jgrapht.graph.DefaultWeightedEdge;
//
//public class TestModel {
//
//	public static void main(String[] args) {
//		// Crea un'istanza del tuo model
//        Model model = new Model();
//
//        // Esegui il metodo creaGrafo con dei valori di esempio
//        String colore = "red";
//        Integer anno = 2015;
//        model.creaGrafo(colore, anno);
//
//        // Ottieni il grafo creato dal model
//        Graph<Integer, DefaultWeightedEdge> graph = model.getGraph();
//
//        // Stampa i vertici del grafo
//        System.out.println("Vertici del grafo:");
//        for (Integer vertex : graph.vertexSet()) {
//            System.out.println(vertex);
//        }
//
//        // Stampa gli archi del grafo
//        System.out.println("Archi del grafo:");
//        for (DefaultWeightedEdge edge : graph.edgeSet()) {
//            Integer sourceVertex = graph.getEdgeSource(edge);
//            Integer targetVertex = graph.getEdgeTarget(edge);
//            double weight = graph.getEdgeWeight(edge);
//            System.out.println(sourceVertex + " -> " + targetVertex + " (peso: " + weight + ")");
//        }
//	}
//
//}
