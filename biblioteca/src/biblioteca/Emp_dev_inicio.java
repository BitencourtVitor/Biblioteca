package biblioteca;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Emp_dev_inicio extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField txtEmprestimo;
	public JTextField txtDevolucao;

	/**
	 * Create the panel.
	 */
	public Emp_dev_inicio() {
		
		JLabel txtDevolucao = new JLabel("Devolução");
		JLabel txtEmprestimo = new JLabel("Empréstimo");
		
		setBackground(new Color(255, 255, 255));
        setBounds(0, 0, 639, 639);
		setFont(new Font("Lato", Font.PLAIN, 15));
		setLayout(null);
		
		JLabel titulo = new JLabel("Controle de Livros");
		titulo.setBounds(30, 20, 605, 20);
		titulo.setFont(new Font("Lato", Font.PLAIN, 20));
		add(titulo);
		
		JLabel marca = new JLabel("");
		marca.setBackground(Color.GRAY);
		marca.setOpaque(true);
		marca.setBounds(0, 0, 10, 101);
		add(marca);
		
		JLabel marca_Escolha_Emp = new JLabel("");
		marca_Escolha_Emp.setVisible(false);
		marca_Escolha_Emp.setOpaque(true);
		marca_Escolha_Emp.setBackground(Color.GRAY);
		marca_Escolha_Emp.setBounds(30, 140, 130, 2);
		add(marca_Escolha_Emp);
		
		JLabel marca_Escolha_Dev = new JLabel("");
		marca_Escolha_Dev.setVisible(false);
		marca_Escolha_Dev.setOpaque(true);
		marca_Escolha_Dev.setBackground(Color.GRAY);
		marca_Escolha_Dev.setBounds(160, 140, 130, 2);
		add(marca_Escolha_Dev);
		
		JLabel txt_1 = new JLabel("Alimentação dos LOGs de Entrada e Saída");
		txt_1.setFont(new Font("Lato", Font.ITALIC, 15));
		txt_1.setBounds(30, 50, 400, 20);
		add(txt_1);
		
		JLabel txt_2 = new JLabel("Inserção de informações sobre retirada e/ou devolução de livros.");
		txt_2.setFont(new Font("Lato", Font.PLAIN, 15));
		txt_2.setBounds(30, 70, 605, 20);
		add(txt_2);
		
		JPanel panel_interno = new JPanel();
		panel_interno.setBackground(Color.WHITE);
		panel_interno.setBounds(30, 180, 579, 436);
		add(panel_interno);
		panel_interno.setLayout(null);
		
		JSeparator line_Menu = new JSeparator();
		line_Menu.setBounds(0, 0, 579, 10);
		panel_interno.add(line_Menu);
		line_Menu.setForeground(Color.GRAY);
		
		JSeparator line_emp_sup = new JSeparator();
		line_emp_sup.setVisible(false);
		line_emp_sup.setForeground(Color.GRAY);
		line_emp_sup.setBounds(30, 140, 130, 1);
		add(line_emp_sup);
		
		JSeparator line_emp_esq = new JSeparator();
		line_emp_esq.setOrientation(SwingConstants.VERTICAL);
		line_emp_esq.setVisible(false);
		line_emp_esq.setForeground(Color.GRAY);
		line_emp_esq.setBounds(30, 140, 1, 40);
		add(line_emp_esq);
		
		JSeparator line_central = new JSeparator();
		line_central.setOrientation(SwingConstants.VERTICAL);
		line_central.setForeground(Color.GRAY);
		line_central.setBounds(160, 140, 1, 40);
		add(line_central);
		
		JSeparator line_dev_sup = new JSeparator();
		line_dev_sup.setVisible(false);
		line_dev_sup.setForeground(Color.GRAY);
		line_dev_sup.setBounds(160, 140, 130, 1);
		add(line_dev_sup);
		
		JSeparator line_dev_dir = new JSeparator();
		line_dev_dir.setVisible(false);
		line_dev_dir.setOrientation(SwingConstants.VERTICAL);
		line_dev_dir.setForeground(Color.GRAY);
		line_dev_dir.setBounds(289, 140, 1, 40);
		add(line_dev_dir);
		
		JLabel txt_descricao_panel_interno = new JLabel("Por gentileza, selecione acima qual das operações você deseja realizar.");
		txt_descricao_panel_interno.setFont(new Font("Lato", Font.PLAIN, 15));
		txt_descricao_panel_interno.setBounds(15, 15, 500, 20);
		panel_interno.add(txt_descricao_panel_interno);
		
		txtEmprestimo.setOpaque(true);
		txtEmprestimo.setBackground(SystemColor.menu);
		txtEmprestimo.setBounds(30, 140, 130, 40);
		add(txtEmprestimo);
		txtEmprestimo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtEmprestimo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marca_Escolha_Emp.setBounds(30, 140, 130, 4);
				
				marca_Escolha_Dev.setVisible(false);
				marca_Escolha_Dev.setBounds(160, 140, 130, 2);
				
				// Painel inferior
				chamar_emprestimo(panel_interno);
				
				// Estilização: Empréstimo
				line_emp_esq.setVisible(true);
				line_emp_sup.setVisible(true);
				txtEmprestimo.setBackground(Color.WHITE);
				txtEmprestimo.setFont(new Font("Lato", Font.BOLD, 15));
				
				// Estilização: Devolução
				line_dev_sup.setVisible(false);
				line_dev_dir.setVisible(false);
				txtDevolucao.setFont(new Font("Lato", Font.PLAIN, 15));
				txtDevolucao.setBackground(SystemColor.menu);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				marca_Escolha_Emp.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(marca_Escolha_Emp.getHeight() != 4) {
					marca_Escolha_Emp.setVisible(false);
				}
			}
		});
		txtEmprestimo.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmprestimo.setFont(new Font("Lato", Font.PLAIN, 15));
		
		txtDevolucao.setOpaque(true);
		txtDevolucao.setBackground(SystemColor.menu);
		txtDevolucao.setBounds(160, 140, 130, 40);
		add(txtDevolucao);
		txtDevolucao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtDevolucao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marca_Escolha_Dev.setBounds(160, 140, 130, 4);
				
				marca_Escolha_Emp.setVisible(false);
				marca_Escolha_Emp.setBounds(30, 140, 130, 2);
				// Painel inferior
				chamar_devolucao(panel_interno);
				
				// Estilização: Devolução
				line_dev_dir.setVisible(true);
				line_dev_sup.setVisible(true);
				txtDevolucao.setBackground(Color.WHITE);
				txtDevolucao.setFont(new Font("Lato", Font.BOLD, 15));
				
				// Estilização: Empréstimo
				line_emp_sup.setVisible(false);
				line_emp_esq.setVisible(false);
				txtEmprestimo.setFont(new Font("Lato", Font.PLAIN, 15));
				txtEmprestimo.setBackground(SystemColor.menu);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				marca_Escolha_Dev.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(marca_Escolha_Dev.getHeight() != 4) {
					marca_Escolha_Dev.setVisible(false);
				}
			}
		});
		txtDevolucao.setHorizontalAlignment(SwingConstants.CENTER);
		txtDevolucao.setFont(new Font("Lato", Font.PLAIN, 15));			
	}
	
    private void chamar_emprestimo(JPanel panel_interno) {
    	panel_interno.removeAll();
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setBounds(0, 0, panel_interno.getWidth(), panel_interno.getHeight());
        panel_interno.add(emprestimo);
        panel_interno.revalidate();
        panel_interno.repaint();
    }
    
    private void chamar_devolucao(JPanel panel_interno) {
    	panel_interno.removeAll();
        Devolucao devolucao = new Devolucao();
        devolucao.setBounds(0, 0, panel_interno.getWidth(), panel_interno.getHeight());
        panel_interno.add(devolucao);
        panel_interno.revalidate();
        panel_interno.repaint();
    }

/* Aqui acaba o código */ }