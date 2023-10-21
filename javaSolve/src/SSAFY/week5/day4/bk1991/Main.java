package SSAFY.week5.day4.bk1991;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * 트리의 한개의 노드를 기록한 클래스
 * 자식들을 맴버로 가지고 있다.
 */
class Node {
    char left;
    char right;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(right).append(" ").append(left).append("\n");
        return sb.toString();
    }
}

/**
 * <pre>
 *     HashMap 자료구에 left 와 right 를 가진 node 클래스를 기록해
 *     노드 단위로 순회 할수 있게 만들었습니다
 *     
 *     
 * </pre>
 * @author 김명진
 * 11640kb	80ms
 */

public class Main {

    static Map<Character, Node> nodeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day4/bk1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        ArrayList<Node> NodeList = new ArrayList<>();
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer nodeTc = new StringTokenizer(br.readLine());
            Node n = new Node();
            char root = nodeTc.nextToken().charAt(0);
            n.left = nodeTc.nextToken().charAt(0);
            n.right = nodeTc.nextToken().charAt(0);
            nodeMap.put(root, n);
        }
        preOrderDfs('A');
        System.out.println();
        inOrderDfs('A');
        System.out.println();
        postOrderDfs('A');
    }

    /**
     * 전위 순회 dfs
     * @param node map 의 키가될 char
     */
    static void preOrderDfs(char node) {
        if (node == '.') return;
        System.out.print(node);
        preOrderDfs(nodeMap.get(node).left);
        preOrderDfs(nodeMap.get(node).right);
    }

    /**
     * 중위순회 dfs
     * @param node
     */
    static void inOrderDfs(char node) {
        if (node == '.') return;
        ;
        inOrderDfs(nodeMap.get(node).left);
        System.out.print(node);
        inOrderDfs(nodeMap.get(node).right);
    }

    /**
     * 후위 순회
     * @param node
     */
    static void postOrderDfs(char node) {
        if (node == '.') return;
        postOrderDfs(nodeMap.get(node).left);
        postOrderDfs(nodeMap.get(node).right);
        System.out.print(node);
    }
}
