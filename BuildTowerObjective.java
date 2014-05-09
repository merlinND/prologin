
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
		this(target, null);
	}
	/**
	 * 
	 * @param target
	 * @param parentObjective The objective that asked to build this tower (can be null)
	 */
	public BuildTowerObjective(Position target, Objective parentObjective) {
		super(target, parentObjective);
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		
		if (phase == Phase.BUILD) {
			// ----- If we can build right now, try and do it
			if (canBuild(getTarget())) {
				// TODO: support larger tower range (when more defensive capacity is needed?)
				Erreur status = Interface.construire(getTarget(), Interface.PORTEE_TOURELLE);
				if (status != Erreur.OK) {
					Logger.err("We were not able to build a tower on " + getTarget() + " because " + status, 2);
					return false;
				}
				
				Tourelle t = Interface.tourelle_case(getTarget());
				// The new tower will automatically defend its position
				Tower tower = new Tower(t, getTarget());
				Mothership.getInstance().registerAgent(tower, parentObjective);
				markCompleted();
				addSpending(Interface.COUT_TOURELLE);
				return true;
			}
			
			// ----- If tower is not buildable right now
			// If it is because the spot is full, try and find an adjacent one
			if (!Map.isBuildable(getTarget())) {
				for (Position p : Map.getNeighbors(getTarget())) {
					if (canBuild(p)) {
						Logger.log("Couldn't build on " + getTarget() + ", we will try on " + p);
						setTarget(p);
						break;
					}
				}
				return false;
			}
			
			// If it is because it requires another tower (build range error),
			Position closestTower = Map.getClosestTowerPosition(getTarget());
			if (Map.distance(getTarget(), closestTower) > Interface.CONSTRUCTION_TOURELLE)  {
				// Go ahead and ask to build the closest missing one
				// TODO: only build it if we can afford it (resource allocation)
				Position[] path = Map.path(getTarget(), closestTower);
				int towersNeeded = (path.length / Interface.CONSTRUCTION_TOURELLE);
				if (path.length > 0 && towersNeeded < Map.possibleTowers()) {
					Position current = getTarget();
					int d = 0;
					while (d <= Interface.CONSTRUCTION_TOURELLE && d < path.length) {
						current = path[path.length - d - 1];
						d++;
					}
					// Ask for it to be built
					Objective intermediate = new BuildTowerObjective(current, getRootObjective());
					intermediate.setPriority(getPriority() * 1.01f);
					owner.addObjective(intermediate);
				}
				else {
					Logger.err("We won't be able to build a tower on " + getTarget() + " because we won't ever attain it", 2);
					markCompleted();
				}
				return false;
			}

			Logger.err("For some reasons, we were not able to build a tower on " + getTarget(), 2);
			return false;
		}
		// During the other phases, nothing to do
		else {
			return true;
		}
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
