package Exception.ExceptionUsuario;


public class OpcaoInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Opção inválida.";
    }
}
