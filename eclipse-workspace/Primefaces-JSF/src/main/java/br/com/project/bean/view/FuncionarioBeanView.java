package br.com.project.bean.view;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.util.all.Mensagens;

@Controller
@Scope("session")
@ManagedBean(name="funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract{
	

	private static final long serialVersionUID = 1L;
	
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";
	
	
	private Entidade objetoSelecionado = new Entidade();
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	

	@Override
	protected Class<Entidade> getImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return entidadeController;
	}
	
	@Override
	public void excluir() throws Exception {
		
	     if(objetoSelecionado.getEnt_codigo() != null && objetoSelecionado.getEnt_codigo() > 0) {
	    	 
	    	 entidadeController.delete(objetoSelecionado);
	    	 list.remove(objetoSelecionado);
	    	 objetoSelecionado = new Entidade();
	    	 sucesso();
	     }
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {

		return " and entity.tipoEntidade = 'FUNCIONARIO' ";
	}
	
	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	
	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}
	
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		
		return urlFind;
	}
	
	@Override
	public void consultarEntidade() throws Exception {
		
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),super.getSqlLazyQuery() );
	}
	

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		
		super.setNomeRelatorioJasper("report_funcionario");
		super.setNomeRelatorioSaida("report_funcionario");
		super.setListDataBeanCollectionReport(entidadeController.findList(getImplement()));
		
		return super.getArquivoReport();
	}
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		
		
		if(!objetoSelecionado.getAcessos().contains("USER")) {
			
			objetoSelecionado.getAcessos().add("USER");
		}
		
		if(entidadeController.existeCpf(objetoSelecionado.getCpf())) {
			
			 Mensagens.msgSeverityInfo(" J?? existe esse CPF cadastrado no sistema!");
			
		}else {
		
		objetoSelecionado =  entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
		sucesso();
		}
	}
	
	@Override
	public void saveEdit() throws Exception {
		
	   objetoSelecionado = entidadeController.merge(objetoSelecionado);	
	   list.add(objetoSelecionado);
	   
	   objetoSelecionado = new Entidade();
	   Mensagens.msgSeverityInfo("Atualizado com sucesso!");
	}
	
	@Override
	public String editar() throws Exception {
		
		return url;
	}
	

}
