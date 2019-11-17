/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author ANDER
 */
public class Produto {

    private Integer idProduto;
    private Integer idCategoria;
    private String dsProduto;
    private BigDecimal vlPreco;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public BigDecimal getVlPreco() {
        return vlPreco;
    }

    public void setVlPreco(BigDecimal vlPreco) {
        this.vlPreco = vlPreco;
    }

}
