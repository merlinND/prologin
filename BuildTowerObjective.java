
/**
 * Build a tower at the desired place.
 * If needed, move closer to it.
 * @author Merlin Nimier-David
 *
 */
public class BuildTowerObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	protected Objective parentObjective;
	
	/*
	 * METHODS
	 */
	public BuildTowerObjective(Position target) {
		this(target, null);
	}
	/**
	 * 
	 * @param target
	 * @param parentObjective The objective that asked to build this tower (can be null)
	 */
	public BuildTowerObjective(Position target, Objective parentObjective) {
		super(target);
		this.parentObjective = parentObjective;
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		
		// If we can build right now, do it
		if (phase == Phase.BUILD) {
			Logger.log("Trying to build tower on " + getTarget(), 3);
			if (canBuild(getTarget())) {
				// TODO: support larger tower range (when more defensive capacity is needed?)
				Erreur status = Interface.construire(getTarget(), Interface.PORTEE_TOURELLE);
				if (status == Erreur.OK) {
					Tourelle t = Interface.tourelle_case(getTarget());
					// The new tower will automatically defend its position
					Tower tower = new Tower(t, getTarget());
					Mothership.getInstance().registerAgent(tower, parentObjective);
					markCompleted();
				}
				else {
					Logger.err("We were not able to build a tower on " + getTarget() + " because " + status, 2);
					return false;
				}
			}
			// If tower is not buildable right now
			// If it requires another tower (build range error),
			// go ahead and try to build it
			else if (false) {
				// TODO: build recursively so as to make it possible
			}
			// If the spot is full, try and find an adjacent one
			else if (Map.hasTower(getTarget())) {
				for (Position p : Map.getNeighbors(getTarget())) {
					if (canBuild(p)) {
						Logger.log("Couldn't build on " + getTarget() + ", we will try on " + p);
						setTarget(p);
						break;
					}
				}
			}
			else {
				Logger.err("We were not able to build a tower on " + getTarget(), 2);
				return false;
			}
		}
		
		return true;
	}
	
	protected boolean canBuild(Position p) {
		int n = Interface.tourelles_joueur(Interface.moi()).length;
		return (Map.canBuild(getTarget()) && (n < Interface.MAX_TOURELLES));
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	@Override
	public String toString() {
		return "Objective[Build tower on " + getTarget() + "]";
	}

}
