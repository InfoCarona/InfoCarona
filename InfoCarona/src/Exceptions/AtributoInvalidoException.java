package Exceptions;


public class AtributoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Atributo inv�lido";
    }
}
