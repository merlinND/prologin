import java.util.Iterator;
import java.util.List;


public class DefendObjective extends SpatialObjective {
	/*
	 * PROPERTIES
	 */
	
	/*
	 * METHODS
	 */
	public DefendObjective(Position target) {
		super(target);
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
		// TODO: eventually, build a tower to defend that same target (in construction phase)
		else if (owner instanceof Sorcerers) {
			if (phase == Phase.MOVE && owner.getPosition() != getTarget()) {
				((Sorcerers)owner).moveClosestTo(getTarget());
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
	
	/*
	 * GETTERS & SETTERS
	 */
	@Override
	public String toString() {
		return "Objective[Defend objective " + getTarget() + "]";
	}
}
