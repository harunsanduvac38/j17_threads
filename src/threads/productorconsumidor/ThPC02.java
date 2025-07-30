package threads.productorconsumidor;

import java.util.LinkedList;
import java.util.Queue;

import threads.ThreadUtil;

public class ThPC02 {
	
	private static final Queue<Integer> buffer = new LinkedList<Integer>();
	private static final int CAPACIDAD = 5;
	private static final int CANT_PROD = 9;
	private static final int CANT_CONS = 8;
	private static int valor = 0;
	private static final Object LOCK = new Object();
	
	
	public static void main(String[] args) {
		
		Runnable productor = () -> {
			while(true) {
				ThreadUtil.sleep();
				synchronized (LOCK) {
				
					while (buffer.size() == CAPACIDAD) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					buffer.offer(valor);
					System.out.println(Thread.currentThread().getName() + " produjo " + valor);
					valor++;
					LOCK.notifyAll();
					
					
					
						
					//control innecesario
					if(buffer.size() > CAPACIDAD) {
						System.err.println("Error se produjeron " + buffer.size());
						System.exit(1);
						
					}
				}
			}
		};
		
		
		Runnable consumidor = () -> {
			while(true) {
				ThreadUtil.sleep();
				synchronized (LOCK) {
					while(buffer.size() == 0) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					int v = buffer.poll();
					System.out.println(Thread.currentThread().getName() + " consumio " + v);
					LOCK.notifyAll();
					
					
					//control innecesario
					if(buffer.size() < 0) {
						System.err.println("El tamaño del buffer es " + buffer.size());
						System.exit(1);
					}
				
				}
			
			}
		};
		
		
		
		
		for (int i = 1; i < CANT_PROD; i++) {
			new Thread(productor, "productor" + i).start();
			
		}
		for (int i = 1; i < CANT_CONS; i++) {
			new Thread(consumidor, "consumidor" + i).start();
			
		}
				
		
		
	}

}
