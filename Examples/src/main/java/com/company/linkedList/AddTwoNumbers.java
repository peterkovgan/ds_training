package com.company.linkedList;

public class AddTwoNumbers {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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

            if (resultNode == null) {
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
