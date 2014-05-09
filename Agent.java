import java.util.Arrays;
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
	
	/**
	 * Try and perform the first objective.
	 * If it fails, move on to the next.
	 * If it completes, remove it and move on to the next.
	 * TODO: find a way to do more things while not conflicting?
	 * @param phase
	 */
	public void performAction(Phase phase) {
		if (objectives.size() > 0) {
			boolean failed = false, hasCompleted = false;
			Object[] sorted = objectives.toArray();
			Arrays.sort(sorted);
			int i = 0;
			do {
				Objective o = (Objective)sorted[i];
				failed = o.perform(phase, this);
				hasCompleted = o.isCompleted();
				// Get rid of the completed objectives
				if (hasCompleted)
					objectives.remove(o);
				i++;
			} while (i < sorted.length && (failed || hasCompleted));
		}
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	/**
	 * TODO: make sure that the most recent objectives (with same priority) are handled first
	 * @param objective
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
