package Exception.ExceptionUsuario;


public class EmailInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Email inv�lido";
    }
}
