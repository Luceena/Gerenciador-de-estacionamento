package negocio;

import dados.Vaga;
import java.io.*;
import java.util.ArrayList;

public class RepositorioVaga {
    private static final String ARQUIVO = "vagas.csv";
    private ArrayList<Vaga> vagas;
    private static RepositorioVaga instance;

    private RepositorioVaga() {
        this.vagas = new ArrayList<>();
        carregarDados();
    }

    public static RepositorioVaga getInstance(){
        if(instance == null){
            synchronized(RepositorioVaga.class){
                if(instance == null){
                    instance = new RepositorioVaga();
                }
            }
        }
        return instance;
    }

    public void adicionar(Vaga vaga) {
        vagas.add(vaga);
        salvarDados();
    }

    public Vaga buscarPorNumero(int numero) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero() == numero) {
                return vaga;
            }
        }
        return null;
    }

    public void atualizar(Vaga vagaAtualizada) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getNumero() == vagaAtualizada.getNumero()) {
                vagas.set(i, vagaAtualizada);
                salvarDados();
                break;
            }
        }
    }

    public void remover(int numero) {
        vagas.removeIf(vaga -> vaga.getNumero() == numero);
        salvarDados();
    }

    public ArrayList<Vaga> listarTodas() {
        return new ArrayList<>(vagas);
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("numero,tipo,preferencial,ocupada");
            for (Vaga vaga : vagas) {
                writer.println(vaga.getNumero() + "," + 
                              vaga.getTipo() + "," + 
                              vaga.isPreferencial() + "," + 
                              vaga.isOcupada());
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
                Vaga vaga = new Vaga(Integer.parseInt(dados[0]), dados[1], 
                                    Boolean.parseBoolean(dados[2]), 
                                    Boolean.parseBoolean(dados[3]));
                vagas.add(vaga);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}