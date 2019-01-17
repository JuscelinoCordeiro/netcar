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

/**
 *
 * @author apolo
 */
public class AgendamentoDAO {

    private Connection conexao;

    public AgendamentoDAO() throws ClassNotFoundException {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void agendar(Agendamento dados) {
        String sql = "insert into agendamento (cd_usuario, cd_tpveiculo, placa, cd_servico, data, horario) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, dados.getCdUsuario());
            stmt.setInt(2, dados.getCdTpVeiculo());
            stmt.setString(3, dados.getPlaca());
            stmt.setInt(4, dados.getCdServico());
            stmt.setDate(5, new Date(dados.getData().getTimeInMillis()));
            stmt.setTime(6, dados.getHorario());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Agendamento getAgendamento(int codigo) {
        String sql = "select ag.*, user.nome, tp.*, sv.*, ta.preco "
                    + "from agendamento as ag inner join usuario as user on ag.cd_usuario = user.cd_usuario "
                    + "inner join tipo_veiculo as tp on tp.cd_tpveiculo = ag.cd_tpveiculo "
                    + "inner join servico as sv on ag.cd_servico = sv.cd_servico "
                    + "inner join tarifa as ta on tp.cd_tpveiculo = ta.cd_tpveiculo and sv.cd_servico = ta.cd_servico "
                    + "where cd_agendamento = ?";

        Agendamento busca = new Agendamento();

        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                busca.setCdAgendamento(rs.getInt("cd_agendamento"));
                busca.setCdServico(rs.getInt("cd_servico"));
                busca.setCdTpVeiculo(rs.getInt("cd_tpveiculo"));
                busca.setPlaca(rs.getString("placa"));
                busca.setCdUsuario(rs.getInt("cd_usuario"));
                busca.setHorario(rs.getTime("horario"));
                busca.setPreco(rs.getFloat("preco"));
                busca.setServico(rs.getString("servico"));
                busca.setTipoVeiculo(rs.getString("tipo"));
                busca.setUsuario(rs.getString("nome"));

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

    public List<Agendamento> getListaDeAgendamentos() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy/MM/dd");
            String dataBusca = dataFormatada.format(d);
            
            List<Agendamento> lista = new ArrayList<>();
            PreparedStatement stmt = this.conexao.prepareStatement("select ag.*, user.nome, tp.*, sv.*, ta.preco "
                    + "from agendamento as ag inner join usuario as user on ag.cd_usuario = user.cd_usuario "
                    + "inner join tipo_veiculo as tp on tp.cd_tpveiculo = ag.cd_tpveiculo "
                    + "inner join servico as sv on ag.cd_servico = sv.cd_servico "
                    + "inner join tarifa as ta on tp.cd_tpveiculo = ta.cd_tpveiculo and sv.cd_servico = ta.cd_servico "
                    + "where data = ? and status = 0 order by horario desc");
            stmt.setString(1, dataBusca);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento busca = new Agendamento();

                busca.setCdAgendamento(rs.getInt("cd_agendamento"));
                busca.setCdServico(rs.getInt("cd_servico"));
                busca.setCdTpVeiculo(rs.getInt("cd_tpveiculo"));
                busca.setPlaca(rs.getString("placa"));
                busca.setCdUsuario(rs.getInt("cd_usuario"));
                busca.setHorario(rs.getTime("horario"));
                busca.setStatus(rs.getInt("status"));
                busca.setUsuario(rs.getString("nome"));
                busca.setTipoVeiculo(rs.getString("tipo"));
                busca.setServico(rs.getString("servico"));
                busca.setPreco(rs.getFloat("preco"));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                busca.setData(data);

                lista.add(busca);
            }
            rs.close();
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Agendamento> getTodosAgendamentos() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy/MM/dd");
            String dataBusca = dataFormatada.format(d);
            
            List<Agendamento> lista = new ArrayList<>();
            PreparedStatement stmt = this.conexao.prepareStatement("select ag.*, user.nome, tp.*, sv.*, ta.preco "
                    + "from agendamento as ag inner join usuario as user on ag.cd_usuario = user.cd_usuario "
                    + "inner join tipo_veiculo as tp on tp.cd_tpveiculo = ag.cd_tpveiculo "
                    + "inner join servico as sv on ag.cd_servico = sv.cd_servico "
                    + "inner join tarifa as ta on tp.cd_tpveiculo = ta.cd_tpveiculo and sv.cd_servico = ta.cd_servico "
                    + "order by ag.data desc");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento busca = new Agendamento();

                busca.setCdAgendamento(rs.getInt("cd_agendamento"));
                busca.setCdServico(rs.getInt("cd_servico"));
                busca.setCdTpVeiculo(rs.getInt("cd_tpveiculo"));
                busca.setPlaca(rs.getString("placa"));
                busca.setCdUsuario(rs.getInt("cd_usuario"));
                busca.setHorario(rs.getTime("horario"));
                busca.setStatus(rs.getInt("status"));
                busca.setUsuario(rs.getString("nome"));
                busca.setTipoVeiculo(rs.getString("tipo"));
                busca.setServico(rs.getString("servico"));
                busca.setPreco(rs.getFloat("preco"));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                busca.setData(data);

                lista.add(busca);
            }
            rs.close();
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraAgendamento(Agendamento agendamento) {

        String sql = "update agendamento set cd_usuario = ?, cd_tpveiculo = ?, placa = ?,"
                + "cd_servico = ?, data = ?, horario = ? where cd_agendamento = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, agendamento.getCdUsuario());
            stmt.setInt(2, agendamento.getCdTpVeiculo());
            stmt.setString(3, agendamento.getPlaca());
            stmt.setInt(4, agendamento.getCdServico());
            stmt.setDate(5, new Date(agendamento.getData().getTimeInMillis()));
            stmt.setTime(6, agendamento.getHorario());
            stmt.setInt(7, agendamento.getCdAgendamento());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void finalizaAgendamento(int codigo) {
        String sql = "update agendamento set status = 1 where cd_agendamento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAgendamento(int codigo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(
                    "delete from agendamento where cd_agendamento = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}//agendamento DAO
