/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Ryan Belmont (belmo057)
 */

public interface Project3GraphADT <T>
{
    void addNode(String id, T value);
    void addEdge(String id1, String id2);
    void setNode(String id, T value);
    T getNode(String id);
    String[] getNodeIds();
    void removeNode(String id);
    void removeEdge(String id1, String id2);
    int countNodes();
    int countEdges();
    int genus();
    String toString();
}
