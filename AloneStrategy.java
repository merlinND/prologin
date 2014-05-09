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
		o = new DefendObjective(Map.FOUNTAIN_N);
		o.setPriority(0.5f);
		objectives.add(o);
		o = new DefendObjective(Map.FOUNTAIN_E);
		o.setPriority(0.5f);
		objectives.add(o);
		o = new DefendObjective(Map.FOUNTAIN_S);
		o.setPriority(0.5f);
		objectives.add(o);
		o = new DefendObjective(Map.FOUNTAIN_W);
		o.setPriority(0.5f);
		objectives.add(o);
		
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
