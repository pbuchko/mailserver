package pbuchko;


//-------------------------------------------------------------------
//BasicSMTPServer.java
//-------------------------------------------------------------------
import org.subethamail.smtp.server.SMTPServer;
import org.subethamail.smtp.auth.LoginAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.smtp.auth.LoginFailedException ;

public class App {
    public static void main(String[] args) {
            MyMessageHandlerFactory myFactory = new MyMessageHandlerFactory() ;
            
            MockAuthenticationHandlerFactory myAuthFactory2 = new MockAuthenticationHandlerFactory();
        	
        	
            //SMTPServer smtpServer = new SMTPServer(myFactory, myAuthFactory2);
            SMTPServer smtpServer = new SMTPServer(myFactory);
            smtpServer.setPort(25000);
            
            
            LoginAuthenticationHandlerFactory p = new LoginAuthenticationHandlerFactory(new UsernamePasswordValidator(){public void login(String arg0, String arg1)
                		throws LoginFailedException {
                	if (!(arg0.equalsIgnoreCase("cctv") && arg1.equalsIgnoreCase(";3vH6`[v*xC`&qu8"))){
                		throw new LoginFailedException("SubEthaMail: unable to authenticate the user");
                	}
                	
                }});
            
            smtpServer.setAuthenticationHandlerFactory(p);
            
            //smtpServer.setPort(25);
            smtpServer.start();
    }
}
