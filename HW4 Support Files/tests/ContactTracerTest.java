import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class ContactTracerTest {
    
    @Test
    public void testSpecExample() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        tracer.addTrace(new Trace("Matt", "Kristian", 3240));
        tracer.addTrace(new Trace("Kristian", "Sanni", 3270));
        tracer.addTrace(new Trace("Kristian", "Kenton", 3360));
        tracer.addTrace(new Trace("Kristian", "Max", 3360));
        tracer.addTrace(new Trace("Kenton", "Kristian", 4020));

        assertEquals(Set.of("Matt", "Kristian", "Kenton", "Max"), 
                tracer.contactTrace("Anna", 130));
    }

    @Test
    public void myOwnTest() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        tracer.addTrace(new Trace("Matt", "Kristian", 3240));
        tracer.addTrace(new Trace("Kristian", "Sanni", 3370));
        tracer.addTrace(new Trace("Kristian", "Kenton", 3360));
        tracer.addTrace(new Trace("Kristian", "Max", 3360));
        tracer.addTrace(new Trace("Kenton", "Kristian", 4020));

        assertEquals(Set.of("Matt", "Kristian", "Kenton", "Max", "Sanni"),
                tracer.contactTrace("Anna", 130));
    }

    @Test
    public void testBasicContacts() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        assertEquals(Set.of("Sanni", "Matt"), tracer.getContacts("Anna"));
    }
    
    @Test
    public void testGetContacts() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        tracer.addTrace(new Trace("Matt", "Kristian", 3240));
        tracer.addTrace(new Trace("Kristian", "Sanni", 3270));
        tracer.addTrace(new Trace("Kristian", "Kenton", 3360));
        tracer.addTrace(new Trace("Kristian", "Max", 3360));
        tracer.addTrace(new Trace("Kenton", "Kristian", 4020));

        assertEquals(Set.of("Sanni", "Matt"), tracer.getContacts("Anna"));
        assertEquals(Set.of("Anna", "Kristian"), tracer.getContacts("Matt"));
        assertEquals(Set.of("Matt", "Sanni", "Kenton", "Max"), tracer.getContacts("Kristian"));
        assertEquals(Set.of("Anna", "Kristian"), tracer.getContacts("Sanni"));
        assertEquals(Set.of("Kristian"), tracer.getContacts("Max"));
        assertEquals(Set.of("Kristian"), tracer.getContacts("Kenton"));
    }
    
    @Test
    public void testGetContactTimes() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        tracer.addTrace(new Trace("Matt", "Kristian", 3240));
        tracer.addTrace(new Trace("Kristian", "Sanni", 3270));
        tracer.addTrace(new Trace("Kristian", "Kenton", 3360));
        tracer.addTrace(new Trace("Kristian", "Max", 3360));
        tracer.addTrace(new Trace("Kenton", "Kristian", 4020));

        assertEquals(List.of(3360, 4020), tracer.getContactTimes("Kristian", "Kenton"));
        assertEquals(List.of(3360, 4020), tracer.getContactTimes("Kenton", "Kristian"));
        
        assertEquals(List.of(), tracer.getContactTimes("Anna", "Kristian"));
        
        assertEquals(List.of(3360), tracer.getContactTimes("Kristian", "Max"));
        assertEquals(List.of(3360), tracer.getContactTimes("Max", "Kristian"));
    }
    
    @Test 
    public void testGetContactsAfter() {
        ContactTracer tracer = new ContactTracer();
        // we start at 100 here, but really its arbitrary.. only the relative time differences matter.
        tracer.addTrace(new Trace("Anna", "Sanni", 100));
        tracer.addTrace(new Trace("Anna", "Matt", 1740));
        tracer.addTrace(new Trace("Matt", "Kristian", 3240));
        tracer.addTrace(new Trace("Kristian", "Sanni", 3270));
        tracer.addTrace(new Trace("Kristian", "Kenton", 3360));
        tracer.addTrace(new Trace("Kristian", "Max", 3360));
        tracer.addTrace(new Trace("Kenton", "Kristian", 4020));
        
        assertEquals(Set.of("Sanni", "Matt"), tracer.getContactsAfter("Anna", 0));
        // note below: inclusive of the timestamp
        assertEquals(Set.of("Sanni", "Matt"), tracer.getContactsAfter("Anna", 100));
        assertEquals(Set.of("Matt"), tracer.getContactsAfter("Anna", 101));
        assertEquals(Set.of(), tracer.getContactsAfter("Anna", 1741));
    }
}