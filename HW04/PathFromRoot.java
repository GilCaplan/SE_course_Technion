public class PathFromRoot {

    /**
     * Finds if a path exists in the tree for the string str
     * @param root the first node in the tree
     * @param str is the string that we're checking
     * @return true if we found a path that matches the string otherwise false
     */
    public static boolean doesPathExist(BinNode<Character> root, String str) {
        //base cases
        if (str.isEmpty())
            return true;
        char check = str.charAt(0);
        if (root == null || check != root.getData())
            return false;
        if(str.length() == 1 && root.getData() == str.charAt(0))
            return true;
        else if(str.length() == 1)
            return false;

        char lastChar = str.charAt(1);
        if (str.length() == 2 && (lastChar == root.getLeft().getData() || lastChar == root.getRight().getData()))
            return true;

        //recursive calls
        check = str.charAt(1);
        if (check == root.getRight().getData())
            return doesPathExist(root.getRight(), str.substring(1));
        if (check == root.getLeft().getData())
            return doesPathExist(root.getLeft(), str.substring(1));
        return false;
    }
}
