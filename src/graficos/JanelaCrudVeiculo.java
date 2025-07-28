package graficos;

import dados.DadosInterface;
import dados.Veiculo;
import negocio.RepositorioVeiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JanelaCrudVeiculo extends JFrame {
    private RepositorioVeiculo repositorio;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JTextField txtMarcaModelo, txtAno, txtPlaca;
    private JComboBox<String> cmbTipo;

    public JanelaCrudVeiculo() {
        this.repositorio = RepositorioVeiculo.getInstance();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciamento de Veículos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Marca/Modelo:"), gbc);
        gbc.gridx = 1;
        txtMarcaModelo = new JTextField(20);
        painel.add(txtMarcaModelo, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        painel.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 3;
        txtAno = new JTextField(10);
        painel.add(txtAno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1;
        txtPlaca = new JTextField(20);
        painel.add(txtPlaca, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        painel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 3;
        cmbTipo = new JComboBox<>(new String[] { "Carro", "Moto", "SUV", "Caminhão", "Van" });
        painel.add(cmbTipo, gbc);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Veículos"));

        String[] colunas = { "Marca/Modelo", "Ano", "Placa", "Tipo" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    carregarVeiculoSelecionado();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnFechar = new JButton("Fechar");

        btnAdicionar.addActionListener(e -> adicionarVeiculo());
        btnAtualizar.addActionListener(e -> atualizarVeiculo());
        btnRemover.addActionListener(e -> removerVeiculo());
        btnLimpar.addActionListener(e -> limparCampos());
        btnFechar.addActionListener(e -> dispose());

        painel.add(btnAdicionar);
        painel.add(btnAtualizar);
        painel.add(btnRemover);
        painel.add(btnLimpar);
        painel.add(btnFechar);

        return painel;
    }

    private void adicionarVeiculo() {
        try {
            if (validarCampos()) {
                Veiculo veiculo = new Veiculo(
                        txtMarcaModelo.getText().trim(),
                        txtAno.getText().trim(),
                        txtPlaca.getText().trim(),
                        (String) cmbTipo.getSelectedItem());

                repositorio.adicionar(veiculo);
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Veículo adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar veículo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarVeiculo() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para atualizar!");
            return;
        }

        try {
            if (validarCampos()) {
                Veiculo veiculo = new Veiculo(
                        txtMarcaModelo.getText().trim(),
                        txtAno.getText().trim(),
                        txtPlaca.getText().trim(),
                        (String) cmbTipo.getSelectedItem());

                repositorio.atualizar(veiculo);
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Veículo atualizado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar veículo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerVeiculo() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para remover!");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover este veículo?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            String placa = (String) modeloTabela.getValueAt(linhaSelecionada, 2);
            repositorio.remover(placa);
            carregarDados();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!");
        }
    }

    private void carregarVeiculoSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            txtMarcaModelo.setText((String) modeloTabela.getValueAt(linhaSelecionada, 0));
            txtAno.setText((String) modeloTabela.getValueAt(linhaSelecionada, 1));
            txtPlaca.setText((String) modeloTabela.getValueAt(linhaSelecionada, 2));
            cmbTipo.setSelectedItem(modeloTabela.getValueAt(linhaSelecionada, 3));
        }
    }

    private boolean validarCampos() {
        if (txtMarcaModelo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Marca/Modelo é obrigatório!");
            return false;
        }
        if (txtAno.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ano é obrigatório!");
            return false;
        }
        if (txtPlaca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa é obrigatória!");
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txtMarcaModelo.setText("");
        txtAno.setText("");
        txtPlaca.setText("");
        cmbTipo.setSelectedIndex(0);
        tabela.clearSelection();
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        for (DadosInterface veiculo : repositorio.listarTodos()) {
            Object[] linha = {
                    veiculo.getCaracteristicasSemFormatacao()
            };
            modeloTabela.addRow(linha);
        }
    }
}
