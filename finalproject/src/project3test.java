public class project3test {
    public static void main(String[] args)
    {
        MoneyGame moneygame = new MoneyGame();

        moneygame.addNode("n1",0);
        moneygame.addNode("n2",1);
        moneygame.addNode("n3",1);
        moneygame.addNode("n4", -2);

        moneygame.addEdge("n1","n2");
        moneygame.addEdge("n1","n3");
        moneygame.addEdge("n1","n4");
        moneygame.addEdge("n2","n3");


        System.out.println("Winnable: " + moneygame.isWinnable());
        System.out.println(moneygame);
    }
}
