package negocio;


public class ControladorEstacionamento {
    
    private static ControladorEstacionamento instance;

    private ControladorEstacionamento(){

    }

    public static ControladorEstacionamento getInstance(){
        if(instance == null){
            synchronized(ControladorEstacionamento.class){
            if(instance == null){
                instance = new ControladorEstacionamento();
            }    
            }
        }
        return instance;
    }


    public void abrirCancela(){
        System.out.println("Abrir cancela");}
    
    public void fecharCancela(){
        System.out.println("Fechar cancela");}
    
    
    
}
