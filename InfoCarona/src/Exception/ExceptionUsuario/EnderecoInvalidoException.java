package Exception.ExceptionUsuario;


public class EnderecoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Endereco inv�lido";
    }
}
