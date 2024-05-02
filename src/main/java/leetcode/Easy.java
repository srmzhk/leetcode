package leetcode;

import java.io.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Easy {
    public static void main(String[] args){
        String str = "{}{[]}()";
        System.out.println(isValidWithStack(str));
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
        if (s.length() < 2)
            return false;
        while (true) {
            if (s.contains("[]"))
                s = s.replace("[]", "");
            else if (s.contains("{}"))
                s = s.replace("{}", "");
            else if (s.contains("()"))
                s = s.replace("()", "");
            else
                return s.isEmpty();
        }
    }
    public static boolean isValidWithStack (String s) {
        if(s.length() < 2)
            return false;
        Stack<Character> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            switch (c) {
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '(':
                    stack.push(')');
                    break;
                default:
                    if(stack.isEmpty() || stack.pop() != c)
                        return false;
            }
        }
        return stack.isEmpty();
    }

    // 27. Remove Element
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}