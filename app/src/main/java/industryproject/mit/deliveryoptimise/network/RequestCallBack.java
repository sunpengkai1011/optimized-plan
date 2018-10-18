package industryproject.mit.deliveryoptimise.network;

public interface RequestCallBack<T> {
    void requestCallBack(T t);
    void requestFailure(T t);
}
