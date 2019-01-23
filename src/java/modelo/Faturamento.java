/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Calendar;

/**
 *
 * @author apolo
 */
public class Faturamento {
    private int cdFaturamento;
    private Calendar data;
    private float faturamento;

    public int getCdFaturamento() {
        return cdFaturamento;
    }

    public void setCdFaturamento(int cdFaturamento) {
        this.cdFaturamento = cdFaturamento;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public float getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(float faturamento) {
        this.faturamento = faturamento;
    }
    
    
}
