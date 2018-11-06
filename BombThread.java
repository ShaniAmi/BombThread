import java.util.concurrent.BlockingQueue;



public class BombThread extends Thread {
	
	private final BlockingQueue<Request> requests;
	public WorkManager workManager;
	public int percentage;
	int secondsLeftToExplosion;
	Request currRequest;
	
	public BombThread(int secondsLeftToExplosion, BlockingQueue<Request> requests) {
		percentage = 0;
		this.requests = requests;
		this.secondsLeftToExplosion = secondsLeftToExplosion;
		workManager = new WorkManager();
	}
	
	public void run() {
		workManager.start();
		try {
		while (this.secondsLeftToExplosion > 0 && 
				percentage != 100) {
			currRequest = requests.take();
			if (currRequest.type.equals("update percentage")) {
				percentage = Math.min(percentage + currRequest.value, 100);
			} else {
				secondsLeftToExplosion = Math.max(currRequest.value, 0);
				System.out.println(currRequest.type + " " + currRequest.value);
			}
		}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		if (percentage == 100) {
			Status.setBombStatus(bombStatus.NEUTRALIZED);
			System.out.println("The bomb was neutralized!");
		} else {
			Status.setBombStatus(bombStatus.EXPLODED);
			System.out.println("The bomb was not neutralized");
		}
		workManager.notifyMe();
	}
	

}
