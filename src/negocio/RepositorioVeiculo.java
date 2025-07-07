package negocio;

import dados.Veiculo;
import java.io.*;
import java.util.ArrayList;

public class RepositorioVeiculo {
    private static final String ARQUIVO = "veiculos.csv";
    private ArrayList<Veiculo> veiculos;

    public RepositorioVeiculo() {
        this.veiculos = new ArrayList<>();
        carregarDados();
    }

    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvarDados();
    }

    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void atualizar(Veiculo veiculoAtualizado) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculoAtualizado.getPlaca())) {
                veiculos.set(i, veiculoAtualizado);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
        salvarDados();
    }

    public ArrayList<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("marca_modelo,ano,placa,tipo");
            for (Veiculo veiculo : veiculos) {
                writer.println(veiculo.getMarca_modelo() + "," + 
                             veiculo.getAno() + "," + 
                             veiculo.getPlaca() + "," + 
                             veiculo.getTipo());
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
//            reader.readLine(); 
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
