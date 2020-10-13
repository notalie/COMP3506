import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

public class FactCheckerTest {

    /**
     * Person a left before person b arrived.
     * TYPE_ONE
     */
    /**
     * Person a and b were at the party at the same time.
     * TYPE_TWO
     */


    @Test
    public void testSimpleInconsistent() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "a", "b"),
                new Fact(Fact.FactType.TYPE_TWO, "a", "b")
        );
        
        assertFalse(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testSimpleConsistent() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "a", "b"),
                new Fact(Fact.FactType.TYPE_ONE, "b", "c")
        );

        assertTrue(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testType2Consistent() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_TWO, "a", "b"),
                new Fact(Fact.FactType.TYPE_TWO, "b", "c")
        );

        assertTrue(FactChecker.areFactsConsistent(facts));
    }
    
    @Test
    public void testConsistent1() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "Anna", "Kenton"),
                new Fact(Fact.FactType.TYPE_TWO, "Kenton", "Katya"),
                new Fact(Fact.FactType.TYPE_TWO, "Katya", "Sanni"),
                new Fact(Fact.FactType.TYPE_ONE, "Sanni", "Matt"),
                new Fact(Fact.FactType.TYPE_TWO, "Matt", "Max")
        );
        
        assertTrue(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testInconsistent1() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "Anna", "Kenton"),
                new Fact(Fact.FactType.TYPE_TWO, "Kenton", "Katya"),
                new Fact(Fact.FactType.TYPE_TWO, "Katya", "Sanni"),
                new Fact(Fact.FactType.TYPE_ONE, "Sanni", "Matt"),
                new Fact(Fact.FactType.TYPE_TWO, "Matt", "Max"),
                new Fact(Fact.FactType.TYPE_ONE, "Max", "Sanni")
        );

        assertFalse(FactChecker.areFactsConsistent(facts));
    }
}