/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anderson e Jean
 */
public class Categoria implements Comparable {

    private Integer idCategoria;
    private String dsCategoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public void setDsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
    }

    @Override
    public String toString() {
        return dsCategoria;
    }

    /**
     * Para ordenar por ordem alfabética
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
