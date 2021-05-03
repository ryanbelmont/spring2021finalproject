/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Ryan Belmont (belmo057)
 */

public class MoneyGame {
    public static void main(String[] args)
    {
        Project3Graph<Integer> map = new Project3Graph<>();
        map.addNode("n1", 5);
        map.addNode("n2", 2);
        map.addNode("n3", 7);
        map.addNode("n4", 6);

        map.addEdge("n1","n2");
        map.addEdge("n2","n3");
        map.addEdge("n1","n3");
        map.addEdge("n4", "n1");
        map.addEdge("n4", "n2");
        map.addEdge("n4", "n3");


        System.out.println(map);
        System.out.println();

        System.out.println(map.countNodes());
        System.out.println(map.countEdges());
        System.out.println(map.genus());
        System.out.println(map.getNode("n1"));


    }
}
