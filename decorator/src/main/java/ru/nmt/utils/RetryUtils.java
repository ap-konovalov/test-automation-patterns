package ru.nmt.utils;

import io.restassured.response.Response;

import java.util.function.Function;
import java.util.function.Supplier;

public class RetryUtils {

    // Перегруженный метод для вызовов без параметров
    public static <T> T retry(Supplier<T> action, Class<? extends Throwable>... retryableExceptions) {
        return retryInternal(action, null,null, retryableExceptions);
    }

    // Новый перегруженный метод для вызовов с параметром
    public static <T, P> T retry(Function<P, T> action, P param, Class<? extends Throwable>... retryableExceptions) {
        return retryInternal(null, action, param, retryableExceptions);
    }

    // Вспомогательный метод для общей логики
    private static <T, P> T retryInternal(
            Supplier<T> supplier,
            Function<P, T> function,
            P param,
            Class<? extends Throwable>[] retryableExceptions
    ) {
        int attempt = 0;
        int maxAttempts = 3;
        while (attempt < maxAttempts) {
            try {
                if (supplier != null) {
                    return supplier.get();
                } else {
                    return function.apply(param);
                }
            } catch (Throwable e) {
                if (!isRetryable(e, retryableExceptions)) {
                    throw e;
                }
                attempt++;
                if (attempt >= maxAttempts) {
                    throw e;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
            }
        }
        throw new RuntimeException("Max retries exceeded");
    }

//    public static <T> T retry(Supplier<T> action, Class<? extends Throwable>... retryableExceptions) {
//        int attempt = 0;
//        int maxAttempts = 3;
//        while (attempt < maxAttempts) {
//            try {
//                return action.get(); // Возвращаем результат
//            } catch (Throwable e) {
//                if (!isRetryable(e, retryableExceptions)) {
//                    throw e;
//                }
//                attempt++;
//                if (attempt >= maxAttempts) {
//                    throw e;
//                }
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                    throw new RuntimeException("Interrupted during retry", ie);
//                }
//            }
//        }
//        throw new RuntimeException("Max retries exceeded"); // Никогда не вызывается, но нужен для компиляции
//    }

    private static boolean isRetryable(Throwable e, Class<? extends Throwable>[] exceptions) {
        for (Class<? extends Throwable> exClass : exceptions) {
            if (exClass.isInstance(e)) {
                return true;
            }
        }
        return exceptions.length == 0; // Если список пуст, все исключения считаются retryable
    }
}
