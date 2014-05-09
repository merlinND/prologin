import java.util.List;

/**
 * Basic strategy: completely ignore that there might be other players.
 * @author Merlin Nimier-David
 */
public class AloneStrategy extends Strategy {

	public AloneStrategy() {
		Objective o;
		// TODO: move to an end game strategy moment
		/*Objective o = new DefendObjective(Map.ARTIFACT);
		o.setPriority(1f);
		objectives.add(o);*/

		// Defend the base
		o = new DefendBaseObjective();
		o.setPriority(0.8f);
		objectives.add(o);
		
		// TODO: make the closer fountains more prioritary
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
			
			o = new DefendObjective(Map.FOUNTAIN_N);
			o.setPriority(priority);
			objectives.add(o);
		}
		
		// When everything else is taken care of, just stash money
		o = new NoopObjective();
		o.setPriority(0f);
		objectives.add(o);
		
		// TODO: game "moment" based strategies
		
		// TODO: normalize all objectives' priority (sum = 1) to help resource management
	}
	
	@Override
	public List<Objective> getObjectives() {
		return objectives;
	}

}
