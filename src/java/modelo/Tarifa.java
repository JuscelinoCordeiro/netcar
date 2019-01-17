/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author apolo
 */
public class Tarifa {
    private int cdTpVeiculo;
    private int cdServico;
    private String servico;
    private String tipoVeiculo;
    private float preco;

    public int getCdTpVeiculo() {
        return cdTpVeiculo;
    }

    public void setCdTpVeiculo(int cdTpVeiculo) {
        this.cdTpVeiculo = cdTpVeiculo;
    }

    public int getCdServico() {
        return cdServico;
    }

    public void setCdServico(int cdServico) {
        this.cdServico = cdServico;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    
}
