import java.util.List;



/**
 * Based on BalancedStrategy
 * @author mnimierdavi
 */
public class AggressiveStrategy extends BalancedStrategy {

	public AggressiveStrategy() {
		super();
		
		Objective o;
		// ----- Begin part
		// Send some scouts
		List<Objective> begin = objectives.get(Part.BEGIN);
		for (Position b : Map.getOpponentBases()) {
			o = new OccupyObjective(b);
			o.setPriority(0.1f);
			o.setSpendingLimit(5 * Interface.COUT_SORCIER);
			begin.add(o);
		}
		
		// ----- Run part
		
		// ----- Idle part
		
		// ----- Final part
		List<Objective> end = objectives.get(Part.END);
		end.clear();
		o = new KillThemAllObjective();
		end.add(o);
	}
	
	@Override
	protected boolean goToEnd(int roundCount) {
		return roundCount >= (Interface.MAX_TOUR - 14);
	}
}
