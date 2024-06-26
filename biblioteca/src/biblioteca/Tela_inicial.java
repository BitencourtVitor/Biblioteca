package biblioteca;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Tela_inicial extends JFrame {

    private JPanel contentPane;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Tela_inicial frame = new Tela_inicial();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Tela_inicial() {
    	setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setBackground(new Color(255, 255, 255));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel titulo = new JLabel("Sistema de Gerenciamento de Biblioteca");
        titulo.setFont(new Font("Lato", Font.PLAIN, 20));
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
        titulo.setBounds(117, 17, 590, 32);
        contentPane.add(titulo);
        
        JLabel leste_bg = new JLabel("");
        leste_bg.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\bg_biblioteca.png"));
        leste_bg.setBounds(768, 0, 256, 700);
        contentPane.add(leste_bg);
        
        JSeparator separator_menu_lateral = new JSeparator();
        separator_menu_lateral.setForeground(new Color(153, 153, 153));
        separator_menu_lateral.setOrientation(SwingConstants.VERTICAL);
        separator_menu_lateral.setBounds(130, 60, 5, 640);
        contentPane.add(separator_menu_lateral);
        
        JSeparator separator_titulo = new JSeparator();
        separator_titulo.setForeground(new Color(153, 153, 153));
        separator_titulo.setBounds(0, 60, 768, 5);
        contentPane.add(separator_titulo);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(131, 61, 639, 639);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Bem vindo!");
        lblNewLabel_1.setFont(new Font("Lato", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(30, 20, 150, 25);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Selecione uma das opções do Menu ao lado para começar.");
        lblNewLabel_1_1.setFont(new Font("Lato", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(30, 50, 400, 25);
        panel.add(lblNewLabel_1_1);
        
        JLabel lbl_Livro = new JLabel("");
        lbl_Livro.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\open-book.png"));
        lbl_Livro.setBounds(49, 15, 32, 32);
        contentPane.add(lbl_Livro);
        
        JLabel lbl_Users = new JLabel("");
        lbl_Users.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Users.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_usuarios(panel);
        	}
        });
        lbl_Users.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\user (1).png"));
        lbl_Users.setBounds(49, 186, 32, 32);
        contentPane.add(lbl_Users);
        
        JLabel users = new JLabel("Usuários");
        users.setCursor(new Cursor(Cursor.HAND_CURSOR));
        users.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_usuarios(panel);
        	}
        });
        users.setHorizontalAlignment(SwingConstants.CENTER);
        users.setFont(new Font("Lato", Font.PLAIN, 15));
        users.setBounds(0, 218, 130, 20);
        contentPane.add(users);
        
        JLabel lbl_Author = new JLabel("");
        lbl_Author.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Author.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_autores(panel);
        	}
        });
        lbl_Author.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Author.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\poem.png"));
        lbl_Author.setBounds(49, 386, 32, 32);
        contentPane.add(lbl_Author);
        
        JLabel autores = new JLabel("Autores");
        autores.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_autores(panel);
        	}
        });
        autores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        autores.setHorizontalAlignment(SwingConstants.CENTER);
        autores.setFont(new Font("Lato", Font.PLAIN, 15));
        autores.setBounds(0, 418, 130, 20);
        contentPane.add(autores);
        
        JLabel lbl_Books = new JLabel("");
        lbl_Books.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Books.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_livros(panel);        	
        	}
        });
        lbl_Books.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\books.png"));
        lbl_Books.setBounds(49, 286, 32, 32);
        contentPane.add(lbl_Books);
        
        JLabel livros = new JLabel("Livros");
        livros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        livros.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_livros(panel);        	
        	}
        });
        livros.setHorizontalAlignment(SwingConstants.CENTER);
        livros.setFont(new Font("Lato", Font.PLAIN, 15));
        livros.setBounds(0, 318, 130, 20);
        contentPane.add(livros);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(153, 153, 153));
        separator_1.setBounds(0, 261, 130, 5);
        contentPane.add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(new Color(153, 153, 153));
        separator_2.setBounds(0, 361, 130, 5);
        contentPane.add(separator_2);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(new Color(153, 153, 153));
        separator_3.setBounds(0, 461, 130, 5);
        contentPane.add(separator_3);
        
        JLabel lbl_Move = new JLabel("");
        lbl_Move.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Move.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_entrada_saida(panel);
        	}
        });
        lbl_Move.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\transacao.png"));
        lbl_Move.setBounds(37, 86, 56, 32);
        contentPane.add(lbl_Move);
        
        JLabel emp_dev = new JLabel("Mover Livros");
        emp_dev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        emp_dev.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_entrada_saida(panel);
        	}
        });
        emp_dev.setHorizontalAlignment(SwingConstants.CENTER);
        emp_dev.setFont(new Font("Lato", Font.BOLD, 15));
        emp_dev.setBounds(0, 118, 130, 20);
        contentPane.add(emp_dev);
        
        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(new Color(153, 153, 153));
        separator_1_1.setBounds(0, 161, 130, 5);
        contentPane.add(separator_1_1);
        
        JLabel lblSair = new JLabel("Sair");
        lblSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSair.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        	}
        });
        lblSair.setIcon(new ImageIcon("D:\\Arquivos\\Downloads\\close (1).png"));
        lblSair.setHorizontalAlignment(SwingConstants.CENTER);
        lblSair.setFont(new Font("Lato", Font.PLAIN, 15));
        lblSair.setBounds(0, 670, 130, 20);
        contentPane.add(lblSair);
        
        JLabel lblSair_BG = new JLabel("");
        lblSair_BG.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSair_BG.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        	}
        });
        lblSair_BG.setOpaque(true);
        lblSair_BG.setBackground(new Color(240, 128, 128));
        lblSair_BG.setBounds(0, 660, 130, 40);
        contentPane.add(lblSair_BG);
        
        JLabel bg_Autores = new JLabel("");
        bg_Autores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg_Autores.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_autores(panel);
        	}
        });
        bg_Autores.setBounds(0, 361, 130, 100);
        contentPane.add(bg_Autores);
        
        JLabel bg_Users = new JLabel("");
        bg_Users.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg_Users.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_usuarios(panel);        	
        	}
        });
        bg_Users.setBounds(0, 161, 130, 100);
        contentPane.add(bg_Users);
        
        JLabel bg_Livros = new JLabel("");
        bg_Livros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg_Livros.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_livros(panel);        	
        	}
        });
        bg_Livros.setBounds(0, 261, 130, 100);
        contentPane.add(bg_Livros);
        
        JLabel bg_Users_1 = new JLabel("");
        bg_Users_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg_Users_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chamar_entrada_saida(panel);    	
        	}
        });
        bg_Users_1.setBounds(0, 60, 130, 100);
        contentPane.add(bg_Users_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Vitor Manoel Bitencourt Araújo");
        lblNewLabel_1_1_1.setForeground(new Color(192, 192, 192));
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setBounds(0, 634, 130, 25);
        contentPane.add(lblNewLabel_1_1_1);
        lblNewLabel_1_1_1.setFont(new Font("Lato", Font.PLAIN, 7));
  
   }

    private void chamar_autores(JPanel panel) {
        panel.removeAll();
        Autores_crud authors_panel = new Autores_crud();
        authors_panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(authors_panel);
        panel.revalidate();
        panel.repaint();
    }

    private void chamar_usuarios(JPanel panel) {
        panel.removeAll();
        Usuarios_crud users_panel = new Usuarios_crud();
        users_panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(users_panel);
        panel.revalidate();
        panel.repaint();
    }

    private void chamar_livros(JPanel panel) {
        panel.removeAll();
        Livros_crud livros_crud = new Livros_crud();
        livros_crud.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(livros_crud);
        panel.revalidate();
        panel.repaint();
    }
    
    private void chamar_entrada_saida(JPanel panel) {
        panel.removeAll();
        Emp_dev_inicio emp_dev_logs = new Emp_dev_inicio();
        emp_dev_logs.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(emp_dev_logs);
        panel.revalidate();
        panel.repaint();
    }
    
/* Aqui acaba o código */ }