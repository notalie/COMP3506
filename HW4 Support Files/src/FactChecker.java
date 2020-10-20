import java.util.*;

public class FactChecker {

    private static class Node {
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

        private void addPerson(String person) {
            this.presentPeople.add(person);
        }

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
     * Checks if a list of facts is internally consistent. 
     * That is, can they all hold true at the same time?
     * Or are two (or potentially more) facts logically incompatible?
     * 
     * @param facts list of facts to check consistency of
     * @return true if all the facts are internally consistent, otherwise false.
     */
    public static boolean areFactsConsistent(List<Fact> facts) {
        LinkedList<Node> factTracker = new LinkedList<>();
        HashMap<String, Node> factMap = new HashMap<>();

        for (Fact fact: facts) {
            Node current;
            if (fact.getType() == Fact.FactType.TYPE_TWO) { // Person a and b were at the party at the same time.

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

            } else { // Person a left before person b arrived.
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
            }
        }
        return true;
    }
}
