package Sistema;

import java.util.LinkedList;
import java.util.List;

public class SolicitacaoDeVaga {
	
	private String origem, destino, donoDaCarona, donoDaSolicitacao, ponto, idSolicitacao;
	
	public SolicitacaoDeVaga(String origem, String destino, String donoDaCarona, String donoDaSolicitacao, String ponto, String idSolicitacao){
		this.origem = origem;
		this.destino = destino;
		this.donoDaCarona = donoDaCarona;
		this.donoDaSolicitacao = donoDaSolicitacao;
		this.ponto = ponto;
		this.idSolicitacao = idSolicitacao;
	}
	
	public String getOrigem(){
		return this.origem;
	}
	
	public String getDestino(){
		return this.destino;
	}

	public String getDonoDaCarona(){
		return this.donoDaCarona;
	}
	
	public String getDonoDaSolicitacao(){
		return this.donoDaSolicitacao;
	}

	public String getPonto(){
		return this.ponto;
	}
	
	public String getIdSolicitacao(){
		return this.idSolicitacao;
	}
	
	public String getAtributoSolicitacao(String atributo){
		String retorno = "";
		if(atributo.equals("origem")){
			retorno = this.origem;
		}else if(atributo.equals("destino")){
			retorno = this.destino;
		}else if(atributo.equals("Dono da carona")){
			retorno = this.donoDaCarona;
		}else if(atributo.equals("Dono da solicitacao")){
			retorno = this.donoDaSolicitacao;
		}else if(atributo.equals("Ponto de Encontro")){
			retorno = this.ponto;
		}
		
		return retorno;
	}
}
