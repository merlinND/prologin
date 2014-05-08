import java.util.ArrayList;
import java.util.List;


/**
 * Main AI
 * @author Merlin Nimier-David
 */
public class Mothership {
	/*
	 * PROPERTIES
	 */
	private static Mothership instance;
	protected List<Agent> agents;
	
	/*
	 * METHODS
	 */
	private Mothership() {
		agents = new ArrayList<Agent>();
		agents.add(new Tower(Map.ARTIFACT));
	}
	
	public void play(Phase phase) {
		for (Agent a : agents)
			a.performAction(phase);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public static Mothership getInstance() {
		if (instance == null)
			instance = new Mothership();
		
		return instance;
	}
}