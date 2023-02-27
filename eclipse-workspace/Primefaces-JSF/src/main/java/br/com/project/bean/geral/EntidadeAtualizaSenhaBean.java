package br.com.project.bean.geral;

import java.io.Serializable;

public class EntidadeAtualizaSenhaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String senhaAtual;
	private String novSenha;
	private String confirmaSenha;
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getNovSenha() {
		return novSenha;
	}
	public void setNovSenha(String novSenha) {
		this.novSenha = novSenha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	

}
