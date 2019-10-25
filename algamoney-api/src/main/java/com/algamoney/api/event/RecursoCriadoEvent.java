/**
 * 
 */
package com.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * @author Morpheus
 *
 */
public class RecursoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long codigo;
	

	public RecursoCriadoEvent(Object source , HttpServletResponse response ,Long codigo) {
		super(source);
		this.response =  response;
		this.codigo = codigo;
		
	}


	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}


	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}
	
	

	

}
