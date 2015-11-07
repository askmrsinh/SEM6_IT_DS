/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashesh
 */
import java.net.*;
import java.io.*;

public class GreetingClient {

  public static void main(String[] args) {
    //assign command line arguments to variables
    String serverName = args[0];
    int port = Integer.parseInt(args[1]);
    try {
      System.out.println("Connecting to " + serverName
              + " on port " + port);
      Socket client = new Socket(serverName, port);
      System.out.println("Just connected to "
              + client.getRemoteSocketAddress());
      //write out a string to the server
      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out
              = new DataOutputStream(outToServer);
      out.writeUTF("Hello from "
              + client.getLocalSocketAddress());
      //read in a string from the server
      InputStream inFromServer = client.getInputStream();
      DataInputStream in
              = new DataInputStream(inFromServer);
      System.out.println("Server says " + in.readUTF());
      //closes the socket, 
      //which makes this Socket object no longer capable of connecting 
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
