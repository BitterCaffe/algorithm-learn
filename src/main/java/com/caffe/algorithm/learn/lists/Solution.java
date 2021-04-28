package com.caffe.algorithm.learn.lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BitterCaffe
 * @date 2020/12/3
 * @description: 单链表相关算法
 * 1、单链表数据结构：链表和节点，单链表只有head节点而链表中的节点只有next和value属性
 * 2、单链表的操作：一般使用双指针就能搞定，也叫快慢指针。
 * 3、前置节点、当前节点、后置节点三个节点有了之后一般问题都能解决
 * 4、如果需要前置节点则可以定义一个虚节点作为前置节点
 */
public class Solution {

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //前一个节点
        ListNode pre = null;
        //当前节点
        ListNode cur = head;
        //后一个节点
        ListNode next = head.next;
        //这里实现有两个思路,第一种就是断开第一个节点的next，并将所有队列的节点向前移动一个，这样cur有了前置节点，并将cur的前置设置为前节点
        //第二种就是直接设置前节点然后整体向前移动一位，这样就能实现反转
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
    }


    /**
     * 第一种思路先断开，在后移，在设置
     * 第二中思路就是三个节点、设置当前节点的前置节点后所有节点后移，直到next==null
     *
     * @param head
     * @return
     */
    public static ListNode solutionV1(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while (cur != null) {
            //设置前置
            cur.next = pre;
            //位移
            pre = cur;
            //位移
            cur = next;
            if (null != next) {
                next = next.next;
            }
        }
        return pre;
    }


    /**
     * 链表反转
     * 链表反转也就是赋值、位移、位移的方式实现……
     *
     * @param head
     * @return
     */
    public static ListNode solutionV2(ListNode head) {
        if (head == null || null == head.next) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
    }


    /**
     * 指定值删除
     * 删除两种思路：第一先把头结点相等的删除，直到节点不相等，在用头节点作为前置节点进行删除
     *
     * @param head
     * @param value
     * @return
     */
    static ListNode nodeRemove(ListNode head, int value) {
        //如果头节点为空则直接返回
        if (null == head) {
            return head;
        }
        //不使用虚节点直接替换，这里使用while循环能删除所有值相等的头结点
        while (head != null && head.val == value) {
            head = head.next;
        }
        ListNode ptr = head;
        //结束、值相等
        while (ptr != null && ptr.next != null) {
            //这里这个判断和上面while循环中一样，如果有一直相等的节点则会全部删除
            if (ptr.next.val == value) {
                ptr.next = ptr.next.next;
            } else {
                //如果没有则向下移动
                ptr = ptr.next;
            }
        }
        return head;
    }


    /**
     * 添加虚节点删除
     *
     * @param head
     * @param value
     * @return
     */
    static ListNode nodeRemove1(ListNode head, int value) {
        if (null == head) {
            return head;
        }
        //堆内存创建了一个对象并设置对象next属性为head
        ListNode dump = new ListNode(0);
        dump.next = head;

        //重新引用节点关系,做遍历使用
        ListNode pre = dump;
        ListNode cur = pre.next;
        while (cur != null) {
            if (cur.val == value) {
                //这里改变了对象链表的引用关系，如果当前节点的值一致相等则会一直删除直到不相等
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dump.next;
    }


    /**
     * 压缩列表，奇数位重复次数，偶数位具体值
     * 解压缩数据返回
     *
     * @param nums
     * @return
     */
    static int[] listsZip(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        //计算新数组长度，奇数位重复次数，偶数位具体值
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        int[] newArr = new int[len];
        int ptr = 0;
        //遍历数组
        for (int i = 0; i < nums.length; i = i + 2) {
            //按照具体重复数遍历
            for (int j = 0; j < nums[i]; j++) {
                //奇数位重复次数，偶数位具体值
                newArr[ptr++] = nums[i + 1];
            }
        }
        return newArr;
    }

    /**
     * 压缩列表，一个数组中奇数位是重复个数，偶数位是具体值
     *
     * @param nums
     * @return
     */
    static int[] listZip(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return nums;
        }
        //计算压缩列表长度,奇数位
        int len = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            len += nums[i];
        }
        //遍历获取值
        int[] arr = new int[len];
        int count = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            int length = nums[i];
            int value = nums[i + 1];
            for (int j = 0; j < length; j++) {
                arr[count++] = value;
            }
        }
        return arr;
    }


    /**
     * 使用递归的方式动态规划计算
     * 递归调用从性能上来说不太友好，因为需要创建栈帧
     *
     * @param n
     * @return
     */
    static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 使用计算公式代替数据
     * 1、使用递归的比较少，因为递归会有入栈、出栈问题，如果栈比较深则存在oom问题所以栈是一种思路
     * 2、吧递归方式转化为公式来计算即进行数据计算
     * 3、这里除以 1000000007 是作为一个特殊的质数，如果相加后取余则不会超出int，如果相乘在取余则不会超出long
     *
     * @param n
     * @return
     */
    static int intFib(int n) {

        if (n < 2) {
            return n;
        }
        int a = 0;
        int b = 1;
        int sum = 0;
        //使用公式替换了递归操作
        for (int i = 1; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    static int intArr(int n) {
        if (n < 2) {
            return n;
        }
        int[] arr = new int[3];
        arr[0] = 0;
        arr[1] = 1;
        //递归是从大到小计算，而这种方式是从小到大计算，注意最后返回的值和边界就OK
        for (int i = 1; i < n; i++) {
            arr[2] = ((arr[0] + arr[1])) % 1000000007;
            arr[0] = arr[1];
            arr[1] = arr[2];
        }
        return arr[2];
    }


    /**
     * 列表、数组输出
     * 传入列表，列表被转化为数组输出
     * 数组和列表数据是反向的，所以这里我们可以使用反向数据来存储
     *
     * @param head
     * @return
     */
    static int[] reversePrint(ListNode head) {
        //如果为空输出空数组
        if (head == null) {
            return new int[]{};
        }
        //如果只有一个节点则输出长度为1的数组
        if (head.next == null) {
            return new int[]{head.val};
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;

        //计算列表元素个数，并做反向转化
        int len = 0;
        while (null != cur) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (null != next) {
                next = next.next;
            }
            len += 1;
        }
        //列表反转，顺序输出
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = pre.val;
            pre = pre.next;
        }
        return arr;
    }


    /**
     * 顺序遍历，数据反向填充
     *
     * @param head
     * @return
     */
    static int[] reversePrintOrder(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] arr = new int[len];
        for (int i = len; i > 0; i--) {
            arr[i - 1] = head.val;
            head = head.next;
        }
        return arr;
    }


    /**
     * 两个链表有序合并
     * 简单、链表递归就可以了
     *
     * @param l1
     * @param l2
     * @return
     */
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //添加虚拟头节点
        ListNode dump = new ListNode(0);
        //引用虚拟节点
        ListNode cur = dump;
        //遍历两个链表，小的作为cur虚节点的next;这里注意对象和引用变量，其实jvm堆内存中对象大小个数没有改变，改变的只是虚结点以及后面节点的引用关系
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                //这种直接赋值的方式破坏了之前的列表的结构，如果不想改变则可以每次新建一个节点
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        //新链表头节点
        return dump.next;
    }


    /**
     * @param l1
     * @param l2
     * @return 两个链表顺序合并, 上面的方式改变了之前链表的数据结构，这里使用不改变的方式
     */
    static ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode empty = new ListNode(0);
        ListNode cur = empty;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode listNode = new ListNode(l2.val);
                cur.next = listNode;
                l2 = l2.next;
            } else {
                ListNode listNode = new ListNode(l1.val);
                cur.next = listNode;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 == null ? l2 : l1);
        return empty.next;
    }

    /**
     * 链表中倒数第几个值开始的链表
     * 倒数第几个节点或节点之后的链表则可以使用快慢指针来实现，步长可以按照需求来设定
     *
     * @param head
     * @param k
     * @return
     */
    static ListNode getKthFromEnd(ListNode head, int k) {
        if (null == head || k < 1) {
            return null;
        }
        ListNode before = head;
        ListNode after = head;
        int index = 0;
        while (null != before) {
            before = before.next;
            if (index >= k) {
                after = after.next;
            }
            index++;
        }
        return after;
    }

    /**
     * 链表指定位置数据
     * 其实还是快慢指针的思路
     *
     * @param head
     * @param k
     * @return
     */
    static ListNode getKthFromEnd2(ListNode head, int k) {
        if (null == head || k < 1) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (null != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 堆内存对象，栈内存引用变量，引用变量操作
     *
     * @param head
     * @param k
     * @return
     */
    static ListNode getFromAndEnd(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        //引用变量
        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0) {
            //引用变量应用的堆内存中对象地址或句柄改变
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //这里的head引用变量、fast引用变量、slow引用变量他们都是栈内存中的局部变量，存储在局部变量表中
        //引用变量引用的具体的对象存储在堆内存中
        return slow;
    }

    /**
     * 递归是分两个步骤的即递和归，所谓的递就是入栈而归就是入栈结束后出栈
     * 虽然递归方式不太友好，但是这个思路就是牛逼
     *
     * @param head
     * @param k
     * @return
     */
    static int size0;

    static ListNode getKthFromEnd3(ListNode head, int k) {

        if (null == head) {
            return null;
        }
        ListNode node = getKthFromEnd3(head.next, k);
        size0++;
        //递归就是一个入栈和出栈的操作，而且顺序刚好相反了，所以满足这个题目要求
        if (size0 == k) {
            return head;
        }
        return node;
    }

    /**
     * 链表中对象相等的方式。1、求长度、走到相等在一起走 ；2、a-b,b-a
     *
     * @param headA
     * @param headB
     * @return
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        ListNode headA1 = headA;
        ListNode headB1 = headB;
        int sizea = 0;
        int sizeb = 0;
        while (headA1 != null) {
            sizea++;
            headA1 = headA1.next;
        }
        while (headB1 != null) {
            sizeb++;
            headB1 = headB1.next;
        }
        if (sizea > sizeb) {
            int index = sizea - sizeb;
            while ((index--) > 0) {
                headA = headA.next;
            }
        } else {
            int index = sizeb - sizea;
            while ((index--) > 0) {
                headB = headB.next;
            }
        }
        //比较对象，对象相等这里需要使用相同长度来校验
        while (headA != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        if (headA == null) {
            return null;
        }
        return headA;
    }

    /**
     * 两个链表中同一个节点对象的查找
     *
     * @param headA
     * @param headB
     * @return
     */
    static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //headA、headB 是值传递，也就是链表首节点地址
        //nodeA、nodeB 值传递，注意对象和应用变量关系
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        //如果两个链表中有相等的节点则两个链表连接起来长度相等则会有一个相等的点
        //如果没有相等的点则当两个链表都遍历结束之后两个节点会同时到达null则退出
        while (nodeA != nodeB) {
            nodeA = (nodeA == null ? headB : nodeA.next);
            nodeB = (nodeB == null ? headA : nodeB.next);
        }
        return nodeA;
    }


    /**
     * 删除指定元素在链表中的值
     *
     * @param head
     * @param val
     * @return
     */
    static ListNode removeElements(ListNode head, int val) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode ptr = dump;
        while (ptr.next != null) {
            if (ptr.next.val == val) {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
            }
        }
        return dump.next;
    }


    /**
     * 给定节点删除
     * 第一种方式：找到节点的前置节点，并进行单链表删除
     * 第二种方式：将当前节点的后置节点值赋值给当前节点并将后置节点删除
     *
     * @param node
     */
    static void deleteNode(ListNode node) {
        if (null == node) {
            return;
        }
        if (null == node.next) {
            node = null;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //两个有序链表合并
    static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode ptr = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ptr.next = l2;
                l2 = l2.next;
            } else {
                ptr.next = l1;
                l1 = l1.next;
            }
            ptr = ptr.next;
        }
        ptr.next = (l1 == null ? l2 : l1);
        return head.next;
    }


    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 判断列表是否有环
     * 第一种：时间复杂度o(n) 空间复杂度o(n) 通过遍历将节点存入集合中的方式
     * 第二种：使用快慢指针方式，如果指针结束或两个指针相遇则是环形的
     *
     * @param head
     * @return
     */
    static boolean hasCycle(ListNode head) {
        //使用快慢节点，如果快慢节点能重合则说明是环形的，否则会到终点
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    /**
     * 回文链表判断
     * 第一种：时间复杂度o(n) 空间复杂度o(n) 使用链表的方式存储
     * <p>
     * 第二种：时间复杂度o(n) 空间复杂度o(1) 使用后半部分链表反转实现
     *
     * @param head
     * @return
     */
    static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode ptr = head;
        int last = 0;
        int start = 0;
        //获取链表大小
        while (ptr != null) {
            list.add(ptr.val);
            ptr = ptr.next;
            last++;
        }
        last--;
        //从头开始，从尾开始
        while (start < last) {
            //不相等则不是回文数字
            if (!list.get(start).equals(list.get(last))) {
                return false;
            }
            start++;
            last--;
        }
        return true;
    }

    /**
     * 回文数判断
     *
     * @param head
     * @return
     */
    static boolean isPalindrome11(ListNode head) {
        if (null == head) {
            return false;
        }
        List<Integer> list = new ArrayList<>(8);
        int last = 0;
        int start = 0;
        ListNode ptr = head;
        while (ptr != null) {
            list.add(ptr.val);
            ptr = ptr.next;
            last++;
        }
        //数组存储原因
        last--;
        while (start <= last) {
            if (!list.get(start).equals(list.get(last))) {
                return false;
            }
            start++;
            last--;
        }
        return true;
    }


    /**
     * 第二种回文判断
     *
     * @param head
     * @return
     */
    static boolean isPalindrome1(ListNode head) {
        if (null == head) {
            return true;
        }
        if (null == head.next) {
            return true;
        }
        ListNode beforeList = getMiddleNode(head);
        ListNode afterList = receiveNode(beforeList.next);
        if (null == afterList) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = afterList;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //是回文
        beforeList.next = receiveNode(afterList);
        return true;

    }

    /**
     * 回文校验
     *
     * @return
     */
    static boolean isPalindrome12(ListNode head) {
        if (null == head) {
            return false;
        }
        //中间节点
        int middle = 0;
        ListNode count = head;
        while (count != null) {
            middle++;
            count = count.next;
        }
        ListNode middlePre = head;
        middle = middle / 2;
        for (int i = 0; i < middle - 1; i++) {
            middlePre = middlePre.next;
        }

        //反转
        ListNode pre = null;
        ListNode cur = middlePre.next;
        ListNode next = cur.next;
        ListNode f = null;
        //链表反转
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }

        f = pre;
        ListNode p1 = head;
        while (f != null) {
            if (f.val != p1.val) {
                return false;
            }
            f = f.next;
            p1 = p1.next;
        }

        //原始链表反转
        return true;
    }


    /**
     * 中间节点获取
     *
     * @param listNode
     * @return
     */
    static ListNode getMiddleNode(ListNode listNode) {
        //使用双指针判断
        ListNode slow = listNode;
        ListNode fast = listNode.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 节点反转
     *
     * @param listNode
     * @return
     */
    static ListNode receiveNode(ListNode listNode) {
        ListNode pre = null;
        ListNode cur = listNode;
        ListNode next = listNode.next;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        // 反转
//        ListNode res = solution.solutionV1(l1);
//        while (res != null) {
//            System.out.println(res.val);
//            res = res.next;
//        }

        //指定节点删除，可以将链表中的所有相等节点删除
//        ListNode listNode = nodeRemove(l1, 2);
//        while (listNode != null) {
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }

        //双指针
//        ListNode listNode = nodeRemove1(l1, 1);
//        while (listNode != null) {
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }

        //压缩值
