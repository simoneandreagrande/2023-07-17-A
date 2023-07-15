package it.polito.tdp.gosales.model;

import java.util.Objects;

public class Arco {

    private Integer source;
    private Integer target;

    private Integer peso;

    public Arco(Integer source, Integer target, Integer peso) {
        this.source = source;
        this.target = target;
        this.peso = peso;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arco arco = (Arco) o;
        return Objects.equals(source, arco.source) && Objects.equals(target, arco.target) && Objects.equals(peso, arco.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, peso);
    }

    @Override
    public String toString() {
        return "Arco da " + source  + " a " + target + ", peso =  " + peso;
    }
}
