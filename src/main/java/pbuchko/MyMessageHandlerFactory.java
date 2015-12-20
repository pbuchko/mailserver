package pbuchko;

//-------------------------------------------------------------------
//MyMessageHandlerFactory.java
//-------------------------------------------------------------------
import org.subethamail.smtp.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MyMessageHandlerFactory implements MessageHandlerFactory {

  public MessageHandler create(MessageContext ctx) {
      return new Handler(ctx);
  }

  class Handler implements MessageHandler {
      MessageContext ctx;

      public Handler(MessageContext ctx) {
              this.ctx = ctx;
      }
      
      public void from(String from) throws RejectException {
              System.out.println("FROM:"+from);
              //
              // Record this event
              //
              processEvent(from);
      }
      
      private void processEvent( String from )
      {
    	  String[] result = from.split("@");
    	  
    	  try{
              File file =new File("/home/paul/mailserver/events/"+result[0]+".txt");
        	  if(!file.exists()){
        	 	file.createNewFile();
        	  }
        	  FileWriter fw = new FileWriter(file,true);
        	  BufferedWriter bw = new BufferedWriter(fw);
        	  PrintWriter pw = new PrintWriter(bw);
        	  
        	  //
        	  // Get the current time for this event in UTC
        	  //
        	  
        	  SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
        	  simpleDate.setTimeZone(TimeZone.getTimeZone("0"));

        	  String timeStamp = simpleDate.format(Calendar.getInstance().getTime());
 
        	  //
        	  // Also get the local time to put on the same line for reference
        	  //
        	  
           	  simpleDate.setTimeZone(TimeZone.getDefault());
        	  String timeStampLocal =  simpleDate.format(Calendar.getInstance().getTime());

        	  
              //This will add a new line to the file content
        	  pw.println(timeStamp+","+timeStampLocal);
              /* Below three statements would add three 
               * mentioned Strings to the file in new lines.
               */
        	   pw.close();

    	  System.out.println("Data successfully appended at the end of file: "+
    			  timeStamp);

           }catch(IOException ioe){
        	   System.out.println("Exception occurred:");
        	   ioe.printStackTrace();
          }
      }

      public void recipient(String recipient) throws RejectException {
              System.out.println("RECIPIENT:"+recipient);
      }

      public void data(InputStream data) throws IOException {
              System.out.println("MAIL DATA");
              System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
             System.out.println(this.convertStreamToString(data));
              System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
      }

      public void done() {
              //System.out.println("Finished");
      }

      public String convertStreamToString(InputStream is) {
              BufferedReader reader = new BufferedReader(new InputStreamReader(is));
              StringBuilder sb = new StringBuilder();
              
              String line = null;
              try {
                      while ((line = reader.readLine()) != null) {
                              sb.append(line + "\n");
                      }
              } catch (IOException e) {
                      e.printStackTrace();
              }
              return sb.toString();
      }

  }
}
