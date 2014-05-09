
public class Tower extends Agent {
	/*
	 * PROPERTIES
	 */
	/**
	 * This tower may be asked to defend a particular target position.
	 * This position is used to choose in which order to attack nearby enemies.
	 */
	protected Position target = null;
	/**
	 * The data structure representing the tower in the game.
	 */
	protected Tourelle tower = null;
	
	/*
	 * METHODS
	 */
	/**
	 * By default, a tower defends its own position
	 */
	public Tower(Tourelle tower, Position position) {
		super(position);
		this.tower = tower;
		addObjective(new DefendObjective(getPosition()));
	}
	
	@Override
	public void update() {
		Tourelle t = Interface.tourelle_case(getPosition());
		if (t.vie <= 0) {
			System.out.println("The tower which was on " + getPosition() + " was destroyed.");
			markDead();
		}
		else {
			this.tower = t;
		}
	}
	
	/**
	 * Towers can only do things in "shoot" phase
	 * TODO: towers don't have variable objectives?
	 */
	public void performAction(Phase phase) {
		if (phase == Phase.SHOOT)
			objectives.peek().perform(phase, this);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public int getRange() {
		return tower.portee;
	}
	/**
	 * @return The number of arrows it can shoot at each turn
	 */
	public int getCapacity() {
		return tower.attaque;
	}
}
