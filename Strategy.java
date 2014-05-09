import java.util.ArrayList;
import java.util.List;


public abstract class Strategy {
	protected List<Objective> objectives;

	// TODO: split a strategy into phases (begin / run / end) + an "idle" objective
	
	public Strategy() {
		objectives = new ArrayList<Objective>();
	}
	
	/**
	 * The ordered list of objectives to realize this strategy.
	 * Each objective has it `priority` field set for more precision.
	 * The last objective is the one that will be repeated when all the others
	 * are being taken care of.
	 * @return
	 */
	public abstract List<Objective> getObjectives();
	
}