package com.caffe.algorithm.learn.lists;

/**
 * @author BitterCaffe
 * @date 2020/12/6
 * @description: 单链表, 单链表数据结构定义，单链表数据结构的简单操作
 */
public class SinglyList {
    private Node head;
    private int size;

    /**
     * 单链表末尾添加
     *
     * @param node
     */
    public void add(Node node) {
        size++;
        //如果头节点为空则新节点做头节点
        if (head == null) {
            head = node;
            return;
        }
        //添加节点到尾部，这种添加的时间复杂度为o(n),空间复杂度为o(1)
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void addv1(Node node) {
        if (null == node) {
            return;
        }

        if (null == head) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }


    /**
     * 单链表指定位置添加，主要是获取前一节点就OK
     *
     * @param node
     * @param index
     */
    public void add(Node node, int index) {
        //
        if (index < 1 || index > size + 1) {
            return;
        }
        //添加到头结点
        if (index == 1) {
            //head变量所引用对象赋值
            node.next = head;
            //引用变量赋值
            head = node;
            size++;
            return;
        }
        //遍历指定位置添加
        Node cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;
    }

    public void addV1(Node node, int index) {
        if (null == node || index < 1) {
            return;
        }
        if (index > size) {
            return;
        }
        //头结点添加
        if (index == 1) {
            //注意这里的head表示的是head所引用的对象在堆内存中的地址或句柄池中的句柄
            node.next = head;
            //这里的head表示的是引用变量，赋值和上面一样是node在堆内存中的地址或句柄池中的句柄
            head = node;
        }
        Node cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        //等号右侧是赋值引用变量对应的内存地址或句柄池中的句柄
        node.next = cur.next;
        //等号左侧就是一个引用变量
        cur.next = node;
    }

    /**
     * 单链表获取尾节点
     *
     * @return
     */
    public Node get() {
        Node e = head;
        for (; e != null; e = e.next) {

        }
        return e;
    }

    /**
     * 单链表获取指定位置节点
     *
     * @param index
     * @return
     */
    public Node get(int index) {
        if (index < 1 || index > size) {
            return null;
        }

        Node cur = head;
        for (int i = 1; i < index; i++) {
            cur = head.next;
        }
        return cur;
    }


    /**
     * 单向链表的删除
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 1 || index > size) {
            return;
        }
        if (1 == index) {
            //head不是引用变量而是引用变量对应的堆内存地址对象
            Node cur = head;
            //这里的head就是是引用变量存储堆内存地址或句柄池地址
            head = cur.next;
            cur.next = null;
            return;
        }
        //找到前置节点
        Node pre = head;
        for (int i = 1; i < index - 1; i++) {
            pre = head.next;
        }
        //找到要删除节点
        Node cur = pre.next;
        pre.next = cur.next;
        cur.next = null;

    }


    /**
     * 引用变量、堆内存地址对象
     *
     * @param index
     */
    public void remove2(int index) {
        if (index < 1 || index > size) {
            return;
        }
        if (index == size) {
            return;
        }
        if (index == 1) {
            Node cur = head;
            head = cur.next;
            cur.next = null;
            cur = null;
            return;
        }
        //从第二个节点开始
        Node pre = head;
        for (int i = 1; i < index - 1; i++) {
            pre = head.next;
        }
        //这样写其实也删除了节点但是cur节点还引用了pre.next.next节点心里膈应
        // 1_2_3;1_3
        Node cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
        cur = null;
        //这样的操作会释放第一个节点和第二个节点之间的引用，但是不释放第二个和第三个节点之间的引用，这个不太好，有可能会一直占用内存
//        pre.next = pre.next.next;
        //上面已经改变了引用关系所以这样直接修改了值而没有释放关系，如果要释放关系则要在定义一个应用变量来处理
//        pre.next.next = null;
//        pre.next = null;

    }

    /**
     * 单链表按照节点获取节点
     *
     * @param node
     * @return
     */
    public Node get(Node node) {
        if (null == node) {
            return null;
        }
        if (node == head) {
            return head;
        }
        Node cur = head;
        while ((cur = cur.next) != null) {
            if (cur == node) {
                break;
            }
        }
        return cur;
    }

    public void printNode() {
        for (Node e = head; e != null; e = e.next) {
            System.out.println("e.value=" + e.value);
        }
    }


    /**
     * 链表结构
     */
    static class Node {
        int value;

        Node next;

        Node(int value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        SinglyList singlyList = new SinglyList();
        Node node = new Node(4);
        singlyList.addv1(new Node(1));
        singlyList.add(new Node(2));
        singlyList.add(node);
        singlyList.add(new Node(5));
        System.out.println(singlyList.size);
        singlyList.printNode();
        System.out.println("node get value:" + singlyList.get(node).value);
        //删除
        singlyList.remove2(3);
        singlyList.printNode();
    }
}
