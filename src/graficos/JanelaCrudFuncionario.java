package graficos;

import dados.Funcionario;
import negocio.RepositorioFuncionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JanelaCrudFuncionario extends JFrame {
    private RepositorioFuncionario repositorio;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JTextField txtNome, txtCpf, txtSalario, txtTempoAtendimento;
    private JSpinner spnAvaliacao;

    public JanelaCrudFuncionario() {
        this.repositorio = RepositorioFuncionario.getInstance();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciamento de Funcionários");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Dados do Funcionário"));
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
        painel.add(new JLabel("Salário:"), gbc);
        gbc.gridx = 3;
        txtSalario = new JTextField(15);
        painel.add(txtSalario, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        painel.add(new JLabel("Avaliação (0-5):"), gbc);
        gbc.gridx = 3;
        spnAvaliacao = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1));
        painel.add(spnAvaliacao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Tempo Atendimento:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtTempoAtendimento = new JTextField(30);
        txtTempoAtendimento.setToolTipText("Ex: 08:00-17:00");
        painel.add(txtTempoAtendimento, gbc);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Funcionários"));

        String[] colunas = { "Nome", "CPF", "Salário", "Avaliação", "Tempo Atendimento" };
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
                    carregarFuncionarioSelecionado();
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

        btnAdicionar.addActionListener(e -> adicionarFuncionario());
        btnAtualizar.addActionListener(e -> atualizarFuncionario());
        btnRemover.addActionListener(e -> removerFuncionario());
        btnLimpar.addActionListener(e -> limparCampos());
        btnFechar.addActionListener(e -> dispose());

        painel.add(btnAdicionar);
        painel.add(btnAtualizar);
        painel.add(btnRemover);
        painel.add(btnLimpar);
        painel.add(btnFechar);

        return painel;
    }

    private void adicionarFuncionario() {
        try {
            if (validarCampos()) {
                Funcionario funcionario = new Funcionario(
                        Double.parseDouble(txtSalario.getText().trim()),
                        (Double) spnAvaliacao.getValue(),
                        txtTempoAtendimento.getText().trim(),
                        txtNome.getText().trim(),
                        txtCpf.getText().trim());

                repositorio.adicionar(funcionario);
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Funcionário adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar funcionário: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarFuncionario() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para atualizar!");
            return;
        }

        try {
            if (validarCampos()) {
                String cpfOriginal = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

                Funcionario funcionario = new Funcionario(
                        Double.parseDouble(txtSalario.getText().trim()),
                        (Double) spnAvaliacao.getValue(),
                        txtTempoAtendimento.getText().trim(),
                        txtNome.getText().trim(),
                        txtCpf.getText().trim());

                repositorio.atualizar(funcionario);
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerFuncionario() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para remover!");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover este funcionário?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
            repositorio.remover(cpf);
            carregarDados();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso!");
        }
    }

    private void carregarFuncionarioSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            txtNome.setText((String) modeloTabela.getValueAt(linhaSelecionada, 0));
            txtCpf.setText((String) modeloTabela.getValueAt(linhaSelecionada, 1));
            txtSalario.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
            spnAvaliacao.setValue(modeloTabela.getValueAt(linhaSelecionada, 3));
            txtTempoAtendimento.setText((String) modeloTabela.getValueAt(linhaSelecionada, 4));
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
        if (txtSalario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Salário é obrigatório!");
            return false;
        }
        if (txtTempoAtendimento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tempo de atendimento é obrigatório!");
            return false;
        }

        try {
            Double.parseDouble(txtSalario.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salário deve ser um número válido!");
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtSalario.setText("");
        txtTempoAtendimento.setText("");
        spnAvaliacao.setValue(0.0);
        tabela.clearSelection();
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        for (Funcionario funcionario : repositorio.listarTodos()) {
            Object[] linha = {
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getSalario(),
                    funcionario.getAvaliacao(),
                    funcionario.getTempoAtendimento()
            };
            modeloTabela.addRow(linha);
        }
    }
}
