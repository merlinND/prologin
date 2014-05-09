

/**
 * A simple class to mark the presence of a home-defense effort
 * in the strategy.
 * @author mnimierdavi
 *
 */
public class DefendBaseObjective extends DefendObjective {

	public DefendBaseObjective() {
		super(Map.base);
	}
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		// TODO: react to attacks
		
		return super.perform(phase, owner);
	}
	
	@Override
	public String toString() {
		return "Objective[DefendBase defending " + getTarget() + "]";
	}
}
