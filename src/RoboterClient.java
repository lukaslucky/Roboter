

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoboterClient {
	// Thread zum Empfang von Servernachrichten
		private Receiver receiverB;
		private Receiver receiverP;
		
		

		class Receiver extends Thread {
			
			private String name;
			private String host;
			private int port;
			private Socket socket;
			private PrintWriter out = null;
			private BufferedReader in;
			
			
			@Override
			public void run() {
				if (port == 9999) {
				try {
					// Socket und Streams erzeugen
					socket = new Socket(host, port);
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					// Teilnehmername senden
					sendActionB(name);
					String line;
					String test [] = null;
					
					// vom Server empfangene Beitraege anzeigen
					while ( (line = in.readLine()) != null) {
						display(line);
						sendActionB(line);
					}
				} catch (IOException e) {
					display("Ende run");
				}
				stopActionB();
				System.out.println("receiver ends");
			} else {
				try {
					// Socket und Streams erzeugen
					socket = new Socket(host, port);
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					// Teilnehmername senden
					 
					//sendActionP(name);
					
					String line;
					// vom Server empfangene Beitraege anzeigen
					while ( (line = in.readLine()) != null) {
						sendActionB(line);
						display(line);	
					}
				} catch (IOException e) {
					display("Ende run" + e);
				}
				stopActionP();
				System.out.println("receiver ends");
			}
		}
	} 

		public RoboterClient() {
			super();
			// Receiver-Thread erzeugen
			receiverB = new Receiver();
			receiverB.host = "localhost";
			receiverB.port = 9999;
			receiverP = new Receiver();
			receiverP.host = "localhost";
			receiverP.port = 8150;
			
		}

		public void startActionB(String name) {
			receiverB.name = name;
			// Receiver-Thread starten
			receiverB.start();
		}
		public void startActionP(String name) {
			receiverP.name = name;
			// Receiver-Thread starten
			receiverP.start();
		}

		public void stopActionB() {
			try {
				// Socket schliessen
				receiverB.socket.close();
			} catch (IOException e) {
			}
		}
		public void stopActionP() {
			try {
				// Socket schliessen
				receiverP.socket.close();
			} catch (IOException e) {
			}
		}

		public void sendActionB(String line) {
			// Text an Server senden
			receiverB.out.println(line);
		}
		public void sendActionP(String line) {
			// Text an Server senden
			receiverP.out.println(line);
		}

		void display(String text) {
			// Consolenausgabe
			System.out.println(text);
		}
	}

