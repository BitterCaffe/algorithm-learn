package com.caffe.algorithm.learn.tree;

import java.util.Stack;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description:
 */
public class TreeStack {

    /**
     * 前序遍历： 根左右
     * 整个遍历过程要搞定节点的打印、入栈、出栈的过程
     *
     * @param node
     */
    public void printBefore(TreeBin.TreeNode node) {
        Stack<TreeBin.TreeNode> stack = new Stack<>();
        //首节点
        TreeBin.TreeNode treeNode = node;
        while (treeNode != null || !stack.empty()) {

            while (treeNode != null) {
                //前序遍历，所以按照顺序打印
                System.out.println("前序节点node.data=" + treeNode.data);
                //将当前根节点入栈，当前节点开始又是一棵树，还是要按照根左右的顺序来处理
                stack.push(treeNode);
                //当前树的根结点作为树根节点，而新树则是左节点作为树的根节点
                treeNode = treeNode.left;
            }
            //上面都是打印根节点，这里每次出栈获取的都是上面存储的树的根节点，也就是每次遍历的右子树
            //走到这边就是当前节点的右节点为空，所以需要向上移动，也就是树的节点向上移动，获取树的右节点这样就能遍历右子树
            if (!stack.empty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }




    /**
     * 前序遍历：跟左右
     *
     * @param node
     */
    public void printBefore1(TreeBin.TreeNode node) {

    }


    /**
     * 中序遍历：左根右
     * 1、获取根节点
     * 2、获取根节点左节点，因为遍历的过程就是左根有这样的顺序。然后入栈直到左节点为空
     * 3、左节点为空则开始出栈打印栈信息
     * 4、走到出栈这一步说明左节点已经打印了，需要遍历右节点。
     *
     * @param treeNode
     */
    public void printMid(TreeBin.TreeNode treeNode) {
        Stack<TreeBin.TreeNode> stack = new Stack<>();
        while (null != treeNode || !stack.isEmpty()) {
            while (null != treeNode) {
                //中序遍历中根节点在中间也就是栈底的这个元素
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            //栈的入栈，出栈，其中添加都是根节点，当所有的根节点
            if (!stack.isEmpty()) {
                TreeBin.TreeNode treeNode1 = stack.pop();
                System.out.println("中序遍历node.data=" + treeNode1.getData());
                treeNode = treeNode1.right;
            }
        }
    }
}
