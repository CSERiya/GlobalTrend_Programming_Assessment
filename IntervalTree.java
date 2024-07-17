import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

class IntervalTreeNode {
    Interval interval;
    int max;
    IntervalTreeNode left;
    IntervalTreeNode right;

    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.max = interval.end;
        this.left = null;
        this.right = null;
    }
}

public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree() {
        this.root = null;
    }

    // Insert a new interval into the tree
    public void insertInterval(int start, int end) {
        Interval newInterval = new Interval(start, end);
        root = insert(root, newInterval);
    }

    private IntervalTreeNode insert(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        if (interval.start < node.interval.start) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        node.max = Math.max(node.max, interval.end);

        return node;
    }

    // Delete an interval from the tree
    public void deleteInterval(int start, int end) {
        Interval target = new Interval(start, end);
        root = delete(root, target);
    }

    private IntervalTreeNode delete(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return null;
        }

        if (interval.start < node.interval.start) {
            node.left = delete(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = delete(node.right, interval);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            IntervalTreeNode minNode = findMin(node.right);
            node.interval = minNode.interval;
            node.right = delete(node.right, minNode.interval);
        }

        // Update the max value of this node
        if (node != null) {
            node.max = Math.max(node.interval.end, Math.max(getMax(node.left), getMax(node.right)));
        }

        return node;
    }

    private IntervalTreeNode findMin(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMax(IntervalTreeNode node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }

    // Find all intervals that overlap with the given interval
    public List<Interval> findOverlappingIntervals(int start, int end) {
        Interval query = new Interval(start, end);
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, query, result);
        return result;
    }

    private void findOverlappingIntervals(IntervalTreeNode node, Interval query, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (doOverlap(node.interval, query)) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.max >= query.start) {
            findOverlappingIntervals(node.left, query, result);
        }

        findOverlappingIntervals(node.right, query, result);
    }

    private boolean doOverlap(Interval i1, Interval i2) {
        return i1.start <= i2.end && i2.start <= i1.end;
    }

    // Utility method for testing
    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        tree.insertInterval(15, 20);
        tree.insertInterval(10, 30);
        tree.insertInterval(17, 19);
        tree.insertInterval(5, 20);
        tree.insertInterval(12, 15);
        tree.insertInterval(30, 40);

        System.out.println("Intervals in the tree:");
        System.out.println(tree.findOverlappingIntervals(Integer.MIN_VALUE, Integer.MAX_VALUE));

        System.out.println("\nOverlapping intervals with [14, 16]:");
        List<Interval> overlappingIntervals = tree.findOverlappingIntervals(14, 16);
        for (Interval interval : overlappingIntervals) {
            System.out.println(interval);
        }

        tree.deleteInterval(10, 30);
        System.out.println("\nIntervals in the tree after deleting [10, 30]:");
        System.out.println(tree.findOverlappingIntervals(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
