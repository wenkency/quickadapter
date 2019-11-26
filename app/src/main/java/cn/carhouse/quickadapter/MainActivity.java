package cn.carhouse.quickadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.adapter.XQuickAdapter;
import cn.carhouse.adapter.XQuickViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573796397226&di=4ebc060b0d6f6a6509cdd08737d24e3a&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121225%2F9252150_165232606338_2.jpg";
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(url);
        }
        XQuickAdapter<String> adapter = new XQuickAdapter<String>(this, data, R.layout.item_recyler) {
            @Override
            protected void convert(XQuickViewHolder holder, String item, int position) {
                holder.setText(R.id.tv, "item " + position);
                holder.displayCircleImage(R.id.iv, item);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }
}
