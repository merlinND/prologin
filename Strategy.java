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
		// Begin => Run: only when all objectives are complete
		if (currentPart == Part.BEGIN) {
			boolean allDone = true;
			for (Objective o : getObjectives())
				allDone = (allDone && o.isCompleted());
			if (allDone)
				setPart(Part.RUN);
		}
		// Run => end: depends on the strategy
		else if (currentPart == Part.RUN) {
			if (goToEnd(roundCount))
				setPart(Part.END);
		}
		
		Logger.log("We are at round " + roundCount + ", now at strategy part " + currentPart);
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
	 * @return
	 */
	public List<Objective> getIdleObjectives() {
		return objectives.get(Part.IDLE);
	}
	
	/**
	 * Switch to the given part and notify the mothership
	 * @param part
	 */
	protected void setPart(Part part) {
		Logger.log("----- Strategy going into part " + part + " -----");
		
		this.currentPart = part;
		Mothership.getInstance().resetObjectives();
	}
}