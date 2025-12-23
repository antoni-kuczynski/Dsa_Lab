package pl.edu.pw.ee;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LargeFileGenerator {

    private static final long FILE_SIZE = 500_000_000L;
    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private static final int BUF_SIZE = 1 << 20;
    private static final Path FILE = Path.of("test1.txt");

    @Test
    public void generateParallel() throws Exception {
        try (FileChannel ch = FileChannel.open(
                FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            ch.truncate(FILE_SIZE);
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        long chunk = FILE_SIZE / THREADS;

        for (int t = 0; t < THREADS; t++) {
            final long start = t * chunk;
            final long end = (t == THREADS - 1)
                    ? FILE_SIZE
                    : start + chunk;

            pool.submit(() -> writeChunk(start, end));
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
    }

    private void writeChunk(long start, long end) {
        Random random = new Random(start);

        try (FileChannel channel = FileChannel.open(
                FILE,
                StandardOpenOption.WRITE)) {

            ByteBuffer buf = ByteBuffer.allocateDirect(BUF_SIZE);
            long pos = start;

            while (pos < end) {
                buf.clear();
                int limit = (int) Math.min(BUF_SIZE, end - pos);

                for (int i = 0; i < limit; i++) {
                    buf.put((byte) random.nextInt(32, 126));
                }

                buf.flip();
                channel.write(buf, pos);
                pos += limit;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
