package cn.carhouse.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Fragment适配器
 * 完全销毁
 */
public class XFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public XFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
