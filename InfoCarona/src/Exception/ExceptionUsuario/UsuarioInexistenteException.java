package Exception.ExceptionUsuario;


public class UsuarioInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Usuário inexistente";
    }
}
