package rpg.Characters;

import java.util.Random;

public class Troll implements CharacterRPG {
    private Random dice = new Random();

    @Override
    public String activate() {
        return "Demande-moi ton chemin, et je te montrerai la voie !";
    }

    @Override
    public int checkAnswer(String answer) {
        return Math.abs(dice.nextInt(10));
    }
}
