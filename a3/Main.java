
package a3;

import static a3.Print.tree;

public class Main {

   public static void main(String[] args){
   /*
    * You will test your own bst implementation in here.
    * Do what you wish to in Main, as we will not be running it.
    * The grader uses its own Main and calls the methods of your other classes.
    *
    * In order to test you should create BST objects, put data into them, 
    * take data out, look for values stored in it, checking size, and looking 
    * at the Nodes to see if they are all linked up correctly as a BST.
    *
    * The Tester class we give you as a suggested way to organize your tests 
    * and allow you to systematically build your ADT method by method, 
    * testing as you go, adding new tests as you add new code.
    * 
    * You will be able to comment in and out various tests easily as you work
    * as I have done below.
    *
    */
       //how do i actually test things with this??
        //how do i create a not empty bts
       //i had to add it to insert if root==null
      BST bst = new BSTImpl();
      BST tree = new BSTImpl();
      Tester tst = new Tester();
      
      //tst.emptyTree(bst);
      bst.insert(3);
      bst.insert(5);
      bst.insert(2);
      bst.insert(6);
      bst.insert(7);
      tree.insert(1);
      tree.insert(4);
      tree.insert(0);
      tree.insert(8);
      //QUESTION: how do i print?
      tree(bst);
      System.out.println(bst.findMin());
      System.out.println(bst.findMax());
      System.out.println(bst.get(5));
      System.out.println(bst.isFullBT());
      System.out.println(bst.merge(tree));
      tree(bst);
      System.out.println(bst.getMaxLeafHeightDiff());
      //tst.insert(3);

      //tst.remove(bst);
      //tst.merge(bst);
 
      // etc...
      
    }
}
