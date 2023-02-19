package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.session.HibernateUseful;


public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	
	final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();

	
	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();


	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}


	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	
	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

		
			Throwable exception = context.getException();

			
			try {

				requestMap.put("exceptionMessage", exception.getMessage());
				
				if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_WARN,"Registro n�o pode ser removido por estar associado.",""));
				
				}
				else if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Registro foi atualizado ou exclu�do por outro usu�rio. Consulte novamente.",""));
					
				}
				else {
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_FATAL,"O sistema se recuperou de um erro inesperado.",""));

					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_INFO,"Voc� pode continuar usando o sistema!",""));
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_FATAL,"O erro foi causado por:\n"+exception.getMessage(), ""));
					
					
					RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro inesperado.')"); 
					
					 RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro inesperado.."));
					
					navigationHandler.handleNavigation(facesContext, null, "/error/error.jsf?faces-redirect=true&expired=true");
				}

				
				facesContext.renderResponse();
			} finally {
				
				SessionFactory sf = HibernateUseful.getSessionFactory();
				
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				
				exception.printStackTrace();
				
				
				iterator.remove();
			}
		}
		getWrapped().handle();
		
	}

}
