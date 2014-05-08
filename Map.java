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
	
	public List<Position> getOpponentBases() {
		// TODO
		throw new NotImplementedException();
	}
}
