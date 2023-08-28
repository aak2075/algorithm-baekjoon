import java.util.*;
import java.util.stream.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodes.add(new Node(x, y, i + 1));
        }
        List<Node> sortedNodes = nodes.stream()
            .sorted(Comparator.comparingInt(Node::getY).reversed())
            .collect(Collectors.toList());
        
        GameTree tree = new GameTree(sortedNodes.get(0));
        sortedNodes.remove(0);
        for (Node n : sortedNodes) {
            tree.put(n);
        }
        
        List<Integer> preorder = mapToIndex(tree.preorder(tree.root));
        List<Integer> postorder = mapToIndex(tree.postorder(tree.root));
        
        int[][] answer = new int[2][preorder.size()];
        for (int i = 0; i < preorder.size(); i++) {
            answer[0][i] = preorder.get(i);
            answer[1][i] = postorder.get(i);
        }
            
        return answer;
    }
    
    public List<Integer> mapToIndex(List<Node> nodes) {
        return nodes.stream()
            .map(Node::getIndex)
            .collect(Collectors.toList());
    }
    
    class GameTree {
        Node root;
        List<Node> preorder;
        List<Node> postorder;
        
        public GameTree(Node root) {
            this.root = root;
            preorder = new ArrayList<>();
            postorder = new ArrayList<>();
        }
        
        public void put(Node n) {
            if (n.x < root.x) {
                if (root.left == null) {
                    root.left = n;
                    return;
                }
                put(root.left, n);                
            } else {
                if (root.right == null) {
                    root.right = n;
                    return;
                }
                put(root.right, n);
            }
        }
        
        public void put(Node parent, Node n) {
            if (n.x < parent.x) {
                if (parent.left == null) {
                    parent.left = n;
                    return;
                }
                put(parent.left, n);
                return;
            }
            if (n.x > parent.x) {
                if (parent.right == null) {
                    parent.right = n;
                    return;
                }
                put(parent.right, n);
                return;
            }
        }
        
        public List<Node> preorder(Node current) {
            // 현재 위치를 방문, 왼쪽서브트리를 모두 방문, 오른쪽 서브트리를 모두 방문
            if (current == null) {
                return preorder;
            }
            preorder.add(current);
            preorder(current.left);
            preorder(current.right);
            return preorder;
        }
        
        public List<Node> postorder(Node current) {
            // 왼쪽 서브트리를 모두 방문, 오른쪽 서브트리를 모두 방문, 현재 위치를 방문
            if (current == null) {
                return postorder;
            }
            postorder(current.left);
            postorder(current.right);
            postorder.add(current);
            return postorder;
        }
    }
    
    class Node {
        int x;
        int y;
        int index;
        Node left;
        Node right;
        
        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
        
        public int getY() {
            return y;
        }
        
        public int getIndex() {
            return index;
        }
        
        @Override
        public boolean equals(Object o) {
            Node that = (Node) o;
            return x == that.x && y == that.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}