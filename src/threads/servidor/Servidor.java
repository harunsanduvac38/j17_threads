package threads.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private int puerto;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
	}

	public void start() {
		
		System.out.println("Servidor esperando conexiones");
		try (ServerSocket server = new ServerSocket(puerto)){
			
			while(true) {
				Socket cliente = server.accept();
				//Atender al cliente
				new AtiendeCliente(cliente);
				
			}
			
		} catch (IOException e) {
			System.err.println("No se puedo abrir el puerto " + puerto);		
		}
	}

	private class AtiendeCliente implements Runnable {
	
		private Socket socket;
		
		public AtiendeCliente(Socket socket) {
			this.socket = socket;
			new Thread(this).start();
		}
	
		@Override
		public void run() {
			try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			
				
				out.println("Bienvenido al Servidor MUTONTO!!");
				
				String linea;
				while ((linea = in.readLine()) != null) {
					out.println("SRV: " + linea);
				}
				
			}catch (IOException e) {
			e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Servidor(1234).start();
	}
	
}


 