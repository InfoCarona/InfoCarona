package Exception.ExceptionsCarona;


public class SessaoInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Sess�o inv�lida";
    }
}
