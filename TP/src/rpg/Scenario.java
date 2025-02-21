package rpg;

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

        // Événement final
        AbstractEvent end = new EventNoChoice("Félicitations, vous avez terminé l'aventure !", scanner, null);

        // Scénario : Combat ou fuite
        AbstractEvent fight = new EventNoChoice("Vous entrez en combat et gagnez !", scanner, end);
        AbstractEvent flee = new EventNoChoice("Vous prenez la fuite et survivez !", scanner, end);

        // Question au joueur
        AbstractEvent booleanChoice = new EventBooleanChoice(
                "Un monstre apparaît ! Voulez-vous combattre ? (oui/non)", scanner, fight, flee, "oui"
        );

        // Bifurcation
        AbstractEvent choiceEvent = new EventMultipleChoice(
                "Vous arrivez à une bifurcation, choisissez votre chemin :",
                scanner,
                booleanChoice,
                new EventNoChoice("Vous tombez dans un piège et mourrez...", scanner, null)
        );

        // Correction ici : On met plus d'événements pour que le hasard soit plus varié
        AbstractEvent randomEvent = new EventRandomChoice(
                "Un événement aléatoire se produit !", scanner, choiceEvent, booleanChoice
        );

        Scenario scenario = new Scenario(randomEvent);
        scenario.run();

        scanner.close();
    }

}
