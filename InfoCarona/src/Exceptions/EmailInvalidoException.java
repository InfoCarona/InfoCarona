package Exceptions;


public class EmailInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Email inválido";
    }
}
