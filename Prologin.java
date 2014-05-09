/**
 * About PiPercent
 * ===============
 * 
 * PiPercent is intended to be a multi-agent artifical intelligence:
 * intelligence is distributed into many agents, each having its own objectives.
 * Objectives can be easily added depending on the rules of the game.
 * For these rules, we had objectives corresponding to occupying a position and to defend the base.
 * 
 * The codebase was thought out to be as generic as possible,
 * and even though many features are still left TODO, it proved very easy
 * to add new strategies.
 * The strategy that was finally used was created ~30 minutes before the deadline,
 * based on the many tools coded beforehand.
 * 
 * (PS: I wanted to sort out the files in tidy packages, but it was
 *  hard to do so while using the provided Makefile)
 * 
 * @author Merlin Nimier-David
 */


public class Prologin extends Interface {
	protected Mothership m;
	
	// Fonction appelée au début de la partie
	public void partie_debut() {
		// Use to filter / disable output
		Logger.disableOutput();
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
