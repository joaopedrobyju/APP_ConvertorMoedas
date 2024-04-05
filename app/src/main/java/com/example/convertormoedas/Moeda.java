package com.example.convertormoedas;

public class Moeda {
    private double valorReais;

    public Moeda(double valorReais) {
        this.valorReais = valorReais;
    }

    public double converterMoedaDolar(){

        double valorMoeda = 4.99;

        double conversaoDolar = valorReais / valorMoeda;

        return conversaoDolar;
    }

    public double converterMoedaEuro(){

        double valorMoeda = 5.38;

        double conversaoEuro = valorReais / valorMoeda;

        return conversaoEuro;
    }

}
