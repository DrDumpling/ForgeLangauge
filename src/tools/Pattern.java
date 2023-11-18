package tools;

import java.util.List;
import java.util.Optional;

public interface Pattern<I, O> {
    Optional<ConvertResult<O>> matches(List<I> input, int readFrom);
}