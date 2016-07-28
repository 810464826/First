package com.work.chenxb.newgit.hotrepo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.work.chenxb.newgit.hotrepo.repolist.RepoListFragment;

/**
 * 作者：ChenXb on 2016/7/28.22:56
 * 邮箱：810464826@qq.com
 */
public class HotRepoAdapter extends FragmentPagerAdapter {
    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        return new RepoListFragment();
    }

    @Override public int getCount() {
        return 10;
    }

    @Override public CharSequence getPageTitle(int position) {
        return "青春"+position;
    }
}
