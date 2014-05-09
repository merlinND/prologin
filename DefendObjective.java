import java.util.Iterator;
import java.util.List;


public class DefendObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	// TODO: replace by zone-safety notion
	protected boolean askedForTowers;	
	
	/*
	 * METHODS
	 */
	public DefendObjective(Position target) {
		this(target, null);
	}
	public DefendObjective(Position target, Objective parentObjective) {
		super(target, parentObjective);
		askedForTowers = false;
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		// As a tower: defending implies killing opponents closest to the position
		if (owner instanceof Tower) {
			if (phase == Phase.SHOOT) {
				shootIfNecessary((Tower)owner);
			}
		}
		// As a sorcerer : defending implies moving to the position and staying there
		else {
			if (phase == Phase.MOVE && (owner instanceof Sorcerers)) {
				((Sorcerers)owner).moveClosestTo(getTarget());
			}
			else if (phase == Phase.BUILD && !askedForTowers) {
				askedForTowers = true;
				askToBuild(owner);
			}
		}
		return true;
	}
	
	/**
	 * Shoot any enemy nearby, closest to the target first
	 * @return The number of arrows shot
	 */
	protected int shootIfNecessary(Tower owner) {
		// First protect the target
		List<Position> targets = Map.getNeighbors(getTarget(), owner.getRange());
		// And then yourself if possible
		if (!getTarget().equals(owner.getPosition())) {
			List<Position> around = Map.getNeighbors(owner.getPosition(), owner.getRange());
			targets.addAll(around);
		}
		
		int nShots = 0;
		Iterator<Position> it = targets.iterator();
		while(nShots < owner.getCapacity() && it.hasNext()) {
			Position p = it.next();
			int n = Map.numberOfOpponentSorcerers(p);
			if (n > 0) {
				n = Math.min(n, owner.getCapacity() - nShots);
				Erreur status = Interface.tirer(n, owner.getPosition(), p);
				if (status == Erreur.OK) {
					Logger.log("Shooting " + p + " (" + n + " arrows)", 4);
					nShots += n;
				}
			}
		}
		
		if (nShots > 0)
			Logger.log("Shot a total of " + nShots + " arrows", 4);
		return nShots;
	}
	
	/**
	 * Add new objectives that correspond to building towers around
	 * the target in order to attain the required
	 * TODO: check if the zone is safe enough
	 * @param owner
	 */
	protected void askToBuild(Agent owner) {
		List<Position> around = Map.getNeighborsEdge(getTarget(), Interface.PORTEE_TOURELLE);
		// No need to build each towers, only a few are enough
		for(int i = 0; i < around.size(); i += around.size()-1) {
			Objective o = new BuildTowerObjective(around.get(i), getRootObjective());
			o.setPriority(getPriority());
			owner.addObjective(o);
		}
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	@Override
	public String toString() {
		return "Objective[Defend objective " + getTarget() + "]";
	}
}
