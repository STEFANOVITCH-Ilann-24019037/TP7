package rpg;

import rpg.Characters.Troll;
import rpg.Events.*;
import java.util.Scanner;

public class Scenario {
    private AbstractEvent start;

    public Scenario(AbstractEvent start) {
        this.start = start;
    }

    public void run() {
        if (start == null) {
            System.out.println(DefaultSettings.MSG_EMPTY_SCENARIO);
            return;
        }

        AbstractEvent current = start;
        while (current != null) {
            current = current.run();
        }

        System.out.println(DefaultSettings.MSG_FINALE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 📌 Événements finaux
        AbstractEvent death = new EventNoChoice("Tu as pris le mauvais chemin et es tombé dans un piège mortel...", scanner, null);
        AbstractEvent victory = new EventNoChoice("Bravo ! Tu as trouvé le trésor caché de la grotte !", scanner, null);
        AbstractEvent escape = new EventNoChoice("Tu as choisi de quitter la grotte sans prendre de risques.", scanner, null);

        // 📌 Fouille pour trouver le mot de passe
        AbstractEvent findPassword = new EventNoChoice("Tu fouilles la pièce... et trouves un vieux parchemin avec le mot : 'DRAGON'.", scanner, null);

        // 📌 Vérification du mot de passe pour entrer dans la grotte
        AbstractEvent passwordCheck = new EventBooleanChoice(
                "Une porte verrouillée bloque le passage. Quel est le mot de passe ?", scanner, victory, death, "dragon"
        );

        // 📌 Le troll peut aider ou désinformer
        Troll troll = new Troll();
        AbstractEvent trollInteraction = new EventInteractionCharacter(
                "Le Troll te regarde...", scanner, troll, passwordCheck, death
        );

        // 📌 Options disponibles : Fouiller, parler au Troll ou partir
        AbstractEvent roomOptions = new EventMultipleChoice(
                "Tu es dans une pièce sombre. Que fais-tu ?",
                scanner,
                findPassword,  // Option 1 : Fouiller la pièce pour trouver le mot de passe
                trollInteraction, // Option 2 : Parler au Troll pour un indice
                escape         // Option 3 : Quitter la grotte sans tenter d'entrer
        );

        // 📌 Point de départ du jeu
        AbstractEvent start = new EventNoChoice("Tu es perdu dans une grotte sombre...", scanner, roomOptions);

        // 📌 Lancement du scénario
        Scenario scenario = new Scenario(start);
        scenario.run();

        scanner.close();
    }


}
