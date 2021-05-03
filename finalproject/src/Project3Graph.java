/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Ryan Belmont (belmo057)
 */

public class Project3Graph <T extends Comparable<T>> implements Project3GraphADT <T> {

    private int numNodes;
    private int numEdges;
    private int[][] adjArray;
    private Node[] NodeArray;

    private class Node <T extends Comparable<T>>
    {
        String id;
        T value;

        /**
         * default constructor for this private class to store each node
         * @param id
         * @param value
         */
        Node(String id, T value)
        {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return id.toString() + "(" + value.toString() + ")";
        }

    }

    /**
     * default constructor
     */
    public Project3Graph()
    {
        NodeArray = new Node[1];
        adjArray = new int[1][1]; // adjacency matrix
        numNodes = 0;
        numEdges = 0;
    }

    /**
     * adds a node to the arrays. It updates the adjacency matrix, and the matrix to hold values
     * @param id
     * @param value
     */
    @Override
    public void addNode(String id, T value) {
        for (int i=0; i<numNodes; i++) // determine if the id is already being used
        {
            if (NodeArray[i].id == id) {
                return;
            }
        }

        numNodes++; // increase num nodes

        int[][] tmp2d = new int[numNodes][numNodes]; // creates a temp 2d adjacency matrix
        for (int i=0; i<numNodes-1; i++)  // embedded for loop to copy old array
        {
            for(int j=0; j<numNodes-1; j++)
            {
                tmp2d[i][j] = adjArray[i][j];
            }
        }
        for (int i=0; i < numNodes; i++) // set 0 to new nodes;
        {
            tmp2d[numNodes - 1][i] = 0;
            tmp2d[i][numNodes - 1] = 0;
        }
        adjArray = tmp2d; // set adjArray to the temporary array

        Node[] tmp = new Node[numNodes]; // now update the array to store values;
        for (int i=0; i<numNodes-1; i++)
        {
            tmp[i] = NodeArray[i];
        }
        tmp[numNodes-1] = new Node(id, value);

        NodeArray = tmp; // set NodeArray to the temporary one
    }

    /**
     * adds an edge that connects two pre-existing nodes. Checks to ensure both nodes
     *  exist, and are different.
     * @param id1
     * @param id2
     */
    @Override
    public void addEdge(String id1, String id2) {
        boolean Node1 = false;
        boolean Node2 = false;
        int node1Location = -1;
        int node2Location = -1;
        for (int i=0; i<numNodes; i++) // determine if both nodes exist
        {
            if(NodeArray[i].id == id1)
            {
                Node1 = true;
                node1Location = i;
            }
            if(NodeArray[i].id == id2)
            {
                Node2 = true;
                node2Location = i;
            }
        }
        if (id1 == id2) // if they are the same node
        {
            return;
        }
        if (Node1 == false || Node2 == false) // if they don't exist
        {
            return;
        }

        // modify the adjacency array
        adjArray[node1Location][node2Location] = 1;
        adjArray[node2Location][node1Location] = 1;
        numEdges = countEdges();
    }

    /**
     * sets the value of an existing node to the specified new value
     * @param id
     * @param value
     */
    @Override
    public void setNode(String id, T value) {
        for (int i=0; i<numNodes; i++) // search array for id
        {
            if(NodeArray[i].id == id) // if find id, change value
            {
                NodeArray[i].value = value;
            }
        }
    }

    /**
     * returns the value of the specified Node, if specified node doesn't exist it will return null.
     * @param id
     * @return value
     */
    @Override
    public T getNode(String id) {
        for (int i=0; i<numNodes; i++) // search whole array
        {
            if (NodeArray[i].id == id) // if id's match return the value
            {
                return (T) NodeArray[i].value;
            }
        }
        return null; // else return null
    }

    /**
     * returns an array of strings populated with all of the id's in the graph
     * @return
     */
    @Override
    public String[] getNodeIds() {
        String[] idArray = new String[numNodes];
        for (int i=0; i<numNodes; i++)
        {
            idArray[i] = NodeArray[i].id;
        }
        return idArray;
    }

