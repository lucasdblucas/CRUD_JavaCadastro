package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Factory.ConnectionFactory;
import Modelo.Usuario;

public class UsuarioDAO {
	
	private Connection connection;
	String id;
	String nome;
	String cpf;
	
	public UsuarioDAO() {
	    this.connection = new ConnectionFactory().getConnection();
	}
	
	public ArrayList listarTodos() {
        try {

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro ao recuperar lista de usuários");
            return null;
        }
    }
	
	 public void salvar(Usuario objUsuario) {
        try {
            String sql;
            if (String.valueOf(objUsuario.getId()).isEmpty()) {
                sql = "INSERT INTO usuario(nome,cpf) VALUES(?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, objUsuario.getNome());
                stmt.setString(2, objUsuario.getCpf());
                
                stmt.execute();
                stmt.close();

            } else {
                sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE usuario.id = ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(5, objUsuario.getId());
                stmt.setString(1, objUsuario.getNome());
                stmt.setString(2, objUsuario.getCpf());
                
                stmt.execute();
                stmt.close();

            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

	public void deletar(Usuario objUsuario) {
        try {
            String sql;
            if (!String.valueOf(objUsuario.getId()).isEmpty()) {
                sql = "DELETE FROM usuario WHERE usuario.id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, objUsuario.getId());
                stmt.execute();
                stmt.close();
            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
	public ArrayList buscar(Usuario objUsuario) {
        try {
            String sql = "";
            if (!objUsuario.getNome().isEmpty()) {
                sql = "SELECT * FROM usuario WHERE nome LIKE '%" + objUsuario.getNome() + "%' ";

            } else if (!objUsuario.getCpf().isEmpty()) {
                sql = "SELECT * FROM usuario WHERE cpf LIKE '%" + objUsuario.getCpf() + "%' ";
            }
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
	}
}
