package ex3;

public class Chopstick {
	private int ID;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;
	private boolean free;

	Chopstick(int ID) {
		this.ID = ID;
		free = true;
	}
	
	// Returns true if the philosopher got the chopstick, false if not
	synchronized void take(Philosopher p) {
		if (free) {
			free = false;
			p.interrupt();
		} else {
			while (!free)  {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	synchronized void release(Philosopher p) {
		if (!free) {
			free = true;
			notify();
			// If this philosopher is waiting, interrupt them
			if (p.waitingLeft || p.waitingRight) {
				p.interrupt();
				p = null;
			}
		}
	}
	    
	public int getID() {
	    return(ID);
	}
}
