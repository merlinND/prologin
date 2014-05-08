import java.util.PriorityQueue;


public abstract class Agent {
	/*
	 * PROPERTIES
	 */
	protected Position position;
	protected PriorityQueue<Objective> objectives;
	
	/*
	 * METHODS
	 */
	public Agent(Position position) {
		this.position = position;
		objectives = new PriorityQueue<Objective>();
	}

	public void performAction(Phase phase) {
		objectives.peek().perform();
		// TODO: clear completed objectives
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
	
}
