package leetcode;

import java.sql.Array;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Easy {
    public static void main(String[] args){
        int[] nums = new int[]{1,1,2};
        System.out.println(removeDuplicates(nums));
    }

    // 1. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[] {i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    // 9. Palindrome Number
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int k = 0;
        while (x > k) {
            k = k * 10 + x % 10;
            x /= 10;
        }
        return (x == k || x == k / 10);
    }
    public static boolean isPalindromeWithString(int x) {
        char[] chars = Integer.toString(x).toCharArray();
        for (int i = 0, j = chars.length - 1; i < chars.length / 2; i++, j--) {
            if(chars[i] != chars[j])
                return false;
        }
        return true;
    }

    // 13. Roman to Integer
    public static int romanToInt(String s) {
        int answ = 0, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            if(4 * num < answ)
                answ -= num;
            else
                answ += num;
        }
        return answ;
    }

    // 14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder answ = new StringBuilder();
        Arrays.sort(strs);
        String firstStr = strs[0];
        String lastStr = strs[strs.length-1];
        for (int i=0; i<Math.min(firstStr.length(), lastStr.length()); i++) {
            if (firstStr.charAt(i) != lastStr.charAt(i)) {
                return answ.toString();
            }
            answ.append(firstStr.charAt(i));
        }
        return answ.toString();
    }

    // 20. Valid Parentheses
    public static boolean isValid(String s) {
        while (true) {
            if (s.contains("()")) {
                s = s.replace("()", "");
            }
            else if (s.contains("{}")) {
                s = s.replace("{}", "");
            }
            else if (s.contains("[]")) {
                s = s.replace("[]", "");
            }
            else {
                return s.isEmpty();
            }
        }
    }
    public static boolean isValidWithStack (String s) {
        if (s.length() % 2 != 0)
            return false;

        Stack<Character> stack = new Stack<>();
        try {
            for (char c : s.toCharArray()) {
                switch (c){
                    case ')':
                        if (stack.pop() != '(')
                            return false;
                        break;
                    case '}':
                        if (stack.pop() != '{')
                            return false;
                        break;
                    case ']':
                        if (stack.pop() != '[')
                            return false;
                        break;
                    default:
                        stack.push(c);
                        break;
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }

        return stack.isEmpty();
    }

    //21. Merge Two Sorted Lists
    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode current = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            else {
                current.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = list1 != null ? list1 : list2;

        return head.next;
    }

    //26. Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[k] != nums[i])
                nums[++k] = nums[i];
        }
        return ++k;
    }

    // 27. Remove Element
    public static int removeElement(int[] nums, int val) {
        return 1;
    }

    // 28. Find the Index of the First Occurrence in a String
    public static int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i < haystack.length(); i++) {
            boolean isFind = true;
            for (int j = 0; j < needle.length(); j++) {
                if(haystack.length() - i < needle.length())
                    return -1;
                else if (needle.charAt(j) != haystack.charAt(i + j)) {
                    isFind = false;
                    break;
                }

            }
            if (isFind)
                return i;
        }
        return -1;
    }

    // 35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length);
    }
    public static int binarySearch(int[] sortedArray, int key, int low, int high) {
        int middle = low  + ((high - low) / 2);

        if (high < low) {
            return high;
        }

        if (key == sortedArray[middle]) {
            return middle;
        } else if (key < sortedArray[middle]) {
            return binarySearch(sortedArray, key, low, middle - 1);
        } else {
            return binarySearch(sortedArray, key, middle + 1, high);
        }
    }

    // 58. Length of Last Word
    public static int lengthOfLastWord(String s) {
        s = s.trim();
        if (!s.contains(" "))
            return s.length();
        else {
            int index = 0;
            while(true) {
                if (s.charAt(s.length() - 1 - index) == ' ')
                    return index;
                index++;
            }
        }
    }

    // 66. Plus One
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                if(i == 0) {
                    digits = new int[digits.length + 1];
                    digits[0] = 1;
                    return digits;
                }
                else
                    digits[i] = 0;
            }
            else {
                digits[i]++;
                break;
            }
        }
        return digits;
    }
}
