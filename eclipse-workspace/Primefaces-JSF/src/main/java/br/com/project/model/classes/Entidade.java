package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;


@Audited
@Entity
public class Entidade implements Serializable{
 
	private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO )
  private Long ent_codigo;	
  
  private String ent_login = null;
  
  private String ent_senha;
  
  private boolean ent_inativo = false;
  
  private String ent_nomeFantasia;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date ent_ultimoacesso;
  
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
	
	Map<Object, Object> map = new HashMap<Object , Object>();
	map.put("ent_codigo",ent_codigo);
	map.put("ent_login", ent_login);
	map.put("ent_nomefantasia", ent_nomeFantasia);
	
	return new JSONObject(map);
}



}