//        int[] zipList = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
//        int[] arr = listsZip(zipList);
//        for (int i : arr) {
//            System.out.println(i);
//        }

        //fib 递归实现
//        long begin1 = System.currentTimeMillis();
//        int fibRes = fib(20);
//        System.out.println("fibRes=" + fibRes + " " + (System.currentTimeMillis() - begin1));
//        long begin2 = System.currentTimeMillis();
//        int intFibRes = intFib(45);
//        System.out.println("intFibRes=" + intFibRes + "  " + (System.currentTimeMillis() - begin2));
//        long begin3 = System.currentTimeMillis();
//        int arrRes = intArr(10);
//        System.out.println("arrRes=" + arrRes + "  " + (System.currentTimeMillis() - begin3));

        // 列表反转输出
//        int[] arr = reversePrint(l1);
//        for (int i : arr) {
//            System.out.println(i);
//        }
        // 列表反转输出
//        int[] arr1 = reversePrintOrder(l1);
//        for (int i : arr1) {
//            System.out.println(i);
//        }

        //链表合并
//        ListNode l11 = new ListNode(1);
//        ListNode l22 = new ListNode(2);
//        l11.next = l22;
//        ListNode head = mergeTwoLists(l1, l11);
//        ListNode head = mergeList(l1, l11);
//
//
//        while (null != head) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//
//        while (null != l1) {
//            System.out.println("l1=" + l1.val);
//            l1 = l1.next;
//        }

        //链表中的导数第几个开始的后面的链表值
