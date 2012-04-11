package Sistema;

import java.util.LinkedList;
import java.util.List;

public class SolicitacaoDeVaga {
	
	private Carona carona;
	private String donoDaSolicitacao, ponto, idSolicitacao;
	private boolean solicitacao = false;
	
	public SolicitacaoDeVaga(Carona carona, String donoDaSolicitacao, String ponto, String idSolicitacao){
		this.carona = carona;
		this.donoDaSolicitacao = donoDaSolicitacao;
		this.ponto = ponto;
		this.idSolicitacao = idSolicitacao;
	}
	
	public String getOrigem(){
		return this.carona.getOrigem();
	}
	
	public String getDestino(){
		return this.carona.getDestino();
	}

	public String getDonoDaCarona(){
		return this.carona.getDonoDaCarona();
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
			retorno = this.getOrigem();
		}else if(atributo.equals("destino")){
			retorno = this.getDestino();
		}else if(atributo.equals("Dono da carona")){
			retorno = this.getDonoDaCarona();
		}else if(atributo.equals("Dono da solicitacao")){
			retorno = this.donoDaSolicitacao;
		}else if(atributo.equals("Ponto de Encontro")){
			retorno = this.ponto;
		}
		
		return retorno;
	}
	
	public void solicitacaoAceita(){
		this.solicitacao = true;
		carona.setVagas(carona.getVagas()-1);
		
	}
	
	public boolean isSolicitacaoAceita(){
		return this.solicitacao;
	}
}
