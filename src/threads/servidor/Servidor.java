package threads.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private int puerto;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
	}

	public void start() {
		
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
		
		
	}
	
}
}


 