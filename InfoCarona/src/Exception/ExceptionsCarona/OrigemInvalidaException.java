package Exception.ExceptionsCarona;


public class OrigemInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Sess�o inexistente";
    }
}
