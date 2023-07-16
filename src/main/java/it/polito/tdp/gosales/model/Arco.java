package it.polito.tdp.gosales.model;

import java.util.Objects;

public class Arco implements Comparable<Arco>{
	
	private Products v1;
	private Products v2;
	private int peso;
	
	public Arco(Products v1, Products v2, int peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}

	public Products getV1() {
		return v1;
	}

	public Products getV2() {
		return v2;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(peso, v1, v2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return peso == other.peso && Objects.equals(v1, other.v1) && Objects.equals(v2, other.v2);
	}

	@Override
	public int compareTo(Arco o) {
		return -this.peso + o.peso;
	}

	@Override
	public String toString() {
		return  "Arco " + v1 + "<->" + v2 + ", peso =" + peso;
	}
	

}
