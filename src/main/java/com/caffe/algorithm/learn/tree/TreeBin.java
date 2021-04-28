package com.caffe.algorithm.learn.tree;

import java.util.List;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description: TODO
 */
public class TreeBin {

    /**
     * 节点数据： 1-2
     * <p>
     * 树的添加也是一个前序添加方式即跟左右的形式。先生成跟节点在添加左节点，再添加右节点
     *
     * @param nodeList
     * @return
     */
    public static TreeNode addV1(List<Integer> nodeList) {
        //无节点则返回null
        if (null == nodeList || nodeList.size() < 1) {
            return null;
        }
        TreeNode node = null;
        //第一次获取1
        Integer data = nodeList.remove(0);
        if (null != data) {
            //第一次创建节点1
            //第二次创建节点2
            node = new TreeNode(data);
            //第一次递归
            //第二次递归，返回null。节点2的左节点空，节点2的右节点空，这样就生成了完整的节点2，会返回节点2
            //node2.left = null,node2.right  = null ,node2= 2;
            //出栈到第一个节点，返回的节点为node2所以node1.left = node2,因为已经有两个节点生成所以右节点值为null
            //node1.right = null
            node.left = addV1(nodeList);
            node.right = addV1(nodeList);
        }
        return node;

    }


    /**
     * 打印，按照前序、中序、后续遍历很简单，就是按照不同顺序递归调用
     * 在整个遍历中要注意传入的参数，以及其中的左节、右节点的信息
     * 递归方式实现前序、中序、后续三种方式，流程按照遍历顺序走就OK……
     * 前序：第一个节点为跟节点
     * 中序：根节点为中间节点
     * 后续：跟节点为最后一个节点
     */
    public void printTreeBefore(TreeNode node) {
        if (null == node) {
            return;
        }
        //递归可以理解为栈帧入栈的过程分析，每一个栈帧是静态的。
        System.out.println("前序遍历node.data=:" + node.data);
        //作为上一节点的左节点，在当前是左子树的跟节点，当左节点深入后左节点对应的树以及根节点，有节点都有了！
        printTreeBefore(node.left);
        //每次执行该方法时，都会先进行左节点的计算，很有可能导致根节点的right方法一直无法执行
        //递归是先入栈，入栈达到条件之后开始出栈，出栈要么是return要么是执行结束！
        printTreeBefore(node.right);
    }

    /**
     * 前置遍历：根左右
     * 前置遍历都是：根左右，每次都是打印根节点，在获取根节点的左子树作为一颗树来处理，这样其实就是一个递归的过程
     * 在原来节点他是作为左节点或右节点，而在新的树中他是作为根节点。他们的遍历需要递归实现，所以整个实现中都使用了递归调用！
     * 根节点、左子树、右子树，按照这样的遍历顺序
     *
     * @param treeNode
     */
    public void printBeforeV1(TreeNode treeNode) {
        //一进来就是一棵树的根节点，所以按照遍历顺序打印
        if (null == treeNode) {
            return;
        }
        System.out.println("前置遍历node.data=" + treeNode.getData());
        printBeforeV1(treeNode.left);
        printBeforeV1(treeNode.right);
    }

    /**
     * 中序方式打印
     * 左根右，对于一个方法来说一个方法就是一个遍历过程，在这里使用递归的方式遍历刚好满足按顺序遍历的要求
     *
     * @param node
     */
    public void printMid(TreeNode node) {
        if (null == node) {
            return;
        }
        //根节点的左子树，左子树中的根节点，根节点中的左子树，递归调用左子树，然后是每个子树中的根节点，然后是右子树。
        //整个调用过程就是递归调用的过程。根节点左子树，左子树的根节点，根节点开始的左子树，左子树的左子树依次递归调用。
        printMid(node.left);
        System.out.println("中序遍历node.data=" + node.data);
        printMid(node.right);
    }

    /**
     * 中序遍历：左根右
     *
     * @param treeNode
     */
    public void printMidV1(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        //以当前节点为树root节点
        printMidV1(treeNode.left);
        System.out.println("中序遍历node.data=" + treeNode.getData());
        printMidV1(treeNode.right);
    }


    /**
     * 后续：左右根，树的打印是按照就是一个递归的方式打印，不论你是用什么方式遍历的都是一个递归的遍历过程所以我们在方法中要注意遍历停止条件和遍历的过程
     * 其实递归的方式也是栈，因为每一次方法的调用对应着一次栈帧的入栈,而方法的结束对应着一次出栈……
     *
     * @param node
     */
    public void printAfter(TreeNode node) {
        if (null == node) {
            return;
        }
        printAfter(node.left);
        printAfter(node.right);
        System.out.println("后续遍历node.data=" + node.data);
    }

    /**
     * 后续遍历： 左右根
     */
    public void printAfterV1(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        printAfterV1(treeNode.left);
        printAfterV1(treeNode.right);
        System.out.println("后续遍历根节点node.data=" + treeNode.getData());
    }

    /**
     * 节点结构
     */

    public static class TreeNode {
        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        int data;

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        TreeNode left;

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

}
