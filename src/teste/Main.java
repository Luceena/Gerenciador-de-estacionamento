package teste;

import dados.Cliente;
import dados.Funcionario;
import dados.Veiculo;
import java.util.ArrayList;

import negocio.ControladorEstacionamento;
import negocio.FuncionamentoFacade;
import negocio.RepositorioCliente;
import negocio.RepositorioFuncionario;
import negocio.RepositorioVeiculo;

public class Main {
    public static void main(String[] args) {
        testarTodosRepositorios();
    }

    public static void testarTodosRepositorios() {
        
        FuncionamentoFacade fachadaPrincipal = FuncionamentoFacade.getInstance();

        Cliente cliente01 = new Cliente(new ArrayList<>(), "Fulano", "111.222.333-44", 30);
        fachadaPrincipal.adicionarCliente(cliente01);

        
        Veiculo veiculo = new Veiculo("Toyota", "2021", "ABC1234", "SUV");
        fachadaPrincipal.adicionarVeiculo(veiculo);
        
        Funcionario func = new Funcionario(3000.0, 4.5, "08:00-17:00", "Beltrano", "555.666.777-88");
        fachadaPrincipal.adicionarFuncionario(func);
        
    }

    public static void testeSingleton(){
        ControladorEstacionamento controller01 = ControladorEstacionamento.getInstance();
        ControladorEstacionamento controller02 = ControladorEstacionamento.getInstance();
        RepositorioCliente clienteRepository01 = RepositorioCliente.getInstance();
        RepositorioCliente clienteRepository02 = RepositorioCliente.getInstance();

        if(clienteRepository01 == clienteRepository02){
            System.out.println("o singleton pegou, OBA!");
        }
        else{
            System.out.println("deu merda alek");
        }
    }
}