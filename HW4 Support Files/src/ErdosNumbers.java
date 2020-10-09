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
    public ErdosNumbers(List<String> papers) {
        this.authors = new HashMap<>();
        for(String paper : papers) {
            String [] collaborators = paper.split(":")[1].split("[|]"); // gets list of collaborators with | splits
            String paperTitle = paper.split(":")[0];
            for (String collaborator: collaborators) {
                // add each collaborator if they don't exist and if they do exist you need to update the collaborator
                if (!authors.containsKey(collaborator)) {
                    authors.put(collaborator, new Author(collaborator));
                }
                authors.get(collaborator).addAuthors(collaborators, paperTitle);
            }
        }
        // TODO: implement this
    }
    
    /**
     * Gets all the unique papers the author has written (either solely or
     * as a co-author).
     * 
     * @param author to get the papers for.
     * @return the unique set of papers this author has written.
     */
    public Set<String> getPapers(String author) {
        // TODO: implement this
        return authors.get(author).papers;
    }

    /**
     * Gets all the unique co-authors the author has written a paper with.
     *
     * @param author to get collaborators for
     * @return the unique co-authors the author has written with.
     */
    public Set<String> getCollaborators(String author) {
        return authors.get(author).collaborators.keySet();
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
        // TODO: implement this
        
        return false;
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
        // TODO: implement this
        
        return 0;
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
        // TODO: implement this
        
        return 0;
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
        // TODO: implement this

        return 0;
    }
}
