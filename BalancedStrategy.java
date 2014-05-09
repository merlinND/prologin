import java.util.List;


public class BalancedStrategy extends Strategy {

	public BalancedStrategy() {
		Objective o;
		
		// ----- Begin part
		// Fortify the base
		o = new DefendBaseObjective();
		o.setPriority(1f);
		objectives.get(Part.BEGIN).add(o);
		
		// ----- Run part
		// Take all interesting points
		// TODO: refactor duplicate code
		List<Objective> run = objectives.get(Part.RUN);
		
		// The closer fountains are more important
		Position[] fountains = {
			Map.FOUNTAIN_N,
			Map.FOUNTAIN_E,
			Map.FOUNTAIN_S,
			Map.FOUNTAIN_W
		};
		int maxFountainDistance = Map.distance(new Position(0, 0), Map.FOUNTAIN_E),
			minFountainDistance = Map.distance(new Position(0, 0), Map.FOUNTAIN_N),
			maxFountainSpending = 800; //...times 2
		float closeFountainPriority = 0.5f,
			  farFountainPriority = 0.1f;
		for (Position f : fountains) {
			int d = Map.distance(Map.base, f);
			float priority = ((d - minFountainDistance) / (float)(maxFountainDistance - minFountainDistance));
			priority = (1 - priority) * (closeFountainPriority - farFountainPriority);
			priority += farFountainPriority;
			
			o = new OccupyObjective(f);
			o.setPriority(priority);
			o.setSpendingLimit((int)(maxFountainSpending * priority));
			run.add(o);
		}
		
		// ----- Idle part
		objectives.get(Part.IDLE).add(new NoopObjective());
		
		// ----- Final part
		// Rush to the artifact
		o = new OccupyObjective(Map.ARTIFACT);
		o.setPriority(1f);
		objectives.get(Part.END).add(o);

	}
	
	@Override
	protected boolean goToRun(int roundCount) {
		return (roundCount >= 10);
	}
	
	@Override
	protected boolean goToEnd(int roundCount) {
		return (roundCount >= (Interface.MAX_TOUR - 10));
	}

}
