package rpg.Events;

import java.util.*;

public class EventMultipleChoice extends AbstractEvent {
    protected List<AbstractEvent> nextEvents;

    public EventMultipleChoice(String text, Scanner inputScanner, AbstractEvent... nextEvents) {
        super(text, inputScanner);
        this.nextEvents = new ArrayList<>(Arrays.asList(nextEvents));
    }

    @Override
    public AbstractEvent run() {
        System.out.println(text);
        for (int i = 0; i < nextEvents.size(); i++) {
            System.out.println((i + 1) + ") " + nextEvents.get(i));
        }

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(promptPlayer()) - 1;
                if (choice >= 0 && choice < nextEvents.size()) break;
            } catch (NumberFormatException e) {
                System.out.println(rpg.DefaultSettings.WARNING_MSG_INTEGER_EXPECTED);
            }
        }

        return nextEvents.get(choice);
    }

    @Override
    public boolean isFinal() {
        return nextEvents.isEmpty();
    }
}
