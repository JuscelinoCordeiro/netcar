/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author apolo
 */
public class Agendamento {
    private int cdAgendamento;
    private int cdUsuario;
    private int cdTpVeiculo;
    private int cdServico;
    private Calendar data;
    private Time horario;
    private int status;
    private String usuario;
    private String tipoVeiculo;
    private String servico;
    private float preco;
    private String placa;
    
    
    

    public int getCdAgendamento() {
        return cdAgendamento;
    }

    public void setCdAgendamento(int cdAgendamento) {
        this.cdAgendamento = cdAgendamento;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public String getDataParaBD() {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy/MM/dd");
        String dataBD = dataFormatada.format(this.data.getTime());
        return dataBD;
    }
    
    public String getDataParaView() {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        String dataView = dataFormatada.format(this.data.getTime());
        return dataView;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
