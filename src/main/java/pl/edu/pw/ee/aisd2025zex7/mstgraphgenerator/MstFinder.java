package pl.edu.pw.ee.aisd2025zex7.mstgraphgenerator;

import pl.edu.pw.ee.aisd2025zex7.mst.MstSearchResult;
import pl.edu.pw.ee.aisd2025zex7.mst.kruskal.KruskalMstSearcher;

import java.io.File;

public class MstFinder {
    private static String PATH_TO_CAPITALS_MATRIX = "stolice.txt";

    public static void main(String[] args) {
        KruskalMstSearcher searcher = new KruskalMstSearcher();
        MstSearchResult result = searcher.getMstResult(new File(PATH_TO_CAPITALS_MATRIX));

        result.print();


    }
}
