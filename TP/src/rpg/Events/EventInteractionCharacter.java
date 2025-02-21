package rpg.Events;

import rpg.Characters.CharacterRPG;
import rpg.DefaultSettings;
import java.util.Scanner;

public class EventInteractionCharacter extends EventMultipleChoice {
    private CharacterRPG guest;  // Le personnage avec lequel le joueur interagit

    public EventInteractionCharacter(String text, Scanner inputScanner, CharacterRPG guest, AbstractEvent... nextEvents) {
        super(text, inputScanner, nextEvents);
        this.guest = guest;
    }
    public boolean isInRange(int index) {
        return index >= 0 && index < nextEvents.size();
    }


    @Override
    public AbstractEvent run() {
        // Activation du personnage invité (ex: "Demande-moi ton chemin, et je te montrerai la voie !")
        System.out.println(guest.activate());

        // Demande au joueur de poser sa question
        String playerAnswer = promptPlayer();
        if (playerAnswer == null) {
            throw new NullPointerException(DefaultSettings.ERROR_MSG_NULL_PLAYER_ANSWER);
        }


        // Vérification de la réponse par le personnage
        int choiceIndex = interpretAnswer(playerAnswer);
        if (isInRange(choiceIndex)) {
            return nextEvents.get(choiceIndex);
        } else {
            System.out.println("T'es pas bon");
            return this; // Redemande une nouvelle réponse
        }
    }

    private int interpretAnswer(String playerAnswer) {
        int response = guest.checkAnswer(playerAnswer);
        return Math.abs(response) % nextEvents.size(); // Assure un index valide
    }
}
