package threads.productorconsumidor;

import java.util.LinkedList;
import java.util.Queue;

import threads.ThreadUtil;

public class ThPC01 {
	
	private static final Queue<Integer> buffer = new LinkedList<Integer>();
	private static final int CAPACIDAD = 5;
	private static int valor = 0;
	
	
	public static void main(String[] args) {
		
		Runnable productor = () -> {
			while(true) {
				ThreadUtil.sleep();
				if(buffer.size() < CAPACIDAD) {
					buffer.offer(valor);
					System.out.println(Thread.currentThread().getName() + " produjo " + valor);
					valor++;
					
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
				if(buffer.size() > 0) {
					int v = buffer.poll();
					System.out.println(Thread.currentThread().getName() + " consumio " + v);
					
					
					//control innecesario
					if(buffer.size() < 0) {
						System.err.println("El tamaño del buffer es " + buffer.size());
						System.exit(1);
					}
				}
			}
		};
		
		new Thread(productor, "productor").start();
		new Thread(consumidor, "consumidor").start();
		
		
		
	}

}
