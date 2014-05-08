
public class Tower extends Agent {
	/*
	 * PROPERTIES
	 */
	/**
	 * This tower may be asked to defend a particular target position.
	 * This position is used to choose in which order to attack nearby enemies.
	 */
	protected Position target = null;
	
	/*
	 * METHODS
	 */
	/**
	 * By default, a tower defends its own position
	 */
	public Tower(Position position) {
		super(position);
		addObjective(new DefendTargetObjective(getPosition()));
	}
	
	/**
	 * Towers can only do things in "shoot" phase
	 * TODO: towers don't have variable objectives?
	 */
	public void performAction(Phase phase) {
		if (phase == Phase.SHOOT)
			objectives.peek().perform();
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
}
