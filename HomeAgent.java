
public class HomeAgent extends Agent {
	/*
	 * PROPERTIES
	 */
	
	
	/*
	 * METHODS
	 */
	public HomeAgent(Position position) {
		super(position);
	}

	/*
	 * GETTERS & SETTERS
	 */
	@Override
	public void update() {
		// When we detect enemy presence near base
		// => do everything we can to protect it
		float danger = Map.evaluateDanger(Map.base);
		if (danger > 0) {
			Objective reaction = new OccupyObjective(Map.base);
			reaction.setSpendingLimit((int)(danger * Interface.COUT_SORCIER * 2));
			addObjective(reaction);
		}
	}

}