    /**
     * removes a specified node and all the edges connected to it. If no node exists
     *  doesn't do anything.
     * @param id
     */
    @Override
    public void removeNode(String id) {
        int nodeLocation = -1;
        for (int i=0; i<numNodes; i++) // find the array location of the correct Node
        {
            if (NodeArray[i].id == id)
            {
                nodeLocation = i;
                break;
            }
        }
        if (nodeLocation == -1) // if node doesn't exist, stop
        {
            return;
        }


        Node[] tmp = new Node[numNodes-1]; // create new temporary array of size 1 less than before
        int iNew=0; // used as a counter besides i. Needed after remove desired
        for (int i=0; i<numNodes; i++) // copy the old array to the tmp one
        {
            if(i == nodeLocation) // skip the location we wish to remove
            {
                continue;
            }
            else {
                tmp[iNew] = NodeArray[i];
                iNew++;
            }
        }
        NodeArray = tmp; // set NodeArray to the temporary one

        // next we must update the adjacency matrix. I simply need to remove the nodes column and row

        int[][] tmp2d = new int[numNodes-1][numNodes-1];
        iNew = 0;
        int jNew = 0; // need 2 secondary counters now
        for (int i=0; i<numNodes; i++)
        {
            jNew = 0;
            if (i == nodeLocation) // if row is to be removed
            {
                continue;
            }
            for (int j=0; j<numNodes; j++)
            {
                if (j == nodeLocation) // if column is to be removed
                {
                    continue;
                }
                tmp2d[iNew][jNew] = adjArray[i][j]; // copy
                jNew++;
            }
            iNew++;
        }
        adjArray = tmp2d; //reset
        numNodes--;
        numEdges = countEdges();

    }

    /**
     * removes an edge between two nodes. It checks that both nodes exist.*
     * @param id1
     * @param id2
     */
    @Override
    public void removeEdge(String id1, String id2) {
        int node1Location = -1;
        int node2Location = -1;
        for (int i=0; i<numNodes; i++) // determine if both nodes exist
        {
            if(NodeArray[i].id == id1)
            {
                node1Location = i;
            }
            if(NodeArray[i].id == id2)
            {
                node2Location = i;
            }
        }
        if (node1Location == -1 || node2Location == -1)
        {
            return;
        }
        //don't need to check if an edge exits, it would become 0 regardless
        adjArray[node1Location][node2Location] = 0;
        adjArray[node2Location][node1Location] = 0;
        countEdges();
    }

    /**
     * returns the number of nodes in the graph
     * @return numNodes
     */
    @Override
    public int countNodes() {
        return numNodes;
    }

    /**
     * returns the number of edges withing the graph
     * @return numEdges
     */
    @Override
    public int countEdges() {
        numEdges = 0;
        for(int i=0; i<numNodes; i++)
        {
            for(int j=0; j<numNodes; j++)
            {
                if (adjArray[i][j] == 1)
                {
                    numEdges++;
                }
            }
        }
        numEdges = numEdges/2;
        return numEdges;
    }

    /**
     * returns a value representing the complexity of the graph. This follows
     *  the equation ((number of edges) - (number of nodes) + 1)
     * @return genus
     */
    @Override
    public int genus() {
        return (numEdges - numNodes + 1);
    }

    /**
     * creates a string that contains each nodes value and key, along with with its edges.
     * The string will be in the form of "id(value): edges to"
     * @return string map
     */
    @Override
    public String toString(){
        String tmp = "";
        for(int i=0; i<numNodes; i++)
        {
            tmp += NodeArray[i].toString() + ": "; // adds the node information
            for (int j=0; j<numNodes; j++) // embedded for loop to find edge information
            {
                if(adjArray[i][j] == 1)
                {
                    tmp += NodeArray[j].id + ", ";
                }
            }
            tmp = tmp.replaceAll(", $", ""); // removes a possible comma at end of string
            tmp += "\n";
        }

        return tmp;
    }
}
