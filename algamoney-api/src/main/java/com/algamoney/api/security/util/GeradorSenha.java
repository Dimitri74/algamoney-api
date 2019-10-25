/**
 * 
 */
package com.algamoney.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Morpheus
 *
 */
public class GeradorSenha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));//senha desejada é admin.
	}

}


