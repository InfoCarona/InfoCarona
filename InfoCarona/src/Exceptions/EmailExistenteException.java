package Exceptions;


public class EmailExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "J� existe um usu�rio com este email";
    }
}
