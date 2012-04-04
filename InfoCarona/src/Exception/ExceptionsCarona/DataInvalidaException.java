package Exception.ExceptionsCarona;


public class DataInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Data inválida";
    }
}
