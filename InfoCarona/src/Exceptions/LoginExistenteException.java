package Exceptions;


public class LoginExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "J� existe um usu�rio com este login";
    }
}
