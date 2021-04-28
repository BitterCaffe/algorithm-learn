package com.caffe.algorithm.learn.treeall;

import com.caffe.algorithm.learn.tree.TreeBin;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description: TODO
 */
public class TreeBinTest {
    public static void main(String[] args) {
        TreeBin treeBin = new TreeBin();
        TreeBin.TreeNode treeNode = new TreeBin.TreeNode(3);
        TreeBin.TreeNode treeNode1 = new TreeBin.TreeNode(9);
        TreeBin.TreeNode treeNode2 = new TreeBin.TreeNode(20);
        TreeBin.TreeNode treeNode3 = new TreeBin.TreeNode(15);
        TreeBin.TreeNode treeNode4 = new TreeBin.TreeNode(7);


        treeNode.setLeft(treeNode1);
        treeNode.setRight(treeNode2);
        treeNode2.setLeft(treeNode3);
        treeNode2.setRight(treeNode4);

        //前序遍历
        System.out.println("前序begin*******************");
        treeBin.printBeforeV1(treeNode);
        System.out.println("前序end*********************");
        System.out.println("中序begin********************");
        treeBin.printMid(treeNode);
        System.out.println("中序end**********************");
        System.out.println("后续begin*********************");
        treeBin.printAfter(treeNode);
    }
}
