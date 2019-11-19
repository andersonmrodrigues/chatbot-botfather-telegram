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
public class PedidoItem {

    private Integer idPedidoItem;
    private Integer idPedido;
    private Integer idProduto;
    private Integer qtPedido;
    private BigDecimal vlPreco;
    private String dsObservacao;
    private Produto produto;
    private Pedido pedido;

    public Integer getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Integer idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQtPedido() {
        return qtPedido;
    }

    public void setQtPedido(Integer qtPedido) {
        this.qtPedido = qtPedido;
    }

    public BigDecimal getVlPreco() {
        return vlPreco;
    }

    public void setVlPreco(BigDecimal vlPreco) {
        this.vlPreco = vlPreco;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
