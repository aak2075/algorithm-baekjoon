import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(number);
                if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
            } else {
                minHeap.offer(number);
                if(!maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
            }
            sb.append(maxHeap.peek() + "\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}

// minHeap: 오름차순
// maxHeap: 내림차순
