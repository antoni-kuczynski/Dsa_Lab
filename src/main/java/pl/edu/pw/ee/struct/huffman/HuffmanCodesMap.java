package pl.edu.pw.ee.struct.huffman;

import pl.edu.pw.ee.struct.map.RbtMap;

import java.util.function.Consumer;

public class HuffmanCodesMap extends RbtMap<String> {

    private HuffmanCodesMap(HuffmanTree tree) {
        Consumer<HuffmanNode> f = node -> {
            if (node.getKey() != null && !node.getCode().isEmpty()) {
                HuffmanCodesMap.this.put(node.getKey(), node.getCode());
            }
        };
        tree.inorderWithFunction(f);
    }

    public static HuffmanCodesMap getInstanceFromTree(HuffmanTree tree) {
        return new HuffmanCodesMap(tree);
    }
}
