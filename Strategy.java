import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Strategy {
	enum Part {
		BEGIN,
		RUN,
		IDLE,
		END
	};
	
	protected HashMap<Part, List<Objective>> objectives;
	protected Part currentPart;
	
	// TODO: split a strategy into phases (begin / run / end) + an "idle" objective
	
	public Strategy() {
		objectives = new HashMap<Part, List<Objective>>();
		for (Part p : Part.values())
			objectives.put(p, new ArrayList<Objective>());
		
		currentPart = Part.BEGIN;
	}
	
	/**
	 * If needed, switch to the next part
	 * @param roundCount The number of rounds elapsed
	 */
	public void update(int roundCount) {
		// Begin => Run: by default, when all objectives are complete
		if (currentPart == Part.BEGIN && goToRun(roundCount)) 
			setPart(Part.RUN);
		// Run => end: depends on the strategy
		else if (currentPart == Part.RUN && goToEnd(roundCount))
			setPart(Part.END);
	}
	
	/**
	 * Should we go to the Run part of the strategy?
	 * By default, we do when each objective is complete
	 */
	protected boolean goToRun(int roundCount) {
		boolean allDone = true;
		for (Objective o : getObjectives())
			allDone = (allDone && o.isCompleted());
		return allDone;
	}
	
	/**
	 * Should we skip to the End part of the strategy?
	 */
	protected abstract boolean goToEnd(int roundCount);
	
	/**
	 * The ordered list of objectives to realize this strategy.
	 * Each objective has it `priority` field set for more precision.
	 * @return The objectives for the current part
	 */
	public List<Objective> getObjectives() {
		return objectives.get(currentPart);
	}
	/**
	 * What to do when everything else it taken care of
	 * @warning Only in RUN phase
	 * @return
	 */
	public List<Objective> getIdleObjectives() {
		if (currentPart == Part.RUN)
			return objectives.get(Part.IDLE);
		else
			return new ArrayList<Objective>();
	}
	
	/**
	 * Switch to the given part and notify the mothership
	 * @param part
	 */
	protected void setPart(Part part) {
		Logger.log("----- Strategy going into part " + part + " -----", 1);
		
		this.currentPart = part;
		Mothership.getInstance().resetObjectives();
	}

	/**
	 * Add an objective to the current part
	 * @param objective
	 */
	public void addObjective(Objective objective) {
		objectives.get(currentPart).add(objective);
	}
}