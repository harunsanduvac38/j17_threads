package threads;

public class Th01Thread extends Thread {
	
	private String mensaje;
	
	public Th01Thread(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println(mensaje);
		}
	}
	
	
	public static void main(String[] args) {
		
		Th01Thread h1 = new Th01Thread("Soy el primero");
		h1.start();
		Th01Thread h2 = new Th01Thread("Soy el segundo");
		h2.start();
		Th01Thread h3 = new Th01Thread("Soy el tercero");
		h3.start();
	}

}
