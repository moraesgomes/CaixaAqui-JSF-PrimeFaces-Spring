package br.com.util.all;

import org.springframework.stereotype.Component;

@Component
public  abstract class BeanViewAbstract implements ActionViewPadrao {

	@Override
	public void limparLista() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		
		
	}

	@Override
	public void saveEdit() throws Exception {
		
		
	}

	@Override
	public void excluir() throws Exception {
		
		
	}

	@Override
	public String ativar() throws Exception {
		
		return null;
	}

	@Override
	public String novo() throws Exception {
		
		return null;
	}
	
	

	@Override
	public String editar() throws Exception {
		
		return null;
	}

	@Override
	public void setarVariavesNulas() throws Exception {
		
		
	}

	@Override
	public void consultarEntidade() throws Exception {
		
		
	}

	@Override
	public void statusOperation(EstatusPersistencia a) throws Exception {
	
		Mensagens.responseOperation(a);
	}
	
	protected void sucesso()  throws Exception{
		
		statusOperation(EstatusPersistencia.SUCESSO);
	}
	
	protected void error() throws Exception{
		
		statusOperation(EstatusPersistencia.ERRO);
		
	}

	@Override
	public String redirecionarNewEntidade() throws Exception {
	
		return null;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		
		return null;
	}
	

	@Override
	public void addMsg(String msg) {
		
		Mensagens.msg(msg);
		
	}


}
