/**
 * An objective which implies a spatial target
 * @author mnimierdavi
 *
 */
public abstract class SpatialObjective extends Objective {
	/*
	 * PROPERTIES
	 */
	protected Position target;
	
	/*
	 * METHODS
	 */
	public SpatialObjective(Position target) {
		this.target = target;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public Position getTarget() {
		return target;
	}
}
