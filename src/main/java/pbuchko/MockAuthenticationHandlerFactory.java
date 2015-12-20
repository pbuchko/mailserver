package pbuchko;

import java.util.ArrayList; 
import java.util.List; 
 
import org.subethamail.smtp.AuthenticationHandler; 
import org.subethamail.smtp.AuthenticationHandlerFactory; 


public class MockAuthenticationHandlerFactory implements AuthenticationHandlerFactory { 
	 
	public List<String> getAuthenticationMechanisms() { 
	  List<String> authMec = new ArrayList<String>(); 
	  authMec.add("PLAIN"); 
	  return authMec; 
	 } 
	 
	 public AuthenticationHandler create() { 
	  return new MockAuthenticationHandler(); 
	 } 
	 
	}
