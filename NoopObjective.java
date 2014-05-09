/**
 * Do nothing and be happy about it.
 * @author mnimierdavi
 */
public class NoopObjective extends Objective {

	@Override
	public boolean perform(Phase phase, Agent owner) {
		return true;
	}

	
	@Override
	public String toString() {
		return "Objective[Noop]";
	}
}