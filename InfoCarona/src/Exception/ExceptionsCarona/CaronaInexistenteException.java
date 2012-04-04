package Exception.ExceptionsCarona;


public class CaronaInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Carona Inexistente";
    }
}
