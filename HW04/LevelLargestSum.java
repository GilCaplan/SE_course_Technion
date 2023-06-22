import java.util.ArrayDeque;

public class LevelLargestSum {
    /**
     *
     * @param root
     * @return
     */
    public static int getLevelWithLargestSum(BinNode<Integer> root) {
        if(root == null)
            return -1;
        ArrayDeque<BinNode<Integer>> currLevel = new ArrayDeque<>();
        ArrayDeque<BinNode<Integer>> prevLevel;
        if(root.getRight() != null)
            currLevel.add(root.getRight());
        if(root.getLeft() != null)
            currLevel.add(root.getLeft());
        int curLevelSum;
        int maxSum = root.getData();
        int maxSumLevel = 0, curLevel = 1;
        //maxSumLevel is the level with the highest sum, curLevel is the level we are currently on

        while(currLevel.size() > 0){//continue while we have another level in our tree
            //get sum of current level in a general way
            curLevelSum = 0;
            for(BinNode<Integer> node : currLevel)
                curLevelSum += node.getData();
            if(curLevelSum > maxSum){
                maxSum = curLevelSum;
                maxSumLevel = curLevel;
            }

            //then compare sums and updatemaxSum, level counter
            curLevel++;
            prevLevel = currLevel;//now becomes previous level
            currLevel = new ArrayDeque<>();
            for(BinNode<Integer> node : prevLevel){
                if(node.getRight() != null)
                    currLevel.add(node.getRight());
                if(node.getRight() != null)
                    currLevel.add(node.getLeft());
            }
        }
        return maxSumLevel;
    }
}
