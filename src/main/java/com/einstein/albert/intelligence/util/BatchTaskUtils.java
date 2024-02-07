package com.einstein.albert.intelligence.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/20
 */
@Slf4j
public class BatchTaskUtils {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(20, 50,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), (ThreadFactory) Thread::new);

    /**
     * <p>批量处理结果</p>
     *
     * @param input  - [List<T>]
     * @param limit  - [int]
     * @param action - [Action<T]
     * @return void
     * @author 张春杰
     * @date 2024/1/20
     */
    public static <T> void batchRun(List<T> input, int limit, Action<T, Void> action) {
        if (input.isEmpty()) {
            return;
        }
        int chunkCount = (int) Math.ceil((double) input.size() / limit);
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < chunkCount; i++) {
            int from = i * limit;
            int to = Math.min(from + limit, input.size());
            chunks.add(input.subList(from, to));
        }
        for (List<T> chunk : chunks) {
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            for (T t : chunk) {
                CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> action.run(t), EXECUTOR);
                futures.add(future);
            }
            try {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        batchRun(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), 5, s -> {
            try {
                Thread.sleep(s % 3 * 1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            log.info("now => " + LocalDateTime.now() + "  s = " + s);
            return null;
        });
    }
}
