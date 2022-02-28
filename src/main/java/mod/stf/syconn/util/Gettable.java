package mod.stf.syconn.util;

public interface Gettable<T> {

    default T get(){
        return (T) this;
    }
}
