package tools;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class BasicConvertResult<T> {
    private final T result;

    private BasicConvertResult(T result) {
        this.result = result;
    }

    public boolean isEmpty() { return result == null; }

    public boolean isResult() { return result != null; }

    public T get() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Convert is empty, no value present");
        }
        return result;
    }

    public static<T> BasicConvertResult<T> of(T value) { return new BasicConvertResult<>(Objects.requireNonNull(value)); }
}