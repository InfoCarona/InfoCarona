package Exception.ExceptionUsuario;


public class LoginInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Login inválido";
    }
}
