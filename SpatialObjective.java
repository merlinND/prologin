/**
 * An objective which implies a spatial target
 * @author Merlin Nimier-David
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
		this(target, null);
	}
	public SpatialObjective(Position target, Objective parentObjective) {
		super(parentObjective);
		this.target = target;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public Position getTarget() {
		return target;
	}
	protected void setTarget(Position target) {
		this.target = target;
	}
}
