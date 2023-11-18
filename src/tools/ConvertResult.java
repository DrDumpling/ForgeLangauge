package tools;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class ConvertResult<T> {
    private final T result;
    private final int resultSize;

    public ConvertResult(T result, int resultSize) {
        this.result = result;
        this.resultSize = resultSize;
    }

    public ConvertResult(T result) {
        this(result, 1);
    }

    public T get() throws NoSuchElementException {
        if (this.result == null) throw new NoSuchElementException("Convert is empty, no value present");
        return result;
    }

    public int getSize() {
        return resultSize;
    }

    public static<T> ConvertResult<T> of(T value) {
        return new ConvertResult<>(Objects.requireNonNull(value));
    }

    public static<T> ConvertResult<T> of(T value, int resultSize) {
        return new ConvertResult<>(Objects.requireNonNull(value), resultSize);
    }
}