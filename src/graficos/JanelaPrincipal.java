package graficos;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        inicializarComponentes();
        configurarIcone();
    }

    private void inicializarComponentes() {
        setTitle("Sistema de Gerenciamento de Estacionamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarPainelCabecalho(), BorderLayout.NORTH);
        add(criarPainelBotoes(), BorderLayout.CENTER);
    }

    private JPanel criarPainelCabecalho() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(52, 73, 94));
        painel.setPreferredSize(new Dimension(0, 120));

        JPanel painelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelLogo.setBackground(new Color(52, 73, 94));

        JLabel logoLabel = criarLogoImagem();
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setBackground(new Color(52, 73, 94));

        JLabel titulo = new JLabel("Gerenciador de Estacionamento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        JLabel subtitulo = new JLabel("Sistema de Gestão Completa");
        subtitulo.setForeground(new Color(189, 195, 199));
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        painelTextos.add(titulo);
        painelTextos.add(subtitulo);

        painelLogo.add(logoLabel);
        painelLogo.add(painelTextos);

        painel.add(painelLogo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 30, 30));
        painel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        painel.setBackground(new Color(236, 240, 241));

        JButton btnClientes = criarBotao("Gerenciar Clientes", "👥", "Cadastro e controle de clientes");
        JButton btnFuncionarios = criarBotao("Gerenciar Funcionários", "👨‍💼", "Gestão da equipe de trabalho");
        JButton btnVeiculos = criarBotao("Gerenciar Veículos", "🚗", "Controle de veículos cadastrados");
        JButton btnSair = criarBotao("Sair do Sistema", "🚪", "Encerrar aplicação");

        btnClientes.addActionListener(e -> new JanelaCrudCliente().setVisible(true));
        btnFuncionarios.addActionListener(e -> new JanelaCrudFuncionario().setVisible(true));
        btnVeiculos.addActionListener(e -> new JanelaCrudVeiculo().setVisible(true));
        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente sair do sistema?",
                    "Confirmar Saída",
                    JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        painel.add(btnClientes);
        painel.add(btnFuncionarios);
        painel.add(btnVeiculos);
        painel.add(btnSair);

        return painel;
    }

    private JButton criarBotao(String texto, String icone, String descricao) {
        JButton botao = new JButton();
        botao.setLayout(new BorderLayout());

        JLabel lblIcone = new JLabel(icone, SwingConstants.CENTER);
        lblIcone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

        JLabel lblTexto = new JLabel(texto, SwingConstants.CENTER);
        lblTexto.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDescricao = new JLabel(descricao, SwingConstants.CENTER);
        lblDescricao.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDescricao.setForeground(new Color(127, 140, 141));

        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setOpaque(false);
        painelTextos.add(lblTexto);
        painelTextos.add(Box.createVerticalStrut(3));
        painelTextos.add(lblDescricao);

        botao.add(lblIcone, BorderLayout.NORTH);
        botao.add(painelTextos, BorderLayout.CENTER);

        botao.setPreferredSize(new Dimension(200, 120));
        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(52, 73, 94));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(52, 152, 219));
                botao.setForeground(Color.WHITE);
                lblDescricao.setForeground(new Color(236, 240, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.WHITE);
                botao.setForeground(new Color(52, 73, 94));
                lblDescricao.setForeground(new Color(127, 140, 141));
            }
        });

        return botao;
    }

    private void configurarIcone() {
        try {
            URL iconURL = getClass().getResource("/recursos/parking-icon.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            } else {
                criarIconePersonalizado();
            }
        } catch (Exception e) {
            criarIconePersonalizado();
        }
    }

    private void criarIconePersonalizado() {
        try {
            int size = 32;
            java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(size, size,
                    java.awt.image.BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(new Color(52, 73, 94));
            g2d.fillRoundRect(2, 2, size - 4, size - 4, 8, 8);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics fm = g2d.getFontMetrics();
            String text = "P";
            int x = (size - fm.stringWidth(text)) / 2;
            int y = ((size - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(text, x, y);

            g2d.dispose();
            setIconImage(image);
        } catch (Exception e) {
            System.err.println("Erro ao criar ícone personalizado: " + e.getMessage());
        }
    }

    private JLabel criarLogoImagem() {
        try {
            URL logoURL = getClass().getResource("/recursos/parking-logo.png");
            if (logoURL != null) {
                ImageIcon originalIcon = new ImageIcon(logoURL);
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(scaledImg));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar logo: " + e.getMessage());
        }

        JLabel logoLabel = new JLabel("🅿️");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        return logoLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new JanelaPrincipal().setVisible(true);
        });
    }
}
