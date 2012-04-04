package Exception.ExceptionsCarona;


public class SessaoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Origem inválida";
    }
}
