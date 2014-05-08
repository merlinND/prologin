
public class DefendTargetObjective extends Objective {
	/*
	 * PROPERTIES
	 */
	protected Position target;
	
	/*
	 * METHODS
	 */
	public DefendTargetObjective(Position target) {
		this.target = target;
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		System.out.println("Objective: defend target " + target);
		
		// As a tower: defending implies killing opponents closest to the position
		if (owner instanceof Tower) {
			
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
	public Position getTarget() {
		return target;
	}
}
