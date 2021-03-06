// ============================================================================
//
// = PACKAGE
//    tests.SOCK_SAP
// 
// = FILENAME
//    SOCKConnectorTest.java
//
// = AUTHOR
//    Prashant Jain
// 
// ============================================================================
package JACE.tests.SOCK_SAP;

import java.io.*;
import java.net.*;
import JACE.OS.*;
import JACE.SOCK_SAP.*;

public class SOCKConnectorTest
{
  static void print_usage_and_die ()
    {
      System.out.println ("Usage: SOCKConnectorTest <hostname> [<port>]");
      System.exit (0);
    }

  void processRequests (SOCKStream stream) throws IOException
    {
      // 1.0 JDK      DataInputStream in = new DataInputStream (System.in);
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String msg;
      int ack_len;

      while (true)
 {
   StringBuffer ack = new StringBuffer ();
   ACE.DEBUG ("Enter input: ");
   ACE.FLUSH ();
   msg = in.readLine ();
   if (msg == null)
     break;
   stream.send (msg);
   ACE.DEBUG ("Waiting for ack...");
   ack_len = stream.recv (ack);
   if (ack_len == 0)
     break;
   else
     ACE.DEBUG (ack.toString ());
 }
    }

  public void init (String host, int port)
    {
      SOCKStream stream = new SOCKStream ();
      SOCKConnector connector = new SOCKConnector ();
      try
 {
   connector.connect (stream,
        host,
        port);
   processRequests (stream);
 }
      catch (IOException e)
 {
   ACE.ERROR (e);
 }
    }

  public static void main (String [] args)
    {
      ACE.enableDebugging ();

      int port = ACE.DEFAULT_SERVER_PORT;
      SOCKConnectorTest client = new SOCKConnectorTest ();

      // check arg count 
      if (args.length == 0 || args.length > 2) 
         print_usage_and_die(); 

      if (args.length == 2)
	{
	  try
	    {
	      port = Integer.parseInt (args[1]);
	    }
	  catch (NumberFormatException e)
	    {
	      client.print_usage_and_die ();
	    }
	}
      client.init (args[0], port);
	

    }
}

