package Exception.ExceptionsCarona;


public class OrigemInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Origem inv�lida";
    }
}
