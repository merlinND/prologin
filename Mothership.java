import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


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
	/** @see #registerAgent(Agent, Objective) */
	protected List<Entry<Agent, Objective>> waitingList;
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
		waitingList = new ArrayList<Entry<Agent, Objective>>();
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
			while (i < l.size()) {
				Objective o = l.get(i);
				//  while getResources() > 0
				// TODO: implement resource management
				// TODO: better evaluate the quantity of effort going on
				// The last objective of the strategy is the one we invest in when everything else is fine
				if (i == l.size() - 1 || efforts.get(o).size() < 1 && !o.isCompleted()) {
					makeEffort(o);
				}
				
				i++;
			}
			// No break: intentional!
			
		case MOVE:
		case SHOOT:
		case SIEGE:
			for (Agent a : agents) {
				a.performAction(phase);
			}
			// Now, take into account the potential new agents that were added
			// as a result of the other agents' actions
			processWaitingList();
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
		if (o instanceof NoopObjective) {
			return;
		}
		else if (o instanceof BuildTowerObjective) {
			o.perform(Phase.BUILD, null);
		}
		else if (o instanceof DefendBaseObjective) {
			addAgent(new HomeAgent(Map.base), o);
		}
		else {
			int n = (int)Math.floor(getResources() / (float)Interface.COUT_SORCIER);
			if (n > 0) {
				Sorcerers s = new Sorcerers(Map.base, n);
				addAgent(s, o);
			}
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
		}
		cleanBodies();
	}
	
	/**
	 * After having updated each agent, we need to remove them if they're dead
	 */
	protected void cleanBodies() {
		Iterator<Agent> it = agents.iterator();
		while(it.hasNext() && !agents.isEmpty()) {
			Agent a = it.next();
			if (a.isDead()) {
				for (List<Agent> l : efforts.values())
					l.remove(a);
				it.remove();
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
	
	/**
	 * Register this agent to add it at the end of the current iteration over agents.
	 * Used to prevent ConcurrentModificationException
	 * @see #addAgent(Agent, Objective)
	 * @param agent
	 * @param assignedTo
	 */
	public void registerAgent(Agent agent, Objective assignedTo) {
		waitingList.add(new SimpleEntry<Agent, Objective>(agent, assignedTo));
	}
	
	/**
	 * Transfer the contents of the waiting list to the agents' & effort lists
	 */
	protected void processWaitingList() {
		for (Entry<Agent, Objective> e : waitingList) {
			addAgent(e.getKey(), e.getValue());
		}
		waitingList.clear();
	}
	
	/**
	 * 
	 * @param agent
	 * @param assignedTo Can be null
	 */
	protected void addAgent(Agent agent, Objective assignedTo) {
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