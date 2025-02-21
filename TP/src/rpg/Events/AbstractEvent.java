package rpg.Events;

import java.util.Scanner;
import rpg.DefaultSettings;

public abstract class AbstractEvent {
    protected String text;
    protected Scanner inputScanner;

    public AbstractEvent(String text, Scanner inputScanner) {
        this.text = text;
        this.inputScanner = inputScanner;
    }

    public abstract AbstractEvent run();
    public abstract boolean isFinal();

    public String promptPlayer() {
        System.out.println(text);
        System.out.print(DefaultSettings.PROMPT_PLAYER);
        return inputScanner.nextLine();
    }

    @Override
    public String toString() {
        return text;
    }
}
