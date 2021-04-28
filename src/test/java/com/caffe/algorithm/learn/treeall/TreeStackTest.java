package com.caffe.algorithm.learn.treeall;

import com.caffe.algorithm.learn.tree.TreeBin;
import com.caffe.algorithm.learn.tree.TreeStack;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description: 非递归方式实现遍历
 */
public class TreeStackTest {

    public static void main(String[] args) {
        TreeStackTest treeStackTest = new TreeStackTest();

        TreeBin.TreeNode treeNode = new TreeBin.TreeNode(3);
        TreeBin.TreeNode treeNode1 = new TreeBin.TreeNode(9);
        TreeBin.TreeNode treeNode2 = new TreeBin.TreeNode(20);
        TreeBin.TreeNode treeNode3 = new TreeBin.TreeNode(15);
        TreeBin.TreeNode treeNode4 = new TreeBin.TreeNode(7);

        treeNode.setLeft(treeNode1);
        treeNode.setRight(treeNode2);
        treeNode2.setLeft(treeNode3);
        treeNode2.setRight(treeNode4);


//        treeStackTest.printBefore(treeNode);
        treeStackTest.printMid(treeNode);
    }


    public void printBefore(TreeBin.TreeNode treeNode) {
        TreeStack treeStack = new TreeStack();
        treeStack.printBefore1(treeNode);
    }


    public void printMid(TreeBin.TreeNode treeNode) {
        TreeStack treeStack = new TreeStack();
        treeStack.printMid(treeNode);
    }

}
