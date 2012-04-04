package Exception.ExceptionsCarona;


public class CaronaInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Carona Inválida";
    }
}
