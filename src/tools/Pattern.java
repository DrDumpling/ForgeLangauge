package tools;

import java.util.Optional;

public interface Pattern<I, O> {
    Optional<BasicConvertResult<O>> matches(I input);
}
