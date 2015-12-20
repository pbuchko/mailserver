package pbuchko;

import org.subethamail.smtp.AuthenticationHandler; 
import org.subethamail.smtp.RejectException; 

public class MockAuthenticationHandler implements AuthenticationHandler {

	 public String auth(String paramString) throws RejectException { 
	  return ""; 
	 } 
	 
	 public Object getIdentity() { 
	  return "testuser"; 
	 }
	
}
