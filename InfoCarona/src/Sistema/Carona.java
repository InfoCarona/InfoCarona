package Sistema;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Carona {
	
	private String idSessao, origem, destino, data, hora, idCarona;
	private int vagas;
	
	public Carona(String idSessao, String origem, String destino, String data,
			String hora, int vagas, String idCarona) {
		this.idSessao = idSessao;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
		this.idCarona = idCarona;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
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
	
}
