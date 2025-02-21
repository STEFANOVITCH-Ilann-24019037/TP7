package rpg.Events;
import java.util.Scanner;

public class EventRandomChoice extends EventMultipleChoice {
    public EventRandomChoice(String text, Scanner inputScanner, AbstractEvent... nextEvents) {
        super(text, inputScanner, nextEvents);
    }

    @Override
    public AbstractEvent run() {
        System.out.println(text);
        if (nextEvents.isEmpty()) return null;
        return nextEvents.get(rpg.DefaultSettings.RANDOM_GENERATOR.nextInt(nextEvents.size()));
    }
}
