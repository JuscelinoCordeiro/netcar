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
import modelo.Tarifa;
import modelo.Usuario;

/**
 *
 * @author apolo
 */
public class TarifaDAO {

    private Connection conexao;

    public TarifaDAO() throws ClassNotFoundException {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionaTarifa(Tarifa novaTarifa) {
        String sql = "insert into tarifa (cd_tpservico, cd_servico, preco) values (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, novaTarifa.getCdTpVeiculo());
            stmt.setInt(2, novaTarifa.getCdServico());
            stmt.setFloat(3, novaTarifa.getPreco());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarifa getTarifa(int cdServico, int cdTpVeiculo) {
        String sql = "select ta.*, tp.tipo, sv.servico "
                + " from tarifa as ta inner join servico as sv on ta.cd_servico = sv.cd_servico"
                + " inner join tipo_veiculo as tp on ta.cd_tpveiculo = tp.cd_tpveiculo "
                + " where ta.cd_servico = ? and ta.cd_tpveiculo = ?";
        Tarifa tarifa = new Tarifa();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdServico);
            stmt.setInt(2, cdTpVeiculo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tarifa.setCdServico(rs.getInt("cd_servico"));
                tarifa.setCdTpVeiculo(rs.getInt("cd_tpveiculo"));
                tarifa.setServico(rs.getString("servico"));
                tarifa.setTipoVeiculo(rs.getString("tipo"));
                tarifa.setPreco(rs.getFloat("preco"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarifa;
    }

    public List<Tarifa> getListaDeTarifa() {
        try {
            List<Tarifa> lista = new ArrayList<>();
            PreparedStatement stmt = this.conexao.prepareStatement("select ta.*, tp.tipo,sv.servico from tarifa as ta "
                    + "inner join tipo_veiculo as tp on tp.cd_tpveiculo = ta.cd_tpveiculo "
                    + "inner join servico as sv on sv.cd_servico = ta.cd_servico where sv.ativo = 1 order by cd_tpveiculo");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarifa tarifa = new Tarifa();

                tarifa.setCdServico(rs.getInt("cd_servico"));
                tarifa.setCdTpVeiculo(rs.getInt("cd_tpveiculo"));
                tarifa.setPreco(rs.getFloat("preco"));
                tarifa.setServico(rs.getString("servico"));
                tarifa.setTipoVeiculo(rs.getString("tipo"));
                lista.add(tarifa);
            }
            rs.close();
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraTarifa(Tarifa tarifa) {

        String sql = "update tarifa set preco = ? where cd_servico = ? and cd_tpveiculo = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setFloat(1, tarifa.getPreco());
            stmt.setInt(2, tarifa.getCdServico());
            stmt.setInt(3, tarifa.getCdTpVeiculo());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void removeTarifa(int cdServico, int cdTpVeiculo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("delete"
                    + "from tarifa where cd_servico = ? and cd_tpveiculo = ?");
            stmt.setInt(1, cdServico);
            stmt.setInt(2, cdTpVeiculo);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
