
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
      //QUESTION: why did we add one?
        //bc lht counts the number of times we recurse
        //but we are missing the arrow from c to left of c so we must add it back.
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
      //THIS ISN'T THE SMARTEST WAY TO DO IT, BC YOU ARE CHECKING EVERYTHING
        //and not making use of the fact you're using a BST
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
            this.root = new NodeImpl(value); size++;
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
                    c.setRight(rc); size++; return k;
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
        //QUESTION: how am i supposed to do this without recursion?
        Node here = this.root;
        //lets see, i want the function to run once for a one node tree,
        //twice for a 2 node tree (height of 1)
        //max of three times for a three node tree
        for (int i = 0; i < this.height() + 1; i++) {
            if (here.getLeft() == null) {
                return here.getValue();
            } else {
                here = here.getLeft();
            }
        }
        return -100;
    }
    @Override
    // interface method ==================================================
    public int findMax() {
        /*See BST.java for method specification */
        /* Your code here */
        Node here = this.root;
        for (int i = 0; i < this.height() + 1; i++) {
            if (here.getRight() == null) {
                return here.getValue();
            } else {
                here = here.getRight();
            }
        }
        return -100;

    }
    
    @Override
    // interface method ==================================================
    public Node get(int val) {
        /*See BST.java for method specification */
        /* Hint: Make sure you return a Node, not an int */
        /* Your code here */
        //so find but without insert :)
        if (!contains(val)) {
            return null;
        }
        if (this.root.getValue() == val) {
            return this.root;
        }
        return get_r(val, this.root);
    }

    private Node get_r(int k, Node c) {
        if (k < c.getValue()) {
            if (c.getLeft().getValue() == k) {
                return c.getLeft();
            } else {
                return get_r(k, c.getLeft());
            }
        }
        if (k > c.getValue()) {
            if (c.getRight().getValue() == k) {
                return c.getRight();
            } else {
                return get_r(k, c.getRight());
            }
        }
        return c;
    }

    @Override
    // interface method ==================================================
    public boolean isFullBT() {
        /*See BST.java for method specification */
        /* Hint: How can you "break-up" the problem into smaller pieces? */
        /* Your code here */
        //soooo what i want to do is check a smaller and smaller tree.
        //I want to call isFullTree_r() on smaller and smaller trees.
        //small tree will say if L&R tree are both !null,
        //  call recursive on both L&R
        // if both null, quit out.
        // at the end, if you havn't returned f, return t

        return isFullTree_r(this.root);
    }
        private boolean isFullTree_r( Node c) {
        //if neither have children
        if (c.getLeft() == null && c.getRight() == null) {
            //idk, congratulate yourself??
            //i need the code to not return but instead to continue on
            //to check more things but maybe it should somehow save that its good?
            return true;
        }
        //if both have children
        else if (c.getLeft() != null && c.getRight() != null) {
            return isFullTree_r(c.getLeft()) && isFullTree_r(c.getRight());
        }
        //otherwise, has only 1 child
            return false;
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
        if (this.root == null) {
            return 0;
        }
        int original_size = this.size();
        merge_r(nbt.getRoot());
        int final_size = this.size();
        return final_size - original_size;
    }
    //i want to pass in two nodes.
        //I will iterate over nbt and add it wherever necessary in og
    private void merge_r(Node c) {
        if (c == null){
            return;
        }
        if (this.contains(c.getValue())) {
            //skip and do not add
        }
        //if c should be added,
        else {
            this.insert(c.getValue());
        }
        merge_r(c.getLeft());
        merge_r(c.getLeft());
    }


    public int getMaxLeafHeightDiff () {
        //MY PLAN: 1. take height of tree
        //2. take smaller and smaller trees until you get to a leaf,
            //3. when you find a leaf, see how many back you've traveled?
            //4. this would require adding to a variable every time you have recursed again
                //this variable cannot be initiated inside the recursion though,
        //      //bc it will then be reinitiated every recursion and cannot count the recursion
        /*See BST.java for method specification */
        /* Hint: Which of the methods you're given are most similar to this? */
        /* Your code here */
        if (this.root == null) {
            return 0;
        } int max = this.height();
        //so i want to find the smallest height between each of the leaves???
        //my recursive function should return an int
        //  then this fn should make it the min only if it is less than the min already.
        int min = getMaxLeafHeightDiff_r(this.root);
        return max-min;
    }
    private int getMaxLeafHeightDiff_r(Node c) {
        //so we want to find the shallowest leaf,
            //aka the leaf which has two null children FIRST
        //when we have found the shallowest leaf,
        //  we want a count of the depth of that leaf
        //if (c.getLeft() == null && c.getRight() == null) {
            //should be returning the node, no? but intellij won't let me
            //return c.getValue();
    //}
        if (c==null) return -1;
        int lht = getMaxLeafHeightDiff_r(c.getLeft());
        int rht = getMaxLeafHeightDiff_r(c.getRight());
        return Math.min(lht,rht) + 1;
}}
