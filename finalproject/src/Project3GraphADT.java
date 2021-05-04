/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Ryan Belmont (belmo057)
 */

public interface Project3GraphADT <T>
{
    /**
     * adds a node to the graph
     * @param id
     * @param value
     */
    void addNode(String id, T value);

    /**
     * adds an edge to the graph
     * @param id1
     * @param id2
     */
    void addEdge(String id1, String id2);

    /**
     * sets the value of an existing node;
     * @param id
     * @param value
     */
    void setNode(String id, T value);

    /**
     * gets the value of an existing node
     * @param id
     * @return value
     */
    T getNode(String id);

    /**
     * returns an array of strings containing an id of all the nodes
     * @return Node ID array
     */
    String[] getNodeIds();

    /**
     * removes a node from the graph. Also deals with any edges connected to it.
     * @param id
     */
    void removeNode(String id);

    /**
     * removes an edge between two nodes in graph
     * @param id1
     * @param id2
     */
    void removeEdge(String id1, String id2);

    /**
     * returns the total amount of nodes in the graph
     * @return numNodes
     */
    int countNodes();

    /**
     * returns the total number of edges within the graph
     * @return numEdges
     */
    int countEdges();

    /**
     * returns the genus of the graph. ((numEdges - numNodes)+1)
     * @return
     */
    int genus();

    /**
     * returns a string representation of the graph
     * @return
     */
    String toString();
}
