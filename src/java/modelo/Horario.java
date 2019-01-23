/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Time;

/**
 *
 * @author apolo
 */
public class Horario {
    private int cdHorario;
    private Time horario;

    public int getCdHorario() {
        return cdHorario;
    }

    public void setCdHorario(int cdHorario) {
        this.cdHorario = cdHorario;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }
    
    
}
