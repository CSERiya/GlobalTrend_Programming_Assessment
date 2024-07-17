import java.util.*;

public class ser_deser {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Serialize a tree to a single string
    public String serialize(TreeNode root) {
        if (root == null) return "";
        
        StringBuilder str = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                str.append("null,");
            } else {
                str.append(node.val).append(",");
                q.add(node.left);
                q.add(node.right);
            }
        }
        
        // Removing trailing comma
        str.deleteCharAt(str.length() - 1);
        System.out.println(" ");
        return str.toString();
    }

    // Deserialize the encoded data to tree
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                q.add(node.left);
            }
            i++;
            if (!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                q.add(node.right);
            }
            i++;
        }
        
        return root;
    }

    public static void main(String[] args) {
        ser_deser codec = new ser_deser();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(25);
        root.right = new TreeNode(32);
        root.right.left = new TreeNode(46);
        root.right.right = new TreeNode(54);
        
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);
        
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized: " + codec.serialize(deserialized)); 
    }
}
