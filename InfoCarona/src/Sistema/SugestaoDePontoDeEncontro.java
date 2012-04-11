package Sistema;

import java.util.LinkedList;
import java.util.List;

public class SugestaoDePontoDeEncontro {
	
	private String idSessao, idCarona, idSugestao;
	private List<String> listaDeSugestaoDePontosDeEncontro;
	private List<String> listaDeRespostasDePontosDeEncontro;
	
	public SugestaoDePontoDeEncontro(String idSessao, String idCarona,
			String idSugestao) {
		this.idCarona = idCarona;
		this.idSessao = idSessao;
		this.idSugestao = idSugestao;
		this.listaDeSugestaoDePontosDeEncontro = new LinkedList<String>();
		this.listaDeRespostasDePontosDeEncontro = new LinkedList<String>();
	}
	
	public String getIdCarona(){
		return this.idCarona;
	}
	
	public String getIdSessao(){
		return this.idSessao;
	}

	public String getIdSugestao(){
		return this.idSugestao;
	}
	
	public List<String> getListaDeSugestaoDePontosDeEncontro(){
		return this.listaDeSugestaoDePontosDeEncontro;
	}
	
	public List<String> getlistaDeRespostasDePontosDeEncontro(){
		return this.listaDeRespostasDePontosDeEncontro;
	}
	public boolean existeSugestao(String pontoSugestao){
		boolean retorno = false;
		for (String sugestao : listaDeSugestaoDePontosDeEncontro) {
			if(sugestao.equals(pontoSugestao)){
				retorno = true;
				break;
		}
		}
		return retorno;
	}
	
}
