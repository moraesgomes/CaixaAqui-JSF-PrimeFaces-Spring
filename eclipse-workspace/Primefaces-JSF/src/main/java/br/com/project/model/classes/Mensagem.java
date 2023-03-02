package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "mensagem")
@SequenceGenerator(name = "mensagem_seq", sequenceName = "mensagem_seq", allocationSize = 1, initialValue = 1)
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "men_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_seq")
	private Long men_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "user_origem.ent_nomeFantasia", principal = 2)
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "user_origem_fk")
	@JoinColumn(name = "user_origem")
	private Entidade user_origem = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Destino", campoConsulta = "user_origem.ent_nomeFantasia", principal = 3)
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "user_destino_fk")
	@JoinColumn(name = "user_destino")
	private Entidade user_destino = new Entidade();

	@Column(nullable = false)
	private Boolean men_lido = Boolean.FALSE;

	@IdentificaCampoPesquisa(descricaoCampo = "Data", campoConsulta = "men_datahora")
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date men_hora = new Date();

	@IdentificaCampoPesquisa(descricaoCampo = "Assunto", campoConsulta = "men_assunto")
	@Column(length = 80, nullable = false)
	private String men_assunto;

	@IdentificaCampoPesquisa(descricaoCampo = "Mensagem", campoConsulta = "men_mensagem", principal = 1)
	@Column(length = 100, nullable = false)
	private String men_mensagem;

	private Boolean men_exigirresposta = false;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getMen_codigo() {
		return men_codigo;
	}

	public void setMen_codigo(Long men_codigo) {
		this.men_codigo = men_codigo;
	}

	public Entidade getUser_origem() {
		return user_origem;
	}

	public void setUser_origem(Entidade user_origem) {
		this.user_origem = user_origem;
	}

	public Entidade getUser_destino() {
		return user_destino;
	}

	public void setUser_destino(Entidade user_destino) {
		this.user_destino = user_destino;
	}

	public Boolean getMen_lido() {
		return men_lido;
	}

	public void setMen_lido(Boolean men_lido) {
		this.men_lido = men_lido;
	}

	public Date getMen_hora() {
		return men_hora;
	}

	public void setMen_hora(Date men_hora) {
		this.men_hora = men_hora;
	}

	public String getMen_assunto() {
		return men_assunto;
	}

	public void setMen_assunto(String men_assunto) {
		this.men_assunto = men_assunto;
	}

	public String getMen_mensagem() {
		return men_mensagem;
	}

	public void setMen_mensagem(String men_mensagem) {
		this.men_mensagem = men_mensagem;
	}

	public Boolean getMen_exigirresposta() {
		return men_exigirresposta;
	}

	public void setMen_exigirresposta(Boolean men_exigirresposta) {
		this.men_exigirresposta = men_exigirresposta;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(men_codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		return Objects.equals(men_codigo, other.men_codigo);
	}

	@Override
	public String toString() {
		return "Mensagem [men_codigo=" + men_codigo + ", men_assunto=" + men_assunto + ", men_mensagem=" + men_mensagem
				+ "]";
	}
	
	 public JSONObject getJson() {
		 
		  Map<Object , Object> map = new HashMap<Object , Object>();
		  map.put("men_codigo", men_codigo);
		  map.put("men_lido", men_lido);
		  map.put("men_assunto", men_assunto);
		  return new JSONObject();
	 }

}
