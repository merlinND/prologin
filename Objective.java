
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
	public static final int DEFAULT_SPENDING_LIMIT = -1;
	protected int spendingLimit, spent;
	
	protected Objective parentObjective;
	
	/*
	 * METHODS
	 */
	public Objective() {
		this(null);
	}
	public Objective(Objective parentObjective) {
		this.priority = 0f;
		this.parentObjective = parentObjective;
		
		this.spendingLimit = DEFAULT_SPENDING_LIMIT;
		this.spent = 0;
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
	
	public int getSpendingLimit() {
		return spendingLimit;
	}
	public void setSpendingLimit(int spendingLimit) {
		this.spendingLimit = spendingLimit;
	}
	public void addSpending(int spent) {
		this.spent += spent;
	}
	public boolean canSpendMore() {
		return (this.spendingLimit < 0) || (this.spent < this.spendingLimit);
	}
	
	/**
	 * @return Whether or not this objective currently needs more work
	 */
	public boolean isSatisfied() {
		return false;
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
	
	private Objective getParentObjective() {
		return this.parentObjective;
	}
	public Objective getRootObjective() {
		Objective current = this;
		while (current.getParentObjective() != null)
			current = current.getParentObjective();
		return current;
	}
}
