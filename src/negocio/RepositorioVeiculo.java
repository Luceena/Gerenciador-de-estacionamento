package negocio;

import dados.DadosInterface;
import dados.Veiculo;
import java.io.*;
import java.util.ArrayList;

public class RepositorioVeiculo implements InterfaceRepositorios{
    private static final String ARQUIVO = "veiculos.csv";
    private ArrayList<DadosInterface> veiculos;
    private static RepositorioVeiculo instance;

    private RepositorioVeiculo() {
        this.veiculos = new ArrayList<>();
        carregarDados();
    }

    public static RepositorioVeiculo getInstance() {
        if (instance == null) {
            synchronized (RepositorioVeiculo.class) {
                if (instance == null) {
                    instance = new RepositorioVeiculo();
                }
            }
        }
        return instance;
    }

    public void adicionar(DadosInterface veiculo) {
        veiculos.add(veiculo);
        salvarDados();
    }

    public DadosInterface buscar(String placa) {
        for (DadosInterface veiculo : veiculos) {
            if (veiculo.getIdentificador().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void atualizar(DadosInterface veiculoAtualizado) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getIdentificador().equals(veiculoAtualizado.getIdentificador())) {
                veiculos.set(i, veiculoAtualizado);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getIdentificador().equals(placa));
        salvarDados();
    }

    public ArrayList<DadosInterface> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("marca_modelo,ano,placa,tipo");
            for (DadosInterface veiculo : veiculos) {
                writer.println(veiculo.getCaracteristicas());
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
            // reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Veiculo veiculo = new Veiculo(dados[0], dados[1], dados[2], dados[3]);
                veiculos.add(veiculo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
