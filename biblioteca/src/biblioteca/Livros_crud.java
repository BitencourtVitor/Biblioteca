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
import java.util.ArrayList;
import java.util.List;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Livros_crud extends JPanel {
	
	@SuppressWarnings("unused")
	private JComboBox<String> ListaAutores;
	
	@SuppressWarnings("unused")
	private Object[][] autores;
	
	private String itemSelecionado;
	private Integer idResultante = null;
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
	private JTextField txtFiltrar;
	private JTextField txtUnidade;

	/**
	 * Create the panel.
	 */
	public Livros_crud() {
		
		autores = new Object[10][2];
		
		setBackground(new Color(255, 255, 255));
        setBounds(0, 0, 639, 639);
		setFont(new Font("Lato", Font.PLAIN, 15));
		setLayout(null);
		
		JLabel titulo = new JLabel("Gestão de Livros");
		titulo.setBounds(30, 20, 605, 20);
		titulo.setFont(new Font("Lato", Font.PLAIN, 20));
		add(titulo);
		
		JLabel marca = new JLabel("");
		marca.setBackground(Color.GRAY);
		marca.setOpaque(true);
		marca.setBounds(0, 200, 10, 101);
		add(marca);
		
		JLabel txt_1 = new JLabel("CRUD de Livros");
		txt_1.setFont(new Font("Lato", Font.ITALIC, 15));
		txt_1.setBounds(30, 50, 400, 20);
		add(txt_1);
		
		JLabel lblLivrosCadastrados = new JLabel("Livros Cadastrados");
		lblLivrosCadastrados.setFont(new Font("Lato", Font.PLAIN, 15));
		lblLivrosCadastrados.setBounds(30, 130, 170, 40);
		add(lblLivrosCadastrados);
		
		JSeparator DeTituloParaInfo = new JSeparator();
		DeTituloParaInfo.setForeground(Color.GRAY);
		DeTituloParaInfo.setBounds(30, 130, 579, 5);
		add(DeTituloParaInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Lato", Font.PLAIN, 11));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(30, 170, 420, 325);
		add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Lato", Font.PLAIN, 11));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		visualizar();
		
		JLabel lblAutores = new JLabel("Alterar Informações");
		lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutores.setFont(new Font("Lato", Font.PLAIN, 13));
		lblAutores.setBounds(450, 170, 159, 20);
		add(lblAutores);
		
		JPanel panel_alterar = new JPanel();
		panel_alterar.setVisible(false);
		panel_alterar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel_alterar.setBackground(Color.WHITE);
		panel_alterar.setBounds(30, 505, 490, 120);
		add(panel_alterar);
		panel_alterar.setLayout(null);
		
		JLabel lblCadastrarUmNovo = new JLabel("título da modificação, né?");
		lblCadastrarUmNovo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarUmNovo.setFont(new Font("Lato", Font.PLAIN, 13));
		lblCadastrarUmNovo.setBounds(5, 5, 160, 25);
		panel_alterar.add(lblCadastrarUmNovo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutor.setFont(new Font("Lato", Font.PLAIN, 11));
		lblAutor.setBounds(15, 40, 40, 25);
		panel_alterar.add(lblAutor);
		
		JLabel lblLink = new JLabel("Nome do livro");
		lblLink.setHorizontalAlignment(SwingConstants.LEFT);
		lblLink.setFont(new Font("Lato", Font.PLAIN, 11));
		lblLink.setBounds(15, 70, 75, 25);
		panel_alterar.add(lblLink);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Lato", Font.PLAIN, 11));
		txtNome.setColumns(10);
		txtNome.setBounds(90, 70, 285, 25);
		panel_alterar.add(txtNome);
		
		JButton btnLançar = new JButton("Inserir");
		btnLançar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Inserir
				if (table.isEnabled() == true) {
					/*String texto = txtNome.getText().toLowerCase();
					for (int i = 0; i < table.getRowCount(); i++) {
						String nomes = table.getValueAt(i, 1).toString().toLowerCase();
						if (nomes.contains(texto)) {
							JOptionPane.showMessageDialog(null, "Este autor já foi cadastrado.");
							return;
						}
					}*/
					lançar_inserir();
				}
				
				// Alterar
				else {
					int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar as informações deste livro?", "Confirmação de Edição", JOptionPane.YES_NO_OPTION);
					
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
		btnLançar.setBounds(380, 40, 100, 25);
		panel_alterar.add(btnLançar);
		
		JComboBox<String> ListaAutores = new JComboBox<String>();
		ListaAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ListaAutores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					itemSelecionado = ListaAutores.getSelectedItem().toString();
					//System.out.println(itemSelecionado);
				}
			}
		});
		ListaAutores.setBackground(Color.WHITE);
		ListaAutores.setEditable(false);
		ListaAutores.setFont(new Font("Lato", Font.PLAIN, 11));
		ListaAutores.setForeground(Color.BLACK);
		ListaAutores.setBounds(90, 40, 170, 22);
		panel_alterar.add(ListaAutores);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				txtUnidade.setText(null);
			}
		});
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\broom.png"));
		btnLimpar.setForeground(Color.DARK_GRAY);
		btnLimpar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(380, 70, 100, 25);
		panel_alterar.add(btnLimpar);
		
		JSeparator linha_alterar = new JSeparator();
		linha_alterar.setVisible(false);
		linha_alterar.setForeground(Color.GRAY);
		linha_alterar.setBounds(520, 565, 100, 5);
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

				preencherListaAutores(ListaAutores);
				
				// Ajuste dos fru-frus
				linha_editar.setVisible(false);
				
				btnLançar.setText("Inserir");
				linha_cadastrar.setVisible(true);
				coluna_alterar.setVisible(true);
				coluna_alterar.setBounds(620, 211, 10, 355);
				linha_alterar.setVisible(true);
				panel_alterar.setVisible(true);
				lblCadastrarUmNovo.setText("Cadastrar um novo livro");
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
				
				preencherListaAutores(ListaAutores);
				
				// Alteração do comportamento
				verificarSelecao_editar();
				if (table.getSelectedRows().length == 1) {
					
					txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtUnidade.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					
				    String valorSelecionado = table.getValueAt(table.getSelectedRow(), 2).toString();
					for (int i = 0; i < ListaAutores.getItemCount(); i++) {
				        if (ListaAutores.getItemAt(i).equals(valorSelecionado)) {
				            ListaAutores.setSelectedIndex(i);
				            break;
				        }
				    }
					
					table.setEnabled(false);
					btnLançar.setText("Alterar");
				
				// Ajuste dos fru-frus
				linha_cadastrar.setVisible(false);
				
				linha_editar.setVisible(true);
				coluna_alterar.setVisible(true);
				coluna_alterar.setBounds(620, 242, 10, 324);
				linha_alterar.setVisible(true);
				panel_alterar.setVisible(true);
				lblCadastrarUmNovo.setText("Editar um autor existente");
				}
			}
		});
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\pencil.png"));
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
				txtNome.setText(null);
				linha_editar.setVisible(false);
				linha_cadastrar.setVisible(false);
				coluna_alterar.setVisible(false);
				coluna_alterar.setBounds(620, 211, 10, 355);
				linha_alterar.setVisible(false);
				panel_alterar.setVisible(false);
				
			}
		});
		lblfecharJanela.setBounds(460, 7, 20, 20);
		panel_alterar.add(lblfecharJanela);
		lblfecharJanela.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\close (1).png"));
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnidades.setFont(new Font("Lato", Font.PLAIN, 11));
		lblUnidades.setBounds(270, 40, 55, 25);
		panel_alterar.add(lblUnidades);
		
		txtUnidade = new JTextField();
		txtUnidade.setFont(new Font("Lato", Font.PLAIN, 11));
		txtUnidade.setColumns(10);
		txtUnidade.setBounds(325, 40, 50, 25);
		panel_alterar.add(txtUnidade);
		
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
					// Linhas de conexão
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
					c.setAutoCommit(false);
								
					// Criação da consulta
					String selectFiltro = "select livros.id, titulo, autor.nome, qtdEstoque from livros join autor on livros.idAutor = autor.id where " + filtrado() + " like ?";
					PreparedStatement p = c.prepareStatement(selectFiltro);
					p.setString(1, txtFiltrar.getText() + "%");
					ResultSet rs = p.executeQuery();
					
					DefaultTableModel d = new DefaultTableModel();
					d.setColumnIdentifiers(new Object[]{"ID", "Titulo", "Autor", "Qtd"});
					
					// Loop de varredura dos dados
					while(rs.next()) {
						
						d.addRow(new Object[] {
						rs.getObject("livros.id"),
						rs.getObject("titulo"),
						rs.getObject("autor.nome"),
						rs.getObject("qtdEstoque")
						});
						
						};
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
		
		JLabel lblFiltrar = new JLabel("Filtrar por");
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltrar.setFont(new Font("Lato", Font.PLAIN, 11));
		lblFiltrar.setBounds(208, 137, 50, 25);
		add(lblFiltrar);
		
		JLabel lblLivro = new JLabel("Autor");
		lblLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivro.setFont(new Font("Lato", Font.BOLD, 11));
		lblLivro.setBounds(257, 137, 30, 25);
		add(lblLivro);
		
		JLabel lblSwitch = new JLabel("");
		lblSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch.png"));
		lblSwitch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (opcaoSwitch.equals("Autor")) {
					lblSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch2.png"));
					lblLivro.setText("Livro");
					opcaoSwitch = "Livro";			
					
				} else if (opcaoSwitch.equals("Livro")) {
					lblSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch.png"));
					lblLivro.setText("Autor");
					opcaoSwitch = "Autor";
				}
			}
		});
		lblSwitch.setBounds(290, 137, 30, 25);
		add(lblSwitch);
		
		JLabel txt_2 = new JLabel("Só será possível cadastrar livros de autores registrados neste sistema.");
		txt_2.setFont(new Font("Lato", Font.PLAIN, 15));
		txt_2.setBounds(30, 70, 605, 20);
		add(txt_2);
			
	}

	private void lançar_inserir() {
		
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
			
			// Puxar o id do autor selecionado
			String selectInserir = "SELECT id FROM autor WHERE nome = ?";
			PreparedStatement p2 = c.prepareStatement(selectInserir);
			p2.setString(1, itemSelecionado);
			ResultSet rs2 = p2.executeQuery();
			if (rs2.next()) {
			    idResultante = rs2.getInt(1);
			}
			
			String insert = "INSERT INTO livros(titulo, idAutor, qtdEstoque) VALUES (?, ?, ?)";
			PreparedStatement p = c.prepareStatement(insert);
			p.setString(1, txtNome.getText());
			p.setString(2, String.valueOf(idResultante));
			p.setString(3, txtUnidade.getText());
			p.execute();
			c.commit();
			c.close();
			
			// Limpeza dos campos, visualização e confirmação pro usuário
			visualizar();
			JOptionPane.showMessageDialog(null, txtNome.getText() + " adicionado(a) com sucesso.");
			txtNome.setText(null);
			txtUnidade.setText(null);
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados");
			ex.printStackTrace();
		}
	}
	public void visualizar() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
						
			// Criação da consulta
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select livros.id, titulo, autor.nome, qtdEstoque from livros join autor on livros.idAutor = autor.id");
			
			DefaultTableModel d = new DefaultTableModel();
			d.setColumnIdentifiers(new Object[]{"ID", "Titulo", "Autor", "Qtd"});
			
			// Loop de varredura dos dados
			while(rs.next()) {
				
				d.addRow(new Object[] {
				rs.getObject("livros.id"),
				rs.getObject("titulo"),
				rs.getObject("autor.nome"),
				rs.getObject("qtdEstoque")
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
			
			// Puxar o id do autor selecionado
			String selectInserir = "SELECT id FROM autor WHERE nome = ?";
			PreparedStatement p2 = c.prepareStatement(selectInserir);
			p2.setString(1, itemSelecionado);
			ResultSet rs2 = p2.executeQuery();
			if (rs2.next()) {
				idResultante = rs2.getInt(1);
			}
			
			String update = "UPDATE livros SET titulo = ?, qtdEstoque = ?, idAutor = ? WHERE id = ?";
			// Execução
			PreparedStatement p = c.prepareStatement(update);
			p.setString(1, txtNome.getText());
			p.setString(2, txtUnidade.getText());
			p.setString(3, String.valueOf(idResultante));
			p.setString(4, table.getValueAt(table.getSelectedRow(), 0).toString());
			p.execute();
			c.commit();
			c.close();
			
			visualizar();
			JOptionPane.showMessageDialog(null, "Informações atualizadas para " + txtNome.getText() + " com sucesso.");
			
			// Limpeza dos campos
			txtNome.setText(null);
			txtUnidade.setText(null);
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
				PreparedStatement p = c.prepareStatement("delete from livros where id = ?");
				p.setInt(1, Integer.parseInt(table.getValueAt(linha, 0).toString()));
				p.execute();
				p.close();
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
		
		coluna.getColumn(2).setPreferredWidth(200);
		
		coluna.getColumn(3).setPreferredWidth(30);
		coluna.getColumn(3).setCellRenderer(centerRenderer);
		
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

	private void preencherListaAutores(JComboBox<String> ListaAutores) {

	    Connection c = null;
	    PreparedStatement p = null;
	    ResultSet rs = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
	        c.setAutoCommit(false);
	        
	        String s = "SELECT DISTINCT id, nome FROM autor";
	        p = c.prepareStatement(s);
	        rs = p.executeQuery();
	        
	        
	        List<Integer> id = new ArrayList<>();
	        List<String> nome = new ArrayList<>();
	        ListaAutores.removeAllItems();
	        
	        while (rs.next()) {
	        	
	            int idAtual = rs.getInt("id");
	            String nomeAtual = rs.getString("nome");
	            
	            id.add(idAtual);
	            nome.add(nomeAtual);
	            ListaAutores.addItem(nomeAtual);
	        }
	        
	        Object[][] autores = new Object[id.size()][2];
	        for (int i = 0; i < id.size(); i++) {
	        	autores[i][0] = id.get(i);
	        	autores[i][1] = nome.get(i);
	        	//System.out.println(autores[i][0] + " - " + autores[i][1]);
	        }
	        //System.out.println(autores.length);
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	public String opcaoSwitch = "Autor";
	public String filtrado() {
		if(opcaoSwitch.equals("Livro")) {
			return "titulo";
		} else {
			return "autor.nome";
		}
	}

/* Aqui acaba o código */ }