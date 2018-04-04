package ex4;

public class Chopstick {
	private int ID;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;
	private boolean free;

	Chopstick(int ID) {
		this.ID = ID;
		free = true;
	}
	
	synchronized void take() throws InterruptedException {
		if (free) {
			free = false;
		} else {
			while (!free)  {
				wait();
			}
		}
	}
	
	synchronized void release() {
		if (!free) {
			free = true;
			notify();
		}
	}
	    
	public int getID() {
	    return(ID);
	}
}
