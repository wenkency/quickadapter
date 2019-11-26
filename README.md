# XQuickAdapter
RecyclerView ListView GridView 通用适配器。

### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


implementation 'com.github.wenkency:quickadapter:1.3.0'

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

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>