package graficos;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JanelaPrincipal extends JFrame {

    // Mapa declarativo com URLs do Icons8
    private static final Map<String, String> URLS_ICONES = new HashMap<String, String>() {{
        put("clientes", "https://img.icons8.com/fluency/48/person-male.png");
        put("funcionarios", "https://img.icons8.com/fluency/48/manager.png");
        put("veiculos", "https://img.icons8.com/fluency/48/car.png");
        put("sair", "https://img.icons8.com/fluency/48/exit.png");
    }};

    private static final String LOGO_URL = "https://img.icons8.com/fluency/64/parking.png";

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
        if (logoLabel != null) {
            logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        }

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

        if (logoLabel != null) {
            painelLogo.add(logoLabel);
        }
        painelLogo.add(painelTextos);

        painel.add(painelLogo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 30, 30));
        painel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        painel.setBackground(new Color(236, 240, 241));

        JButton btnClientes = criarBotao("Gerenciar Clientes", "clientes", "Cadastro e controle de clientes");
        JButton btnFuncionarios = criarBotao("Gerenciar Funcionários", "funcionarios", "Gestão da equipe de trabalho");
        JButton btnVeiculos = criarBotao("Gerenciar Veículos", "veiculos", "Controle de veículos cadastrados");
        JButton btnSair = criarBotao("Sair do Sistema", "sair", "Encerrar aplicação");

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

    private JButton criarBotao(String texto, String tipoIcone, String descricao) {
        JButton botao = new JButton();
        botao.setLayout(new BorderLayout());

        JLabel lblIcone = carregarIcone(tipoIcone);

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

        if (lblIcone != null) {
            botao.add(lblIcone, BorderLayout.NORTH);
            botao.add(painelTextos, BorderLayout.CENTER);
        } else {
            botao.add(painelTextos, BorderLayout.CENTER);
        }

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

    private JLabel carregarIcone(String tipo) {
        String urlString = URLS_ICONES.get(tipo);
        if (urlString == null) {
            System.err.println("Tipo de ícone não encontrado: " + tipo);
            return null;
        }

        try {
            URL iconURL = new URL(urlString);
            ImageIcon originalIcon = new ImageIcon(iconURL);
            
            // Aguardar carregamento
            while (originalIcon.getImageLoadStatus() == MediaTracker.LOADING) {
                Thread.sleep(10);
            }
            
            if (originalIcon.getIconWidth() > 0 && originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
                System.out.println("Ícone " + tipo + " carregado de Icons8");
                return new JLabel(new ImageIcon(scaledImg), SwingConstants.CENTER);
            }
        } catch (Exception e) {
            System.err.println("Falha ao carregar " + tipo + " de Icons8: " + e.getMessage());
        }
        
        System.err.println("Ícone " + tipo + " não pôde ser carregado");
        return null;
    }

    private void configurarIcone() {
        try {
            URL iconURL = getClass().getResource("/recursos/parking-icon.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
                System.out.println("Ícone da janela carregado do arquivo local");
            } else {
                System.err.println("Ícone local não encontrado");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone da janela: " + e.getMessage());
        }
    }

    private JLabel criarLogoImagem() {
        try {
            URL logoURL = new URL(LOGO_URL);
            ImageIcon originalIcon = new ImageIcon(logoURL);
            
            // Aguardar carregamento
            while (originalIcon.getImageLoadStatus() == MediaTracker.LOADING) {
                Thread.sleep(10);
            }
            
            if (originalIcon.getIconWidth() > 0 && originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                System.out.println("✅ Logo carregado do Icons8");
                return new JLabel(new ImageIcon(scaledImg));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar logo do Icons8: " + e.getMessage());
        }
        
        System.err.println("Logo não pôde ser carregado");
        return null;
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
