package threads;

public class Th04Sincronizacion {
	
	public static void main(String[] args) {
//		m1();
		//m1() hilos desincronizados
//		Thread th1 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});
//		Thread th2 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});
		
		
		Thread th1 = new Thread(() ->{
			while(true) {
				ThreadUtil.sleep();
				m2();
			}
		});
		Thread th2 = new Thread(() ->{
			while(true) {
				ThreadUtil.sleep();
				m2();
			}
		});
		
		th1.start();
		th2.start();
		
		
	}

	
	public static void m1() {
		System.out.println(Thread.currentThread().getName() + " entradndo");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " saliendo");
		System.out.println("------------------------------");
	}
	
	
	public static synchronized void m2() {
		System.out.println(Thread.currentThread().getName() + " entradndo");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " saliendo");
		System.out.println("------------------------------");
	}
}
