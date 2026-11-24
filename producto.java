package com.gescomin.modelo;

import java.math.BigDecimal;

public class Producto {

    private int idProducto;
    private String codigoSku;
    private String nombreProducto;
    private BigDecimal precioUnitario;
    private int stockActual;
    private boolean activo;

    public Producto() {
    }

    public Producto(String codigoSku, String nombreProducto, BigDecimal precioUnitario, int stockActual, boolean activo) {
        this.codigoSku = codigoSku;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.stockActual = stockActual;
        this.activo = activo;
    }

    public Producto(int idProducto, String codigoSku, String nombreProducto, BigDecimal precioUnitario, int stockActual, boolean activo) {
        this.idProducto = idProducto;
        this.codigoSku = codigoSku;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.stockActual = stockActual;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoSku() {
        return codigoSku;
    }

    public void setCodigoSku(String codigoSku) {
        this.codigoSku = codigoSku;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", codigoSku='" + codigoSku + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stockActual=" + stockActual +
                ", activo=" + activo +
                '}';
    }
}
