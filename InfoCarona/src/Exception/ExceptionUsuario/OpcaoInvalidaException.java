package Exception.ExceptionUsuario;


public class OpcaoInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Op��o inv�lida.";
    }
}
