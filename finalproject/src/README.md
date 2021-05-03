Project 3

## Overview
For this project you will implement a simple graph data structure that will be used to solve a math problem. The actual programming component of this project will be fairly minimal as the difficulty will come in the design of the data structure itself. As such, there will be minimal information provided on how a graph can be implemented.

## Instructions
For this project you will need to create 3 files: `Project3GraphADT.java`, `Project3Graph.java`, and `MoneyGame.java`. `Project3GraphADT` is a generic interface, `Project3Graph` is a generic class that implements `Project3GraphADT`, and `MoneyGame` is a class that will use the `Project3Graph` data structure.

### Graphs
A graph is a data structure that is composed of nodes and edges. The nodes store a data point (such as an integer) and the edges describe how the nodes are connected. Typical functionality of a graph data structure includes the ability to add and remove nodes, add and remove edges between two nodes, and get the number of nodes and edges. Depending on the purpose of the graph, more functionality may be added.

I provided an intro to graphs in Lecture 33. There is also a chapter in the textbook (chapter 25) that is about graphs (you are encouraged to read it, but not required). You are free to seek other information about graphs on the internet, but remember that the point of this project is to implement a data structure from the ground up. As such, it will be considered academic dishonesty if you find and use graph implementation examples on the internet (including pseudo code).

One extra bit that we will need for our graph is the graph **genus**. The genus is a way of gauging how "complicated" the graph is. It is simply calculated as `E - N + 1`, where `E` is the number of edges in the graph and `N` is the number of nodes in the graph.

Keep in mind that the idea of what a graph is can get complicated really quickly. This project is implementing the most basic form of a graph. To spare yourself some confusion, I would recommend not reading anything about directional graphs, weighted graphs, or other more complicated forms of graphs until you finish this project.

### The Project3GraphADT interface and Project3Graph class

The `Project3GraphADT` will provide the abstract method declarations that the `Project3Graph` class will implement. In order for us to do some testing, I am requiring that the following methods be added to the interface:

* `void addNode(String id, T value)`
* `void addEdge(String id1, String id2)`
* `void setNode(String id, T value)`
* `T getNode(String id)`
* `String[] getNodeIds()`
* `void removeNode(String id)`
* `void removeEdge(String id1, String id2)`
* `int countNodes()`
* `int countEdges()`
* `int genus()`
* `String toString()`

If you want, you may add additional methods. Note that each of the nodes has an id (of type String), and a value (of generic type T). It is common for graph data structures to keep track of nodes by some sort of id. Again, in order to have some sort of testing available, I am requiring that your data structure keeps track of nodes with a String id. 

`Project3Graph` will actually implement the graph data structure. This means that you need to determine a way to store the nodes (which have an id and value), as well as which nodes are connected to which nodes (the edges). There are several ways to do this, and chapter 25 of the textbook desribes two of the most common. **You may not use an existing ADT that Java provides (such as `java.util.List` or `java.util.Map`)**. Note that this means that you also cannot use Java classes that implement those ADTs (such as `java.util.ArrayList` or `java.util.HashMap`). You may use what we have learned in class, but you will have to reimplement it into your code (as opposed to copy-pasting the `CSCI1913ArrayList.java` into your project and directly using the `CSCI1913ArrayList` class). You may also create new classes in order to support your data structure. This is going to be the most difficult part of the project, and I suggest that you spend a decent amount of time planning this out before actually doing any programming.

The following is a brief description of what each of the methods listed above should do.

#### addNode
`addNode` will add a node to the graph.

#### addEdge
`addEdge` will add an edge between the two specified nodes. If one (or both) of the edges do not exist in the graph it will do nothing.

#### setNode
`setNode` will set the specified existing node's value. If the specified node does not exist in the graph it will do nothing.

#### getNode
`getNode` will return the value of the specified node. If the specified node does not exist in the graph it will return `null`.

#### getNodeIds
`getNodeIds` will return an array of Strings that is populated with each of the node ids in the graph. If there are no nodes in the graph it returns an empty String array.

#### removeNode
`removeNode` will remove the specified node from the graph (and all of the edges connected to it). If the specified node does not exist in the graph it will do nothing.

#### removeEdge
`removeEdge` will remove the edge between the two specified nodes. If one (or both) of the edges do not exist in the graph, or there is not an edge already between them, it will do nothing.

#### countNodes
`countNodes` will return the number of nodes in the graph.

#### countEdges
`countEdges` will return the number of edges in the graph.

#### genus
`genus` will return the genus of the graph.

#### toString
`toString` will return a String that represents the graph. It must show each node, the value of that node, and the other nodes that it is connected to (the edges). For example, a graph that has three edges (with ids `n1`, `n2`, and `n3`; and values 5, 2, and 7 respectively) that are all connected to each other could look like this:
```
n1(5): n2, n3
n2(2): n1, n3
n3(7): n1, n2
```

Make sure to describe how we should read this String in the `toString` javadoc.

