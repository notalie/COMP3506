import java.util.*;

public class ContactTracer {


    private class Person { // going to have to make two nodes for each type that is implemented

        String name;

        HashMap<String, ArrayList<Integer>> contacts; // contactNames, list of times contacted

        private Person(String contactName) {
            this.name = contactName;
            this.contacts = new HashMap<>();
        }

        private void addPerson(String contactedPerson, int time) {
            ArrayList<Integer> times;
            if (this.contacts.containsKey(contactedPerson)) {
                times = this.contacts.get(contactedPerson);
            } else {
                times = new ArrayList<>();
            }
            times.add(time);
            this.contacts.put(contactedPerson, times);
        }
    }

    HashMap<String, Person> contactsList;

    /**
     * Initialises an empty ContactTracer with no populated contact traces.
     */
    public ContactTracer() {
        this.contactsList = new HashMap<>();
    }

    /**
     * Initialises the ContactTracer and populates the internal data structures
     * with the given list of contract traces.
     * 
     * @param traces to populate with
     * @require traces != null
     */
    public ContactTracer(List<Trace> traces) {
        this.contactsList = new HashMap<>(); // initialise hashmap first
        for (Trace trace: traces) {
            contactsList.put(trace.getPerson1(), new Person(trace.getPerson1()));
            contactsList.put(trace.getPerson1(), new Person(trace.getPerson2()));
        }
    }

    /**
     * Adds a new contact trace to 
     * 
     * If a contact trace involving the same two people at the exact same time is
     * already stored, do nothing.
     * 
     * @param trace to add
     * @require trace != null
     */
    public void addTrace(Trace trace) {
        // get node for both person 1 and person 2
        // add times to the node, need to check if the time already exists before adding
        Person person1;
        Person person2;

        if (this.contactsList.containsKey(trace.getPerson1())) {
            person1 = this.contactsList.get(trace.getPerson1());
        } else {
            person1 = new Person(trace.getPerson1());
        }

        if (this.contactsList.containsKey(trace.getPerson2())) {
            person2 = this.contactsList.get(trace.getPerson2());
        } else {
            person2 = new Person(trace.getPerson2());
        }

        person1.addPerson(trace.getPerson2(), trace.getTime());
        person2.addPerson(trace.getPerson1(), trace.getTime());
        this.contactsList.put(trace.getPerson1(), person1);
        this.contactsList.put(trace.getPerson2(), person2);
    }

    /**
     * Gets a list of times that person1 and person2 have come into direct 
     * contact (as per the tracing data).
     *
     * If the two people haven't come into contact before, an empty list is returned.
     * 
     * Otherwise the list should be sorted in ascending order.
     * 
     * @param person1 
     * @param person2
     * @return a list of contact times, in ascending order.
     * @require person1 != null && person2 != null
     */
    public List<Integer> getContactTimes(String person1, String person2) {
        if (this.contactsList.get(person1).contacts.get(person2) == null) {
            return new ArrayList<>(); // if it's empty/no times between them
        }
        return this.contactsList.get(person1).contacts.get(person2);
    }

    /**
     * Gets all the people that the given person has been in direct contact with
     * over the entire history of the tracing dataset.
     * 
     * @param person to list direct contacts of
     * @return set of the person's direct contacts
     */
    public Set<String> getContacts(String person) {
        return this.contactsList.get(person).contacts.keySet();
    }

    /**
     * Gets all the people that the given person has been in direct contact with
     * at OR after the given timestamp (i.e. inclusive).
     * 
     * @param person to list direct contacts of
     * @param timestamp to filter contacts being at or after
     * @return set of the person's direct contacts at or after the timestamp
     */
    public Set<String> getContactsAfter(String person, int timestamp) {
        Person p = this.contactsList.get(person);

        Set<String> peopleToContact = new HashSet<>();

        for (String contact: p.contacts.keySet()) {
            for (int time: p.contacts.get(contact)) {
                if (time >= timestamp) {
                    peopleToContact.add(contact);
                }
            }
        }
        return peopleToContact;
    }

    /**
     * Initiates a contact trace starting with the given person, who
     * became contagious at timeOfContagion.
     * 
     * Note that the return set shouldn't include the original person the trace started from.
     * 
     * @param person to start contact tracing from
     * @param timeOfContagion the exact time person became contagious
     * @return set of people who may have contracted the disease, originating from person
     */
    public Set<String> contactTrace(String person, int timeOfContagion) {
        // TODO: implement this!
        
        return null;
    }
}
