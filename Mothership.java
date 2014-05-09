import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
			List<Objective> l = strategy.getObjectives();
			int i = 0;
			while (i < l.size() && getResources() > 0) {
				Objective o = l.get(i);
				// TODO: better evaluate the quantity of effort going on
				if (i == l.size() - 1 || efforts.get(o).size() < 1) {
					makeEffort(o);
				}
				
				i++;
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
	 * Make an effort towards the given objective
	 * TODO: support more objective types
	 * @param o
	 */
	protected void makeEffort(Objective o) {
		if (o instanceof BuildTowerObjective) {
			o.perform(Phase.BUILD, null);
		}
		else {
			int n = (int)Math.floor(getResources() / (float)Interface.COUT_SORCIER);
			Sorcerers s = new Sorcerers(Map.base, n);
			addAgent(s, o);
		}
	}
	
	/**
	 * Sync the agents with the game state
	 * TODO: update each agent
	 * TODO: remove the dead agents
	 */
	public void update() {
		Iterator<Agent> it = agents.iterator();
		while(it.hasNext()) {
			Agent a = it.next();
			a.update();
			if (a.isDead()) {
				it.remove();
			}
		}
	}
	
	public void cleanBodies() {
		Iterator<Agent> it = agents.iterator();
		while(it.hasNext()) {
			Agent a = it.next();
			if (a.isDead()) {
				it.remove();
				for (List<Agent> l : efforts.values())
					l.remove(a);
			}
		}
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
		if (assignedTo != null) {
			agent.addCompulsoryObjective(assignedTo);
			efforts.get(assignedTo).add(agent);
		}
	}
	/**
	 * Remove the agent corresponding to this iterator from all lists.
	 * @param it
	 */
	public void removeAgent(Agent agent) {
		agents.remove(agent);
		for (List<Agent> l : efforts.values())
			l.remove(agent);
	}
	
	/**
	 * @return The current amount of "magic" available
	 */
	public int getResources() {
		return Interface.magie(Interface.moi());
	}
}