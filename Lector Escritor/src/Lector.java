import java.util.Random;

public class Lector extends Thread{
	private int id;
	public Lector(int id) {
		start();
		this.id = id;
	}
	
	private void leer() {
		Random rdmNum = new Random();
		int sleepTime = rdmNum.nextInt(250 - 25 + 1) + 25;
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int consumedNumber = Buffer.getLibreria().poll();
		System.out.println("Lector " + this.id + ": ISBN " + consumedNumber + " leído.");
	}
	
	@Override
	public void run() {
		while(true) {
			if(Buffer.getLibreria().size() == 0) {
				System.out.println("Lector " + this.id + ": El buffer está vacio, esperando a que el escritor escriba.");
			}
			try {
				Buffer.getsNoVacio().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			leer();
			
			Buffer.getsNoLleno().release();
		}
	}
}
