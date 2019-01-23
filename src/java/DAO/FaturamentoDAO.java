/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modelo.Agendamento;
import modelo.Faturamento;

/**
 *
 * @author apolo
 */
public class FaturamentoDAO {

    private Connection conexao;

    public FaturamentoDAO() throws ClassNotFoundException {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public List<Faturamento> getFaturamento() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy/MM/dd");
        String dataBusca = dataFormatada.format(d);
        Faturamento busca = new Faturamento();

//        String sql = "select * from faturamento where data = ?";
        String sql = "SELECT data, SUM(faturamento) as faturamento FROM faturamento WHERE data = ? ";
        List<Faturamento> lista = new ArrayList<>();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, dataBusca);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Faturamento fat = new Faturamento();
//                fat.setCdFaturamento(rs.getInt(1));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate(2));
                fat.setData(data);
                fat.setFaturamento(rs.getFloat(3));
                lista.add(fat);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        try {
//            PreparedStatement stmt;
//            ResultSet rs;
//            stmt = conexao.prepareStatement(sql);
//            stmt.setString(1, dataBusca);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                busca.setCdFaturamento(rs.getInt("cd_data"));
//                busca.setFaturamento(rs.getInt("faturamento"));
//
//                Calendar data2 = Calendar.getInstance();
//                data2.setTime(rs.getDate("data"));
//                busca.setData(data2);
//            }
//            stmt.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return busca;
    }

    public List<Faturamento> getFaturamento30Dias() {
        String sql = "select * from faturamento limit 30";
        List<Faturamento> lista = new ArrayList<>();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Faturamento fat = new Faturamento();
                fat.setCdFaturamento(rs.getInt(1));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate(2));
                fat.setData(data);
                fat.setFaturamento(rs.getFloat(3));
                lista.add(fat);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Faturamento> getFaturamentoMensal() {
        String sql = "select * from faturamento limit 30";
        List<Faturamento> lista = new ArrayList<>();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Faturamento fat = new Faturamento();
                fat.setCdFaturamento(rs.getInt(1));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate(2));
                fat.setData(data);
                fat.setFaturamento(rs.getFloat(3));
                lista.add(fat);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void faturamento(Agendamento ag) {

        String sql = "select * from faturamento where data = ?";
        String sql2 = "update faturamento set faturamento = ? where cd_faturamento = ?";
        String sql3 = "insert into faturamento (data, faturamento) values ( ?, ?)";
        Faturamento faturamento = new Faturamento();
        float tarifa, fatura, total;
        int cdFaturamento;
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new Date(ag.getData().getTimeInMillis()));
            rs = stmt.executeQuery();
            if (rs.next()) {
                fatura = rs.getFloat("faturamento");
                cdFaturamento = rs.getInt("cd_faturamento");
                tarifa = ag.getPreco();
                total = fatura + tarifa;
                stmt = conexao.prepareStatement(sql2);
                stmt.setFloat(1, total);
                stmt.setInt(2, cdFaturamento);
                stmt.execute();
            } else {
                stmt = conexao.prepareStatement(sql3);
                stmt.setDate(1, new Date(ag.getData().getTimeInMillis()));
                stmt.setFloat(2, ag.getPreco());
                stmt.execute();
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void faturamento2(int cdTpVeiculo, int cdServico) {
        String sql = "select preco from tarifa where cd_tpveiculo = ? and cd_servico = ?";
        String sql2 = "select faturamento from faturamento where cd_faturamento = ?";
        String sql3 = "select faturamento from faturamento where cd_faturamento = ?";

        Faturamento busca = new Faturamento();
        float tarifa, fatura, total;
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdTpVeiculo);
            stmt.setInt(2, cdServico);
            rs = stmt.executeQuery();
            if (rs.next()) {
                tarifa = rs.getFloat("preco");
            } else {
                tarifa = 0;
            }

            stmt = conexao.prepareStatement(sql2);
            java.util.Date d = new java.util.Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String data = dataFormatada.format(d);
            stmt.setString(1, data);
            rs = stmt.executeQuery();
            if (rs.next()) {
                fatura = rs.getInt("faturamento");
            } else {
                fatura = 0;
            }

            total = fatura + tarifa;
            stmt = conexao.prepareStatement(sql3);
            stmt.setString(1, data);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Faturamento consulta(int codigo) {
        String sql = "select * from faturamento where cd_data = ?";
        Faturamento busca = new Faturamento();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                busca.setCdFaturamento(rs.getInt("cd_data"));
                busca.setFaturamento(rs.getInt("faturamento"));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                busca.setData(data);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return busca;
    }

}//fim
