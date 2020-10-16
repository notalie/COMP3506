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
    public void testMildInconsistent() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "a", "b"),
                new Fact(Fact.FactType.TYPE_ONE, "b", "c"),
                new Fact(Fact.FactType.TYPE_ONE, "c", "a")

                );

        assertFalse(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testMildInconsistent2() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "a", "b"),
                new Fact(Fact.FactType.TYPE_ONE, "b", "c"),
                new Fact(Fact.FactType.TYPE_TWO, "c", "a")

        );

        assertFalse(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testMildInconsistent3() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "a", "b"),
                new Fact(Fact.FactType.TYPE_TWO, "b", "c"),
                new Fact(Fact.FactType.TYPE_TWO, "c", "d"),
                new Fact(Fact.FactType.TYPE_ONE, "d", "a")

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

    @Test
    public void testInconsistent2() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "Anna", "Kenton"),
                new Fact(Fact.FactType.TYPE_TWO, "Kenton", "Katya"),
                new Fact(Fact.FactType.TYPE_TWO, "Katya", "Sanni"),
                new Fact(Fact.FactType.TYPE_ONE, "Sanni", "Matt"),
                new Fact(Fact.FactType.TYPE_TWO, "Matt", "Max"),
                new Fact(Fact.FactType.TYPE_ONE, "Max", "Anna")
        );
        assertFalse(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testTransitive() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "P1", "P2"),
                new Fact(Fact.FactType.TYPE_ONE, "P2", "P3"),
                new Fact(Fact.FactType.TYPE_ONE, "P3", "P4"),
                new Fact(Fact.FactType.TYPE_ONE, "P4", "P5"),
                new Fact(Fact.FactType.TYPE_ONE, "P5", "P6"),
                new Fact(Fact.FactType.TYPE_ONE, "P6", "P7"),
                new Fact(Fact.FactType.TYPE_TWO, "P1", "P7")
        ); //TODO: Fix this I think
        assertFalse(FactChecker.areFactsConsistent(facts));
    }

    @Test
    public void testTransitive2() {
        List<Fact> facts = List.of(
                new Fact(Fact.FactType.TYPE_ONE, "P1", "P2"),
                new Fact(Fact.FactType.TYPE_ONE, "P4", "P5"),
                new Fact(Fact.FactType.TYPE_ONE, "P5", "P6"),
                new Fact(Fact.FactType.TYPE_ONE, "P2", "P3"),
                new Fact(Fact.FactType.TYPE_TWO, "P1", "P7"),
                new Fact(Fact.FactType.TYPE_ONE, "P6", "P7"),
                new Fact(Fact.FactType.TYPE_ONE, "P3", "P4")
        );

        assertFalse(FactChecker.areFactsConsistent(facts));
    }
}