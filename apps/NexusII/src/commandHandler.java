// To handle the events generated by command-line. The user will be saying
// /nick <nick-name> 

import java.awt.* ; 
import java.util.* ; 

//import NexusII.client.* ; 

import awtCommand.* ; 


public class commandHandler implements Command,consts { 
  NexusClientApplet applet_ ; 
  // will handle the /nick command for now -- urgent 
  public commandHandler(NexusClientApplet applet ) { 
    applet_ = applet ; 
  } 

  public void execute(Object target,Event evt,Object what) { 
    // Right now assume that it is just a nick command 
    if(DEBUG) { 
      System.out.println("Nick typed in ---- ");
    }
    StringTokenizer t = new StringTokenizer((String)what) ; 
    // string for holding /nick and <nickname>
    String command = null  ; 
    if(t.hasMoreTokens()) { 
      command = t.nextToken();
    } 

    if(command.equalsIgnoreCase("/nick")) { 
      if(t.hasMoreTokens()) { 
	// have to send a nick packet to server 
	applet_.myName  = new String(t.nextToken()); 
	applet_.sendNickToServer(applet_.myName);

      } 
    }
    // clear the field in the gui 
    ((TextField)target).setText(""); 

  }
}
// of class 
