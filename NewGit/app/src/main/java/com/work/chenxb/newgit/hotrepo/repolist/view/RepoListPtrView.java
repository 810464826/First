package com.work.chenxb.newgit.hotrepo.repolist.view;

import java.util.List;

/**
 * 作者：ChenXb on 2016/7/28.23:05
 * 邮箱：810464826@qq.com
 */
public interface RepoListPtrView {
    void showContentView();
    void showErrorView(String errorMsg);
    void showEmptyView();
    void showMessage(String msg);
    void stopRefresh();
    void refreshData(List<String> data);
}
