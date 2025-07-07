package negocio;

import dados.Cliente;
import dados.Veiculo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCliente {
    private static final String ARQUIVO = "clientes.csv";
    private ArrayList<Cliente> clientes;

    public RepositorioCliente() {
        this.clientes = new ArrayList<>();
        carregarDados();
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
        salvarDados();
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(clienteAtualizado.getCpf())) {
                clientes.set(i, clienteAtualizado);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
        salvarDados();
    }

    public ArrayList<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("nome,cpf,idade,frequencia,classificacao,adimplente");
            for (Cliente cliente : clientes) {
                writer.println(cliente.getNome() + "," + 
                               cliente.getCpf() + "," + 
                               cliente.getIdade() + "," + 
                               cliente.getFrequencia() + "," + 
                               cliente.getClassificacao() + "," + 
                               cliente.isAdimplente());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            reader.readLine(); 
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                ArrayList<Veiculo> veiculos = new ArrayList<>(); 
                Cliente cliente = new Cliente(veiculos, dados[0], dados[1], Integer.parseInt(dados[2]));
                cliente.setFrequencia(Integer.parseInt(dados[3]));
                cliente.setClassificacao(dados[4]);
                cliente.setAdimplente(Boolean.parseBoolean(dados[5]));
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}