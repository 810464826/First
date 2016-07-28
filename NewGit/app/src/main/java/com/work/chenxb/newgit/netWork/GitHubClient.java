package com.work.chenxb.newgit.netWork;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 作者：ChenXb on 2016/7/28.23:28
 * 邮箱：810464826@qq.com
 */
public class GitHubClient {
    private GitHubApi gitHubApi;

    public GitHubClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build();
        // 构建API
        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public GitHubApi getGitHubApi() {
        return gitHubApi;
    }
}