//        ListNode head = getKthFromEnd3(l1, 2);
//        while (null != head) {
//            System.out.println(head.val);
//            head = head.next;
//        }
        // 两个数组大小相等
//        ListNode l11 = new ListNode(1);
//        ListNode l22 = new ListNode(2);
//        l11.next = l22;
//        ListNode node = getIntersectionNode(l1, l2);
//        if (null == node) {
//            System.out.println("null");
//        } else {
//            System.out.println(node.val);
//        }

        //链表中求相等的节点
//        ListNode res = getIntersectionNode2(l1, l2);
//        System.out.println(null == res ? null : res.val);

        //删除指定节点
//        ListNode head = removeElements(l1, 2);
//        if (null == head) {
//            System.out.println("null");
//        } else {
//            while (null != head) {
//                System.out.println(head.val);
//                head = head.next;
//            }
//        }

        //给定节点删除
//
//        deleteNode(l3);
//        while (l1 != null) {
//            System.out.println(l1.val);
//            l1 = l1.next;
//        }
//        ListNode l11 = new ListNode(1);
//        ListNode l22 = new ListNode(2);
//        l11.next = l22;
//        ListNode res = getIntersectionNode2(l2, l11);
//        while (l2 != null) {
//            System.out.println(l2.val);
//            l2 = l2.next;
//        }

        // 回文数字判断
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(2);
        a.next = b;
        b.next = c;
        c.next = d;
        boolean res = isPalindrome12(a);
        System.out.println(res);
    }


    /**
     * 单链表
     */
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

