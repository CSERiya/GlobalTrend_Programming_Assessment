
import java.util.Arrays;

public class Kthlargest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        Arrays.sort(nums);
        System.out.println("The " + k + "th largest element is " + nums[nums.length-k]); 

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        Arrays.sort(nums2);
        System.out.println("The " + k + "th largest element is " +nums2[nums2.length-k]);
    }
}
