package Exceptions;


public class UsuarioLoginExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Já existe um usuário com este login";
    }
}
