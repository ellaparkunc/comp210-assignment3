
package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() { root=null; size=0; }

    public BSTImpl(int val) { this.root=new NodeImpl(val); size=1; }

    // The implementations given to you are intended to help you 
    // see how the linked cells work, and how to program with them.
    //
    // These methods are patterns to illustrate for you how to set up
    // the method implementations as recursion.
    //
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive helper methods.

    @Override
    // interface method ==================================================
    public int height() {       
      // It is not recursive itself, but it calls a recursive
      // private "helper" method and passes it access to the tree cells.
      return height_recursive(this.root);
    }
    // private recursive helper
    private int height_recursive(Node c) {
      // private inner "helper" method with different signature
      // this helper method uses recursion to traverse
      // and process the recursive structure of the tree of cells
      if (c==null) return -1;
      int lht = height_recursive(c.getLeft());
      int rht = height_recursive(c.getRight());
      return Math.max(lht,rht) + 1;
    }
    
    @Override
    // interface method ==================================================
    public boolean contains(int val) { 
      if(this.root==null) return false; 
      return contains_r(val,root); 
    }
    // private recursive helper
    private boolean contains_r(int val, Node c) {
      if(c == null) return false;
      if(c.getValue()==val) return true;
      //if left or right node contains it, it returns true
            // otherwise, it runs the recursive part of contains_r again
        //when it goes past a leaf, c==null
            // and it returns false and recursion stops
      return contains_r(val, c.getLeft()) || contains_r(val, c.getRight());
    }

    @Override
    // interface method, used by autograder, do not change
    public Node getRoot() { return this.root; }
    
    @Override
    // interface method ==================================================
    public int size() { return this.size; }

    
    @Override
    // interface method ===================================================
    //calling remove(int) will
        //call 1. remove_r w that int and 2.pass that tree's root in
    public void remove(int value) { remove_r(value,this.root); }
    // private recursive helper
    private Node remove_r(int k, Node c) {
      if (c==null) return null; // item not found, nothing to do
      // now we know we have a real node to examine
      //int cflag = k.compareTo(c.getValue());
        //vc for value of c
      int vc=c.getValue();
      if (k<vc) { // k is smaller than node
          //WHAT IS SETLEFT?
          //recursively calls remove_r on
            //1. value to remove and 2. the node left of node we lookin @
            //this returns the left half of the tree without the element
          c.setLeft(remove_r(k,c.getLeft()));
        return c;
      }
      else if (k>vc) { // k is larger than node
        c.setRight(remove_r(k,c.getRight()));
        return c;
      }
      else { // k==vc   // found it... now figure out how to rearrange
        // cases
          //if node to remove is a leaf, simply set equal to null
        if (c.getLeft()==null && c.getRight()==null) { c = null; this.size--; } // leaf
        //if only right side contains something, set that removed space with what was in the right side
        else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); this.size--; } // RC only
        else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); this.size--; } // LC only
        else { // 2 children
            //finds biggest left
          Node mc = findMaxCell(c.getLeft());
          //sets the removed spot as biggest left
          c.setValue(mc.getValue());
          //passes 1. biggest left value and 2.left of THAT as the node
            //removes that left value and replaces it with the biggest left
                //and so forth AKA you move everything up by one
          c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
        }
        return c;
      }
    }
    // private recursive helper
    //goes right till it can't go right no more
    private Node findMaxCell(Node c) { 
      if (c.getRight()==null) return c;
      return findMaxCell(c.getRight());
    } 

  
   //====================================================================================
   //
   // The methods below here are part of the public BST interface we defined, 
   // but you will write the implementation code.
   // 
   // At the moment, they have return statements that return dummy values; this
   // is so they will compile, but those return values are just dummy behavior
   // you will replace the dummy returns with your own code and proper return values.
   //
   //====================================================================================

  
    @Override
    // interface method ==================================================
    public int insert(int value) {
        //QUESTION: WHY DOES MY INSERT ADD SOME BUT NOT OTHERS OF THE INTS?
        //okay wait remove apparently doesn't work either
        // so its something to do w how i'm testing it
        if (this.root == null) {
            this.root = new NodeImpl(value);
        }
        //If we try to add an element, and it is a duplicate, return the element
        //but make no changes to the tree... no new nodes added
        if (contains(value)){
            return value;
        }
        return insert_r(value,this.root); }

        private int insert_r(int k, Node c) {
        //HOW TO ADD VALUE? (not rename)
            //you do new NodeImpl(int)
            if (k < c.getValue()) {
                //if k is less than left most value
                if (c.getLeft() == null) {
                    Node lc = new NodeImpl(k);
                    c.setLeft(lc); size++; return k;
                } else {
                    //if there is a value to the left, recurse on that value and k
                    //go lower and left or higher and right until you hit null
                    return insert_r(k, c.getLeft());
                }
            }

            if (k > c.getValue()) {
                //if k is greater than right most value
                if (c.getRight() == null) {
                    Node rc = new NodeImpl(k);
                    c.setLeft(rc); size++; return k;
                } else {
                    //if there is a value to the left, recurse on that value and k
                    //go lower and left or higher and right until you hit null
                    return insert_r(k, c.getRight());
                }
            } else {
                return -1;
            }
            //if k < Node c,
                //&& left of that = null, add value to the left
                //recursively call insert_r on (k & node to the left)
            //same for greater than except for right
        }

        /* Hint: Don't forget to update size */
        /* Hint: You can find examples of how to create a new Node object elsewhere in the code */


    @Override
    // interface method ==================================================
    public int findMin() {
        /*See BST.java for method specification */
        /* Your code here */
        
        return Integer.MAX_VALUE; // Dummy return statement.  Remove when you implement!
    }
    
    @Override
    // interface method ==================================================
    public int findMax() {
        /*See BST.java for method specification */
        /* Your code here */

       return Integer.MIN_VALUE; // Dummy return statement.  Remove when you implement!

    }
    
    @Override
    // interface method ==================================================
    public Node get(int val) {
        /*See BST.java for method specification */
        /* Hint: Make sure you return a Node, not an int */
        /* Your code here */

      return null; // Dummy return statement.  Remove when you implement!
    }
    
    @Override
    // interface method ==================================================
    public boolean isFullBT() {
        /*See BST.java for method specification */
        /* Hint: How can you "break-up" the problem into smaller pieces? */
        /* Your code here */

        return false; // Dummy return statement.  Remove when you implement!
    }
    
    @Override
    // interface method ==================================================
    public int merge(BST nbt) {
        /*See BST.java for method specification */
      // Hint: traverse bst using pre-order
      // as each node is visited, take the value there
      // and do this.insert(value)
      // have to somehow count when an add is successful
      // so we can return the number of nodes added
         /* Your code here */
        
        return 0;  // Dummy return statement.  Remove when you implement!
    }

    public int getMaxLeafHeightDiff () {
        /*See BST.java for method specification */
        /* Hint: Which of the methods you're given are most similar to this? */
        /* Your code here */
        
        return 0;// Dummy return statement.  Remove when you implement!
    }

}
