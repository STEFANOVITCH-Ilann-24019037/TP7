package rpg.Events;

import java.util.Scanner;

public class EventNoChoice extends AbstractEvent {
    private AbstractEvent next;

    public EventNoChoice(String text, Scanner inputScanner, AbstractEvent next) {
        super(text, inputScanner);
        this.next = next;
    }

    @Override
    public AbstractEvent run() {
        System.out.println(text);
        return next;
    }

    @Override
    public boolean isFinal() {
        return next == null;
    }
}
