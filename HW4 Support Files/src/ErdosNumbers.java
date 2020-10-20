import java.util.*;

public class ErdosNumbers {
    /**
     * String representing Paul Erdos's name to check against
     */
    public static final String ERDOS = "Paul Erdös";

    private class Author {

        Set<String> papers; // list of papers worked on

        HashMap<String, Integer> collaborators; // e.g. AuthorName:AmountOfPapers worked on

        String name;

        private Author(String name) {
            this.name = name;
            papers = new HashSet<>();
            collaborators = new HashMap<>();
        }

        private void addAuthor(String name, String paperTitle) {
            papers.add(paperTitle);
            if (collaborators.containsKey(name)) { // name exists in collaborators
                Integer numPapers = collaborators.get(name);
                collaborators.put(name, ++numPapers);
            } else if (!name.equals(this.name)){
                collaborators.put(name, 1);
            }
        }

        private void addAuthors(String[] authorNames, String paperTitle) {
            for (String name: authorNames) {
                addAuthor(name, paperTitle);
            }
        }
    }

    /**
     * Initialises the class with a list of papers and authors.
     *
     * Each element in 'papers' corresponds to a String of the form:
     * 
     * [paper name]:[author1][|author2[|...]]]
     *
     * Note that for this constructor and the below methods, authors and papers
     * are unique (i.e. there can't be multiple authors or papers with the exact same name or title).
     * 
     * @param papers List of papers and their authors
     */
    HashMap<String, Author> authors; // Name, AuthorObject //TODO: comment properly
    HashMap<String, String[]> paperCollaborators; //PaperName, Authors

    public ErdosNumbers(List<String> papers) {
        this.authors = new HashMap<>();
        this.paperCollaborators = new HashMap<>();

        for(String paper : papers) {
            String [] collaborators = paper.split(":")[1].split("[|]"); // gets list of collaborators with | splits
            String paperTitle = paper.split(":")[0];
            paperCollaborators.put(paperTitle, collaborators);
            for (String collaborator: collaborators) {
                // add each collaborator if they don't exist and if they do exist you need to update the collaborator
                if (!authors.containsKey(collaborator)) {
                    authors.put(collaborator, new Author(collaborator));
                }
                authors.get(collaborator).addAuthors(collaborators, paperTitle);
            }
        }
    }
    
    /**
     * Gets all the unique papers the author has written (either solely or
     * as a co-author).
     * 
     * @param author to get the papers for.
     * @return the unique set of papers this author has written.
     */
    public Set<String> getPapers(String author) {
        return authors.get(author).papers;
    }

    /**
     * Gets all the unique co-authors the author has written a paper with.
     *
     * @param author to get collaborators for
     * @return the unique co-authors the author has written with.
     */
    public Set<String> getCollaborators(String author) {
        if (authors.get(author) != null) {
            return authors.get(author).collaborators.keySet();
        } else {
            return Set.of();
        }
    }

    /**
     * Checks if Erdos is connected to all other author's given as input to
     * the class constructor.
     * 
     * In other words, does every author in the dataset have an Erdos number?
     * 
     * @return the connectivity of Erdos to all other authors.
     */
    public boolean isErdosConnectedToAll() {
        Stack<String> toVisit = new Stack<>();
        Set<String> visited = new HashSet<>();

        String current = ERDOS;
        toVisit.add(current); // Start with Erdos

        while(!toVisit.isEmpty()) {
            current = toVisit.pop();

            for (String collaborator: getCollaborators(current)) {
                if (!visited.contains(collaborator)) { // Has not been visited
                    toVisit.push(collaborator);
                }
            }
            visited.add(current); // Mark as visited
        }

        return visited.size() == this.authors.size();
    }

