
public class Sorcerers extends Agent {
	/*
	 * PROPERTIES
	 */
	protected int size;
	
	/*
	 * METHODS
	 */
	public Sorcerers(Position position, int size) {
		super(position);
		
		// Create the nearest possible number of sorcerers to `size`
		Erreur status = null;
		do {
			status = Interface.creer(size);
			if (status == Erreur.MAGIE_INSUFFISANTE)
				size--;
		} while (status == Erreur.MAGIE_INSUFFISANTE || size <= 0);
		// TODO: mark yourself as dead if size == 0 (or automatically at first update)
		
		System.out.println("Created " + size + " sorcerers.");
		
		this.size = size;
	}
	
	@Override
	public void update() {	
		// TODO
	}
	
	/*
	 * GETTERS & SETTERS
	 */
}
