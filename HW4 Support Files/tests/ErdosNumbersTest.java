import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class ErdosNumbersTest {
    
    public static final List<String> SIMPLE_TEST_SET = List.of(
            "Tight bounds on the chromatic sum of a connected graph:Thomassen, C.|Paul Erdös|Alavi, Y.|Malde, P. J.|Schwenk, A. J.",
            "Some complete bipartite graph—tree Ramsey numbers:Burr, S.|Paul Erdös|Faudree, R. J.|Rousseau, C. C.|Schelp, R. H.",
            "Vertex-distinguishing proper edge-colorings:Burris, A. C.|Schelp, R. H.",
            "Adjacent vertex distinguishing edge-colorings:Balister, P. N.|Gyori, E.|Lehel, J.|Schelp, R. H.",
            "Sur les fonctions arithmétiques liées aux diviseurs consécutifs:Paul Erdös|Tenenbaum, G.",
            "Integers without large prime factors:Hildebrand, A.|Tenenbaum, G.",
            "Vertex‐distinguishing edge colorings of graphs:Balister, P. N.|Riordan, O. M.|Schelp, R. H."
    );
    
    @Test
    public void basicTest() {
        ErdosNumbers nums = new ErdosNumbers(SIMPLE_TEST_SET);
        assertEquals(Set.of("Vertex‐distinguishing edge colorings of graphs", "Adjacent vertex distinguishing edge-colorings"), nums.getPapers("Balister, P. N."));
        assertEquals(Set.of("Riordan, O. M.", "Gyori, E.", "Lehel, J.", "Schelp, R. H."), nums.getCollaborators("Balister, P. N."));
    }

    @Test
    public void basicTest2() {
        ErdosNumbers nums = new ErdosNumbers(List.of(
                "Adjacent vertex distinguishing edge-colorings:Balister, P. N.|Gyori, E.|Lehel, J.|Schelp, R. H.",
                "Vertex‐distinguishing edge colorings of graphs:Balister, P. N.|Riordan, O. M.|Schelp, R. H."
        ));
        assertEquals(Set.of("Vertex‐distinguishing edge colorings of graphs", "Adjacent vertex distinguishing edge-colorings"), nums.getPapers("Balister, P. N."));
        assertEquals(Set.of("Riordan, O. M.", "Gyori, E.", "Lehel, J.", "Schelp, R. H."), nums.getCollaborators("Balister, P. N."));
    }
    
    @Test
    public void simpleNumberTest() {
        ErdosNumbers nums = new ErdosNumbers(SIMPLE_TEST_SET);
        assertEquals(0, nums.calculateErdosNumber("Paul Erdös"));
        assertEquals(1, nums.calculateErdosNumber("Schelp, R. H."));
        assertEquals(2, nums.calculateErdosNumber("Burris, A. C."));
        assertEquals(2, nums.calculateErdosNumber("Riordan, O. M."));
        assertEquals(2, nums.calculateErdosNumber("Balister, P. N."));
    }
    
    @Test
    public void testConnectivity() {
        ErdosNumbers nums = new ErdosNumbers(SIMPLE_TEST_SET);
        assertTrue(nums.isErdosConnectedToAll());
        
        List<String> unconnectedSet = new ArrayList<String>(SIMPLE_TEST_SET);
        unconnectedSet.add("On Computable Numbers, with an Application to the Entscheidungsproblem:Turing A. M.");
        ErdosNumbers nums2 = new ErdosNumbers(unconnectedSet);
        assertFalse(nums2.isErdosConnectedToAll());
    }
    
    @Test
    public void testAverageAuthors() {
        ErdosNumbers nums = new ErdosNumbers(SIMPLE_TEST_SET);
        
        assertEquals(1.66666666, nums.averageErdosNumber("Vertex‐distinguishing edge colorings of graphs"), 0.001);
    }
    
    @Test
    public void simpleWeightedTest() {
        ErdosNumbers nums = new ErdosNumbers(SIMPLE_TEST_SET);

        assertEquals(1.0, nums.calculateWeightedErdosNumber("Schelp, R. H."), 0.001);
        assertEquals(2.0, nums.calculateWeightedErdosNumber("Burris, A. C."), 0.001);
        assertEquals(2.0, nums.calculateWeightedErdosNumber("Riordan, O. M."), 0.001);
        assertEquals(1.5, nums.calculateWeightedErdosNumber("Balister, P. N."), 0.001);
    }
    
    @Test
    public void uqTest() {
        List<String> papers = List.of(
                "New Ramsey Bounds from Cyclic Graphs of Prime Order:Neil J. Calkin|Paul Erdös|Craig A. Tovey",
                "Path Planning on Grids The Effect of Vertex Placement on Path Length:James Bailey|Craig A. Tovey|Tansel Uras|Sven Koenig|Alex Nash",
                "From Shared Subspaces to Shared Landmarks A Robust Multi-Source Classification Approach:Sarah M. Erfani|Mahsa Baktashmotlagh|Masud Moshtaghi|Vinh Nguyen|Christopher Leckie|James Bailey|Kotagiri Ramamohanarao",
                "Robust Domain Generalisation by Enforcing Distribution Invariance:Sarah M. Erfani|Mahsa Baktashmotlagh|Masud Moshtaghi|Vinh Nguyen|Christopher Leckie|James Bailey|Kotagiri Ramamohanarao",
                "Maximum induced trees in graphs:Paul Erdös|Michael E. Saks|Vera T. Sós",
                "Every finite distributive lattice is a set of stable matchings for a small stable marriage instance:Dan Gusfield|Robert W. Irving|Paul Leather|Michael E. Saks",
                "Database indexing for large DNA and protein sequence collections:Ela Hunt|Malcolm P. Atkinson|Robert W. Irving",
                "A Database Index to Large Biological Sequences:Ela Hunt|Malcolm P|Atkinson|Robert W. Irving",
                "The pervasiveness of evolution in GRUMPS software:Huw Evans|Malcolm P. Atkinson|Margaret I. Brown|Julie Cargill|Murray Crease|Steve Draper|Philip D. Gray|Richard Thomas",
                "Distinct Distances Determined By Subsets of a Point Set in Space:David Avis|Paul Erdös|János Pach",
                "Quasi-Planar Graphs Have a Linear Number of Edges:Pankaj K. Agarwal|Boris Aronov|János Pach|Richard Pollack|Micha Sharir",
                "Indexing uncertain data:Pankaj K. Agarwal|Siu-Wing Cheng|Yufei Tao|Ke Yi",
                "Distributed Loop Network with Minimum Transmission Delay:Paul Erdös|D. Frank Hsu",
                "An agent-based architecture for securing mobile IP:Xun Yi|Shigeki Kitazawa|Hisao Sakazaki|Eiji Okamoto|D. Frank Hsu",
                "An Online Estimation Algorithm of State-of-Charge of Lithium-Ion Batteries:Yong Feng|Cheng Meng|Fengling Han|Xun Yi|Xinghuo Yu",
                "An approach for stability analysis of a single-bit high-order digital sigma-delta modulator:Amin Z. Sadik|Zahir M. Hussain|Xinghuo Yu|Peter O'Shea",
                "Clique Partitions of Chordal Graphs:Paul Erdös|Edward T. Ordman|Yechezkel Zalcstein",
                "The size of chordal, interval and threshold subgraphs:Paul Erdös|András Gyárfás|Edward T. Ordman|Yechezkel Zalcstein",
                "Synchronization Problems Solvable by Generalized PV Systems:Peter B. Henderson|Yechezkel Zalcstein",
                "Programming early considered harmful:Judith L. Gersting|Peter B. Henderson|Philip Machanick|Yale N. Patt",
                "Dynamic Cache Switching in Reconfigurable Embedded Systems:John Shield|Peter Sutton|Philip Machanick",
                "Convexly Independent Subsets of the Minkowski Sum of Planar Point Sets:Friedrich Eisenbrand|János Pach|Thomas Rothvoß|Nir B. Sopher",
                "Exact quantification of the sub-optimality of uniprocessor fixed priority pre-emptive scheduling:Robert I. Davis|Thomas Rothvoß|Sanjoy K. Baruah|Alan Burns",
                "Deriving Specifications of Control Programs for Cyber Physical Systems:Alan Burns|Ian J. Hayes|Cliff B. Jones",
                "Intersection Statements for Systems of Sets:Walter A. Deuber|Paul Erdös|David S. Gunderson|Alexandr V. Kostochka|A. G. Meyer",
                "Strong chromatic index of subcubic planar multigraphs:Alexandr V. Kostochka|Xiaodong Li|Watcharintorn Ruksasakchai|Michael Santana|Tao Wang|G. Yu",
                "Workshops at PPSN 2018:Robin Purshouse|Christine Zarges|Sylvain Cussat-Blanc|Michael G. Epitropakis|Marcus Gallagher|Thomas Jansen|Pascal Kerschke|Xiaodong Li|Fernando G. Lobo|Julian F. Miller|Pietro S. Oliveto|Mike Preuss|Giovanni Squillero|Alberto Paolo Tonda|Markus Wagner|Thomas Weise|Dennis Wilson|Borys Wróbel|Ales Zamuda",
                "Clique coverings of the edges of a random graph:Béla Bollobás|Paul Erdös|Joel Spencer|Douglas B. West",
                "Finite Model Theory and Its Applications:Erich Grädel|Phokion G. Kolaitis|Leonid Libkin|Maarten Marx|Joel Spencer|Moshe Y. Vardi|Yde Venema|Scott Weinstein",
                "Viewpoints on \"Logic activities in Europe\", twenty years later:Luca Aceto|Thomas A. Henzinger|Joost-Pieter Katoen|Wolfgang Thomas|Moshe Y. Vardi",
                "Linear-Invariant Generation for Probabilistic Programs - Automated Support for Proof-Based Methods:Joost-Pieter Katoen|Annabelle McIver|Larissa Meinicke|Carroll C. Morgan",
                "A Class of Edge Critical 4-Chromatic Graphs:Guantao Chen|Paul Erdös|András Gyárfás|Richard H. Schelp",
                "Optimization Model and Algorithm Help to Screen and Treat Sexually Transmitted Diseases:Kun Zhao|Guantao Chen|Thomas Gift|Guoyu Tao",
                "Characterising and Predicting Urban Mobility Dynamics by Mining Bike Sharing System Data:Ida Bagus Irawan Purnama|Neil W. Bergmann|Raja Jurdak|Kun Zhao",
                "Design Exploration for FPGA-Based Multiprocessor Architecture JPEG Encoding Case Study:Jason Wu|John Williams|Neil W. Bergmann|Peter Sutton",
                "Minimum-Diameter Cyclic Arrangements in Mapping Data-Flow Graphs onto VLSI Arrays:Paul Erdös|Israel Koren|Shlomo Moran|Gabriel M. Silberman|Shmuel Zaks",
                "Geometric Applications of a Matrix-Searching Algorithm:Alok Aggarwal|Maria M. Klawe|Shlomo Moran|Peter W. Shor|Robert E. Wilber",
                "High Performance Single-Error-Correcting Quantum Codes for Amplitude Damping:Peter W. Shor|Graeme Smith|John A. Smolin|Bei Zeng"
        );
        
        ErdosNumbers nums = new ErdosNumbers(papers);

        assertEquals(Integer.MAX_VALUE, nums.calculateErdosNumber("AAAAAAA BBBBBB"));

        assertEquals(3, nums.calculateErdosNumber("Mahsa Baktashmotlagh"));
        assertEquals(4, nums.calculateErdosNumber("Richard Thomas"));
        assertEquals(3, nums.calculateErdosNumber("Yufei Tao"));
        assertEquals(4, nums.calculateErdosNumber("Peter O'Shea"));
        assertEquals(4, nums.calculateErdosNumber("Peter Sutton"));
        assertEquals(4, nums.calculateErdosNumber("Ian J. Hayes"));
        assertEquals(3, nums.calculateErdosNumber("Marcus Gallagher"));
        assertEquals(4, nums.calculateErdosNumber("Larissa Meinicke"));
        assertEquals(4, nums.calculateErdosNumber("John Williams"));
        assertEquals(3, nums.calculateErdosNumber("Graeme Smith"));

        assertEquals(2.5, nums.calculateWeightedErdosNumber("Mahsa Baktashmotlagh"), 0.001);
        assertEquals(4, nums.calculateWeightedErdosNumber("Richard Thomas"), 0.001);
        assertEquals(3, nums.calculateWeightedErdosNumber("Yufei Tao"), 0.001);
        assertEquals(4, nums.calculateWeightedErdosNumber("Peter O'Shea"), 0.001);
        assertEquals(3.5, nums.calculateWeightedErdosNumber("Peter Sutton"), 0.001);
        assertEquals(4, nums.calculateWeightedErdosNumber("Ian J. Hayes"), 0.001);
        assertEquals(3, nums.calculateWeightedErdosNumber("Marcus Gallagher"), 0.001);
        assertEquals(4, nums.calculateWeightedErdosNumber("Larissa Meinicke"), 0.001);
        assertEquals(4, nums.calculateWeightedErdosNumber("John Williams"), 0.001);
        assertEquals(3, nums.calculateWeightedErdosNumber("Graeme Smith"), 0.001);

        assertTrue(nums.isErdosConnectedToAll());
    }

}