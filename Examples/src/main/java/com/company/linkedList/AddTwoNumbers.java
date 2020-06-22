package com.company.linkedList;

public class AddTwoNumbers {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args) {
        AddTwoNumbers task = new AddTwoNumbers();
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        n1.next=n2;
        ListNode n3 = new ListNode(3);
        n2.next=n3;


        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(9);
        ListNode n6 = new ListNode(9);
        ListNode n7 = new ListNode(9);
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;

        ListNode node = task.addTwoNumbers(n1, n4);

        for(;;){
            System.out.print(node.val);
            System.out.print(",");
            if(node.next==null){
                break;
            }
            node = node.next;
        }

    }

    /**
     *
     * Input: (2 -> 4 -> 3)
     *      + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     *
     */

    public ListNode addTwoNumbers(ListNode n1, ListNode n2) {

        if (n1 == null && n2 == null) return new ListNode(0);

        //how many digits to add to the looping digits
        int summaryOfNextLoop = 0;
        int radix = 10;

        ListNode resultNode = null;
        ListNode runningLastNode = null;

        while (true) {

            if (n1 != null) {
                summaryOfNextLoop += n1.val;
                n1 = n1.next;
            }

            if (n2 != null) {
                summaryOfNextLoop += n2.val;
                n2 = n2.next;
            }

            int lowDigit = summaryOfNextLoop % radix;

            ListNode localNode = new ListNode(lowDigit);

            if (resultNode == null) {//it happens only 1-ce
                resultNode = localNode;
                runningLastNode = resultNode;
            } else {
                runningLastNode.next = localNode;
                runningLastNode = runningLastNode.next;
            }


            summaryOfNextLoop = summaryOfNextLoop / radix;
            if (n1 == null && n2 == null) {
                break;
            }
        }

        if (summaryOfNextLoop > 0) {
            int value = summaryOfNextLoop % radix;
            ListNode oneBeforeLast = new ListNode(value);
            runningLastNode.next = oneBeforeLast;
            int last = summaryOfNextLoop / radix;
            if (last > 0) {
                oneBeforeLast.next = new ListNode(last);
            }

        }


        return resultNode;


    }

}
