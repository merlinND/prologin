
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
		} while (status == Erreur.MAGIE_INSUFFISANTE && size > 0);
		this.size = size;
		System.out.println("Created " + size + " sorcerers.");

		// Mark yourself as dead if size == 0 (or automatically at first update)
		if (size <= 0)
			markDead();
	}
	
	@Override
	public void update() {	
		int n = Interface.nb_sorciers(getPosition(), Interface.moi());
		if (n <= 0) {
			System.out.println("The sorcerers who were on " + getPosition() + " were all killed.");
			markDead();
		}
		else {
			this.size = n;
		}
	}
	
	public void moveClosestTo(Position target) {
		if (getPosition().equals(target))
			return;
		
		Position destination = getPosition();
		// TODO: re-implement `chemin` to be faster
		Position[] steps = Interface.chemin(getPosition(), target);
		// The farthest step attainable is at most PORTEE_SORCIER away
		int distance = 0;
		while (distance < Interface.PORTEE_SORCIER && distance < steps.length)
			destination = steps[distance++];
		
		System.out.println("Moving from " + getPosition() + " to " + destination + "(" + (distance-1) + " away)");
		Erreur status = Interface.deplacer(getPosition(), destination, getSize());
		// TODO: handle errors, we could lose agents !
		if (status == Erreur.OK)
			setPosition(destination);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public int getSize() {
		return size;
	}
}
