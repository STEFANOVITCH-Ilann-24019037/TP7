package rpg.Events;

import java.util.Scanner;

public class EventBooleanChoice extends AbstractEvent {
    private AbstractEvent eventTrue;
    private AbstractEvent eventFalse;
    private String truth;

    public EventBooleanChoice(String text, Scanner inputScanner, AbstractEvent eventTrue, AbstractEvent eventFalse, String truth) {
        super(text, inputScanner);
        this.eventTrue = eventTrue;
        this.eventFalse = eventFalse;
        this.truth = truth.toLowerCase();
    }

    @Override
    public AbstractEvent run() {
        System.out.println(text);
        String answer = promptPlayer().toLowerCase();

        return answer.equals(truth) ? eventTrue : eventFalse;
    }

    @Override
    public boolean isFinal() {
        return eventTrue == null || eventFalse == null;
    }
}
