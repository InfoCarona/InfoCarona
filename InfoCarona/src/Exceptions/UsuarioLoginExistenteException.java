package Exceptions;


public class UsuarioLoginExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "J� existe um usu�rio com este login";
    }
}
