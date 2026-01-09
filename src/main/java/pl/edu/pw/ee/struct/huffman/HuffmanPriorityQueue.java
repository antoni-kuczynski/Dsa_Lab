package pl.edu.pw.ee.struct.huffman;

import pl.edu.pw.ee.struct.map.RbtMap;
import pl.edu.pw.ee.struct.queue.PriorityQueue;

import java.util.ArrayList;

public class HuffmanPriorityQueue extends PriorityQueue<HuffmanNode> {

    public HuffmanPriorityQueue(RbtMap<Long> map) {
        ArrayList<byte[]> keys = map.keys();

        for (byte[] key : keys) {
            this.offer(
                    HuffmanNode.newKeyNode(key, map.get(key), null)
            );
        }
    }


}
