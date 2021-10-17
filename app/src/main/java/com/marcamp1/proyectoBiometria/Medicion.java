package com.marcamp1.proyectoBiometria;

public class Medicion {

    private Integer valorCO2; // en ppm
    private Integer valorTemperatura; // en ºC
    private String fecha; // dia y hora

    // constructor vacío para poder utilizar el POJO
    public Medicion(){};

    // constructor
    public Medicion(Integer valorCO2_, Integer valorTemperatura_, String fecha_) {
        this.valorCO2 = valorCO2_;
        this.valorTemperatura = valorTemperatura_;
        this.fecha = fecha_;
    }

    // métodos GET --------------------------------------------------------------------

    public Integer getValorCO2() {
        return valorCO2;
    }

    public Integer getValorTemperatura() {
        return valorTemperatura;
    }

    public String getFecha() {
        return fecha;
    }
}
