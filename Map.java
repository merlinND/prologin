import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Position of the objectives around the map
 * @author mnimierdavi
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
	 * @param p
	 * @return 
	 */
	public static List<Position> getNeighbors(Position p) {
		List<Position> result = new ArrayList<Position>();
		result.add(p.left());
		result.add(p.right());
		result.add(p.up());
		result.add(p.down());
		
		Iterator<Position> it = result.iterator();
		while (it.hasNext()) {
			Position candidate = it.next();
			if (!candidate.isValid() || !isWalkable(candidate))
				it.remove();
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
