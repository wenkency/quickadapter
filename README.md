# imageloader
图片加载类，加载正常、圆形、圆角图片

### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


implementation 'com.github.wenkency:quickadapter:1.0.0'

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
```

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>