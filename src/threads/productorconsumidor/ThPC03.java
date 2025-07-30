package threads.productorconsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import threads.ThreadUtil;

public class ThPC03 {
	
//	private static final Queue<Integer> buffer = new LinkedList<Integer>();
	private static final int CAPACIDAD = 5;
	private static final LinkedBlockingQueue<Integer> buffer = new LinkedBlockingQueue<Integer>(CAPACIDAD);
	
	
	private static final int CANT_PROD = 6;
	private static final int CANT_CONS = 3;
//	private static int valor = 0;
	private static AtomicInteger valor = new AtomicInteger(0);
	
	
//	private static final Object LOCK = new Object();
	
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(8);
//		System.out.println(Runtime.getRuntime().availableProcessors());
		
		
		
		
		
		
		Runnable productor = () -> {
			while(true) {
				ThreadUtil.sleep();
				int v = valor.getAndIncrement();
				buffer.add(v);
				System.out.println(Thread.currentThread().getName() + " produjo " + v);
				
			}
		};
		
		
		Runnable consumidor = () -> {
			while(true) {
				ThreadUtil.sleep();
				int v;
				try {
					v = buffer.take();
					System.out.println(Thread.currentThread().getName() + " consumio " + v);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
			}
		};
		
		
		
		
		for (int i = 1; i < CANT_PROD; i++) {
			executor.submit(productor);
		}
		for (int i = 1; i < CANT_CONS; i++) {
			executor.submit(consumidor);
		}
				
		
		
	}

}
