package Exception.ExceptionsCarona;


public class IDCaronaInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Identificador do carona é inválido";
    }
}
