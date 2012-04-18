package Exception.ExceptionUsuario;


public class numeroMaximoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Numero maximo de Id permitido";
    }
}
