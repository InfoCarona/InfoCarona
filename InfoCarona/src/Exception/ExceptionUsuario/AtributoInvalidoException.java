package Exception.ExceptionUsuario;


public class AtributoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Atributo inválido";
    }
}
