package Exception.ExceptionsCarona;


public class PontoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Ponto Inv�lido";
    }
}
