import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * Main AI
 * @author Merlin Nimier-David
 */
public class Mothership {
	/*
	 * PROPERTIES
	 */
	private static Mothership instance;
	/** Current strategy being pursued (represents an ordered list of Objective) */
	protected Strategy strategy;
	/** A list of all the agents */
	protected List<Agent> agents;
	/**
	 * A list of every agent dedicated to each objective.
	 * TODO: resource allocation system
	 */
	protected HashMap<Objective, List<Agent>> efforts;

	/*
	 * METHODS
	 */
	private Mothership() {
		// TODO: switch strategies during gameplay depending on adversaries' behavior
		agents = new ArrayList<Agent>();
		efforts = new HashMap<Objective, List<Agent>>();
		
		setStrategy(new AloneStrategy());
	}
	
	public void play(Phase phase) {
		switch (phase) {
		case BUILD:
			// Update (sync agents <=> game state)
			update();
			
			// Go through the objectives and assign as much effort as needed
			// TODO: make sure we're using the right priority
			for (Objective o : strategy.getObjectives()) {
				// TODO: better evaluate the quantity of effort going on
				if (efforts.get(o).size() < 1) {
					// TODO: support different objective kind
					Sorcerers s = new Sorcerers(Map.base, 1);
					addAgent(s, o);
				}
			}			
			break;
			
		case MOVE:
		case SHOOT:
		case SIEGE:
			for (Agent a : agents)
				a.performAction(phase);		
			break;
			
		default:
			System.err.println("Unrecongnized phase!");
			break;
		}
	}
	
	/**
	 * Sync the agents with the game state
	 * TODO: update each agent
	 * TODO: remove the dead agents
	 */
	public void update() {
		
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public static Mothership getInstance() {
		if (instance == null)
			instance = new Mothership();
		
		return instance;
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
		// TODO: how to reassign the existing agents?
		efforts.clear();
		for (Objective o : strategy.getObjectives())
			efforts.put(o, new ArrayList<Agent>());
	}
	
	public void addAgent(Agent agent, Objective assignedTo) {
		agents.add(agent);
		agent.addCompulsoryObjective(assignedTo);
		efforts.get(assignedTo).add(agent);
	}
	public void removeAgent(Agent agent) {
		// TODO: remove agent from **all** lists (efforts)
		throw new NotImplementedException();
	}
}