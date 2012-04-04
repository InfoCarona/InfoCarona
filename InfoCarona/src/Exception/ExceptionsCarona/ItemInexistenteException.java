package Exception.ExceptionsCarona;


public class ItemInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Item inexistente";
    }
}
