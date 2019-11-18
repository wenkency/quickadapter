package cn.carhouse.adapter;

public abstract class XQuickSupport<T> implements XQuickMultiSupport<T> {


    @Override
    public boolean isSpan(T item) {
        return false;
    }

    @Override
    public int getSpanSize(T item, int position) {
        return 1;
    }
}
