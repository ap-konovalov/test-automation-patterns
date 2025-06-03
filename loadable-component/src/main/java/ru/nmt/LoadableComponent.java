package ru.nmt;

public abstract class LoadableComponent<T extends LoadableComponent<T>> {

    protected abstract void load();

    protected abstract void isLoaded();

    @SuppressWarnings("unchecked")
    public T open() {
        load();
        isLoaded();
        return (T) this;
    }
}