package Exception.ExceptionsCarona;


public class TrajetoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Trajeto Inexistente";
    }
}
