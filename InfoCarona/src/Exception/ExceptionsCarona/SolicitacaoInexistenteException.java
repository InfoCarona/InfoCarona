package Exception.ExceptionsCarona;


public class SolicitacaoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Solicitação inexistente";
    }
}
