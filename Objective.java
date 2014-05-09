
/**
 * 
 * @author Merlin Nimier-David
 */
public abstract class Objective implements Comparable<Objective> {
	/*
	 * PROPERTIES
	 */
	public static final float MAX_PRIORITY = 1000000;
	protected float priority;
	protected boolean completed;
	
	/*
	 * METHODS
	 */
	public Objective() {
		
	}
	
	public abstract boolean perform(Phase phase, Agent owner);
	
	@Override
	public int compareTo(Objective other) {
		return (int)(other.priority - this.priority);
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
	
	public boolean isCompleted() {
		return completed;
	}
	/**
	 * Set this objective to completed, which will cause it
	 * to be removed from its owner's objectives.
	 */
	public void markCompleted() {
		setCompleted(true);
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
