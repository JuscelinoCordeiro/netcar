/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author apolo
 */
public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() throws ClassNotFoundException {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionaUsuario(Usuario usuario) {
        String sql = "insert into usuario (nome, endereco, celular, fixo, idt) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getCelular());
            stmt.setString(4, usuario.getFixo());
            stmt.setInt(5, usuario.getIdt());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getUsuario(int codigo) {
        String sql = "select * from usuario where cd_usuario = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conexao.prepareStatement(sql);
//            stmt.clearParameters();
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario.setCdUsuario(rs.getInt("cd_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setFixo(rs.getString("fixo"));
                usuario.setIdt(rs.getInt("idt"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public List<Usuario> getListaDeUsuario() {
        try {
            List<Usuario> lista = new ArrayList<>();
            PreparedStatement stmt = this.conexao.prepareStatement("SELECT * FROM usuario order by nome asc");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setCdUsuario(rs.getInt("cd_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setFixo(rs.getString("fixo"));
                usuario.setIdt(rs.getInt("idt"));

                lista.add(usuario);
            }
            rs.close();
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraUsuario(Usuario usuario) {

        String sql = "update usuario set nome = ?, endereco = ?,"
                + "celular = ?, fixo = ?, idt = ? where cd_usuario = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getCelular());
            stmt.setString(4, usuario.getFixo());
            stmt.setInt(5, usuario.getIdt());
            stmt.setLong(6, usuario.getCdUsuario());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void removeUsuario(int codigo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("delete"
                    + " from usuario where cd_usuario = ?");
            stmt.setInt(1, codigo);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

//    public boolean existeUsuario(Usuario usuario) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
