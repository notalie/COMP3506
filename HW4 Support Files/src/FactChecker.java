import java.util.*;

public class FactChecker {

    /* Represents a cluster of people who were present at the party */
    private static class Node {

        /* A set of people who were present at the party */
        Set<String> presentPeople;

        private Node(String person1, String person2) {
            this.presentPeople = new HashSet<>();
            this.presentPeople.add(person1);
            this.presentPeople.add(person2);
        }

        private Node(String person1) {
            this.presentPeople = new HashSet<>();
            this.presentPeople.add(person1);
        }

        /**
         * Adds a person to the node cluster
         * @param person - the person to add
         */
        private void addPerson(String person) {
            this.presentPeople.add(person);
        }

        /**
         * Checks if the cluster contains the person
         * @param person - the person to check
         * @return
         */
        private boolean containsPerson(String person) {
            return this.presentPeople.contains(person);
        }
    }

    /**
     * Checks if person B was at the party before person A.
     * This is an inconsistency only for facts of type one
     * @param fact - the fact to check
     * @param factTracker - the fact tracker to iterate though
     * @return - a boolean
     */
    private static boolean containsPersonBefore(Fact fact, LinkedList<Node> factTracker) {
        Iterator<Node> iterator = factTracker.iterator();
        while (iterator.hasNext()) {
            Node current = iterator.next();
            if (current.containsPerson(fact.getPersonB())) {
                return true;
            } else if (current.containsPerson(fact.getPersonA())) {
                return false;
            }
        }
        return false;
    }


    /**
     * Function for type 1 facts (Person a left before person b arrived.)
     * @param factTracker - a linked list of the facts to track
     * @param factMap - a map that links nodes to
     * @param fact - the fact to add/check
     * @return - true if there are no inconsistencies and false if there is
     */
    private static boolean isTypeOneConsistent(LinkedList<Node> factTracker, HashMap<String, Node> factMap, Fact fact) {
        Node current;
        Node toAdd = new Node(fact.getPersonB());

        if (factMap.containsKey(fact.getPersonA())) { // B already in the map
            current = factMap.get(fact.getPersonA());
            if (current.containsPerson(fact.getPersonB())) { // A was with B
                return false;
            } else if (containsPersonBefore(fact, factTracker)) {
                return false;
            }
            factTracker.add(toAdd); // Add node to indicate that it came after person B

        } else { // B not in the map, make a new node
            current = new Node(fact.getPersonA());
            factTracker.add(current);
            factTracker.add(toAdd);
        }

        factMap.put(fact.getPersonA(), current); // add the nodes to the hashmap
        factMap.put(fact.getPersonB(), toAdd);
        return true;
    }


    /**
     * Function for type 2 facts (Person a and b were at the party at the same time)
     * @param factTracker - a linked list of the facts to track
     * @param factMap - a map that links nodes to
     * @param fact - the fact to add/check
     * @return - true if there are no inconsistencies and false if there is
     */
    private static boolean isTypeTwoConsistent(LinkedList<Node> factTracker, HashMap<String, Node> factMap, Fact fact) {
        Node current;
        if (factMap.containsKey(fact.getPersonA())) { // Person A already in the data structure
            current = factMap.get(fact.getPersonA());

            if (factMap.containsKey(fact.getPersonB())) { // Person B already exists, no need to add to the fact tracker
                return false;
            }

            current.addPerson(fact.getPersonB()); // Add person B to person A's node
        } else { // Person A not in the structure, add person and person b to the fact tracker
            current = new Node(fact.getPersonA(), fact.getPersonB());
            factTracker.add(current);
        }

        factMap.put(fact.getPersonB(), current); // Add person A and person B to the hashmap
        factMap.put(fact.getPersonA(), current);
        return true;
    }


    /**
     * Checks if a list of facts is internally consistent. 
     * That is, can they all hold true at the same time?
     * Or are two (or potentially more) facts logically incompatible?
     * 
     * @param facts list of facts to check consistency of
     * @return true if all the facts are internally consistent, otherwise false.
     */
    public static boolean areFactsConsistent(List<Fact> facts) {
        LinkedList<Node> factTracker = new LinkedList<>(); // a linked list representing the order of arrival
        HashMap<String, Node> factMap = new HashMap<>(); // a hashmap where each string represents a person's name which points to the node clusters in the linked list

        for (Fact fact: facts) {
            if (fact.getType() == Fact.FactType.TYPE_ONE && !isTypeOneConsistent(factTracker, factMap, fact)) { // Person a left before person b arrived.
                return false;
            } else if (fact.getType() == Fact.FactType.TYPE_TWO && !isTypeTwoConsistent(factTracker, factMap, fact)) { // Person a and b were at the party at the same time.
                return false;
            }
        }
        return true;
    }
}
