
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
	public boolean perform() {
		System.out.println("Objective: defend target " + target);
		return true;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
}
