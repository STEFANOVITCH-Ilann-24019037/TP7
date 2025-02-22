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
        AbstractEvent death = new EventNoChoice("Le troll éclate de rire et t'écrase d'un coup de massue... Fin tragique.", scanner, null);
        AbstractEvent victory = new EventNoChoice("Le troll hoche la tête et se pousse lentement, révélant un passage secret. Tu continues ton aventure !", scanner, null);

        // 📌 Fouille de la grotte (avec chance de trouver le mot de passe)
        AbstractEvent foundNothing = new EventNoChoice("Tu fouilles sous les pierres moussues... mais tu ne trouves rien d'utile.", scanner, null);
        AbstractEvent foundPassword = new EventNoChoice("Sous une pierre, tu découvres un vieux parchemin : 'Mot de passe : DRAGON'.", scanner, null);

        AbstractEvent searchCave = new EventRandomChoice(
                "Tu fouilles la grotte...",
                scanner,
                foundNothing,  // 50% de chances de ne rien trouver
                foundPassword  // 50% de chances de trouver le mot de passe
        );

        // 📌 Vérification du mot de passe par le Troll
        AbstractEvent trollPasswordCheck = new EventBooleanChoice(
                "Le troll te fixe et ricane : 'Ah, un voyageur égaré... Si tu veux passer cette porte, donne-moi le mot de passe !'",
                scanner,
                victory,  // Mot de passe correct
                death,    // Mauvaise réponse = mort
                "DRAGON"
        );

        // 📌 Rencontre avec le Troll
        AbstractEvent talkToTroll = new EventNoChoice("Tu t'approches du troll, qui te fixe de ses petits yeux jaunes...", scanner, trollPasswordCheck);

        // 📌 Premier choix du joueur
        AbstractEvent firstChoice = new EventMultipleChoice(
                "Le joueur se réveille dans une grotte sombre et humide. L'air est froid, et l'odeur de mousse et de pierre emplit ses narines. Devant lui, une immense porte de pierre est gardée par un troll imposant, assis sur un rocher. Ses petits yeux jaunes brillent dans l'obscurité, et un large sourire dévoile ses dents jaunies.\nQue fais-tu ?",
                scanner,
                searchCave,   // Choix 1 : Fouiller la grotte
                talkToTroll   // Choix 2 : Parler au troll
        );

        // 📌 Lancement du scénario
        Scenario scenario = new Scenario(firstChoice);
        scenario.run();

        scanner.close();
    }



}
