import java.util.List;

/**
 * Basic strategy: completely ignore that there might be other players.
 * @author Merlin Nimier-David
 */
public class AloneStrategy extends Strategy {

	public AloneStrategy() {
		// ----- Begin part
		// Empty
		
		// ----- Run part
		// Take all interesting points
		List<Objective> run = objectives.get(Part.RUN);
		Objective o;
		
		// The closer fountains are more important
		Position[] fountains = {
			Map.FOUNTAIN_N,
			Map.FOUNTAIN_E,
			Map.FOUNTAIN_S,
			Map.FOUNTAIN_W
		};
		int maxFountainDistance = Map.distance(new Position(0, 0), Map.FOUNTAIN_E),
			minFountainDistance = Map.distance(new Position(0, 0), Map.FOUNTAIN_N);
		float closeFountainPriority = 0.5f,
			  farFountainPriority = 0.1f;
		for (Position f : fountains) {
			int d = Map.distance(Map.base, f);
			float priority = ((d - minFountainDistance) / (float)(maxFountainDistance - minFountainDistance));
			priority = (1 - priority) * (closeFountainPriority - farFountainPriority);
			priority += farFountainPriority;
			
			o = new DefendObjective(f);
			o.setPriority(priority);
			run.add(o);
		}
		// Take the artifact
		o = new DefendObjective(Map.ARTIFACT);
		o.setPriority(1f);
		run.add(o);
		
		// ----- Idle part
		objectives.get(Part.IDLE).add(new NoopObjective());
		
		// ----- Final part
		// Empty
	}

	@Override
	protected boolean goToEnd(int roundCount) {
		// This strategy doesn't have an end part
		return false;
	}
}
