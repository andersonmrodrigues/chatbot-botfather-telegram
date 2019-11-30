/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author ANDER
 */
public class Pedido {

    private Integer idPedido;
    private Integer idCliente;
    private Date dtPedido;
    private Boolean fgFinalizado;
    private Boolean fgEntregue;
    private Cliente cliente;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public Boolean getFgFinalizado() {
        return fgFinalizado;
    }

    public void setFgFinalizado(Boolean fgFinalizado) {
        this.fgFinalizado = fgFinalizado;
    }

    public Boolean getFgEntregue() {
        return fgEntregue;
    }

    public void setFgEntregue(Boolean fgEntregue) {
        this.fgEntregue = fgEntregue;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
