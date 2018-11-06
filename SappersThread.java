import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class SappersThread extends Thread {
	
	private final BlockingQueue<Request> queue;
	
	public SappersThread(BlockingQueue<Request> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (Status.getBombStatus() == bombStatus.ONPROCESS) {
				Random random = new Random();
				int randPercentage = Math.max(1, random.nextInt(10));
				queue.put(new Request("update percentage", randPercentage));
				sleep(50);
			}	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
