public class Prologin extends Interface {
	// Fonction appelée au début de la partie
	public void partie_debut() {
		System.out.println("===== Start of game =====");
	}

	// Fonction appelée pendant la phase de construction
	public void phase_construction() {
		System.out.println("===== Build phase =====");
	}

	// Fonction appelée pendant la phase de déplacement
	public void phase_deplacement() {
		System.out.println("===== Move phase =====");
	}

	// Fonction appelée pendant la phase de tirs des tourelles
	public void phase_tirs() {
		System.out.println("===== Shoot phase =====");
	}

	// Fonction appelée pendant la phase de siège des tourelles
	public void phase_siege() {
		System.out.println("===== Siege phase =====");
	}

	// Fonction appelée à la fin de la partie
	public void partie_fin() {
		System.out.println("===== End of game =====");
	}

}
