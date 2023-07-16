# XQuickAdapter
/**
 * RecyclerView、ListView、GridView通用的适配器
 * ListView、GridView请调用：{@link #setListView(boolean)}
 */

### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


implementation 'com.github.wenkency:quickadapter:2.0.1'
implementation 'com.github.wenkency:imageloader:2.8.0'
implementation "com.github.bumptech.glide:glide:4.11.0"
annotationProcessor "com.github.bumptech.glide:compiler:4.11.0"

```

### 使用方式
```
 mRecyclerView.setAdapter(new XQuickAdapter<String>(this, data, R.layout.item_recyler) {
            @Override
            protected void convert(XQuickViewHolder holder, String item, int position) {
                holder.setText(R.id.tv, "item " + position);
                holder.displayCircleImage(R.id.iv, item);
            }
        });
  // 增
  adapter.add(item);
  adapter.addAll(list);
  // 删
  adapter.remove(item);
  adapter.remove(index);
  // 改
  adapter.set(index,item);
  // 获取集合数据
  adapter.getData();
  // 刷新数据
  adapter.notifyItemsData();
```