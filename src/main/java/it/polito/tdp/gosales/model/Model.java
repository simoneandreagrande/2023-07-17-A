package it.polito.tdp.gosales.model;

import it.polito.tdp.gosales.dao.GOsalesDAO;
import javafx.scene.shape.Arc;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Model {

	private GOsalesDAO dao;
	private SimpleWeightedGraph<Products, DefaultEdge> graph;
	private HashMap<Integer,Products> idMap;
	private List<Arco> allArchi;

	public Model() {

		this.dao = new GOsalesDAO();
		this.graph = new SimpleWeightedGraph(DefaultEdge.class);
		this.idMap = new HashMap<>();

	}

	public void buildGraph (String brand, Integer year) {
		this.graph = new SimpleWeightedGraph(DefaultEdge.class);

		List<Products> nodes = new ArrayList<Products>(this.dao.getNodes(brand));
		List<Arco> edges = new ArrayList<Arco>(this.dao.getEdges(brand,year));

		System.out.println(nodes.size()+"");
		System.out.println(edges.size()+"");
		this.allArchi = new ArrayList<Arco>(edges);

		for (Products p : nodes)
			this.idMap.put(p.getNumber(), p);

		Graphs.addAllVertices(this.graph,nodes);
		for (Arco c : edges) {
			System.out.println(c + "");
			Graphs.addEdge(this.graph, this.idMap.get(c.getSource()), this.idMap.get(c.getTarget()), c.getPeso());
		}
	}

	public List<Integer> repeatedProducts(){
		List<Arco> topArchi = getTopArchi();

		List<Integer> ids = new ArrayList<>();
		for (Arco a : topArchi) {
			ids.add(a.getSource());
			ids.add(a.getTarget());
		}

		List<Integer> found = new ArrayList<>();
		List<Integer> remaining = new ArrayList<>(ids);

		for (Integer i : ids) {
			remaining.remove(i);

			if (remaining.contains(i) && !found.contains(i)) {
				found.add(i);
				remaining.remove(i);
			}
			else remaining.remove(i);
		}

		return found;
	}

	public List<Arco> getTopArchi() {
		return this.allArchi.subList(0,3);
	}
	public List<Integer> getAllYears(){
		return this.dao.getAllYears();
	}

	public Integer getNumNodi(){
		return this.graph.vertexSet().size();
	}

	public Integer getNumArchi(){
		return this.graph.edgeSet().size();
	}

	public List<String> getAllColors(){ return this.dao.getAllColors(); }


}
