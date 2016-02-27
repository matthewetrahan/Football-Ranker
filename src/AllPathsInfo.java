
import java.text.DecimalFormat;

/**
 * This class is used to store information about a vertex and its paths
 * to other vertices. Paths are not stored individually. Instead aggregate
 * statistics for the paths are stored including the name of the vertex, 
 * the number of paths 
 * (which equals the number of other vertices the vertex is connected to) 
 * and the total cost of all the paths.
 */
public class AllPathsInfo implements Comparable<AllPathsInfo>{

    private static final DecimalFormat ourFormatter = new DecimalFormat("0.0000");
    private static final int PADDING = 20;

    private String vertexName;
    private int numPaths;
    private double totalCost;
    private double aveCost;

    /**
     * Create a new AllPathsInfo object.
     * <br>pre: st != null, ps > 0
     * @param st The name of the vertex for this path info
     * @param ps The total number of paths
     * @param total The total cost of all paths
     */
    public AllPathsInfo(String st, int ps, double total) {
        if(st == null)
            throw new IllegalArgumentException("The vertex name may not be null. " + st);
        if(ps <= 0)
            throw new IllegalArgumentException("The number of paths must be greater than 0. " + ps);        
        vertexName = st;
        numPaths = ps;
        totalCost = total;
        aveCost = totalCost / numPaths;
    }

    /**
     * Primary key is number of paths, then average cost per path.
     */
    public int compareTo(AllPathsInfo other) {
        int result = other.numPaths - numPaths;
        if(result == 0)
            result = (aveCost < other.aveCost) ? -1 : (aveCost == other.aveCost) ? 0 : 1;
        if(result == 0)
            result = vertexName.compareTo(other.vertexName);
        return result;
    }

    /**
     * Adjust the average cost per path.
     * @param adjust
     */
    public void adjustAveCost(double adjust) {
        aveCost *= adjust;
    }

    
    public String toString() {
        return "Name: " + String.format("%1$-" + PADDING  + "s", vertexName) + " cost per path: " + ourFormatter.format(aveCost) + ", num paths: " + numPaths;
    }

    /**
     * Get the name of the vertex where the paths originate from.
     * @return the name of the vertex.
     */
    public String getName() {
        return vertexName;
    }

    public static AllPathsInfo makeCopy(AllPathsInfo org) {
        AllPathsInfo result = new AllPathsInfo(org.vertexName, org.numPaths, org.totalCost);
        return result;
    }

    /**
     * Get the number of paths from this vertex to other vertices. This should be
     * the number of shortest paths.
     * @return the number of paths from this vertex to others.
     */
    public int getNumPaths() {
        return numPaths;
    }

    /**
     * Get the total cost of all shortest paths.
     * @return the total cost of all shortest paths.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Get the average cost per path.
     * @return the average cost per path.
     */
    public double getAveCost() {
        return aveCost;
    }
    
    public boolean equals(Object other) {
        boolean result = other instanceof AllPathsInfo;
        if(result)
            result = vertexName.equals(((AllPathsInfo) other).vertexName);
        return result;
    }
}