package br.com.project.bean.geral;

import org.springframework.stereotype.Component;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.util.all.BeanReportView;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView{

	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getImplement();

	protected abstract InterfaceCrud <?> getController();
	
	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {
		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}
	
	
   
}
