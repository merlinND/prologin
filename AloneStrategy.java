import java.util.List;

/**
 * Basic strategy: completely ignore that there might be other players.
 * @author Merlin Nimier-David
 */
public class AloneStrategy extends Strategy {

	public AloneStrategy() {
		Objective o = new DefendObjective(Map.ARTIFACT);
		o.setPriority(1f);
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
		
		// We build defense towers further around the base (otherwise some of the range is wasted)
		List<Position> around = Map.getNeighborsEdge(Map.base, Interface.PORTEE_TOURELLE);
		// No need to build each towers, only a few are enough
		for(int i = 0; i < around.size(); i += around.size()-1) {
			o = new BuildTowerObjective(around.get(i));
			o.setPriority(0.1f);
			objectives.add(o);
		}
		around = Map.getNeighborsEdge(Map.base, Interface.PORTEE_TOURELLE * 2);
		o = new BuildTowerObjective(around.get(around.size() / 2));
		o.setPriority(0.1f);
		objectives.add(o);
		
		// When everything else is taken care of, protect the base by spamming sorcerers
		o = new DefendObjective(Map.base);
		o.setPriority(0f);
		objectives.add(o);
	}
	
	@Override
	public List<Objective> getObjectives() {
		return objectives;
	}

}
