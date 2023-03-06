package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;

@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ent_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;

	private String ent_login = null;

	private String ent_senha;

	private boolean ent_inativo = false;

	@IdentificaCampoPesquisa(campoConsulta = "ent_nomeFantasia", descricaoCampo = "Nome", principal = 1)
	private String ent_nomeFantasia;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;

	private String tipoEntidade = "";
	
	@Column(unique = true)
	private String cpf;

	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = {
			@UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = { "ent_codigo",
					"esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();
	
	private String email;
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	
	public Set<String> getAcessos() {
		return acessos;
	}

	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public String getTipoEntidade() {
		return tipoEntidade;
	}

	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}

	public boolean getEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}

	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}

	public String getEnt_nomeFantasia() {
		return ent_nomeFantasia;
	}

	public void setEnt_nomeFantasia(String ent_nomeFantasia) {
		this.ent_nomeFantasia = ent_nomeFantasia;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ent_codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		return Objects.equals(ent_codigo, other.ent_codigo);
	}

	public JSONObject getJson() {

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		map.put("ent_login", ent_login);
		map.put("ent_nomefantasia", ent_nomeFantasia);

		return new JSONObject(map);
	}

	@Override
	public String toString() {
		return "Entidade [ent_codigo=" + ent_codigo + ", ent_login=" + ent_login + ", ent_nomeFantasia="
				+ ent_nomeFantasia + ", tipoEntidade=" + tipoEntidade + "]";
	}
	
	
	public Set<Permissao> getAcessosPermissao(){
		
		Set<Permissao> permissoes = new HashSet<Permissao>();
		
		for(String acesso:acessos) {
			
			for(Permissao acess:Permissao.values()) {
				
				if(acesso.equalsIgnoreCase(acess.name())) {
					permissoes.add(acess);
				}
			}
		}
		
		return permissoes;
	}

}
