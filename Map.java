import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	
	public static int numberOfOpponentSorcerers(Position p) {
		int n = 0;
		for (int j : Interface.adversaires()) {
			if (j != Interface.moi())
				n += Interface.nb_sorciers(p, j);
		}
		return n;
	}
	
	/**
	 * @return The number of towers that we can still build
	 */
	public static int possibleTowers() {
		return (Interface.MAX_TOURELLES - Interface.tourelles_joueur(Interface.moi()).length);
	}
	public static boolean hasTower(Position p) {
		return (Interface.info_case(p) == CaseInfo.CASE_TOURELLE);
	}
	
	/**
	 * Find **our** closest tower near `from`.
	 * The base is considered as a tower, since it provides the base point
	 * for new tower construction.
	 * @param from
	 * @return
	 */
	public static Position getClosestTowerPosition(Position from) {
		Position result = Map.base;
		int minDistance = distance(from, result);
		for (Tourelle t : Interface.tourelles_joueur(Interface.moi())) {
			int d = distance(from, t.pos);
			if (d < minDistance) {
				minDistance = d;
				result = t.pos;
			}
		}
		return result;
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
	 * Check if a **walkable** square contains a danger.
	 * TODO: take into account nearby towers!
	 * @param p
	 * @return
	 */
	public static boolean hasDanger(Position p) {
		return (Interface.joueur_case(p) != Interface.moi());
	}
	
	/**
	 * Use BFS to find a walkable path between `from` and `to`
	 * TODO: another version that takes danger into account
	 * @see Interface.chemin
	 * @param from
	 * @param to
	 * @return a list of the steps to take
	 */
	public static Position[] path(Position from, Position to) {
		Stack<Position> path = new Stack<Position>();
		
		if (!from.equals(to)) {
			Queue<Position> q = new LinkedList<Position>();
			Set<Position> seen = new HashSet<Position>();
			HashMap<Position, Position> parents = new HashMap<Position, Position>();
			
			// Start from the end
			seen.add(to);
			q.add(to);
			Position current = to;
			while (!q.isEmpty() && !current.equals(from)) {
				current = q.poll();
				List<Position> adj = getNeighbors(current);
				// TODO: prioritize alternating movements (=> will create diagonals)
				for (Position p : adj) {
					if (!seen.contains(p)) {
						seen.add(p);
						parents.put(p, current);
						q.add(p);
					}
				}
			}
			
			// If we've reached the target, rewind to gather the steps
			while (!current.equals(to)) {
				path.add(current);
				current = parents.get(current);
			}
			path.add(current);
		}

		// Convert to array for backwards compatibility with `Interface.chemin`
		Position[] result = new Position[path.size()];
		for (int i = 0; i < path.size(); ++i)
			result[i] = path.get(i);
		return result;
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
	public static List<Position> getNeighborsEdge(Position p, int distance) {
		return getNeighbors(p, distance, true);
	}
	
	/**
	 * Get a list of the all the **walkable** squares at a distance 1 to `distance` from `p`
	 * @see #getNeighbors(Position, int, boolean)
	 * @param p
	 * @param distance
	 * @return
	 */
	public static List<Position> getNeighbors(Position p, int distance) {
		return getNeighbors(p, distance, false);
	}
	
	/**
	 * Get a list of the all the **walkable** squares at a distance 1 to `distance` from `p`
	 * @param p
	 * @param distance
	 * @param edgeOnly Discard every square that is not exactly `distance` away
	 * @return
	 */
	public static List<Position> getNeighbors(Position p, int distance, boolean edgeOnly) {
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
					visited.put(neighbor, true);
					if (d < distance)
						queue.add(neighbor);
					if (!edgeOnly || d == distance)
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
