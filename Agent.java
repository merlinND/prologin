import java.util.PriorityQueue;


public abstract class Agent {
	/*
	 * PROPERTIES
	 */
	protected Position position;
	protected PriorityQueue<Objective> objectives;
	protected boolean dead;
	
	/*
	 * METHODS
	 */
	public Agent(Position position) {
		this.position = position;
		this.dead = false;
		objectives = new PriorityQueue<Objective>();
	}

	/**
	 * Check if the agent is still alive and well.
	 * Evaluate the progress on all objectives.
	 * Detect danger and adjust objectives appropriately.
	 */
	public abstract void update();
	
	public void performAction(Phase phase) {
		// TODO: find a way to do more things while keeping everything possible
		boolean isCompleted = false;
		do {
			Objective o = objectives.peek();
			o.perform(phase, this);
			isCompleted = o.isCompleted();
			// Get rid of the completed objectives
			if (isCompleted)
				objectives.poll();
		} while (isCompleted);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public void addObjective(Objective objective) {
		objectives.add(objective);
	}
	public void addCompulsoryObjective(Objective objective) {
		// TODO: make sure this object takes the first place (priority +inf?)
		objective.setPriority(Objective.MAX_PRIORITY);
		objectives.add(objective);
	}

	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}

	public PriorityQueue<Objective> getObjectives() {
		return objectives;
	}
	public void setObjectives(PriorityQueue<Objective> objectives) {
		this.objectives = objectives;
	}
	
	public boolean isDead() {
		return dead;
	}
	public void markDead() {
		this.dead = true;
	}
	
}
