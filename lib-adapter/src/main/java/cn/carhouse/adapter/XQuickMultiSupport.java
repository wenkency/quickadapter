package cn.carhouse.adapter;

/**
 * ListView GridView RecyclerView多条目适配
 */

public interface XQuickMultiSupport<T> {
    /**
     * 获取View类型的数量
     */
    int getViewTypeCount();

    /**
     * 根据数据，获取多条目布局ID
     */
    int getLayoutId(T item, int position);

    /**
     * 根据数据，获取ItemViewType
     */
    int getItemViewType(T item, int position);


    /**
     * 是否合并条目-->>使用RecyclerView时，无效请用原生的RecyclerView
     */
    boolean isSpan(T item);

    /**
     * 获取一行占多少列
     */
    int getSpanSize(T item, int position);
}
