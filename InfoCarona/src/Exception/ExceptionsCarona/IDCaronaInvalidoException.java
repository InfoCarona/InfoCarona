package Exception.ExceptionsCarona;


public class IDCaronaInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Identificador do carona � inv�lido";
    }
}
