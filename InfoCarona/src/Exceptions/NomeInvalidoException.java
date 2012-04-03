package Exceptions;


public class NomeInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Nome inválido";
    }
}
