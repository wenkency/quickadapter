package cn.carhouse.quickadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.adapter.XQuickAdapter;
import cn.carhouse.adapter.XQuickViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private XQuickAdapter<String> adapter;

    String url="https://buydo.oss-accelerate.aliyuncs.com/buydo/6574697f1f1b4576835cc837697835b4.jpeg";
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(url);
        }
        adapter = new XQuickAdapter<String>(this, data, R.layout.item_recyler) {
            @Override
            protected void convert(XQuickViewHolder holder, String item, int position) {
                holder.setText(R.id.tv, "item " + position);
                holder.displayCircleImage(R.id.iv, item);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    public void test(View view) {
        adapter.remove(0);
    }
}
