
/**
 * 
 * @author mnimierdavi
 */
public abstract class Objective implements Comparable<Objective> {
	/*
	 * PROPERTIES
	 */
	public static final float MAX_PRIORITY = 1000000;
	protected float priority;
	
	/*
	 * METHODS
	 */
	public Objective() {
		
	}
	
	public abstract boolean perform(Phase phase, Agent owner);
	
	@Override
	public int compareTo(Objective other) {
		return (int)(this.priority - other.priority);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public float getPriority() {
		return priority;
	}
	public void setPriority(float priority) {
		this.priority = priority;
	}
	
}
