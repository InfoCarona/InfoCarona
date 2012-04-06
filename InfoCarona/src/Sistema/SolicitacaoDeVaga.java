package Sistema;

import java.util.LinkedList;
import java.util.List;

public class SolicitacaoDeVaga {
	
	private String idSessao, idCarona, idSolicitacao, ponto;
	
	public SolicitacaoDeVaga(String idSessao, String idCarona, String idSolicitacao, String ponto){
		this.idCarona = idCarona;
		this.idSessao = idSessao;
		this.idSolicitacao = idSolicitacao;
		this.ponto = ponto;
	}
	
	public String getIdCarona(){
		return this.idCarona;
	}
	
	public String getIdSessao(){
		return this.idSessao;
	}

	public String getIdSolicitacao(){
		return this.idSolicitacao;
	}

	public String getPonto(){
		return this.ponto;
	}
	
}
