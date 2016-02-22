/*  Student information for assignment:
 *
 *  On my honor, <Matthew Trahan>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: mt28529
 *  email address: mtrahan920@sbcglobal.net
 *  Grader name: Eric
 *  Number of slip days I am using: 0
 */

/*
 * Question. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methdos are reasonable. 
 * However if all results from all college football teams are included 
 * some unexpected results occur. 
 * 
 * Suggest another way of method of ranking teams using the results 
 * from the graph. Thouroughly explain your method. The method can build 
 * on one of the three existing algroithms.
 * It would be better if there could be a way to go deeper into the stats to compare.
 * For example, you could look at advanced statistics that websites like Pro Football Focus
 * uses which quantifies certain things like strength of schedule and their stats in
 * comparison to the other teams. There could be a way to weight the nodes with their
 * respective stats.
 */

public class GraphAndRankTester {
    
    public static void main(String[] args)  {
        boolean success = false;
        int test = 1;
        String [][] edges = 
            {{"A", "B", "1"},
             {"B", "C", "3"},
             {"D", "B", "12"},
             {"F", "C", "3"},
             {"G", "A", "7"},
             {"D", "F", "4"},
             {"D", "G", "5"},
             {"D", "E", "6"}};
     
     Graph g = new Graph();
     for(String[] edge : edges) {
         g.addEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
         g.addEdge(edge[1], edge[0], Integer.parseInt(edge[2]));
     }
        //test 1
        g.dijkstra("D");
        success =g.findPath("A").toString().equals("[D, F, C, B, A]");
        if (success){
			System.out.println("Test "+test+" passed");
		}
		else {
			System.out.println("Test "+test+" failed");
		}
        test++;
        //test 2
        g.dijkstra("B");
        success =g.findPath("C").toString().equals("[D, F, C]");
        if (success){
			System.out.println("Test "+test+" passed");
		}
		else {
			System.out.println("Test "+test+" failed");
		}
        test++;
        //test 3
        g.findAllPaths(false);
        success = g.getDiameter() ==3;
        if (success){
			System.out.println("Test "+test+" passed");
		}
		else {
			System.out.println("Test "+test+" failed");
		}
        test++;
        //test 4
        g.findAllPaths(true);
        success = g.costOfLongestShortestPath()==17;
        if (success){
			System.out.println("Test "+test+" passed");
		}
		else {
			System.out.println("Test "+test+" failed");
		}
        String actual = "2005ap_poll.txt";
        String gameResults = "div12005.txt";
        
        FootballRanker ranker = new FootballRanker(gameResults, actual);
        
        System.out.println();
        //new tests for the ranker for 2005 results
        double actualError = ranker.doUnweighted(false);
        if(actualError == 6.8)
            System.out.println("Passed unweighted test");
        else
            System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 6.8, actual: " + actualError);
        
        actualError = ranker.doWeighted(false);
        if(actualError == 5.8)
            System.out.println("Passed weigthed test");
        else
            System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 5.8, actual: " + actualError);

        actualError = ranker.doWeightedAndWinPercentAdjusted(false);
        if(actualError == 3.0)
            System.out.println("Passed unweighted win percent test");
        else
            System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 3.0, actual: " + actualError);
    }
    
    public static void doRankTests(FootballRanker ranker) {
        double actualError = ranker.doUnweighted(false);
        if(actualError == 13.7)
            System.out.println("Passed unweighted test");
        else
            System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 13.7, actual: " + actualError);
        
        actualError = ranker.doWeighted(false);
        if(actualError == 12.6)
            System.out.println("Passed weigthed test");
        else
            System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 12.6, actual: " + actualError);
        
        
        actualError = ranker.doWeightedAndWinPercentAdjusted(false);
        if(actualError == 6.3)
            System.out.println("Passed unweighted win percent test");
        else
            System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 6.3, actual: " + actualError);       
    }
    
    public static void testGraph() {
        String [][] edges = 
               {{"A", "B", "1"},
                {"B", "C", "3"},
                {"B", "D", "12"},
                {"C", "F", "3"},
                {"A", "G", "7"},
                {"D", "F", "4"},
                {"D", "G", "5"},
                {"D", "E", "6"}};
        
        Graph g = new Graph();
        for(String[] edge : edges) {
            g.addEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
            g.addEdge(edge[1], edge[0], Integer.parseInt(edge[2]));
        }
        
        g.dijkstra("A");
        String actualPath = g.findPath("E").toString();
        String expected = "[A, B, C, F, D, E]";
        if(actualPath.equals(expected))
            System.out.println("Passed dijkstra path test.");
        else
            System.out.println("Failed dijkstra path test. Expected: " + expected + " actual " + actualPath);  
        
        // find all paths using unweighted edges
        g.findAllPaths(false);
        int actualDiameter = g.getDiameter();
        if(actualDiameter == 3)
            System.out.println("Passed diameter test with weighted == false.");
        else
            System.out.println("Failed diameter test with weighted == false. Expected 3 got " + actualDiameter);
        
        double costOfLongestShortestPath = g.costOfLongestShortestPath();
        if(costOfLongestShortestPath == 3.0)
            System.out.println("Passed cost of longest shortest path test with weighted == false.");
        else
            System.out.println("Failed cost of longest shortest path test with weighted == false. Expected 3.0 got " + actualDiameter);
        
        System.out.println();
        
        String[] expectedPaths = {  "Name: D                    cost per path: 1.3333, num paths: 6",
                                    "Name: B                    cost per path: 1.5000, num paths: 6",
                                    "Name: F                    cost per path: 1.8333, num paths: 6",
                                    "Name: G                    cost per path: 1.8333, num paths: 6",
                                    "Name: A                    cost per path: 2.0000, num paths: 6",
                                    "Name: C                    cost per path: 2.0000, num paths: 6",
                                    "Name: E                    cost per path: 2.1667, num paths: 6"};
        
        // GACKY STRING TESTING!
        int index = 0;
        for(AllPathsInfo api : g.getAllPaths()) {
            if(expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            }
            else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
        
        System.out.println();
        // find all paths using weighted edges
        g.findAllPaths(true);
        actualDiameter = g.getDiameter();
        if(actualDiameter == 5)
            System.out.println("Passed diameter test with weighted == true.");
        else
            System.out.println("Failed diameter test with weighted == true. Expected 5 got " + actualDiameter);
             
        costOfLongestShortestPath = g.costOfLongestShortestPath();
        if(costOfLongestShortestPath == 17.0)
            System.out.println("Passed cost of longest shortest path test with weighted = true");
        else
            System.out.println("Failed cost of longest shortest path test with weighted = true. Expected 17.0 got " + actualDiameter);
        
        System.out.println();
        expectedPaths = new String[] {  "Name: F                    cost per path: 6.5000, num paths: 6",
                                        "Name: C                    cost per path: 6.8333, num paths: 6",
                                        "Name: D                    cost per path: 7.1667, num paths: 6",
                                        "Name: B                    cost per path: 7.3333, num paths: 6",
                                        "Name: A                    cost per path: 7.8333, num paths: 6",
                                        "Name: G                    cost per path: 8.5000, num paths: 6",
                                        "Name: E                    cost per path: 12.1667, num paths: 6"};
        index = 0;
        
        for(AllPathsInfo api : g.getAllPaths()) {
            if(expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            }
            else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
    }
}