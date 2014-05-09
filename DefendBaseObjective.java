import java.util.List;



/**
 * A simple class to mark the presence of a home-defense effort
 * in the strategy.
 * @author Merlin Nimier-David
 *
 */
public class DefendBaseObjective extends DefendObjective {
	
	public DefendBaseObjective() {
		super(Map.base);
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		// TODO: build an actual wall around the base, open it for the end rush
		// TODO: react to attacks
		
		return super.perform(phase, owner);
	}
	
	@Override
	public boolean isSatisfied() {
		// TODO Assess if the base is safe
		return super.isSatisfied();
	}
	
	protected void askToBuild(Agent owner) {
		List<Position> around = Map.getNeighborsEdge(getTarget(), Interface.PORTEE_TOURELLE);
		// No need to build each towers, only a few are enough
		for(int i = 0; i < around.size(); i += around.size()-1) {
			Objective o = new BuildTowerObjective(around.get(i), getRootObjective());
			o.setPriority(getPriority());
			owner.addObjective(o);
		}
		// Even try and go further away
		around = Map.getNeighborsEdge(getTarget(), Interface.PORTEE_TOURELLE * 2);
		for(int i = 0; i < around.size(); i += 2) {
			Objective o = new BuildTowerObjective(around.get(i), getRootObjective());
			o.setPriority(getPriority() / 2f);
			owner.addObjective(o);
		}
		
		// TODO: send more sorcerers if needed (how to do that?)
	}
	
	@Override
	public String toString() {
		return "Objective[DefendBase defending " + getTarget() + "]";
	}
}
