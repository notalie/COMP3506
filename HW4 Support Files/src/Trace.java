import java.util.Collections;
import java.util.Set;

/**
 * Represents a contact trace tuple in question 3.
 * 
 * DO NOT MODIFY OR SUBMIT THIS FILE.
 */
public class Trace {
    private final String person1;
    private final String person2;
    private final int time;

    /**
     * Creates a new contact trace.
     *
     * Note that timestamps/dates are represented as ints, in the resolution of minutes.
     * Their actual absolute value is not important for developing your algorithm.
     * Only differences between two timestamps should be
     * used (which hence gives the number of minutes between them).
     *
     * @param person1 involved in the interaction
     * @param person2 involved in the interaction
     * @param time of interaction (in minutes).
     */
    public Trace(String person1, String person2, int time) {
        this.person1 = person1;
        this.person2 = person2;
        this.time = time;
    }

    /**
     * Get the first person involved in this contact trace. Note that traces
     * are bidirectional so the order of first/second has no meaning.
     *
     * @return first person
     */
    public String getPerson1() {
        return person1;
    }

    /**
     * Get the second person involved in this contact trace. Note that traces
     * are bidirectional so the order of first/second has no meaning.
     *
     * @return second person
     */
    public String getPerson2() {
        return person2;
    }

    /**
     * The timestamp of this interaction
     * 
     * @return the timestamp (in the unit of minutes)
     */
    public int getTime() {
        return time;
    }
}
