package Exceptions;


public class LoginExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Já existe um usuário com este login";
    }
}
