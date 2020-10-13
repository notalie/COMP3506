import java.util.*;

public class FactChecker {

    private static class Node {
        Set<String> presentPeople;

        String next;

        private Node(String person1, String person2) {
            this.presentPeople = new HashSet<>();
            this.presentPeople.add(person1);
            this.presentPeople.add(person2);
        }

        private Node(String person1) {
            this.presentPeople = new HashSet<>();
            this.presentPeople.add(person1);
        }

        private void addNext(String next) {
            this.next = next;
        }

        private void addPerson(String person) {
            this.presentPeople.add(person);
        }

        private boolean containsPerson(String person) {
            return this.presentPeople.contains(person);
        }
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
            if (fact.getType() == Fact.FactType.TYPE_TWO) { // Person a and b were at the party at the same time. //TODO: Error Checking
                if (factMap.containsKey(fact.getPersonA())) { // person A person is already established as a
                    current = factMap.get(fact.getPersonA());
                    if (current.next != null && current.next.equals(fact.getPersonB())) {
                        return false;
                    }
                    current.addPerson(fact.getPersonB());
                } else {
                    current = new Node(fact.getPersonA(), fact.getPersonB());
                    factTracker.add(current);
                }

                factMap.put(fact.getPersonB(), current);
                factMap.put(fact.getPersonA(), current);

            } else { // Person a left before person b arrived.
                Node toAdd = new Node(fact.getPersonB());
                if (factMap.containsKey(fact.getPersonA())) { // A came before B, B already in the map
                    current = factMap.get(fact.getPersonA());
                    if (current.containsPerson(fact.getPersonB())) {
                        return false;
                    }
                } else {
                    current = new Node(fact.getPersonA());
                    factTracker.add(current);
                    factTracker.add(toAdd);
                }

                current.addNext(fact.getPersonB());
                factMap.put(fact.getPersonA(), current);
                factMap.put(fact.getPersonB(), toAdd);
            }
        }
        return true;
    }
}
