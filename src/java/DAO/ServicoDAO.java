/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Servico;
import modelo.Usuario;

/**
 *
 * @author apolo
 */
public class ServicoDAO {

    private Connection conexao;

    public ServicoDAO() throws ClassNotFoundException {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionaServico(Servico servico) {
        String sql = "insert into servico (servico) values ( ? )";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, servico.getServico());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Servico getServico(int codigo) {
        try {
            PreparedStatement stmt = this.conexao.prepareStatement("select * from servico where cd_servico = ?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            Servico servico = new Servico();
            while (rs.next()) {
                servico.setCdServico(rs.getInt("cd_servico"));
                servico.setServico(rs.getString("servico"));
            }
            rs.close();
            stmt.close();
            return servico;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Servico> getListaDeServico() {
        try {
            List<Servico> lista = new ArrayList<>();
            PreparedStatement stmt = this.conexao.prepareStatement("select * from servico");
//            PreparedStatement stmt = this.conexao.prepareStatement("select * from servico where ativo = 1");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();

                servico.setCdServico(rs.getInt("cd_servico"));
                servico.setServico(rs.getString("servico"));
                lista.add(servico);
            }
            rs.close();
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraServico(Servico servico) {
        String sql = "update servico set servico = ? where cd_servico = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, servico.getServico());
            stmt.setInt(2, servico.getCdServico());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desativaServico(int codigo) {
        String sql = "update servico set ativo = ? where cd_servico = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, 0);
            stmt.setInt(2, codigo);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeServico(int codigo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("delete"
                    + "from servico where cd_servico = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
