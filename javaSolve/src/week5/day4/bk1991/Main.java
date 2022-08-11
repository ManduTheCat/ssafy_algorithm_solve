package week5.day4.bk1991;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.StringTokenizer;


class Node{
    int left;
    int right;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(right).append(" ").append(left).append("\n");
        return sb.toString();
    }
}
public class Main {
    static  Node [] adjList = new Node[26];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week5/day4/bk1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        ArrayList<Node> NodeList = new ArrayList<>();
        for(int tc = 0; tc < Tc; tc++){
            StringTokenizer nodeTc = new StringTokenizer(br.readLine());
            Node n = new Node();
            int root = nodeTc.nextToken().charAt(0)-'0';
            n.left = nodeTc.nextToken().charAt(0)-'0';
            n.right = nodeTc.nextToken().charAt(0)-'0';
            adjList[root] = n;
            for(Node no : adjList){
                System.out.println(no);
            }
        }
    }


}
