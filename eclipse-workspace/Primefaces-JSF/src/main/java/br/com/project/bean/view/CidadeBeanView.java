package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
	
	private Cidade objetoSelecionado = new Cidade();
	
	private CarregamentoLazyListForObject<Cidade> list = new CarregamentoLazyListForObject<Cidade>();
	
	public CarregamentoLazyListForObject<Cidade> getList() throws Exception {
		
		return list;
	}
   
	@Autowired
	private CidadeController cidadeController;
	
	@Override
	public String save() throws Exception {
		
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		 return "";
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		
		super.setNomeRelatorioJasper("report_cidade");
		super.setNomeRelatorioSaida("report_cidade");
		super.setListDataBeanCollectionReport(cidadeController.findList(getImplement()));
		
		return super.getArquivoReport();
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		
		list.clean();
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Cidade();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
	
		saveNotReturn();
	}
	
	@Override
	public String novo() throws Exception {
		setarVariavesNulas();
		return url;
	}
	
	@Override
	public void setarVariavesNulas() throws Exception {
		
		list.clean();
		objetoSelecionado = new Cidade();
	}
	
	@Override
	public String editar() throws Exception {
		
		list.clean();
		return url;
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cidade) cidadeController.getSession().get(getImplement(), objetoSelecionado.getCid_codigo());
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		sucesso();
	
	}
	
	
	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	protected Class<Cidade> getImplement() {
		
		return Cidade.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		
		setarVariavesNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Cidade> getController() {
		
		
		return cidadeController;
	}
	
	@Override
	public void consultarEntidade() throws Exception {

		objetoSelecionado = new Cidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
		
		super.consultarEntidade();
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {

		return "";
	}
	
	

}
