package org.afk;

import org.afk.fallback.DefaultJaDiContainer;
import org.afk.fallback.DefaultJaDiModifier;
import org.afk.fallback.DefaultJaDiStorage;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by axel on 28.10.15.
 */
public class JanusDixie {

    private final JaDiContainer container;
    private final JaDiStorage storage;
    private final JaDiModifier modifier;

    public JanusDixie(JaDiContainer container, JaDiStorage storage, JaDiModifier modifier) {
        this.container = Optional.ofNullable(container).orElse(new DefaultJaDiContainer());
        this.storage = Optional.ofNullable(storage).orElse(new DefaultJaDiStorage());
        this.modifier = Optional.ofNullable(modifier).orElse(new DefaultJaDiModifier());
    }

    /**
     * Adds a consumer for an id. If there is no
     *
     * @param <T>
     * @param id
     * @param setter
     * @param defaultProvider
     */
    public <T> void add(String id, Consumer<T> setter, Supplier<T> defaultProvider) {
        T defaultValue = defaultProvider != null ? defaultProvider.get() : null;

        T contained = container.get(id);

        T stored = storage.retrieve(id);
        if (stored == null) storage.store(id, defaultValue);

        setter.accept(stored != null ? stored : defaultValue);
    }

}
