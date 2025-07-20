package negocio;

import dados.Funcionario;
import java.io.*;
import java.util.ArrayList;

public class RepositorioFuncionario {
    private static final String ARQUIVO = "funcionarios.csv";
    private ArrayList<Funcionario> funcionarios;
    private static RepositorioFuncionario instance;

    private RepositorioFuncionario() {
        this.funcionarios = new ArrayList<>();
        carregarDados();
    }

    public static RepositorioFuncionario getInstance() {
        if (instance == null) {
            synchronized (RepositorioFuncionario.class) {
                if (instance == null) {
                    instance = new RepositorioFuncionario();
                }
            }
        }
        return instance;
    }

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarDados();
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    public void atualizar(Funcionario funcionarioAtualizado) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(funcionarioAtualizado.getCpf())) {
                funcionarios.set(i, funcionarioAtualizado);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String cpf) {
        funcionarios.removeIf(funcionario -> funcionario.getCpf().equals(cpf));
        salvarDados();
    }

    public ArrayList<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("nome,cpf,salario,avaliacao,tempoAtendimento");
            for (Funcionario funcionario : funcionarios) {
                writer.println(funcionario.getNome() + "," +
                        funcionario.getCpf() + "," +
                        funcionario.getSalario() + "," +
                        funcionario.getAvaliacao() + "," +
                        funcionario.getTempoAtendimento());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists())
            return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Funcionario funcionario = new Funcionario(Double.parseDouble(dados[2]),
                        Double.parseDouble(dados[3]),
                        dados[4],
                        dados[0],
                        dados[1]);
                funcionarios.add(funcionario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
