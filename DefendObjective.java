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
		super(target);
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
			else if (phase == Phase.BUILD) {
				buildIfNecessary(owner);
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
					Logger.log("Shooting " + p + " (" + n + " arrows)", 3);
					nShots += n;
				}
			}
		}
		
		if (nShots > 0)
			Logger.log("Shot a total of " + nShots + " arrows", 3);
		return nShots;
	}
	
	/**
	 * If not done already, add new objectives that correspond to building towers around
	 * the target in order to attain the required
	 * TODO: check if the zone is safe enough
	 * @param owner
	 * @return Wether new objectives were placed
	 */
	protected boolean buildIfNecessary(Agent owner) {
		if (!askedForTowers) {
			askedForTowers = true;
			Logger.log("Asking for towers to the owner");
			List<Position> around = Map.getNeighborsEdge(Map.base, Interface.PORTEE_TOURELLE);
			// No need to build each towers, only a few are enough
			for(int i = 0; i < around.size(); i += around.size()-1) {
				Objective o = new BuildTowerObjective(around.get(i));
				o.setPriority(getPriority());
				owner.addObjective(o);
			}
			// Even try and go further away
			around = Map.getNeighborsEdge(Map.base, Interface.PORTEE_TOURELLE * 2);
			Objective o = new BuildTowerObjective(around.get(around.size() / 2));
			o.setPriority(getPriority() / 2f);
			owner.addObjective(o);
			
			// TODO: send more sorcerers if needed (how to do that?)
			return true;
		}
		return false;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	@Override
	public String toString() {
		return "Objective[Defend objective " + getTarget() + "]";
	}
}