    /**
     * Calculate the Erdos number of an author. 
     * 
     * This is defined as the length of the shortest path on a graph of paper 
     * collaborations (as explained in the assignment specification).
     * 
     * If the author isn't connected to Erdos (and in other words, doesn't have
     * a defined Erdos number), returns Integer.MAX_VALUE.
     * 
     * Note: Erdos himself has an Erdos number of 0.
     * 
     * @param author to calculate the Erdos number of
     * @return authors' Erdos number or otherwise Integer.MAX_VALUE
     */
    public int calculateErdosNumber(String author) {
        Queue<String> toVisit = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        String current;
        toVisit.add(author); // level 1
        toVisit.add(null);

        int distance = 0;

        while(!toVisit.isEmpty()) {
            current = toVisit.poll();

            if (current == null) { // reached a new level
                distance++;
                toVisit.add(null); // add indicator for next level
                if (toVisit.peek() == null) {
                    return Integer.MAX_VALUE; // end of the search
                }
                continue;
            }

            if (current.equals(ERDOS)) {
                return distance;
            }

            if (!visited.contains(current)) {
                for (String collaborator: getCollaborators(current)) {
                    if (!visited.contains(collaborator)) {
                        toVisit.add(collaborator);
                    }
                }
            }
            visited.add(current);
        }
        return Integer.MAX_VALUE; // If nothing else is returned
    }

    /**
     * Gets the average Erdos number of all the authors on a paper.
     * If a paper has just a single author, this is just the author's Erdos number.
     *
     * Note: Erdos himself has an Erdos number of 0.
     *
     * @param paper to calculate it for
     * @return average Erdos number of paper's authors
     */
    public double averageErdosNumber(String paper) {
        String[] authorsInPaper = paperCollaborators.get(paper);
        int totalNumbers = 0;
        for (String author: authorsInPaper) {
            totalNumbers += calculateErdosNumber(author);
        }
        return (double)totalNumbers/authorsInPaper.length;
    }


    private class Node implements Comparable<Node>{
        private String value;

        private Double key;

        private Node(String s, Double i) {
            this.value = s;
            this.key = i;
        }

        private String getValue() {
            return this.value;
        }

        private Double getKey() {
            return this.key;
        }

        public int compareTo(Node n) {
            if (this.key < n.key) {
                return -1;
            } else if (this.key > n.key) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private void replaceKey(Map.Entry<Double, String> toReplace, Double valueToReplaceWith, PriorityQueue<Node> pq) {
        Iterator<Node> i = pq.iterator();
        while(i.hasNext()) {
            Node current = i.next();
            if (current.getKey().equals(toReplace.getKey()) && current.getValue().equals(toReplace.getValue())) {
                current.key = valueToReplaceWith;
            }
        }
    }


    /**
     * Calculates the "weighted Erdos number" of an author.
     * 
     * If the author isn't connected to Erdos (and in other words, doesn't have
     * an Erdos number), returns Double.MAX_VALUE.
     *
     * Note: Erdos himself has a weighted Erdos number of 0.
     * 
     * @param author to calculate it for
     * @return author's weighted Erdos number
     */
    public double calculateWeightedErdosNumber(String author) {
        Map<String, Double> d = new HashMap<>();

        Map<String, Double> cloud = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>();

        Map<String, Map.Entry<Double, String>> pqTokens = new HashMap<>();

        for (String v: this.authors.keySet()) {
            if (v.equals(author)) {
                d.put(v, 0.0);
            } else {
                d.put(v, Double.MAX_VALUE);
            }
            pq.add(new Node(v, d.get(v)));
            pqTokens.put(v, new AbstractMap.SimpleEntry<>(d.get(v), v));
        }

        while (!pq.isEmpty()) {
            Node entry = pq.poll();
            double key = entry.getKey();
            String u = entry.getValue();
            cloud.put(u, key);
            pqTokens.remove(u);

            for (String v: getCollaborators(u)) {
                if (cloud.get(v) == null) {
                    // perform relaxation step on edge (u,v)
                    double e = 1.0 / this.authors.get(u).collaborators.get(v);

                    if ((d.get(u) + e) < d.get(v)) {
                        d.put(v, d.get(u) + e);
                        replaceKey(pqTokens.get(v), d.get(v), pq);
                    }
                }
            }
        }
        return cloud.get(ERDOS);
    }
}
