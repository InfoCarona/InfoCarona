package Exception.ExceptionsCarona;


public class TrajetoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Trajeto Inválida";
    }
}
