package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UsuarioDAO;
import Modelo.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaInicial extends JFrame {
	
	// Componentes
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_listagem;
	
	// Variaveis de dados
	private Usuario objUsuario;
	private UsuarioDAO objDAO;
	private boolean buscar = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Caregar tabela com as informações do banco de dados
	public void carregarTable(Usuario objUsuario) {

        objDAO = new UsuarioDAO();
        ArrayList dados = new ArrayList();
        if (buscar) {
            dados = objDAO.buscar(objUsuario);
        } else {
            objUsuario = new Usuario();
            dados = objDAO.listarTodos();
        }
        String[] colunas = objUsuario.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        tbListagemUsuario.setModel(modelo);
        tbListagemUsuario.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbListagemUsuario.getColumnModel().getColumn(0).setResizable(false);
        tbListagemUsuario.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbListagemUsuario.getColumnModel().getColumn(1).setResizable(false);
        tbListagemUsuario.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbListagemUsuario.getColumnModel().getColumn(2).setResizable(false);
        tbListagemUsuario.getTableHeader().setReorderingAllowed(false);
        //  tbListagemUsuario.setAutoResizeMode(tbListagemUsuario.AUTO_RESIZE_ALL_COLUMNS);
        // tbListagemUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
	
	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel Label_titulo = new JLabel("Cadastro de Usu\u00E1rios");
		Label_titulo.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		
		JLabel Label_ID = new JLabel("ID: ");
		
		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel Label_CPF = new JLabel("CPF: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		
		JButton btnDeletar = new JButton("Deletar");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(110)
							.addComponent(Label_titulo, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Label_ID)
								.addComponent(lblNome)
								.addComponent(Label_CPF))
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_ID, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(textField)
								.addComponent(textField_1))
							.addGap(69)
							.addComponent(btnCadastrar)
							.addGap(29)
							.addComponent(btnDeletar, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(Label_titulo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Label_ID)
						.addComponent(textField_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar)
						.addComponent(btnDeletar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Label_CPF)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_listagem = new JTable();
		scrollPane.setColumnHeaderView(table_listagem);
		contentPane.setLayout(gl_contentPane);
	}
}
