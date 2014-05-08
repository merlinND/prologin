// This file has been generated, if you wish to
// modify it in a permanent way, please refer
// to the script file : gen/generator_java.rb

// Information sur les cases
enum CaseInfo {
	CASE_SIMPLE, // <- Case simple
	CASE_TOURELLE, // <- Tourelle
	CASE_BASE, // <- Base du joueur
	CASE_FONTAINE, // <- Fontaine magique
	CASE_ARTEFACT, // <- Artefact magique
	CASE_ERREUR, // <- Erreur
}

// Erreurs possibles
enum Erreur {
	OK, // <- L'action s'est effectuée avec succès
	ANNULER_IMPOSSIBLE, // <- Aucune action à annuler
	CASE_IMPOSSIBLE, // <- Cette case n'existe pas
	CASE_ADVERSE, // <- Vous ne contrôlez pas cette case
	CASE_UTILISEE, // <- Cette case n'est pas libre
	CASE_VIDE, // <- Cette case est vide
	VALEUR_INVALIDE, // <- Cette valeur est invalide
	MAGIE_INSUFFISANTE, // <- Vous n'avez pas assez de magie
	SORCIERS_INSUFFISANTS, // <- Vous n'avez pas assez de sorciers
	ATTAQUE_INSUFFISANTE, // <- Vous n'avez pas assez de points d'attaque
	PHASE_INCORRECTE, // <- Cette action ne peut pas être utilisée lors de cette
						// phase du jeu.
	PORTEE_INSUFFISANTE, // <- Vous n'avez pas assez de portée pour effectuer
							// cette action
	PERDANT, // <- Vous avez perdu et ne pouvez pas effectuer d'actions
}

// Représente la position sur la carte
class Position {
	public int x; // Coordonnée en X
	public int y; // Coordonnée en Y

	public Position() {
	}
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			return false;
		else {
			Position other = (Position)obj;
			return (this.x == other.x && this.y == other.y);
		}
	}
}

// Représente une tourelle
class Tourelle {
	public Position pos = new Position(); // Position de la tourelle
	public int portee; // Portée de la tourelle
	public int joueur; // Joueur qui possède la tourelle
	public int vie; // Nombre de points de vie de la tourelle
	public int attaque; // Nombre de points d'attaque de la tourelle
}

public class Interface {
	// Taille du terrain (longueur et largeur)
	public static final int TAILLE_TERRAIN = 31;

	// Nombre de joueurs dans la partie
	public static final int NB_JOUEURS = 4;

	// Nombre maximum de tours à jouer avant la fin de la partie
	public static final int MAX_TOUR = 100;

	// Magie gagnée à chaque tour
	public static final int MAGIE_TOUR = 20;

	// Magie gagnée à chaque tour pour chaque fontaine possédée
	public static final int MAGIE_FONTAINES = 15;

	// Magie gagnée à chaque sorcier tué
	public static final int MAGIE_COMBAT = 1;

	// Magie récupérée à chaque tourelle supprimée
	public static final int MAGIE_SUPPRESSION = 10;

	// Nombre de points de magie par sorcier
	public static final int COUT_SORCIER = 2;

	// Nombre de points de magie par tourelle
	public static final int COUT_TOURELLE = 20;

	// Coût exponentiel pour chaque case de portée supplémentaire
	public static final int COUT_PORTEE = 4;

	// Nombre maximum de cases qu'un sorcier peut franchir à chaque tour.
	public static final int PORTEE_SORCIER = 4;

	// Portée de base d'une tourelle
	public static final int PORTEE_TOURELLE = 3;

	// Portée de construction des tourelles
	public static final int CONSTRUCTION_TOURELLE = 3;

	// Points de vie d'une tourelle à sa création
	public static final int VIE_TOURELLE = 10;

	// Points d'attaque d'une tourelle au début d'un tour
	public static final int ATTAQUE_TOURELLE = 10;

	// Points gagnés pour avoir survécu à la fin de la partie
	public static final int POINTS_SURVIVRE = 1;

	// Points gagnés pour avoir vaincu un adversaire
	public static final int POINTS_VAINQUEUR = 1;

	// Points gagnés pour contrôler une fontaine à la fin de la partie
	public static final int POINTS_CONTROLE_FONTAINE = 1;

	// Points gagnés pour contrôler un artefact à la fin de la partie
	public static final int POINTS_CONTROLE_ARTEFACT = 4;

	// Retourne le type de la case à l'emplacement `pos`
	public static native CaseInfo info_case(Position pos);

	// Retourne la liste des tourelles qui appartiennent au joueur ``joueur``
	public static native Tourelle[] tourelles_joueur(int joueur);

	// Retourne la magie que possède le joueur ``joueur``
	public static native int magie(int joueur);

	// Retourne le nombre de sorciers du joueur ``joueur`` sur la case ``pos``
	public static native int nb_sorciers(Position pos, int joueur);

	// Retourne le nombre de sorciers du joueur ``joueur`` déplacables sur la
	// case ``pos``
	public static native int nb_sorciers_deplacables(Position pos, int joueur);

	// Retourne le numéro du joueur qui contrôle la case ``pos``
	public static native int joueur_case(Position pos);

	// Retourne la tourelle située sur la case ``pos``
	public static native Tourelle tourelle_case(Position pos);

	// Retourne la position de la base du joueur ``joueur``
	public static native Position base_joueur(int joueur);

	// Retourne vrai si l'on peut construire sur la case ``pos``
	public static native boolean constructible(Position pos, int joueur);

	// Retourne la liste des positions constituant le plus court chemin allant
	// de la case ``pos1`` à la case ``pos2``. Attention : Cette fonction est
	// lente.
	public static native Position[] chemin(Position pos1, Position pos2);

	// Construire une tourelle à la position ``pos``
	public static native Erreur construire(Position pos, int portee);

	// Supprimer une tourelle à la position ``pos``
	public static native Erreur supprimer(Position pos);

	// Tirer avec ``pts`` points de dégats depuis la tourelles ``tourelle`` sur
	// la position ``cible``
	public static native Erreur tirer(int pts, Position tourelle, Position cible);

	// Créer ``nb`` sorciers dans la base
	public static native Erreur creer(int nb);

	// Déplace ``nb`` sorciers de la position ``depart`` jusqu'à la position
	// ``arrivee``.
	public static native Erreur deplacer(Position depart, Position arrivee,
			int nb);

	// Attaquer la tourelle à la position ``cible`` depuis la position ``pos``
	public static native Erreur assieger(Position pos, Position cible,
			int nb_sorciers);

	// Retourne le numéro de votre joueur
	public static native int moi();

	// Retourne la liste des numéros de vos adversaires
	public static native int[] adversaires();

	// Retourne le numéro du tour actuel
	public static native int tour_actuel();

	// Retourne la distance entre deux positions
	public static native int distance(Position depart, Position arrivee);

	// Annule la dernière action
	public static native Erreur annuler();

	// Affiche le contenu d'une valeur de type case_info
	public static native void afficher_case_info(CaseInfo v);

	// Affiche le contenu d'une valeur de type erreur
	public static native void afficher_erreur(Erreur v);

	// Affiche le contenu d'une valeur de type position
	public static native void afficher_position(Position v);

	// Affiche le contenu d'une valeur de type tourelle
	public static native void afficher_tourelle(Tourelle v);

}
