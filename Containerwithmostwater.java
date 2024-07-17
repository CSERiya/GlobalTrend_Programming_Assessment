public class Containerwithmostwater {

    public int[] maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        int[] res = new int[3]; 

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;

            if (currentArea > maxArea) {
                maxArea = currentArea;
                res[0] = left;
                res[1] = right;
                res[2] = maxArea;
            }

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Containerwithmostwater solver = new Containerwithmostwater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] res = solver.maxArea(height);
        System.out.println("Indices of lines: " + res[0] + ", " + res[1]);
        System.out.println("Maximum area: " + res[2]);
    }
    
}
