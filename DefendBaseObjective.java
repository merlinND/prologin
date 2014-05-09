

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
	public boolean isCompleted() {
		// TODO Assess wether base is safe
		return super.isCompleted();
	}
	
	@Override
	public String toString() {
		return "Objective[DefendBase defending " + getTarget() + "]";
	}
}
