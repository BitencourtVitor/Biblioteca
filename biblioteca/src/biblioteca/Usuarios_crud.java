package biblioteca;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Usuarios_crud extends JPanel {
	
	public String banco_de_dados() {
		String diretorio = "jdbc:mysql://localhost:3306/database";
		return diretorio;
	}
	public String user_senha() {
		String log_sen = "root";
		return log_sen;
	}

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtFiltrar;

	/**
	 * Create the panel.
	 */
	public Usuarios_crud() {
		setBackground(new Color(255, 255, 255));
        setBounds(0, 0, 639, 639);
		setFont(new Font("Lato", Font.PLAIN, 15));
		setLayout(null);
		
		JLabel titulo = new JLabel("Gestão de Usuários");
		titulo.setBounds(30, 20, 605, 20);
		titulo.setFont(new Font("Lato", Font.PLAIN, 20));
		add(titulo);
		
		JLabel marca = new JLabel("");
		marca.setBackground(Color.GRAY);
		marca.setOpaque(true);
		marca.setBounds(0, 100, 10, 101);
		add(marca);
		
		JLabel txt_1 = new JLabel("CRUD de Usuários");
		txt_1.setFont(new Font("Lato", Font.ITALIC, 15));
		txt_1.setBounds(30, 50, 605, 20);
		add(txt_1);
		
		JLabel txt_2 = new JLabel("Apenas usuários registrados podem realizar empréstimos de livros.");
		txt_2.setFont(new Font("Lato", Font.PLAIN, 15));
		txt_2.setBounds(30, 70, 605, 20);
		add(txt_2);
		
		JLabel lblUsuariosCadastrados = new JLabel("Usuários Cadastrados");
		lblUsuariosCadastrados.setFont(new Font("Lato", Font.PLAIN, 15));
		lblUsuariosCadastrados.setBounds(30, 130, 170, 40);
		add(lblUsuariosCadastrados);
		
		JSeparator DeTituloParaInfo = new JSeparator();
		DeTituloParaInfo.setForeground(Color.GRAY);
		DeTituloParaInfo.setBounds(30, 130, 579, 5);
		add(DeTituloParaInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Lato", Font.PLAIN, 11));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(30, 170, 420, 325);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Lato", Font.PLAIN, 11));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		visualizar();
		
		JLabel lblUsuarios = new JLabel("Alterar Informações");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Lato", Font.PLAIN, 13));
		lblUsuarios.setBounds(450, 170, 159, 20);
		add(lblUsuarios);
		
		JPanel panel_alterar = new JPanel();
		panel_alterar.setVisible(false);
		panel_alterar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel_alterar.setBackground(Color.WHITE);
		panel_alterar.setBounds(30, 505, 420, 120);
		add(panel_alterar);
		panel_alterar.setLayout(null);
		
		JLabel lblCadastrarUmNovo = new JLabel("título da modificação, né?");
		lblCadastrarUmNovo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarUmNovo.setFont(new Font("Lato", Font.PLAIN, 13));
		lblCadastrarUmNovo.setBounds(5, 5, 160, 25);
		panel_alterar.add(lblCadastrarUmNovo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Lato", Font.PLAIN, 11));
		lblNome.setBounds(15, 40, 50, 25);
		panel_alterar.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Lato", Font.PLAIN, 11));
		txtNome.setBounds(80, 40, 225, 25);
		panel_alterar.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblLink = new JLabel("Email");
		lblLink.setHorizontalAlignment(SwingConstants.LEFT);
		lblLink.setFont(new Font("Lato", Font.PLAIN, 11));
		lblLink.setBounds(15, 70, 65, 25);
		panel_alterar.add(lblLink);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lato", Font.PLAIN, 11));
		txtEmail.setColumns(10);
		txtEmail.setBounds(80, 70, 225, 25);
		panel_alterar.add(txtEmail);
		
		JButton btnLançar = new JButton("Inserir");
		btnLançar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Inserir
				if (table.isEnabled() == true) {
					
					/*
					String texto = txtNome.getText().toLowerCase();
					for (int i = 0; i < table.getRowCount(); i++) {
						String nomes = table.getValueAt(i, 1).toString().toLowerCase();
						if (nomes.contains(texto)) {
							JOptionPane.showMessageDialog(null, "Este autor já foi cadastrado.");
							return;
						}
					} */
					
					lançar_inserir();
				}
				
				// Alterar
				else {
					int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar as informações deste usuário?", "Confirmação de Edição", JOptionPane.YES_NO_OPTION);
					
					if (resposta == JOptionPane.YES_OPTION) {
						
						/*
					    String texto = txtNome.getText().toLowerCase();
					    String nomeAtual = (String) table.getValueAt(table.getSelectedRow(), 1);
					    List<String> nomes = new ArrayList<String>();
					    for (int i = 0; i < table.getRowCount(); i++) {
					    	nomes.add(((String) table.getValueAt(i, 1)).toLowerCase());
					    }
					    if (!texto.equals(nomeAtual.toLowerCase()) && Collections.frequency(nomes, texto) != 0) {
					        JOptionPane.showMessageDialog(null, "Este autor já foi cadastrado.");
					        return;
					    }
					    */
					    lançar_editar();
					    btnLançar.setText("Inserir");
					    
					}
				}
			}
		});
		btnLançar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\insert-arrow.png"));
		btnLançar.setForeground(Color.DARK_GRAY);
		btnLançar.setFont(new Font("Lato", Font.BOLD, 11));
		btnLançar.setBackground(Color.WHITE);
		btnLançar.setBounds(310, 40, 100, 25);
		panel_alterar.add(btnLançar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				txtEmail.setText(null);
			}
		});
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\broom.png"));
		btnLimpar.setForeground(Color.DARK_GRAY);
		btnLimpar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(310, 70, 100, 25);
		panel_alterar.add(btnLimpar);
		
		JSeparator linha_alterar = new JSeparator();
		linha_alterar.setVisible(false);
		linha_alterar.setForeground(Color.GRAY);
		linha_alterar.setBounds(450, 565, 170, 5);
		add(linha_alterar);
		
		JSeparator coluna_alterar = new JSeparator();
		coluna_alterar.setVisible(false);
		coluna_alterar.setOrientation(SwingConstants.VERTICAL);
		coluna_alterar.setForeground(Color.GRAY);
		coluna_alterar.setBounds(620, 211, 10, 355);
		add(coluna_alterar);
		
		JSeparator linha_cadastrar = new JSeparator();
		linha_cadastrar.setVisible(false);
		linha_cadastrar.setForeground(Color.GRAY);
		linha_cadastrar.setBounds(600, 211, 20, 5);
		add(linha_cadastrar);
		
		JSeparator linha_editar = new JSeparator();
		linha_editar.setVisible(false);
		linha_editar.setForeground(Color.GRAY);
		linha_editar.setBounds(600, 242, 20, 5);
		add(linha_editar);
		
		
		JButton btnCadastrar = new JButton("Cadastrar novo");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Ajuste dos fru-frus
				linha_editar.setVisible(false);
				
				btnLançar.setText("Inserir");
				linha_cadastrar.setVisible(true);
				coluna_alterar.setVisible(true);
				coluna_alterar.setBounds(620, 211, 10, 355);
				linha_alterar.setVisible(true);
				panel_alterar.setVisible(true);
				lblCadastrarUmNovo.setText("Cadastrar um novo autor");
			}
		});
		btnCadastrar.setForeground(Color.DARK_GRAY);
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\plus.png"));
		btnCadastrar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnCadastrar.setBounds(460, 200, 139, 25);
		add(btnCadastrar);
		
		JButton btnEditar = new JButton("Editar linha");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Alteração do comportamento
				verificarSelecao_editar();
				if (table.getSelectedRows().length == 1) {
					txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtEmail.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					table.setEnabled(false);
					btnLançar.setText("Alterar");
				
				// Ajuste dos fru-frus
				linha_cadastrar.setVisible(false);
				
				linha_editar.setVisible(true);
				coluna_alterar.setVisible(true);
				coluna_alterar.setBounds(620, 242, 10, 324);
				linha_alterar.setVisible(true);
				panel_alterar.setVisible(true);
				lblCadastrarUmNovo.setText("Editar um usuário existente");
				}
			}
		});
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\pencil.png"));
		btnEditar.setSelectedIcon(null);
		btnEditar.setForeground(Color.DARK_GRAY);
		btnEditar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(460, 230, 139, 25);
		add(btnEditar);
		
		
		JButton btnDeletar = new JButton("Deletar linha(s)");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarSelecao_deletar();
				if (table.getSelectedRows().length != 0) {
					int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja realizar essa exclusão?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						excluir();
					}
				}
			}
		});
		btnDeletar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\clear.png"));
		btnDeletar.setForeground(Color.DARK_GRAY);
		btnDeletar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnDeletar.setBackground(Color.WHITE);
		btnDeletar.setBounds(460, 260, 139, 25);
		add(btnDeletar);
		
		JLabel lblfecharJanela = new JLabel("");
		lblfecharJanela.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblfecharJanela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.isEnabled() == false) {
					table.setEnabled(true);
				}
				txtNome.setText(null);
				txtEmail.setText(null);
				linha_editar.setVisible(false);
				linha_cadastrar.setVisible(false);
				coluna_alterar.setVisible(false);
				coluna_alterar.setBounds(620, 211, 10, 355);
				linha_alterar.setVisible(false);
				panel_alterar.setVisible(false);
			}
		});
		lblfecharJanela.setBounds(390, 7, 20, 20);
		panel_alterar.add(lblfecharJanela);
		lblfecharJanela.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\close (1).png"));
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizar();
			}
		});
		btnAtualizar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\refresh.png"));
		btnAtualizar.setForeground(Color.DARK_GRAY);
		btnAtualizar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.setBounds(425, 136, 26, 26);
		add(btnAtualizar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					// Linhas de Consulta
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
					
					String select = "select id, nome, email from usuario where nome like ?";
					PreparedStatement p = c.prepareStatement(select);
					p.setString(1, txtFiltrar.getText() + "%");
					ResultSet rs = p.executeQuery();
					
					DefaultTableModel d = new DefaultTableModel();
					d.setColumnIdentifiers(new Object[]{"ID", "Nome", "Email"});
					
					// Loop de varredura dos dados
					while(rs.next()) {
						
						d.addRow(new Object[] {
						rs.getObject("id"), 
						rs.getObject("nome"), 
						rs.getObject("email")
						});
					}
					table.setModel(d);
					organizar_tabela();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		txtFiltrar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFiltrar.setText(null);
				txtFiltrar.setForeground(Color.BLACK);
				txtFiltrar.setFont(new Font("Lato", Font.PLAIN, 11));
			}
		});
		txtFiltrar.setForeground(Color.GRAY);
		txtFiltrar.setText("Digite para filtrar...");
		txtFiltrar.setFont(new Font("Lato", Font.ITALIC, 11));
		txtFiltrar.setColumns(10);
		txtFiltrar.setBounds(320, 137, 100, 25);
		add(txtFiltrar);
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltrar.setFont(new Font("Lato", Font.PLAIN, 11));
		lblFiltrar.setBounds(280, 137, 40, 25);
		add(lblFiltrar);
	}

	private void lançar_inserir() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
			
			String insert = "INSERT INTO usuario(nome, email) VALUES (?, ?) ";
			PreparedStatement p = c.prepareStatement(insert);
			p.setString(1, txtNome.getText());
			p.setString(2, txtEmail.getText());
			p.execute();
			c.commit();
			c.close();
			
			// Limpeza dos campos, visualização e confirmação pro usuário
			visualizar();
			JOptionPane.showMessageDialog(null, txtNome.getText() + " adicionado(a) com sucesso.");
			txtNome.setText(null);
			txtEmail.setText(null);
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados");
			ex.printStackTrace();
		}
	}
	private void visualizar() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
						
			// Criação da consulta
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select id, nome, email from usuario");
			
			DefaultTableModel d = new DefaultTableModel();
			d.setColumnIdentifiers(new Object[]{"ID", "Nome", "Email"});
			
			// Loop de varredura dos dados
			while(rs.next()) {
				
				d.addRow(new Object[] {
				rs.getObject("id"), 
				rs.getObject("nome"), 
				rs.getObject("email")
				});
				
				};
			table.setModel(d);
			organizar_tabela();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void lançar_editar() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
		
			String update = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";
			
			// Execução
			PreparedStatement p = c.prepareStatement(update);
			p.setString(1, txtNome.getText());
			p.setString(2, txtEmail.getText());
			p.setString(3, table.getValueAt(table.getSelectedRow(), 0).toString());
			p.execute();
			c.commit();
			c.close();
			
			visualizar();
			JOptionPane.showMessageDialog(null, "Informações atualizadas para " + txtNome.getText() + " com sucesso.");
			
			// Limpeza dos campos
			txtNome.setText(null);
			txtEmail.setText(null);
			table.setEnabled(true);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void excluir() {
		try {
			// Linhas de Consulta
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
			
			// Loop de exclusão dos dados selecionados
			for (int linha : table.getSelectedRows()) {
				PreparedStatement p = c.prepareStatement("delete from usuario where id = ?");
				p.setInt(1, Integer.parseInt(table.getValueAt(linha, 0).toString()));
				p.execute();
			}
			c.commit();
			
			JOptionPane.showMessageDialog(null, "Deletado(s) com sucesso.");
			visualizar();
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void organizar_tabela() {
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		TableColumnModel coluna = table.getColumnModel();
		
		coluna.getColumn(0).setPreferredWidth(30);
		coluna.getColumn(0).setCellRenderer(centerRenderer);
		
		coluna.getColumn(1).setPreferredWidth(160);
		
		coluna.getColumn(2).setPreferredWidth(212);
		
		table.doLayout();
		
	}
	private void verificarSelecao_editar() {
		if (table.getSelectedRows().length != 1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.");
			return;
		}
	}
	private void verificarSelecao_deletar() {
		if (table.getSelectedRows().length == 0) {
			JOptionPane.showMessageDialog(null, "Selecione uma ou mais linhas para deletar.");
			return;
		}
	}

/* Aqui acaba o código */ }