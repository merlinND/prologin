import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Position of the objectives around the map
 * @author Merlin Nimier-David
 */
public class Map {
	public static final Position 
		ARTIFACT = new Position(Interface.TAILLE_TERRAIN / 2, Interface.TAILLE_TERRAIN / 2),
		FOUNTAIN_N = new Position(Interface.TAILLE_TERRAIN / 2, 0),
		FOUNTAIN_E = new Position(Interface.TAILLE_TERRAIN - 1, Interface.TAILLE_TERRAIN / 2),
		FOUNTAIN_S = new Position(Interface.TAILLE_TERRAIN / 2, Interface.TAILLE_TERRAIN - 1),
		FOUNTAIN_W = new Position(0, Interface.TAILLE_TERRAIN / 2);
	// TODO: other useful positions in the map
	
	/**
	 * This position must be set at the beginning of the game.
	 */
	public static Position base;
	
	public static List<Position> getOpponentBases() {
		// TODO
		throw new NotImplementedException();
	}
	
	/*
	 * HELPER FUNCTIONS
	 */
	
	/**
	 * @param one
	 * @param two
	 * @return Manhattan distance between two positions
	 */
	public static int distance(Position one, Position two) {
		return Math.abs(one.x - two.x) + Math.abs(one.y - two.y);
	}
	
	/**
	 * Must be exactly one step away (not further, not closer)
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean isAdjacentTo(Position one, Position two) {
		return (distance(one, two) == 1);
	}
	
	public static boolean isWalkable(Position p) {
		CaseInfo info = Interface.info_case(p);
		switch (info) {
		case CASE_ARTEFACT:
		case CASE_BASE:
		case CASE_FONTAINE:
		case CASE_SIMPLE:
			return true;
		
		case CASE_ERREUR:
		case CASE_TOURELLE:
		default:
			return false;
		}
	}
	
	/**
	 * Determine if we can build a tower at the given spot.
	 * TODO: check that other constraints are checked (proximity to previous towers, free terrain, etc)
	 * @param current
	 * @return
	 */
	public static boolean canBuild(Position target) {
		return Interface.constructible(target, Interface.moi());
	}
	
	/**
	 * Get a list of the **walkable** squares adjacent to `p`
	 * @see #getNeighbors(Position, boolean)
	 * @param p
	 * @return
	 */
	public static List<Position> getNeighbors(Position p) {
		return getNeighbors(p, true);
	}
	
	/**
	 * Get a list of the squares adjacent to `p`
	 * @param p
	 * @param checkWalkable If we should make sure that the returned positions are all walkable
	 * @return 
	 */
	public static List<Position> getNeighbors(Position p, boolean checkWalkable) {
		List<Position> result = new ArrayList<Position>();
		result.add(p.left());
		result.add(p.right());
		result.add(p.up());
		result.add(p.down());
		
		Iterator<Position> it = result.iterator();
		while (it.hasNext()) {
			Position candidate = it.next();
			if (!candidate.isValid() || (checkWalkable && !isWalkable(candidate)))
				it.remove();
		}
		return result;
	}
	
	/**
	 * Get a list of the **walkable** squares at a distance `distance` from `p`
	 * @param p
	 * @param distance
	 * @return 
	 */
	public static List<Position> getNeighbors(Position p, int distance) {
		List<Position> result = new ArrayList<Position>();
		HashMap<Position, Boolean> visited = new HashMap<Position, Boolean>();
		
		Queue<Position> queue = new LinkedList<Position>();
		queue.add(p);
		visited.put(p, true);
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			List<Position> adj = getNeighbors(current, false);

			for (Position neighbor : adj) {
				int d = distance(p, neighbor);
				if (d <= distance && !visited.containsKey(neighbor)) {
					System.out.println("Considering the new position " + neighbor + " distance " + d);
					visited.put(neighbor, true);
					if (d < distance)
						queue.add(neighbor);
					else if (d == distance)
						result.add(neighbor);
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * Determine the closest position adjacent to `target` when coming from `from`
	 * @warning If none of the neighborhood is walkable, we return the target
	 * @param from
	 * @param target
	 * @return
	 */
	public static Position getAdjacentPosition(Position from, Position target) {
		List<Position> neighborhood = getNeighbors(target);
		Position closest = target;
		int min = distance(from, target) + 1;
		for (Position p : neighborhood) {
			int d = distance(p, target);
			if (d < min) {
				closest = p;
				min = d;
			}
		}
		return closest;
	}
}
