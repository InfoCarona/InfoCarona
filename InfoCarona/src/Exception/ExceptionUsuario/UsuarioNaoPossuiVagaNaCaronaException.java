package Exception.ExceptionUsuario;


public class UsuarioNaoPossuiVagaNaCaronaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Usu�rio n�o possui vaga na carona.";
    }
}
