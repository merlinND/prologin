
/**
 * Build a tower at the desired place.
 * If needed, move closer to it.
 * @author mnimierdavi
 *
 */
public class BuildTowerObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	
	
	/*
	 * METHODS
	 */
	public BuildTowerObjective(Position target) {
		super(target);
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		System.out.println("Trying to build tower on " + getTarget());
		
		// If we can build right now, do it
		if (phase == Phase.BUILD) {
			if (Map.canBuild(getTarget())) {
				// TODO: support larger tower range
				Erreur status = Interface.construire(getTarget(), Interface.PORTEE_TOURELLE);
				if (status == Erreur.OK) {
					// The new tower will automatically defend its position
					Tower tower = new Tower(getTarget());
					Mothership.getInstance().addAgent(tower, this);
				}
				else {
					System.err.println("We were not able to build a tower on " + getTarget() + " because " + status);
				}
			}
			// If tower is not buildable right now
			// TODO: try on an adjacent square?
			else {
				System.err.println("We were not able to build a tower on " + getTarget());
			}
		}
		
		return true;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	

}
