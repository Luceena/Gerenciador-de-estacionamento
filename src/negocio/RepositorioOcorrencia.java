package negocio;

import dados.Cliente;
import dados.Ocorrencia;
import dados.Veiculo;
import java.io.*;
import java.util.ArrayList;

public class RepositorioOcorrencia {
    private static final String ARQUIVO = "ocorrencias.csv";
    private ArrayList<Ocorrencia> ocorrencias;

    public RepositorioOcorrencia() {
        this.ocorrencias = new ArrayList<>();
        carregarDados();
    }

    public void adicionar(Ocorrencia ocorrencia) {
        ocorrencias.add(ocorrencia);
        salvarDados();
    }

    public Ocorrencia buscarPorTipo(String tipo) {
        for (Ocorrencia ocorrencia : ocorrencias) {
            if (ocorrencia.getTipo().equals(tipo)) {
                return ocorrencia;
            }
        }
        return null;
    }

    public void atualizar(Ocorrencia ocorrenciaAtualizada) {
        for (int i = 0; i < ocorrencias.size(); i++) {
            if (ocorrencias.get(i).getTipo().equals(ocorrenciaAtualizada.getTipo())) {
                ocorrencias.set(i, ocorrenciaAtualizada);
                salvarDados();
                break;
            }
        }
    }

    public void remover(String tipo) {
        ocorrencias.removeIf(ocorrencia -> ocorrencia.getTipo().equals(tipo));
        salvarDados();
    }

    public ArrayList<Ocorrencia> listarTodas() {
        return new ArrayList<>(ocorrencias);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("tipo,veiculos_envolvidos,envolvidos");
            for (Ocorrencia ocorrencia : ocorrencias) {
                StringBuilder veiculosStr = new StringBuilder();
                for (Veiculo veiculo : ocorrencia.getVeiculos_envolvidos()) {
                    veiculosStr.append(veiculo.getPlaca()).append(";");
                }
                
                StringBuilder envolvidosStr = new StringBuilder();
                for (Cliente cliente : ocorrencia.getEnvolvidos()) {
                    envolvidosStr.append(cliente.getCpf()).append(";");
                }
                
                writer.println(ocorrencia.getTipo() + "," + 
                              veiculosStr.toString() + "," + 
                              envolvidosStr.toString());
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
              
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
