package negocio;

import java.util.ArrayList;

import dados.Cliente;
import dados.Funcionario;
import dados.Veiculo;
import dados.DadosInterface;

public class FuncionamentoFacade {

    private static FuncionamentoFacade instance;

    private ControladorEstacionamento controladorEstacionamento;
    private RepositorioCliente repositorioCliente;
    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioOcorrencia repositorioOcorrencia;
    private RepositorioVaga repositorioVaga;
    private RepositorioVeiculo repositorioVeiculo;

    private FuncionamentoFacade() {
        controladorEstacionamento = ControladorEstacionamento.getInstance();
        repositorioCliente = RepositorioCliente.getInstance();
        repositorioFuncionario = RepositorioFuncionario.getInstance();
        repositorioOcorrencia = RepositorioOcorrencia.getInstance();
        repositorioVaga = RepositorioVaga.getInstance();
        repositorioVeiculo = RepositorioVeiculo.getInstance();
    }

    public static FuncionamentoFacade getInstance() {
        if (instance == null) {
            synchronized (FuncionamentoFacade.class) {
                if (instance == null) {
                    instance = new FuncionamentoFacade();
                }
            }
        }
        return instance;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        repositorioFuncionario.adicionar(funcionario);
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        repositorioVeiculo.adicionar(veiculo);
    }

    public void adicionar(InterfaceRepositorios repositorio, DadosInterface dado){
        repositorio.adicionar(dado);
    }
}
