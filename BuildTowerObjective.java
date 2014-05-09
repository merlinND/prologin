
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
			if (canBuild(getTarget())) {
				// TODO: support larger tower range
				Erreur status = Interface.construire(getTarget(), Interface.PORTEE_TOURELLE);
				if (status == Erreur.OK) {
					Tourelle t = Interface.tourelle_case(getTarget());
					// The new tower will automatically defend its position
					Tower tower = new Tower(t, getTarget());
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
	
	protected boolean canBuild(Position p) {
		int n = Interface.tourelles_joueur(Interface.moi()).length;
		return (Map.canBuild(getTarget()) && (n < Interface.MAX_TOURELLES));
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	

}
