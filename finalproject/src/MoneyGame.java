/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Ryan Belmont (belmo057)
 */

public class MoneyGame {
    private Project3Graph<Integer> graph = new Project3Graph<>(); // graph of the game

    /**
     * adds a node to the private graph, which represents the MoneyGame graph
     * @param id
     * @param element
     */
    public void addNode(String id, int element)
    {
        graph.addNode(id, element);
    }

    /**
     * adds an edge to the graph
     * @param id1
     * @param id2
     */
    public void addEdge(String id1, String id2)
    {
        graph.addEdge(id1,id2);
    }

    /**
     * finds the sum of the money contained in the graph
     * @return sum
     */
    public int sum()
    {
        int sum = 0;
        int numNodes = graph.countNodes(); // number of nodes in graph
        String[] idString = new String[1]; // get an array of the Id's so I know what to search for.
        idString = graph.getNodeIds();

        for (int i=0; i<numNodes; i++)
        {
            sum += graph.getNode(idString[i]); // add current Node's value into sum
        }

        return sum;
    }

    /**
     * returns true if the game is winnable, false if not
     * @return winnable
     */
    public boolean isWinnable()
    {
        if(sum() >= graph.genus()) // if winnable
        {
            return true;
        }
        return false; // else
    }

    /**
     * returns a string version of the graph
     * @return String
     */
    public String toString()
    {
        return graph.toString();
    }

}
