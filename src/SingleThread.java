
public class SingleThread extends Thread{
	static volatile boolean runFlag = true;

	public void run() {
		while(runFlag){
			try {
				MovingBlock.moveDown();
				Paint.getCanvas().repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static void stopRunning() {
		runFlag = false;
	}
	
	void startRunning() {
		runFlag = true;
	}
	
}



