package Exception.ExceptionsCarona;


public class VagaInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Vaga inválida";
    }
}
