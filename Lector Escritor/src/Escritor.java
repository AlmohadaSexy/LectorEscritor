
public class Escritor extends Thread{
	
	public Escritor() {
		start();
	}
	
	
	private void escribe() throws InterruptedException {
		int numP =(int)(Math.random()*(999));
		
		int sleepTime = (int)(Math.random()*(250-25+1));
		
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