### The MoneyGame class
The "Money Game" is a simple game that is used in graph theory. The graph nodes can have an integer value, and that value can be negative, positive, or zero. This value represents an amount of money. The goal of the game is to distribute the money (though the graph edges) such that each node ends up with a value greather than or equal to zero (all nodes have no "debt"). To distribute money, you select a node and that node gives 1 "money" to each of the nodes that it is connected to (increasing their value by 1). If the node has less money than the number of  nodes it is connected to, then that node will go into "debt" (it will have a negative value). [Here is a good video](https://youtu.be/U33dsEcKgeQ) describing the game (it was this video that was the inspiration for this project).

Creating an algorithm to solve the game can actually get fairly complicated, and combined with the `Project3Graph` implementation would be too much for this project. As such, **you are not required to write a method that solves this game, or even make a play.** What you are required to do is provide a method that returns a boolean value representing whether the game is winnable or not. This is described in the YouTube video, but essentially if the total amount of "money" in the graph (the sum of all the node's values) is greater than or equal to the genus of the graph, the game can be won. It is technically possible for a game to be winnable if this not the case, but for our purposes, if this condition is not true than we will say that the game is not winnable.

The `MoneyGame` class will use your `Project3Graph` graph data structure to store the game state. 

* `void addNode(String id, int element)`
* `void addEdge(String id1, String id2)`
* `int sum()`
* `boolean isWinnable()`
* `String toString()`

It should be possible to make the `MoneyGame` class extend the `Project3Graph`, meaning it would inherit many of the above methods and they would not need to be implemented. The other option (and the one that should be easier to implement) is to have the `MoneyGame` class contain a `Project3Graph` object (it would have a "has-a" relationship instead of a "is-a" relationship). Doing it this way would require that you implement all of the above methods, but many of them would just call the graph object's equivilant method (`addNode` would just call the graph's `addNode` method and pass the arguments for example).

Again, these methods are the one that are required, but you may add additional methods if you want/need. In fact, to fully play the game you would need to add quite a few more methods, but to simply tell if a game is winnable or not the methods listed above should be sufficient.

The following is a brief description of what each of the methods listed above should do.

#### addNode
`addNode` will add a node to the graph.

#### addEdge
`addEdge` will add an edge between the two specified nodes. If one (or both) of the edges do not exist in the graph it will do nothing.

#### sum
`sum` will return the sum of all of the node's values. This is total amount of "money" in the graph.

#### isWinnable
`isWinnable` will return `true` if the game can be won, and `false` otherwise. The game can be won if the total amount of "money" in the graph is greater than or equal to the graph's genus.

#### toString
`toString` should simply return the graph's `toString` value.



### Implementation
The following is the order of steps that I recommend.

1) Spend a decent amount of time planning how your graph data structure will work. Try to come up with a plan for the whole thing before you start programming.
1) Create the `Project3GraphADT` generic interface.
1) Create a driver class with a main method. This is where you will be doing your testing.
1) Create the `Project3Graph` generic class. Along the way add tests to your main method and make sure that it is working as you expect.
1) Try to identify any edge cases and create more tests.
1) Create the `MoneyGame` class. Again, add tests along the way to help you identify any steps that may have caused an error.

## Requirements
* Your subission must only include `Project3GraphADT.java`, `Project3Graph.java`, and `MoneyGame.java`. You may create other files for testing purposes, but they should not be required for you graph datastructure or money game class to work.
* Your `Project3GraphADT` interface must be a generic interface and include all of the methods listed and described in the "Instructions" section.
* Your `Project3Graph` class must be a generic class that implements the `Project3GraphADT` interface.
* Your `Project3Graph` class may not use a Java ADT to store the graph information.
* Your `MoneyGame` class must somehow use the `Project3Graph` class (either through a is-a or a has-a relationship) and it must implement the methods listed and described in the "Instructions" section.

## Documentation Requirements
* You must copy the following comment and paste it at the start of all the Java files you create. You must also edit it to have your name and your x500.
```
/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Your Name (your x500)
 */
```

* Each method in the `Project3GraphADT` interface and the `Project3Graph` and `MoneyGame` classes must have javadoc comments that describe the purpose of the method and make use of the ```@param``` and ```@return``` tags when applicable.
* All code must have comments that describe the logical steps. This does not need to be line by line, but rather logical chunk by logical chunk.

## Rubric
Here I am laying out how points will be divided amongst the various components of the project. Each component has a max number of points, as well as the ways that points can be deducted. Note that you can not receive negative points for any of the components.
 
#### Documentation (10 points)
* 2 points: All comments at the start of the Java files have been edited to have your name and your x500.
  - -1 point if some, but not all comments have been edited
  - -2 points if no comments have been edited 
* 4 points: All methods have a javadoc that describes the method's purpose, its parameters (using the ```@param``` tag), and what it returns (using the ```@return``` tag).
  - -2 point if most, but not all of the functions have a sufficient javadoc
  - -3 points if some of the functions have sufficient javadocs
  - -4 points if none of the functions have sufficient javadocs
* 4 points: All code must have comments that describe the logical steps.
  - -2 point if most of the code is well commented
  - -3 points if some of the code is well commented
  - -4 points if none of the code is commented

#### Functionality (35 points)
* 5 points: The `Project3GraphADT` interface is implemented as described in the "Instructions" section.
  - -1 point for each method that is not implemented as an abstract method in the interface
  - -3 points if the interface is not a generic interface
* 20 points: The `Project3Graph` class works as described in the "Instructions" section.
  - -2 point for each method that does not work as expected (as described in the "Instructions" section).
    * -3 points if a major fix is required
  - -5 points if `Project3Graph` is not a generic class
  - -5 points if `Project3Graph` does not implement the generic `Project3GraphADT` interface.
* 10 points: The `MoneyGame` class works as described in the "Instructions" section.
  - -2 point for each method that does not work as expected (as described in the "Instructions" section).
    * -3 points if a major fix is required
  

