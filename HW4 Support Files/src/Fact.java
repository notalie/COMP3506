/**
 * Represents a fact for question 2.
 * 
 * DO NOT MODIFY OR SUBMIT THIS FILE.
 */
public class Fact {

    public static enum FactType {
        /**
         * Person a left before person b arrived.
         */
        TYPE_ONE,
        
        /**
         * Person a and b were at the party at the same time.
         */
        TYPE_TWO
    }
    
    private final FactType type;
    private final String personA;
    private final String personB;

    /**
     * Creates a new fact.
     * 
     * @param type of fact
     * @param personA
     * @param personB
     */
    public Fact(FactType type, String personA, String personB) {
        this.type = type;
        this.personA = personA;
        this.personB = personB;
    }


    /**
     * Gets the fact type.
     *
     * @return the type of fact
     */
    public FactType getType() {
        return type;
    }

    /**
     * @return person a involved in the fact
     */
    public String getPersonA() {
        return personA;
    }

    /**
     * @return person b involved in the fact
     */
    public String getPersonB() {
        return personB;
    }

    
}