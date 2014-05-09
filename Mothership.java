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
		
		setStrategy(new AggressiveStrategy());
	}
	
	public void play(Phase phase) {
		switch (phase) {
		case BUILD:
			// Update (update strategy, sync agents <=> game state)
			update();
			
			// Go through the objectives and assign as much effort as needed
			// TODO: make sure we're using the right priority
			List<Objective> l = strategy.getObjectives();
			int i = 0;
			while (i < l.size()) {
				Objective o = l.get(i);
				// TODO: implement resource management
				// TODO: better evaluate the quantity of effort going on
				if (!o.isSatisfied() && !o.isCompleted()) {
					makeEffort(o);
				}
				
				i++;
			}
			// The "idle" objective of the strategy is the one we invest in when everything else is fine
			for (Objective o : strategy.getIdleObjectives()) {
				makeEffort(o);
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
			Logger.err("Unrecongnized phase!");
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
			if (o.canSpendMore())
				o.perform(Phase.BUILD, null);
		}
		else if (o instanceof DefendBaseObjective) {
			addAgent(new HomeAgent(Map.base), o);
		}
//		else if (o instanceof KillThemAllObjective) {
//			addAgent(new HomeAgent(Map.base), o);
//		}
		else if (o instanceof OccupyObjective){
			int available = getResources();
			if (o.getSpendingLimit() >= 0)
				available = Math.min(available, o.getSpendingLimit());
			
			int n = (int)Math.floor(available / (float)Interface.COUT_SORCIER);
			if (n > 0 && o.canSpendMore()) {
				Sorcerers s = new Sorcerers(n);
				o.addSpending(n * Interface.COUT_SORCIER);
				addAgent(s, o);
			}
		}
		else {
			Logger.err("What effort should we do for " + o + " ?");
		}
	}
	
	/**
	 * Update the strategy.
	 * Sync the agents with the game state.
	 */
	public void update() {
		strategy.update(Interface.tour_actuel());
		
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
		resetObjectives();
	}
	
	public void resetObjectives() {
		efforts.clear();
		for (Entry<Agent, Objective> e : waitingList)
			e.setValue(null);
		
		for (Objective o : strategy.getObjectives())
			efforts.put(o, new ArrayList<Agent>());
		// We reassign all the agents to the most prioritary objective
		// TODO: reassign the agents more intelligently?
		if (strategy.getObjectives().size() > 0) {
			Objective first = strategy.getObjectives().get(0);
			for(Agent a : agents) {
				a.clearObjectives();
				assignAgentTo(a, first);
			}
		}
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
		if (assignedTo != null && efforts.containsKey(assignedTo))
			assignAgentTo(agent, assignedTo);
	}
	
	protected void assignAgentTo(Agent agent, Objective assignedTo) {
		agent.addCompulsoryObjective(assignedTo);
		efforts.get(assignedTo).add(agent);
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

	/**
	 * Dynamically add an objective to the current part of the strategy
	 */
	public void addObjective(Objective objective) {
		strategy.addObjective(objective);
		efforts.put(objective, new ArrayList<Agent>());
	}
}