package com.work.chenxb.newgit.hotrepo.repolist;

import android.os.AsyncTask;
import android.util.Log;

import com.work.chenxb.newgit.hotrepo.repolist.view.RepoListView;
import com.work.chenxb.newgit.netWork.GitHubApi;
import com.work.chenxb.newgit.netWork.GitHubClient;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 执行相关的业务逻辑
 * 作者：ChenXb on 2016/7/28.23:04
 * 邮箱：810464826@qq.com
 */
public class RepoListPresenter {
    private RepoListView repoListView;
    private int count;

    public RepoListPresenter(RepoListView repoListView) {
        this.repoListView = repoListView;
    }
    //下拉刷新***********************************************************
    public void refresh(){
        new RefreshTask().execute();
    }
    class RefreshTask extends AsyncTask<Void,Void,Void> {
        //异步加载耗时操作
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> list=new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add("好烦的数据"+(count++));
            }
            repoListView.stopRefresh();
            repoListView.refreshData(list);
            repoListView.showContentView();
        }
    }

    // 上拉加载更多处理****************************************************
    public void loadMore() {
        repoListView.showLoadMoreLoading();
        new LoadMoreTask().execute();
    }

    final class LoadMoreTask extends AsyncTask<Void, Void, Void> {
        @Override protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                datas.add("没有加载数据啊 " + (count++));
            }
            repoListView.addMoreData(datas);
            repoListView.hideLoadMore();
        }
    }

    //下拉刷新**网络的数据*********************************************************
    public void reFresh() {
        GitHubClient gitHubClient = new GitHubClient();
        GitHubApi gitHubApi = gitHubClient.getGitHubApi();
        Call<ResponseBody> call = gitHubApi.getRetrofitContributors();
        // #1 直接在当前线程执行
        // #2 异步执行
        call.enqueue(refreshCallback);
    }

    private final Callback<ResponseBody> refreshCallback = new Callback<ResponseBody>() {
        // 响应
        @Override public void onResponse(Call<ResponseBody> call,
                                         Response<ResponseBody> response) {
            repoListView.stopRefresh();
            // 成功200 - 299
            if (response.isSuccessful()) {
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        repoListView.showMessage("未知错误!");
                        return;
                    }
                    String content = body.string();
                    // content:就是从服务器拿到的响应体数据
                    Log.d("TAG", content);
                    repoListView.showContentView();
                } catch (IOException e) {
                    onFailure(call, e);
                }
            }
            // 其它code
            else {
                // 401:一般指未授权
                repoListView.showMessage("code:" + response.code());
            }
        }

        // 失败(如网络连接异常)
        @Override public void onFailure(Call<ResponseBody> call, Throwable t) {
            repoListView.stopRefresh();
            repoListView.showMessage(t.getMessage());
            repoListView.showContentView();
        }
    };

}
