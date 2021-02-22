package com.caffe.algorithm.learn.skiplist;

import java.util.Random;

/**
 * @author BitterCaffe
 * @date 2020/12/2
 * @description: https://zhuanlan.zhihu.com/p/200815425
 */
public class SkipList {
    //结点“晋升”的概率
    private static final double PROMOTE_RATE = 0.5;
    //这里的头节点，尾节点是最底层的链表
    //新增节点是在head、tail之间添加，head、tail是有默认值的
    //如果新增加层则第一层的down是从第一层指向head即链表而up是从链表指向第一层
    private Node head, tail;
    private int maxLevel;

    public SkipList() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }


    /**
     * 查找结点
     *
     * @param data
     * @return
     */
    public Node search(int data) {
        Node p = findNode(data);
        if (p.data == data) {
            System.out.println("找到结点：" + data);
            return p;
        }
        System.out.println("未找到结点：" + data);
        return null;
    }


    /**
     * 找到值对应的前置结点
     * 找到的值就是第0层数据，先从head——》tail search, then  up->down 查找
     *
     * @param data
     * @return
     */
    private Node findNode(int data) {
        //其实每次进来之后head就是最上层的头节点
        //每次都是从头节点开始查找插入节点的前置节点即把他小的最后一个节点
        //跳表中第0层数据是有序的
        Node node = head;

        while (true) {
            //在链表查找合适节点
            //查找最后一个比data小的节点
            while (node.right.data != Integer.MAX_VALUE && node.right.data <= data) {
                node = node.right;
            }
            //从上层寻找下一层，如果只有一层即第0层没有上层则返回的就是第一层的节点
            if (node.down == null) {
                break;
            }
            node = node.down;
        }
        return node;
    }

    /**
     * 插入结点
     *
     * @param data
     */
    public void insert(int data) {
        //查找插入节点前一个节点，这个节点的位置是最底层的链表
        Node preNode = findNode(data);
        //如果data相同，直接返回
        if (preNode.data == data) {
            return;
        }

        Node node = new Node(data);
        //这里的添加只是在找到的节点后添加节点，添加过程是有序的。没有上下层之间的关系
        appendNode(preNode, node);
        int currentLevel = 0;
        //随机决定结点是否“晋升”
        Random random = new Random();
        while (random.nextDouble() < PROMOTE_RATE) {
            //如果当前层已经是最高层，需要增加一层
            //如果是第0层则需要添加一层，head、tail节点指向新添加节点的head和tail
            if (currentLevel == maxLevel) {
                addLevel();
            }
            //找到上一层的前置节点
            //找到有上层的节点，如果只有0层则这个节点只能是tail
            while (preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            //把“晋升”的新结点插入到上一层
            Node upperNode = new Node(data);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node.up = upperNode;
            node = upperNode;
            currentLevel++;
        }
    }


    /**
     * 在前置结点后面添加新结点
     *
     * @param preNode
     * @param newNode
     */
    private void appendNode(Node preNode, Node newNode) {
        //双向链表
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }


    /**
     * 增加一层
     */
    private void addLevel() {
        maxLevel++;
        Node p1 = new Node(Integer.MIN_VALUE);
        Node p2 = new Node(Integer.MAX_VALUE);
        p1.right = p2;
        p2.left = p1;
        p1.down = head;
        head.up = p1;
        p2.down = tail;
        tail.up = p2;
        head = p1;
        tail = p2;
    }


    /**
     * 删除结点
     *
     * @param data
     * @return
     */
    public boolean remove(int data) {
        Node removedNode = search(data);
        if (removedNode == null) {
            return false;
        }

        int currentLevel = 0;
        while (removedNode != null) {
            //双向链表删除，search查找到的是第0层的节点
            removedNode.right.left = removedNode.left;
            removedNode.left.right = removedNode.right;
            //如果不是最底层，且只有无穷小和无穷大结点，删除该层
            if (currentLevel != 0 && removedNode.left.data == Integer.MIN_VALUE && removedNode.right.data == Integer.MAX_VALUE) {
                removeLevel(removedNode.left);
            } else {
                currentLevel++;
            }
            removedNode = removedNode.up;
        }

        return true;
    }


    /**
     * 删除一层
     *
     * @param leftNode
     */
    private void removeLevel(Node leftNode) {
        Node rightNode = leftNode.right;
        //如果删除层是最高层
        if (leftNode.up == null) {
            leftNode.down.up = null;
            rightNode.down.up = null;
        } else {
            leftNode.up.down = leftNode.down;
            leftNode.down.up = leftNode.up;
            rightNode.up.down = rightNode.down;
            rightNode.down.up = rightNode.up;
        }
        maxLevel--;
    }


    /**
     * 输出底层链表
     */
    public void printList() {
        Node node = head;
        while (node.down != null) {
            node = node.down;
        }
        while (node.right.data != Integer.MAX_VALUE) {
            System.out.print(node.right.data + " ");
            node = node.right;
        }
        System.out.println();
    }

    /**
     * 链表数据结构
     */
    public class Node {
        public int data;
        //跳表结点的前后和上下都有指针
        public Node up, down, left, right;

        public Node(int data) {
            this.data = data;
        }
    }


    /**
     * add
     *
     * @param args
     */
    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.insert(50);
        list.insert(15);
//        list.insert(13);
//        list.insert(20);
//        list.insert(100);
//        list.insert(75);
//        list.insert(99);
//        list.insert(76);
//        list.insert(83);
//        list.insert(65);
        list.printList();
        list.search(50);
        list.remove(50);
        list.search(50);
    }
}
