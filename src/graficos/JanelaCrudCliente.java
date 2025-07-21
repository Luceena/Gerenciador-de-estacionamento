package graficos;

import dados.Cliente;
import dados.Veiculo;
import negocio.RepositorioCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JanelaCrudCliente extends JFrame {
    private RepositorioCliente repositorio;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JTextField txtNome, txtCpf, txtIdade;
    private JCheckBox chkAdimplente;
    private JComboBox<String> cmbClassificacao;
    private JSpinner spnFrequencia;

    public JanelaCrudCliente() {
        this.repositorio = RepositorioCliente.getInstance();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciamento de Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        txtCpf = new JTextField(20);
        painel.add(txtCpf, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        painel.add(new JLabel("Idade:"), gbc);
        gbc.gridx = 3;
        txtIdade = new JTextField(10);
        painel.add(txtIdade, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        painel.add(new JLabel("Frequência:"), gbc);
        gbc.gridx = 3;
        spnFrequencia = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        painel.add(spnFrequencia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Classificação:"), gbc);
        gbc.gridx = 1;
        cmbClassificacao = new JComboBox<>(new String[] { "neutra", "VIP", "regular", "problemático" });
        painel.add(cmbClassificacao, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        chkAdimplente = new JCheckBox("Adimplente");
        chkAdimplente.setSelected(true);
        painel.add(chkAdimplente, gbc);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));

        String[] colunas = { "Nome", "CPF", "Idade", "Frequência", "Classificação", "Adimplente" };
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
                    carregarClienteSelecionado();
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

        btnAdicionar.addActionListener(e -> adicionarCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnRemover.addActionListener(e -> removerCliente());
        btnLimpar.addActionListener(e -> limparCampos());
        btnFechar.addActionListener(e -> dispose());

        painel.add(btnAdicionar);
        painel.add(btnAtualizar);
        painel.add(btnRemover);
        painel.add(btnLimpar);
        painel.add(btnFechar);

        return painel;
    }

    private void adicionarCliente() {
        try {
            if (validarCampos()) {
                Cliente cliente = new Cliente(
                        new ArrayList<Veiculo>(),
                        txtNome.getText().trim(),
                        txtCpf.getText().trim(),
                        Integer.parseInt(txtIdade.getText().trim()));

                cliente.setFrequencia((Integer) spnFrequencia.getValue());
                cliente.setClassificacao((String) cmbClassificacao.getSelectedItem());
                cliente.setAdimplente(chkAdimplente.isSelected());

                repositorio.adicionar(cliente);
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCliente() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar!");
            return;
        }

        try {
            if (validarCampos()) {
                String cpfOriginal = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
                Cliente cliente = repositorio.buscarPorCpf(cpfOriginal);

                if (cliente != null) {
                    cliente.setNome(txtNome.getText().trim());
                    cliente.setCpf(txtCpf.getText().trim());
                    cliente.setIdade(Integer.parseInt(txtIdade.getText().trim()));
                    cliente.setFrequencia((Integer) spnFrequencia.getValue());
                    cliente.setClassificacao((String) cmbClassificacao.getSelectedItem());
                    cliente.setAdimplente(chkAdimplente.isSelected());

                    repositorio.atualizar(cliente);
                    carregarDados();
                    limparCampos();
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCliente() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para remover!");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover este cliente?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
            repositorio.remover(cpf);
            carregarDados();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
        }
    }

    private void carregarClienteSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            txtNome.setText((String) modeloTabela.getValueAt(linhaSelecionada, 0));
            txtCpf.setText((String) modeloTabela.getValueAt(linhaSelecionada, 1));
            txtIdade.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
            spnFrequencia.setValue(modeloTabela.getValueAt(linhaSelecionada, 3));
            cmbClassificacao.setSelectedItem(modeloTabela.getValueAt(linhaSelecionada, 4));
            chkAdimplente.setSelected((Boolean) modeloTabela.getValueAt(linhaSelecionada, 5));
        }
    }

    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
            return false;
        }
        if (txtCpf.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF é obrigatório!");
            return false;
        }
        if (txtIdade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Idade é obrigatória!");
            return false;
        }

        try {
            Integer.parseInt(txtIdade.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número válido!");
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtIdade.setText("");
        spnFrequencia.setValue(0);
        cmbClassificacao.setSelectedIndex(0);
        chkAdimplente.setSelected(true);
        tabela.clearSelection();
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        for (Cliente cliente : repositorio.listarTodos()) {
            Object[] linha = {
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getIdade(),
                    cliente.getFrequencia(),
                    cliente.getClassificacao(),
                    cliente.isAdimplente()
            };
            modeloTabela.addRow(linha);
        }
    }
}
