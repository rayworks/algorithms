package org.sean.tree;

/***
 * 1361. Validate Binary Tree Nodes
 */
public class BinaryTreeValidator {
    private boolean[] visited;

    /**
     * The flag indicates whether we've finished the validation for the subtrees
     */
    private Boolean validated = null;

    private int cnt;

    // O(N)
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        visited = new boolean[n];

        // locate the candidate node as the root by checking inDegree property
        int[] inDegrees = new int[n];
        for (int k = 0; k < n; k++) {
            if (leftChild[k] != -1) {
                inDegrees[leftChild[k]] += 1;
            }
            if (rightChild[k] != -1) {
                inDegrees[rightChild[k]] += 1;
            }
        }
        int root = -1;
        for (int j = 0; j < n; j++) {
            if (inDegrees[j] == 0) {
                root = j;
                break;
            }
        }
        if (root == -1)
            return false;

        validateBinaryTreeNodesHelper(root, leftChild, rightChild);

        return (validated == null || validated) && cnt == n;
    }

    private boolean validateBinaryTreeNodesHelper(int curr, int[] leftChild, int[] rightChild) {
        if (validated != null)
            return validated;

        if (visited[curr]) {
            validated = false;
            return false;
        }

        visited[curr] = true;
        cnt++;
        if (leftChild[curr] == -1 && rightChild[curr] == -1)
            return true;

        boolean leftRes = leftChild[curr] == -1 || validateBinaryTreeNodesHelper(leftChild[curr], leftChild, rightChild);
        boolean rightRes = rightChild[curr] == -1 || validateBinaryTreeNodesHelper(rightChild[curr], leftChild, rightChild);

        boolean ret = leftRes && rightRes;
        if (!ret) {
            validated = false;
        }
        return ret;
    }
}
