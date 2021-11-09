package cn.carhouse.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager的一个通用Adapter
 */

public abstract class XQuickPagerAdapter<T> extends PagerAdapter {
    protected List<T> mData;// 数据
    protected boolean isLooper;
    private int mLayoutId;
    // 缓存池
    private ArrayList<View> mCaches = new ArrayList<>(4);
    // 是否缓存
    private boolean isCache;

    public XQuickPagerAdapter(List<T> data, int layoutId, boolean isLopper) {
        this(data, layoutId, isLopper, true);
    }

    public XQuickPagerAdapter(List<T> data, int layoutId, boolean isLopper, boolean isCache) {
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.mLayoutId = layoutId;
        this.isLooper = isLopper;
        this.isCache = isCache;
    }

    public XQuickPagerAdapter(List<T> data, int layoutId) {
        this(data, layoutId, true);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        if (!isLooper) {
            return mData.size();
        }
        return mData.size() > 1 ? Integer.MAX_VALUE : mData.size();
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // 轮播模式才执行
        if (isLooper) {
            ViewPager mViewPager = (ViewPager) container;
            int position = mViewPager.getCurrentItem();
            if (position == getCount() - 1) {
                position = 0;
                mViewPager.setCurrentItem(position, false);
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mData.size();
        XQuickViewHolder holder;
        if (mCaches.size() <= 0) {
            // 创建ViewHolder
            holder = createListHolder(container, mLayoutId);
        } else {
            View view = mCaches.remove(0);
            if (view != null) {
                holder = (XQuickViewHolder) view.getTag();
            } else {
                // 创建ViewHolder
                holder = createListHolder(container, mLayoutId);
            }
        }
        // 判断一下
        try {
            if (holder.itemView.getParent() != null) {
                ViewGroup viewGroup = (ViewGroup) holder.itemView.getParent();
                viewGroup.removeView(holder.itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        container.addView(holder.itemView);
        // 绑定View的数据
        convert(holder, mData.get(position), position);
        return holder.itemView;
    }

    /**
     * 创建ListView的Holer
     */
    @NonNull
    private XQuickViewHolder createListHolder(ViewGroup parent, int layoutId) {
        XQuickViewHolder holder;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        holder = new XQuickViewHolder(itemView, layoutId);
        itemView.setTag(holder);
        return holder;
    }

    protected abstract void convert(XQuickViewHolder holder, T data, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 1.移除View
        if (isCache) {
            if (mCaches.size() > 2) {
                mCaches.remove(0);
            }
        }
        View view = (View) object;
        container.removeView(view);
        if (isCache) {
            mCaches.add(view);
        }

    }

    //==========================================数据相关================================================
    public void add(int index, T elem) {
        if (mData != null && mData.size() > 0) {
            mData.add(index, elem);
        } else {
            mData.add(elem);
        }
        notifyItemsData();
    }

    public void add(T elem) {
        if (elem != null) {
            mData.add(elem);
        }
        notifyItemsData();
    }


    public void addAll(List<T> data) {
        if (data == null || data.size() <= 0) {
            return;
        }
        mData.addAll(data);
        notifyItemsData();
    }

    public void addAll(int index, List<T> data) {
        if (data == null || data.size() <= 0) {
            return;
        }
        if (mData != null && mData.size() > 0) {
            mData.addAll(index, data);
        } else {
            mData.addAll(data);
        }
        notifyItemsData();
    }


    public void addFirst(T elem) {
        mData.add(0, elem);
        notifyItemsData();
    }

    public void set(T oldElem, T newElem) {
        set(mData.indexOf(oldElem), newElem);
        notifyItemsData();
    }

    public void set(int index, T elem) {
        mData.set(index, elem);
        notifyItemsData();
    }

    public void remove(T elem) {
        mData.remove(elem);
        notifyItemsData();
    }

    public void remove(int index) {
        mData.remove(index);
        notifyItemsData();
    }

    public void removeAll(List<T> elem) {
        if (elem != null && elem.size() > 0) {
            mData.removeAll(elem);
        }
        notifyItemsData();
    }

    public void replaceAll(List<T> elem) {
        mData.clear();
        if (elem != null && elem.size() > 0) {
            mData.addAll(elem);
        }
        notifyItemsData();
    }

    /**
     * 清除
     */
    public void clear() {
        mData.clear();
        notifyItemsData();
    }

    /**
     * 刷新数据
     */
    public void notifyItemsData() {
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return mData.contains(elem);
    }

    /**
     * 获取集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 获取集合的长度
     */
    public int getDataSize() {
        return mData.size();
    }

    /**
     * 获取集合第一个元素
     */
    public T getFirst() {
        if (mData != null && mData.size() > 0) {
            return mData.get(0);
        }
        return null;
    }

    /**
     * 获取集合最后一个元素
     */
    public T getLast() {
        if (mData != null && mData.size() > 0) {
            return mData.get(mData.size() - 1);
        }
        return null;
    }

    /**
     * 是不是最后一个条目
     */
    public boolean isLast(int position) {
        return position == mData.size() - 1;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}
