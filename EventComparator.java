import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getStartTime() - e2.getStartTime() < 0) {
            return -1;
        } else if (e1.getStartTime() - e2.getStartTime() > 0) {
            return 1;
        } else {
            return e1.customer().getName() - e2.customer().getName();
        }
    }

}
