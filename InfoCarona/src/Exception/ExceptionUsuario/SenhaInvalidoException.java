package Exception.ExceptionUsuario;


public class SenhaInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Senha inválido";
    }
}
