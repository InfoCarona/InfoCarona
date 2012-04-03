package Exceptions;


public class UsuarioEmailExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Já existe um usuário com este email";
    }
}
