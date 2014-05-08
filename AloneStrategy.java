import java.util.List;

/**
 * Basic strategy: completely ignore that there might be other players.
 * @author mnimierdavi
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
		
		List<Position> around = Map.getNeighbors(Map.base);
		for (Position p : around) {
			o = new BuildTowerObjective(p);
			o.setPriority(0.1f);
			objectives.add(o);
		}
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
