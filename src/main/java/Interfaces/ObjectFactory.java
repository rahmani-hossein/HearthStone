package Interfaces;

import model.Minion;

import java.util.Map;

public interface ObjectFactory<T> {
    /**
     * Returns a new instance of an object type T.
     *
     * @return T an new instance of the object type T
     */
    public abstract T createNew(Map<String, Object> map);
     public Minion createNewMinion(Map<String, Object> map);
}
