package ss.control;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] arr ;
        for (int i = 0; i <= nums.length; i++) {
            int e = nums[i];
            int t = target - e;
            for (int j = i + 1; j <= nums.length; j++) {
                if (nums[j] == t) {
                    arr = new int[]{i, j};
                    return  arr;
                }
            }
        }
        return  new int[0];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
    }
}