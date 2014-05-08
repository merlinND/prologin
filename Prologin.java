public class Prologin extends Interface {
	protected Mothership m;
	
	// Fonction appelée au début de la partie
	public void partie_debut() {
		System.out.println("===== Start of game =====");

		Map.base = Interface.base_joueur(moi());
		m = Mothership.getInstance();
	}

	// Fonction appelée pendant la phase de construction
	public void phase_construction() {
		System.out.println("===== Build phase =====");
		m.play(Phase.BUILD);
	}

	// Fonction appelée pendant la phase de déplacement
	public void phase_deplacement() {
		System.out.println("===== Move phase =====");
		m.play(Phase.MOVE);
	}

	// Fonction appelée pendant la phase de tirs des tourelles
	public void phase_tirs() {
		System.out.println("===== Shoot phase =====");
		m.play(Phase.SHOOT);
	}

	// Fonction appelée pendant la phase de siège des tourelles
	public void phase_siege() {
		System.out.println("===== Siege phase =====");
		m.play(Phase.SIEGE);
	}

	// Fonction appelée à la fin de la partie
	public void partie_fin() {
		System.out.println("===== End of game =====");
	}

}
