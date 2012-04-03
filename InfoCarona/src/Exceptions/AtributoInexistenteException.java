package Exceptions;


public class AtributoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Atributo inexistente";
    }
}
