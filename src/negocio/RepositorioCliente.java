package negocio;

import dados.Cliente;
import dados.DadosCliente;
import dados.DadosInterface;
import dados.Veiculo;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import negocio.ControladorEstacionamento;

public class RepositorioCliente implements InterfaceRepositorios {
    private static final String ARQUIVO = "clientes.csv";
    private ArrayList<DadosInterface> clientes;
    private static RepositorioCliente instance;

    private RepositorioCliente() {
        this.clientes = new ArrayList<>();
        carregarDados();
    }

    public static RepositorioCliente getInstance() {
        if (instance == null) {
            synchronized (RepositorioCliente.class) {
                if (instance == null) {
                    instance = new RepositorioCliente();
                }
            }
        }
        return instance;
    }

    public void adicionar(DadosInterface cliente) {
        clientes.add(cliente);
        salvarDados();
    }

    public DadosInterface buscar(String cpf) {
        for (DadosInterface cliente : clientes) {
            if (cliente.getIdentificador().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void atualizar(DadosInterface clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificador().equals(clienteAtualizado.getIdentificador())) {
                clientes.set(i, clienteAtualizado);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String cpf) {
        clientes.removeIf(cliente -> cliente.getIdentificador().equals(cpf));
        salvarDados();
    }

    public ArrayList<DadosInterface> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorCpf(String cpf) {
        for (DadosInterface cliente : clientes) {
            if(cliente instanceof Cliente){
                if (cliente.getIdentificador().equals(cpf)) {
                    return (Cliente)cliente;
                }
            }    
        }
        return null;
    }

    private void salvarDads() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("nome,cpf,idade,frequencia,classificacao,adimplente");
            for (DadosInterface cliente : clientes) {
                writer.println(cliente.getCaracteristicasReco(cliente));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("nome,cpf,idade,frequencia,classificacao,adimplente");
            for (DadosInterface cliente : clientes) {
                DadosCliente informacao = cliente.getCaracteristicasReco(cliente);
                writer.println(informacao.nome() + ","
                + informacao.cpf() + "," + informacao.idade() + "," + informacao.frequencia()
                + "," + informacao.classificacao() + "," + informacao.adimplente());
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

    public double cobrarEstacionamento(Cliente cliente){
        double taxaEstacionamento = 15 + cliente.getTempo() % 60 * 10;
        if(verificarDesconto(cliente)){
            taxaEstacionamento *= cliente.getDesconto();
        }
        cliente.setDivida(taxaEstacionamento);
        return cliente.getDivida();
    }

    public void pagarEstacionamento(Cliente cliente, double valor) throws InterruptedException{
        double verificacao = cliente.getDivida() - valor;
        if(verificacao <= 0){
            ControladorEstacionamento.abrirCancela();
            Thread.sleep(5000);
            ControladorEstacionamento.fecharCancela();
        }
        else{
            System.err.println("valor insuficiente");
        }
    }

    public boolean verificarDesconto(Cliente cliente){
        aplicarDesconto(cliente);
        if(cliente.getDesconto() > 0){
            return true;
        }
        return false;
    }

    public void aplicarDesconto(Cliente cliente){
        switch (cliente.getFrequencia()) {
            case 5:
                cliente.setDesconto(0.05);
                break;
            

            case 10:
                cliente.setDesconto(0.1);
                break;


            case 15:

                cliente.setDesconto(0.2);
                break;

            default:
                break;
        }
    }

    public void entradaEstacionamento(Cliente cliente){
        LocalDateTime agora = LocalDateTime.now();
        
    }
}