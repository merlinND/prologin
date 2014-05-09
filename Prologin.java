public class Prologin extends Interface {
	protected Mothership m;
	
	// Fonction appelée au début de la partie
	public void partie_debut() {
		// Use to filter / disable output
		Logger.setLogLevel(3);
		Logger.log("===== Start of game =====", 1);

		Map.base = Interface.base_joueur(moi());
		m = Mothership.getInstance();
	}

	// Fonction appelée pendant la phase de construction
	public void phase_construction() {
		Logger.log("===== Build phase =====", 1);
		m.play(Phase.BUILD);
	}

	// Fonction appelée pendant la phase de déplacement
	public void phase_deplacement() {
		Logger.log("===== Move phase =====", 1);
		m.play(Phase.MOVE);
	}

	// Fonction appelée pendant la phase de tirs des tourelles
	public void phase_tirs() {
		Logger.log("===== Shoot phase =====", 1);
		m.play(Phase.SHOOT);
	}

	// Fonction appelée pendant la phase de siège des tourelles
	public void phase_siege() {
		Logger.log("===== Siege phase =====", 1);
		m.play(Phase.SIEGE);
	}

	// Fonction appelée à la fin de la partie
	public void partie_fin() {
		Logger.log("===== End of game =====", 1);
	}

}
