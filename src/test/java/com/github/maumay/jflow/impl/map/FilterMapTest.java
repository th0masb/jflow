package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;
import java.util.Optional;

public class FilterMapTest extends
        AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
    private static Optional<String> filterEmpty(String input)
    {
        return input.isEmpty() ? Optional.empty() : Optional.of(input);
    }

    @Override
    protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
    {
        return list(new Case<>(
                list("", "1", "", "2"),
                iter -> iter.filterMap(x -> filterEmpty(x)),
                list("1", "2")));
    }
}
