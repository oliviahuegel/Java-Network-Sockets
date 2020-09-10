import java.io.*;
import java.net.*;
import java.util.*;

public class PingClient{
public static void main(String[] args) throws Exception{ 

// Get command line arguments. 
if (args.length != 2){ 
System.out.println("Required arguments: host port "); 
return;
} 

//Set the server address and port 
InetAddress serverAddress = InetAddress.getByName(args[0]); 
int serverPort = Integer.parseInt(args[1]); 

// Create a datagram socket for receiving and sending UDP packets 
// through the port specified on the command line. 
DatagramSocket socket = new DatagramSocket(); 

// Need to set the socket timeout -- 1 sec. 
socket.setSoTimeout(1000); 

// Create a datagram packet for replies 
DatagramPacket reply = new DatagramPacket(new byte[1024], 1024); 

// Send the 10 PING messages 
for (int i = 0; i < 10; ++i){ 

// Create the message
long time = (new Date()).getTime();
String message = "PING " + i + " " + time + "\r\n";
byte[] data = message.getBytes("ISO8859-1"); 

//Create a datagram packet to send 
DatagramPacket request = new DatagramPacket(data, data.length, serverAddress, serverPort); 

// Send the message 
System.out.print("Packet sent: " + message); socket.send(request); 

// Get the reply 
try{ 
	socket.receive(reply); 
} 
catch (SocketTimeoutException e) 
{ 
	System.out.println("No reply from server."); 
	System.out.println(); continue; } long receiveTime = (new Date()).getTime(); 
	System.out.print("Packet received. "); System.out.print("time = " + (receiveTime - time)); 
	System.out.println(); System.out.println(); 
} 
} 
} 