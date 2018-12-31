package avl.util;

import avl.AVLTree;
import avl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeToStrings {

    final private AVLTree<?> tree;

    public static String toTree(AVLTree<?> tree) {
        return new TreeToStrings(tree).formatVertically(tree.getRoot(),"",false);
    }

    public static String toOutline(AVLTree<?> tree) {
        return new TreeToStrings(tree).formatVertically();
    }

    private TreeToStrings(AVLTree<?> tree) {
        this.tree = tree;
    }

    private String spacing(int level) {
        String ans = "";
        for (int i = 0; i < level; ++i) {
            ans = ans + "  ";
        }
        return ans;
    }

    /**
     * Formats the tree with indentation to show level.
     * This is NOT the way you see trees in a text, but it is
     * common to show nested and recursive structures this way.
     *
     * @return a String representing this node and below
     */
    private String formatVertically() {
        if (tree.getRoot() == null)
            return "";
        else {
            Queue<TreeNode<?>> q = new LinkedList<>();
            q.add(tree.getRoot());
            q.add(null);
            int level = 0;
            String ans = "";
            while (!q.isEmpty()) {
                TreeNode<?> element = q.poll();
                if (element == null) {
                    q.add(null);
                    if (q.peek()==null){
                        return ans;
                    }
                    level++;
                    continue;
                }
                ans = ans + spacing(level);
                ans = ans + element.value.toString() + "\n";
            }

            return ans;
        }
    }

    private int treeHeight()
    {
        if (tree.getRoot()==null) {
            return -1;
        }
        return treeHeight(tree.getRoot());
    }

    private int treeHeight(TreeNode<?> root){
        if (root==null)
            return -1;
        return Math.max(treeHeight(root.left)+1,treeHeight(root.right)+1);
    }

    private static String center(String s, int width) {
        if (s.length() > width) {
            throw new Error("String  " + s + " already longer than " + width);
        }
        String ans = s;
        boolean left = true;
        while (ans.length() < width) {
            if (left)
                ans = " " + ans;
            else
                ans = ans + " ";
            left = !left;
        }
        return ans;
    }

    /**
     * Returns a representation of the tree in the form one usually sees.
     * It looks like a tree with the root centered on the first line.
     *
     * @return the tree as a tree
     */
    public String formatVertically(TreeNode<?> root, String prefix, boolean left) {
        String pre = "";
        if (root == tree.getRoot()) {
            pre = "Root-";
        } else {
            pre = (left ? "L-" : "R-");
        }
        String ans = "";
        if (root == null) {
            //System.out.println(prefix + pre + " <null>");
            return ans;
        }
        ans+=prefix + pre+root.toString()+"\n";
        ans+=formatVertically(root.left, prefix + "|  ", true);
        ans+=formatVertically(root.right, prefix + "|  ", false);
        return ans;
    }

    //prints it out as a tree by doing a breadth first search
    public String formatAsTree() {
        Queue<TreeNode<?>> q = new LinkedList<>();
        TreeNode special = new TreeNode<>(1);
        String ans = "";
        q.add(tree.getRoot());
        q.add(special);
        String line = "";
        int level = treeHeight();
        String space = space(level);
        
        while(!q.isEmpty()){
            TreeNode<?> t = q.poll();
            if(t==special){
                ans+=line+"\n";
                line="";
                level-=1;
                space = space(level);
                q.add(special);
                if (q.peek()==special)
                    break;
                continue;
            }
            if (t!=null){
                line += space + t.value.toString() + space;
                q.add(t.left);
                q.add(t.right);
            }
            else {
                line += space + "NIL" + space;
            }
        }
        return ans;
    }

    public String space(int level){
        String ans = "";
        for (int i = 0; i < 2<<level; i++){
            ans+=" ";
        }
        return ans;
    }
}
