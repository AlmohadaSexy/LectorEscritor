import java.util.Random;

public class Escritor extends Thread{
	
	public Escritor() {
		start();
	}
	
	
	private void escribe() throws InterruptedException {
		
		Random rdmNum = new Random();
		int numP = rdmNum.nextInt(999) + 1;
		
		int sleepTime = rdmNum.nextInt(250-25+1)+25; // Entre 25 y 250ms
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Escritor: ISBN "+numP+" escrito.");
		
		// Agregamos al Buffer
		
		Buffer.getLibreria().add(numP);
		
		
	}
	
	
	@Override
	public void run() {
		while(true) {
			if(Buffer.getLibreria().size() == Buffer.bSize) {
				System.out.println("Escritor. Nada mas que escribir, compadre.");
			}
			
			try {
				Buffer.getsNoLleno().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				escribe();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Buffer.getsNoVacio().release();
		}
	}
}
