package biblioteca;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Devolucao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	private JTextField txtAluno;
	private JTextField txtTitulo;
	private JTextField txtData;
	private JTextField txtQtd;
	private JTextField txtDev;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Devolucao() {
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
		line_Menu.setBounds(259, 0, 320, 1);
		add(line_Menu);
		line_Menu.setForeground(Color.GRAY);
		
		JLabel lblDevolucoesTitulo = new JLabel("Devolução do Empréstimo");
		lblDevolucoesTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevolucoesTitulo.setFont(new Font("Lato", Font.ITALIC, 15));
		lblDevolucoesTitulo.setBounds(0, 20, 579, 25);
		add(lblDevolucoesTitulo);
		
		JLabel lblEmpEmVigencia = new JLabel("Empréstimos em Vigência");
		lblEmpEmVigencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpEmVigencia.setFont(new Font("Lato", Font.BOLD, 13));
		lblEmpEmVigencia.setBounds(15, 60, 160, 20);
		add(lblEmpEmVigencia);
		
		JLabel lblFiltrar = new JLabel("Filtrar por");
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltrar.setFont(new Font("Lato", Font.PLAIN, 11));
		lblFiltrar.setBounds(319, 65, 50, 25);
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
				    String filtro = 
				            "SELECT "
				            + "logemprestar.id, "
				            + "livros.titulo, "
				            + "usuario.nome, "
				            + "dataEmp, "
				            + "qtdEmp "
				            + "FROM logemprestar "
				            + "JOIN livros ON logemprestar.idLivro = livros.id "
				            + "JOIN usuario ON logemprestar.idUser = usuario.id "
				            + "WHERE " + filtrado() + " LIKE ? "
				            + "AND condicao = 'VIGENTE'";
				    PreparedStatement p = c.prepareStatement(filtro);
				    p.setString(1, txtFiltrar.getText() + "%");
				    ResultSet rs = p.executeQuery();
				    
				    DefaultTableModel d = new DefaultTableModel();
				    d.setColumnIdentifiers(new Object[]{"ID", "Titulo", "Aluno", "Data", "Qtd"});

				    SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

				    // Loop de varredura dos dados
				    while(rs.next()) {
				        String dataString = rs.getString("dataEmp");
				        Date data = formatoOriginal.parse(dataString);
				        String dataFormatada = formatoBrasileiro.format(data);
				        
				        d.addRow(new Object[] {
				            rs.getObject("id"),
				            rs.getObject("titulo"),
				            rs.getObject("nome"),
				            dataFormatada,
				            rs.getObject("qtdEmp")
				        });
				    }

				    table.setModel(d);
				    organizar_tabela();
				    
				} catch (Exception ex) {
				    ex.printStackTrace();
				}


			}
		});
		txtFiltrar.setForeground(Color.GRAY);
		txtFiltrar.setText("Digite para filtrar...");
		txtFiltrar.setFont(new Font("Lato", Font.ITALIC, 11));
		txtFiltrar.setColumns(10);
		txtFiltrar.setBounds(431, 65, 108, 25);
		add(txtFiltrar);		
		
		JButton btnRegistrar = new JButton("Devolver");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ret = Integer.parseInt(txtQtd.getText());
				int dev = Integer.parseInt(txtDev.getText());
				if (dev > ret && ret != 0) {
					JOptionPane.showMessageDialog(null, "Não é possível devolver uma quantidade de livros maior do que a vigente no empréstimo.");
				} else {
					lançar_devolucao();
				}
			}
		});
		btnRegistrar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\devolucao.png"));
		btnRegistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRegistrar.setFont(new Font("Lato", Font.BOLD, 11));
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setBounds(405, 305, 130, 25);
		add(btnRegistrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\broom.png"));
		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.setFont(new Font("Lato", Font.PLAIN, 11));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(405, 335, 130, 25);
		add(btnLimpar);
		
		JLabel lblCliqueNaOpo = new JLabel("Clique na opção desejada");
		lblCliqueNaOpo.setForeground(Color.GRAY);
		lblCliqueNaOpo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliqueNaOpo.setFont(new Font("Lato", Font.ITALIC, 11));
		lblCliqueNaOpo.setBounds(15, 80, 140, 20);
		add(lblCliqueNaOpo);
		
		JSeparator line_Menu_2 = new JSeparator();
		line_Menu_2.setForeground(Color.GRAY);
		line_Menu_2.setBounds(0, 0, 131, 1);
		add(line_Menu_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Lato", Font.PLAIN, 11));
		scrollPane.setBounds(10, 110, 559, 150);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTitulo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtAluno.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtData.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtQtd.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				
				txtTitulo.setBackground(Color.WHITE);
				txtAluno.setBackground(Color.WHITE);
				txtData.setBackground(Color.WHITE);
				txtQtd.setBackground(Color.WHITE);
			}
		});
		table.setFont(new Font("Lato", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		visualizar();
		
		JLabel lblDetalheDoEmprestimo = new JLabel("Detalhes do Empréstimo");
		lblDetalheDoEmprestimo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetalheDoEmprestimo.setFont(new Font("Lato", Font.BOLD, 13));
		lblDetalheDoEmprestimo.setBounds(15, 275, 150, 20);
		add(lblDetalheDoEmprestimo);
		
		JLabel lblAluno = new JLabel("Aluno");
		lblAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAluno.setFont(new Font("Lato", Font.PLAIN, 11));
		lblAluno.setBounds(25, 305, 40, 25);
		add(lblAluno);
		
		txtAluno = new JTextField();
		txtAluno.setFont(new Font("Lato", Font.PLAIN, 11));
		txtAluno.setEditable(false);
		txtAluno.setColumns(10);
		txtAluno.setBounds(90, 305, 125, 25);
		add(txtAluno);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 11));
		lblTitulo.setBounds(25, 335, 40, 25);
		add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Lato", Font.PLAIN, 11));
		txtTitulo.setEditable(false);
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(90, 335, 125, 25);
		add(txtTitulo);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataInicial.setFont(new Font("Lato", Font.PLAIN, 11));
		lblDataInicial.setBounds(25, 365, 65, 25);
		add(lblDataInicial);
		
		txtData = new JTextField();
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setFont(new Font("Lato", Font.PLAIN, 11));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(90, 365, 125, 25);
		add(txtData);
		
		JLabel lblQtd = new JLabel("Livros retirados");
		lblQtd.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtd.setFont(new Font("Lato", Font.PLAIN, 11));
		lblQtd.setBounds(235, 305, 85, 25);
		add(lblQtd);
		
		txtQtd = new JTextField();
		txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		txtQtd.setFont(new Font("Lato", Font.PLAIN, 11));
		txtQtd.setEditable(false);
		txtQtd.setColumns(10);
		txtQtd.setBounds(329, 305, 35, 25);
		add(txtQtd);
		
		JLabel lblDevolve = new JLabel("A devolver");
		lblDevolve.setHorizontalAlignment(SwingConstants.LEFT);
		lblDevolve.setFont(new Font("Lato", Font.BOLD, 11));
		lblDevolve.setBounds(235, 335, 65, 25);
		add(lblDevolve);
		
		txtDev = new JTextField();
		txtDev.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					
					int ret = Integer.parseInt(txtQtd.getText());
					int dev = Integer.parseInt(txtDev.getText());
					if (dev > ret && ret != 0) {
						txtQtd.setForeground(Color.RED);
						txtQtd.setFont(new Font("Lato", Font.BOLD, 11));
					} else {
						txtQtd.setForeground(Color.BLACK);
						txtQtd.setFont(new Font("Lato", Font.PLAIN, 11));
					}
				}
			}
		});
		txtDev.setHorizontalAlignment(SwingConstants.CENTER);
		txtDev.setText("1");
		txtDev.setFont(new Font("Lato", Font.PLAIN, 11));
		txtDev.setColumns(10);
		txtDev.setBounds(329, 335, 35, 25);
		add(txtDev);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(225, 300, 1, 95);
		add(separator);
		
		JLabel lblSwitch = new JLabel("Título");
		lblSwitch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSwitch.setFont(new Font("Lato", Font.BOLD, 11));
		lblSwitch.setBounds(369, 65, 30, 25);
		add(lblSwitch);
		
		JLabel btnSwitch = new JLabel("");
		btnSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (opcaoSwitch.equals("Aluno")) {
					btnSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch.png"));
					lblSwitch.setText("Título");
					opcaoSwitch = "Título";			
					
				} else if (opcaoSwitch.equals("Título")) {
					btnSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch2.png"));
					lblSwitch.setText("Aluno");
					opcaoSwitch = "Aluno";
				}
			}
		});
		btnSwitch.setIcon(new ImageIcon("C:\\Users\\Ryzen\\OneDrive\\Área de Trabalho\\biblioteca\\icons\\switch.png"));
		btnSwitch.setBounds(401, 65, 30, 25);
		add(btnSwitch);
		
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
		btnLoadAluno_1.setBounds(544, 65, 24, 24);
		add(btnLoadAluno_1);
		
	}
	
	private void visualizar() {
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);
						
			// Criação da consulta
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT "
					+ "logemprestar.id, "
					+ "livros.titulo, "
					+ "usuario.nome, "
					+ "dataEmp, "
					+ "qtdEmp "
					+ "FROM logemprestar "
					+ "JOIN livros ON logemprestar.idLivro = livros.id "
					+ "JOIN usuario ON logemprestar.idUser = usuario.id "
		            + "WHERE condicao = 'VIGENTE';"
					);
			
			DefaultTableModel d = new DefaultTableModel();
			d.setColumnIdentifiers(new Object[]{"ID", "Titulo", "Aluno", "Data", "Qtd"});

			SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

			// Loop de varredura dos dados
			while(rs.next()) {
			    String dataString = rs.getString("dataEmp");
			    Date data = formatoOriginal.parse(dataString);
			    String dataFormatada = formatoBrasileiro.format(data);
			    
			    d.addRow(new Object[] {
			        rs.getObject("logemprestar.id"),
			        rs.getObject("livros.titulo"),
			        rs.getObject("usuario.nome"),
			        dataFormatada,
			        rs.getObject("qtdEmp")
			    });
			}
			
			c.commit();
			c.close();
			
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
		
		coluna.getColumn(1).setPreferredWidth(250);
			
		coluna.getColumn(2).setPreferredWidth(250);
		
		coluna.getColumn(3).setPreferredWidth(100);
		coluna.getColumn(3).setCellRenderer(centerRenderer);
		
		coluna.getColumn(4).setPreferredWidth(30);
		coluna.getColumn(4).setCellRenderer(centerRenderer);
		
		table.doLayout();
	}
	private void limpar() {
		txtTitulo.setText(null);
		txtAluno.setText(null);
		txtData.setText(null);
		txtQtd.setText(null);
		table.clearSelection();
	}
	private void lançar_devolucao() {
		
		try {
			// Linhas de conexão
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
			c.setAutoCommit(false);

			// Registro no Log de Devolução
			String insert = "INSERT INTO logdevolver(idEmp, dataDev, qtdDev) VALUES (?, ?, ?)";
			PreparedStatement p = c.prepareStatement(insert);
			p.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
			String dataAgora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			p.setString(2, dataAgora);
			p.setString(3, txtDev.getText().toString());
			p.execute();
			
			// Acréscimo ao estoque de livros
			String update1 = "UPDATE livros SET qtdEstoque = qtdEstoque + ? WHERE titulo = ?";
			PreparedStatement p1 = c.prepareStatement(update1);
			p1.setString(1, txtDev.getText().toString());
			p1.setString(2, table.getValueAt(table.getSelectedRow(), 1).toString());
			p1.execute();
			
			// Correção do Log de Empréstimo
			String update2 = "UPDATE logemprestar SET qtdEmp = qtdEmp - ? WHERE id = ?";
			PreparedStatement p2 = c.prepareStatement(update2);
			p2.setString(1, txtDev.getText().toString());
			p2.setString(2, table.getValueAt(table.getSelectedRow(), 0).toString());
			p2.execute();
			
			// Mudança na condição do empréstimo, caso seja quitado
			String select = "SELECT qtdEmp FROM logemprestar WHERE id = ?";
			PreparedStatement p3 = c.prepareStatement(select);
			p3.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
			ResultSet rs = p3.executeQuery();
			String resultado = null;
			if (rs.next()) {
				resultado = rs.getString("qtdEmp");
			}
			if (resultado.equals("0")) {
				String mudar = "UPDATE logemprestar SET condicao = ? WHERE id = ?";
				PreparedStatement p4 = c.prepareStatement(mudar);
				p4.setString(1, "DEVOLVIDO");
				p4.setString(2, table.getValueAt(table.getSelectedRow(), 0).toString());
				p4.execute();
			}
			
			// Encerramento das operações SQL
			c.commit();
			c.close();
			
			// Limpeza dos campos, visualização e confirmação pro usuário
			JOptionPane.showMessageDialog(null, "Devolução registrada com sucesso.");
			visualizar();
			limpar();
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados");
			ex.printStackTrace();
		}
	}
	
	public String opcaoSwitch = "Título";
	public String filtrado() {
		if(opcaoSwitch.equals("Título")) {
			return "livros.titulo";
		} else {
			return "usuario.nome";
		}
	}
	
/* Aqui acaba o código */ }