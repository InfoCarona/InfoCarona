package Exception.ExceptionsCarona;


public class HoraInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Hora inválida";
    }
}
