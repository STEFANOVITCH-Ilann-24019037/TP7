package rpg.Characters;

import java.util.Random;

public class Troll implements CharacterRPG {
    private Random dice = new Random();

    @Override
    public String activate() {
        return "Mot de passe de la grotte!";
    }

    @Override
    public int checkAnswer(String answer) {
        return Math.abs(dice.nextInt(10));
    }
}
