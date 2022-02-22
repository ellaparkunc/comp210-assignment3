package a3;

public class Print {
   //tree class
   public static void tree(BST tr) { 
      System.out.println();
      System.out.println("-------------------tree--");
      if (tr.getRoot()==null) { System.out.println(" [ empty tree ]"); } 
      //come again? using tree_r and getting the root of the tree we passed in?
      //calls tree_r with node = tree's root and k=0
      else { tree_r(tr.getRoot(),0); }
      System.out.println("--------------------end--"+"\n");
   }
   //r is for recursive
   private static void tree_r(Node n,int k) {
      if (n==null) return;
      //recursively calls tree_r
      tree_r(n.getRight(),k+3);
      //runs the function 3 more times than k
      //just prints space??? why
      for (int i=0; i<k; i++) { System.out.print(" "); }
      //prints the value of passed in node
      System.out.println(n.getValue());
      //makes the for loop print out three more spaces??
      tree_r(n.getLeft(),k+3);
   }

}
