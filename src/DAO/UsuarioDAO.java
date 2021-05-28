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
                    rs.getString("email"),
                    rs.getString("telefone")
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

	
//	public ArrayList buscar(Usuario objUsuario) {
//        try {
//            String sql = "";
//            if (!objUsuario.getNome().isEmpty()) {
//                sql = "SELECT * FROM usuario WHERE nome LIKE '%" + objUsuario.getNome() + "%' ";
//
//            } else if (!objUsuario.getCpf().isEmpty()) {
//                sql = "SELECT * FROM usuario WHERE cpf LIKE '%" + objUsuario.getCpf() + "%' ";
//            }
//            ArrayList dado = new ArrayList();
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                dado.add(new Object[]{
//                    rs.getInt("id"),
//                    rs.getString("nome"),
//                    rs.getString("cpf"),
//                    rs.getString("email"),
//                    rs.getString("telefone")
//                });
//
//            }
//            ps.close();
//            rs.close();
//            connection.close();
//
//            return dado;
//        } catch (SQLException e) {
//            e.getMessage();
//            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
//            return null;
//        }
	
}
