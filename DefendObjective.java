
public class DefendObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	
	/*
	 * METHODS
	 */
	public DefendObjective(Position target) {
		super(target);
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		// As a tower: defending implies killing opponents closest to the position
		if (owner instanceof Tower) {
			// TODO
		}
		// As a sorcerer : defending implies moving to the position and staying there
		// TODO: eventually, build a tower to defend that same target (in construction phase)
		else if (owner instanceof Sorcerers) {
			if (phase == Phase.MOVE && owner.getPosition() != getTarget()) {
				((Sorcerers)owner).moveClosestTo(getTarget());
			}
		}
		return true;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
}
