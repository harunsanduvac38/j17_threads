package threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Th06EcuacionSegundoGrado {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		double a = 1;
		double b = 3;
		double c = 2;
		
		//Calcular b^2 
		CompletableFuture<Double> bCuadrado = CompletableFuture.supplyAsync(() -> {
			ThreadUtil.sleep(3000);
			System.out.println("Calculando b^2...");
			return b * b;			
		});
		
		
		//calcular 4 * a * c
		
		CompletableFuture<Double> cuatroAC = CompletableFuture.supplyAsync(() -> {
			ThreadUtil.sleep(4000);
			System.out.println("Calculando 4 * a * c...");
			return 4 * a * c;
		});
		
		//Calcular el discriminante => b^2 - 4ac
		//A bCuadrado lo combinamos con cuatroAC. Esa operación se realizará cuando los dos hayan finalizado.
		//Usar thenCombine y le pasaremos un BiFunction
		
		CompletableFuture<Double> discriminante = bCuadrado.thenCombine(cuatroAC, (b2, ac4) -> {
			ThreadUtil.sleep(1000);
			System.out.println("Calculando discrimnante...");
			return b2 - ac4;
			});
		
		//Calcular la raiz del discriminante
		//Si el discriminante ees menor que 0, lanzamos una excepción
		
		CompletableFuture<Double> raizDiscriminante = discriminante.thenApply(d -> {
			ThreadUtil.sleep(3000);
			if(d < 0) {
				throw new RuntimeException("Discriminante negativo");				
			}else {
			System.out.println("Calculando el raiz del discriminante...");
			return Math.sqrt(d);
			}
		});
		
		//Calcular -b
		CompletableFuture<Double> menosB = CompletableFuture.supplyAsync(() -> {
			ThreadUtil.sleep(500);
			System.out.println("Calculando -b...");
			return -b;
		});
		
		
		
		
		
		//Calcular 2 * a
		
		CompletableFuture<Double> dosA = CompletableFuture.supplyAsync(() -> {
			ThreadUtil.sleep(750);
			System.out.println("Calculando 2 * a");
			return 2 * a;
		});
		
		
		
		//Calcular x1 => ((-b + raiz(discriminante)) / 2a
		//Combinar menosB con raizDiscriminante y al resultado(cuando termine) lo combinamos con dosA
		
		CompletableFuture<Double> x1 = menosB.thenCombine(raizDiscriminante, (mB, raiz) -> {
			ThreadUtil.sleep(500);
			System.out.println("Calculando x1...");
			return mB + raiz;
		}).thenCombine(dosA, (num, dA) -> num / dA);
		
		
		//Calcular x2 => ((-b - raiz(discriminante)) / 2a
		//Combinar menosB con raizDiscriminante y al resultado(cuando termine) lo combinamos con dosA
		
		CompletableFuture<Double> x2 = menosB.thenCombine(raizDiscriminante, (mB, raiz) -> {
			ThreadUtil.sleep(500);
			System.out.println("Calculando x2...");
			return mB - raiz;
		}).thenCombine(dosA, (num, dA) -> num / dA);
		
		System.out.println("x1 = " + x1.get());
		System.out.println("x2 = " + x2.get());
		
		
	}

}
