/**
 * Try to keep a live sorcerer on the target square.
 * @author Merlin Nimier-David
 */
public class OccupyObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	
	
	/*
	 * METHODS
	 */
	public OccupyObjective(Position target) {
		super(target);
	}

	@Override
	public boolean perform(Phase phase, Agent owner) {
		if (owner instanceof Sorcerers && phase == Phase.MOVE) {
			((Sorcerers)owner).moveClosestTo(getTarget());
		}
		return true;
	}

	@Override
	public boolean isSatisfied() {
		return Map.hasControl(getTarget());
	}
	
	/*
	 * GETTERS & SETTERS
	 */
}
