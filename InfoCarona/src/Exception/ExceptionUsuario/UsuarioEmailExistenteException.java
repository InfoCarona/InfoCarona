package Exception.ExceptionUsuario;


public class UsuarioEmailExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "J� existe um usu�rio com este email";
    }
}
