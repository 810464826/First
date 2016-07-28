package com.work.chenxb.newgit.hotrepo.repolist.view;

import java.util.List;

/**
 * 提供回调接口 外部实现该接口 实现具体的方法
 * 作者：ChenXb on 2016/7/28.23:05
 * 邮箱：810464826@qq.com
 */
public interface RepoListLoadMoreView {
    void showLoadMoreLoading();

    void hideLoadMore();

    void showLoadMoreErro(String erroMsg);

    void addMoreData(List<String> datas);
}
