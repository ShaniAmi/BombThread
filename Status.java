
public class Status {
	
	private static bombStatus bombStatus;
	
	public static bombStatus getBombStatus() {
		return bombStatus;
	}
	
	public static synchronized void setBombStatus(bombStatus status) {
		bombStatus = status;
	}

}
