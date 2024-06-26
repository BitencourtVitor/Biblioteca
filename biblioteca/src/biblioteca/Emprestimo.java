package biblioteca;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Emprestimo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdAluno;
	private JTextField txtNomeAluno;
	private JTextField txtEmailAluno;
	private JTextField txtFiltrar;
	private JTable table;
	private JTextField txtTituloResultado;
	private JTextField txtAutorResultado;
	private JTextField txtQtdRetirar;
	private JTextField txtEstoqueResultado;

	/**
	 * Create the panel.
	 */
	public Emprestimo() {
		setBackground(new Color(255, 255, 255));
        setBounds(0, 0, 579, 436);
		setFont(new Font("Lato", Font.PLAIN, 15));
		setLayout(null);
		
		JSeparator line_vertical_1 = new JSeparator();
		line_vertical_1.setOrientation(SwingConstants.VERTICAL);
		line_vertical_1.setForeground(Color.GRAY);
		line_vertical_1.setBounds(0, 0, 1, 435);
		add(line_vertical_1);
		
		JSeparator line_vertical_2 = new JSeparator();
		line_vertical_2.setOrientation(SwingConstants.VERTICAL);
		line_vertical_2.setForeground(Color.GRAY);
		line_vertical_2.setBounds(578, 0, 1, 435);
		add(line_vertical_2);
		
		JSeparator line_inferior = new JSeparator();
		line_inferior.setForeground(Color.GRAY);
		line_inferior.setBounds(0, 435, 579, 1);
		add(line_inferior);
		
		JSeparator line_Menu = new JSeparator();
		line_Menu.setBounds(130, 0, 449, 1);
		add(line_Menu);
		line_Menu.setForeground(Color.GRAY);
		
		JLabel lblRegistroDeNovos = new JLabel("Registrar Empréstimo");
		lblRegistroDeNovos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeNovos.setFont(new Font("Lato", Font.ITALIC, 15));
		lblRegistroDeNovos.setBounds(0, 20, 579, 25);
		add(lblRegistroDeNovos);
		
		JLabel lblDadosDoAluno = new JLabel("Dados do Aluno");
		lblDadosDoAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblDadosDoAluno.setFont(new Font("Lato", Font.BOLD, 13));
		lblDadosDoAluno.setBounds(15, 60, 100, 20);
		add(lblDadosDoAluno);
		
		JLabel lbl_Id = new JLabel("Informe o ID");
		lbl_Id.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Id.setFont(new Font("Lato", Font.BOLD, 11));
		lbl_Id.setBounds(15, 90, 70, 25);
		add(lbl_Id);
		
		txtIdAluno = new JTextField();
		txtIdAluno.setToolTipText("Digite o ID e aperte Enter ou clique no botão ao lado.");
		txtIdAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					 chamar_nome_aluno();
				 }
			}
		});
		txtIdAluno.setFont(new Font("Lato", Font.PLAIN, 11));
		txtIdAluno.setBounds(85, 90, 35, 25);
		add(txtIdAluno);
		txtIdAluno.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Lato", Font.PLAIN, 11));
		lblNome.setBounds(160, 90, 40, 25);
		add(lblNome);
		
		txtNomeAluno = new JTextField();
		txtNomeAluno.setEditable(false);
		txtNomeAluno.setFont(new Font("Lato", Font.PLAIN, 11));
		txtNomeAluno.setColumns(10);
		txtNomeAluno.setBounds(200, 90, 120, 25);
		add(txtNomeAluno);
		
		txtEmailAluno = new JTextField();
		txtEmailAluno.setFont(new Font("Lato", Font.PLAIN, 11));
		txtEmailAluno.setEditable(false);
		txtEmailAluno.setColumns(10);
		txtEmailAluno.setBounds(370, 90, 170, 25);
		add(txtEmailAluno);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Lato", Font.PLAIN, 11));
		lblEmail.setBounds(330, 90, 40, 25);
		add(lblEmail);
		
		JSeparator line_Menu_1 = new JSeparator();
		line_Menu_1.setForeground(Color.GRAY);
		line_Menu_1.setBounds(10, 130, 559, 5);
		add(line_Menu_1);
		
		JLabel lblEscolhaDoLivro = new JLabel("Escolha do Livro");
		lblEscolhaDoLivro.setHorizontalAlignment(SwingConstants.LEFT);
		lblEscolhaDoLivro.setFont(new Font("Lato", Font.BOLD, 13));
		lblEscolhaDoLivro.setBounds(15, 140, 100, 20);
		add(lblEscolhaDoLivro);
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltrar.setFont(new Font("Lato", Font.PLAIN, 12));
		lblFiltrar.setBounds(279, 145, 40, 25);
		add(lblFiltrar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFiltrar.setText(null);
				txtFiltrar.setForeground(Color.BLACK);
				txtFiltrar.setFont(new Font("Lato", Font.PLAIN, 11));
			}
		});
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					// Linhas de conexão
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
					c.setAutoCommit(false);
								
					// Criação da consulta
					String selectFiltro = "select livros.id, titulo, autor.nome, qtdEstoque from livros join autor on livros.idAutor = autor.id where titulo like ? OR autor.nome like ?";
					PreparedStatement p = c.prepareStatement(selectFiltro);
					p.setString(1, txtFiltrar.getText() + "%");
					p.setString(2, txtFiltrar.getText() + "%");
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
		txtFiltrar.setForeground(Color.GRAY);
		txtFiltrar.setText("Informe o título do livro ou nome do autor...");
		txtFiltrar.setFont(new Font("Lato", Font.ITALIC, 11));
		txtFiltrar.setColumns(10);
		txtFiltrar.setBounds(319, 145, 220, 25);
		add(txtFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Lato", Font.PLAIN, 11));
		scrollPane.setBounds(10, 190, 559, 115);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTituloResultado.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtAutorResultado.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtEstoqueResultado.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				
				txtTituloResultado.setBackground(Color.WHITE);
				txtAutorResultado.setBackground(Color.WHITE);
				txtEstoqueResultado.setBackground(Color.WHITE);
			}
		});
		table.setFont(new Font("Lato", Font.PLAIN, 11));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		visualizar();
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 11));
		lblTitulo.setBounds(10, 350, 40, 25);
		add(lblTitulo);
		
		txtTituloResultado = new JTextField();
		txtTituloResultado.setFont(new Font("Lato", Font.PLAIN, 11));
		txtTituloResultado.setEditable(false);
		txtTituloResultado.setColumns(10);
		txtTituloResultado.setBounds(50, 350, 150, 25);
		add(txtTituloResultado);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutor.setFont(new Font("Lato", Font.PLAIN, 11));
		lblAutor.setBounds(10, 380, 40, 25);
		add(lblAutor);
		
		txtAutorResultado = new JTextField();
		txtAutorResultado.setFont(new Font("Lato", Font.PLAIN, 11));
		txtAutorResultado.setEditable(false);
		txtAutorResultado.setColumns(10);
		txtAutorResultado.setBounds(50, 380, 150, 25);
		add(txtAutorResultado);
		
		JLabel lblQtdRetirar = new JLabel("Quantidade a retirar");
		lblQtdRetirar.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtdRetirar.setFont(new Font("Lato", Font.BOLD, 11));
		lblQtdRetirar.setBounds(225, 380, 110, 25);
		add(lblQtdRetirar);
		
		txtQtdRetirar = new JTextField();
		txtQtdRetirar.setToolTipText("Informe quantos deste livro serão retirados.");
		txtQtdRetirar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					
					int est = Integer.parseInt(txtEstoqueResultado.getText());
					int ret = Integer.parseInt(txtQtdRetirar.getText());
					if (ret > est && est != 0) {
						txtEstoqueResultado.setForeground(Color.RED);
						txtEstoqueResultado.setFont(new Font("Lato", Font.BOLD, 11));
					} else {
						txtEstoqueResultado.setForeground(Color.BLACK);
						txtEstoqueResultado.setFont(new Font("Lato", Font.PLAIN, 11));
					}
				}
			}
		});
		txtQtdRetirar.setHorizontalAlignment(SwingConstants.CENTER);
		txtQtdRetirar.setText("1");
		txtQtdRetirar.setFont(new Font("Lato", Font.PLAIN, 11));
		txtQtdRetirar.setColumns(10);
		txtQtdRetirar.setBounds(335, 380, 35, 25);
		add(txtQtdRetirar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int est = Integer.parseInt(txtEstoqueResultado.getText());
				int ret = Integer.parseInt(txtQtdRetirar.getText());
				if (ret > est && est != 0) {
					JOptionPane.showMessageDialog(null, "Não é possível retirar uma quantidade maior do que o quanto temos em estoque.");
				} else {
					lançar_registro_emprestimo();
				}
			}
		});
		btnRegistrar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\tick.png"));
		btnRegistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRegistrar.setFont(new Font("Lato", Font.BOLD, 11));
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setBounds(420, 350, 110, 25);
		add(btnRegistrar);
		
		JLabel lblDetalheDoEmprestimo = new JLabel("Detalhes do Empréstimo");
		lblDetalheDoEmprestimo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetalheDoEmprestimo.setFont(new Font("Lato", Font.BOLD, 13));
		lblDetalheDoEmprestimo.setBounds(15, 320, 150, 20);
		add(lblDetalheDoEmprestimo);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setToolTipText("Desfazer o que foi informado.");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\broom.png"));
		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(420, 380, 110, 25);
		add(btnLimpar);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstoque.setFont(new Font("Lato", Font.PLAIN, 11));
		lblEstoque.setBounds(285, 350, 50, 25);
		add(lblEstoque);
		
		txtEstoqueResultado = new JTextField();
		txtEstoqueResultado.setHorizontalAlignment(SwingConstants.CENTER);
		txtEstoqueResultado.setFont(new Font("Lato", Font.PLAIN, 11));
		txtEstoqueResultado.setEditable(false);
		txtEstoqueResultado.setColumns(10);
		txtEstoqueResultado.setBounds(335, 350, 35, 25);
		add(txtEstoqueResultado);
		
		JLabel lblCliqueNaOpo = new JLabel("Clique na opção desejada");
		lblCliqueNaOpo.setForeground(Color.GRAY);
		lblCliqueNaOpo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliqueNaOpo.setFont(new Font("Lato", Font.ITALIC, 11));
		lblCliqueNaOpo.setBounds(15, 160, 140, 20);
		add(lblCliqueNaOpo);
		
		JButton btnLoadAluno = new JButton("");
		btnLoadAluno.setToolTipText("Clique para identificar o aluno.");
		btnLoadAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamar_nome_aluno();
			}
		});
		btnLoadAluno.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\next.png"));
		btnLoadAluno.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLoadAluno.setFont(new Font("Lato", Font.BOLD, 11));
		btnLoadAluno.setBackground(Color.WHITE);
		btnLoadAluno.setBounds(125, 90, 25, 24);
		add(btnLoadAluno);
		
		JButton btnLoadAluno_1 = new JButton("");
		btnLoadAluno_1.setToolTipText("Atualizar as informações do banco de dados.");
		btnLoadAluno_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			visualizar();
			}
		});
		btnLoadAluno_1.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\refresh.png"));
		btnLoadAluno_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLoadAluno_1.setFont(new Font("Lato", Font.BOLD, 11));
		btnLoadAluno_1.setBackground(Color.WHITE);
		btnLoadAluno_1.setBounds(542, 145, 24, 24);
		add(btnLoadAluno_1);
		
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
	private void chamar_nome_aluno() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);

			String select = "SELECT nome, email FROM usuario WHERE id = ?";
			PreparedStatement p = c.prepareStatement(select);
			p.setString(1, txtIdAluno.getText());
			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				txtNomeAluno.setText((String) rs.getObject("nome"));
				txtEmailAluno.setText((String) rs.getObject("email"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		txtNomeAluno.setBackground(Color.WHITE);
		txtEmailAluno.setBackground(Color.WHITE);
	}
	private void lançar_registro_emprestimo() {
		
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);

			// Registro no Log de Empréstimo
			String insert = "INSERT INTO logemprestar(idLivro, idUser, dataEmp, qtdEmp, condicao) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(insert);
			p.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
			p.setString(2, String.valueOf(txtIdAluno.getText()));
			String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			p.setString(3, formattedDateTime);
			p.setString(4, txtQtdRetirar.getText().toString());
			p.setString(5, "VIGENTE");
			p.execute();
			
			// Dedução do estoque de livros
			String update = "UPDATE livros SET qtdEstoque = qtdEstoque - ? WHERE id = ?";
			PreparedStatement p1 = c.prepareStatement(update);
			p1.setString(1, txtQtdRetirar.getText().toString());
			p1.setString(2, table.getValueAt(table.getSelectedRow(), 0).toString());
			p1.execute();
			
			// Encerramento das operações SQL
			c.commit();
			c.close();
			
			// Limpeza dos campos, visualização e confirmação pro usuário
			JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso.");
			visualizar();
			limpar();
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados");
			ex.printStackTrace();
		}
	}
	private void limpar(){
		txtTituloResultado.setText(null);
		txtAutorResultado.setText(null);
		txtEstoqueResultado.setText(null);
		txtQtdRetirar.setText("1");
		table.clearSelection();
	}
 }