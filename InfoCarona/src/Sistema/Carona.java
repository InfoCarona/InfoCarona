package Sistema;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Carona {
	
	private String idSessao, origem, destino, data, hora, idCarona, donoDaCarona;
	private int vagas;
	private List<SugestaoDePontoDeEncontro> listaDeSugestoes;
	
	public Carona(String idSessao, String origem, String destino, String data,
			String hora, int vagas, String idCarona, String donoDaCarona) {
		this.idSessao = idSessao;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
		this.idCarona = idCarona;
		this.donoDaCarona = donoDaCarona;
		this.listaDeSugestoes = new LinkedList<SugestaoDePontoDeEncontro>();
	}
	
	public List<SugestaoDePontoDeEncontro> getListaDeSugestoes(){
		return this.listaDeSugestoes;
	}
	
	public String getOrigem() {
		return this.origem;
	}
	
	public String getDonoDaCarona(){
		return this.donoDaCarona;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	public String getIdSessao(){
		return this.idSessao;
	}
	
	public String getIdCarona(){
		return this.idCarona;
	}
	
	public String getAtributo(String atributo){
		String retorno = null;
		
		if(atributo.equals("origem")){
			retorno = this.origem;
		}else if(atributo.equals("vagas")){
			retorno = (this.vagas+"");
		}else if(atributo.equals("destino")){
			retorno = this.destino;
		}else if(atributo.equals("data")){
			retorno = this.data;
		}
		
		return retorno;
	}
	
	public String toString(){
		return (this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + this.hora);
	}
	
	public String getDadosCarona(){
		return ("origem=" + origem + " destino=" + destino + " data=" + data + " hora=" + hora + " vagas=" + vagas);
	}
	
}
