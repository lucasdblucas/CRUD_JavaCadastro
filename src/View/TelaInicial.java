package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Modelo_Tabela;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaInicial extends JFrame {
	
	// Componentes
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Nome;
	private JTextField textField_CPF;
	private JTable table_listagem;
	private JButton btnCadastrar;
	JButton btnDeletar;
	
	// Variaveis de dados
	private Usuario objUsuario;
	private UsuarioDAO objDAO;
	private boolean buscar = false;
	
	/**
	 * Launch the application.
	 */
	public static void main (String[] args) {
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
        objUsuario = new Usuario();
        ArrayList dados = new ArrayList();
        
        if (buscar) {
            dados = new ArrayList();//objDAO.buscar(objUsuario);
        } else {
            dados = objDAO.listarTodos();
        }

        Modelo_Tabela modelo = new Modelo_Tabela(dados, objUsuario.getColunas());

        table_listagem.setModel(modelo);
        table_listagem.getColumnModel().getColumn(0).setPreferredWidth(80);
        table_listagem.getColumnModel().getColumn(0).setResizable(false);
        table_listagem.getColumnModel().getColumn(1).setPreferredWidth(120);
        table_listagem.getColumnModel().getColumn(1).setResizable(false);
        table_listagem.getColumnModel().getColumn(2).setPreferredWidth(250);
        table_listagem.getColumnModel().getColumn(2).setResizable(false);
        
        table_listagem.getTableHeader().setReorderingAllowed(false);
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
		textField_ID.setEditable(false);
		textField_ID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel Label_CPF = new JLabel("CPF: ");
		
		textField_Nome = new JTextField();
		textField_Nome.setColumns(10);
		
		textField_CPF = new JTextField();
		textField_CPF.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnDeletar = new JButton("Deletar");
		
		btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnSalvarActionPerformed(evt);
            }
        });
		
		btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnDeletarActionPerformed(evt);
            }
        });
		
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
								.addComponent(textField_Nome)
								.addComponent(textField_CPF))
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
						.addComponent(textField_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Label_CPF)
						.addComponent(textField_CPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_listagem = new JTable();
		table_listagem.setModel(new javax.swing.table.DefaultTableModel(
	        new Object [][] {
	            {null, null, null},
	            {null, null, null},
	            {null, null, null},
	            {null, null, null}
	        },
	        new String [] {"ID", "Nome", "CPF"}
	    ){
	        Class[] types = new Class [] {
	            java.lang.String.class, java.lang.String.class, java.lang.String.class
	        };
	        boolean[] canEdit = new boolean [] {
	            false, true, true
	        };
	
	        public Class getColumnClass(int columnIndex) {
	            return types [columnIndex];
	        }
	
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return canEdit [columnIndex];
	        }
	    });
	    table_listagem.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            table_listagem_mouseclick(evt);
	        }
	    });
		
		scrollPane.setViewportView(table_listagem);
		contentPane.setLayout(gl_contentPane);
		
		carregarTable(null);
	}
	
	private void tbnSalvarActionPerformed(java.awt.event.ActionEvent evt) {

        objUsuario = new Usuario();
        objUsuario.setId(textField_ID.getText());
        objUsuario.setNome(textField_Nome.getText());
        objUsuario.setCpf(textField_CPF.getText());

        // fazendo a validação dos dados
        if ((textField_Nome.getText().isEmpty()) || (textField_CPF.getText().isEmpty()) ){
            JOptionPane.showMessageDialog(null, "Valores Nome e CPF precisam ser informados");
        } else {
            // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
            objDAO = new UsuarioDAO();
            objDAO.salvar(objUsuario);
            JOptionPane.showMessageDialog(null, "Usuário " + textField_Nome.getText() + " inserido com sucesso! ");
        }

        carregarTable(null);

        // apaga os dados preenchidos nos campos de texto
        setClear();
    }
	
	private void tbnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
		objUsuario = new Usuario();
        objUsuario.setId(textField_ID.getText());

        // fazendo a validação dos dados
        if ((textField_ID.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Informe valores para os campos");
        } else {
            // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
            objDAO = new UsuarioDAO();
            objDAO.deletar(objUsuario);
            JOptionPane.showMessageDialog(null, "Usuário Removido com Sucesso! ");
        }

        carregarTable(null);

        // apaga os dados preenchidos nos campos de texto
        setClear();
    
	}
	
	private void setClear() {
		textField_ID.setText(null);
		textField_Nome.setText(null);
		textField_CPF.setText(null);
	}
	
	private void table_listagem_mouseclick (java.awt.event.MouseEvent evt) {
		textField_ID.setText(table_listagem.getValueAt(table_listagem.getSelectedRow(), 0).toString());
        textField_Nome.setText(table_listagem.getValueAt(table_listagem.getSelectedRow(), 1).toString());
        textField_CPF.setText(table_listagem.getValueAt(table_listagem.getSelectedRow(), 2).toString());
        
	}
}
