package com.example.study.entity;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/17 15:20    <br>
 * Description: 仓库列表   <br>
 */
public class RepositorySearchBean {

    @Override
    public String toString() {
        return "RepositorySearch{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", items=" + items +
                '}';
    }

    /**
     * total_count : 2005
     * incomplete_results : false
     * items : [{"id":8575137,"node_id":"MDEwOlJlcG9zaXRvcnk4NTc1MTM3","name":"butterknife","full_name":"JakeWharton/butterknife","private":false,"owner":{"login":"JakeWharton","id":66577,"node_id":"MDQ6VXNlcjY2NTc3","avatar_url":"https://avatars0.githubusercontent.com/u/66577?v=4","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","html_url":"https://github.com/JakeWharton","followers_url":"https://api.github.com/users/JakeWharton/followers","following_url":"https://api.github.com/users/JakeWharton/following{/other_user}","gists_url":"https://api.github.com/users/JakeWharton/gists{/gist_id}","starred_url":"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/JakeWharton/subscriptions","organizations_url":"https://api.github.com/users/JakeWharton/orgs","repos_url":"https://api.github.com/users/JakeWharton/repos","events_url":"https://api.github.com/users/JakeWharton/events{/privacy}","received_events_url":"https://api.github.com/users/JakeWharton/received_events","type":"User","site_admin":false},"html_url":"https://github.com/JakeWharton/butterknife","description":"Bind Android views and callbacks to fields and methods.","fork":false,"url":"https://api.github.com/repos/JakeWharton/butterknife","forks_url":"https://api.github.com/repos/JakeWharton/butterknife/forks","keys_url":"https://api.github.com/repos/JakeWharton/butterknife/keys{/key_id}","collaborators_url":"https://api.github.com/repos/JakeWharton/butterknife/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/JakeWharton/butterknife/teams","hooks_url":"https://api.github.com/repos/JakeWharton/butterknife/hooks","issue_events_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/events{/number}","events_url":"https://api.github.com/repos/JakeWharton/butterknife/events","assignees_url":"https://api.github.com/repos/JakeWharton/butterknife/assignees{/user}","branches_url":"https://api.github.com/repos/JakeWharton/butterknife/branches{/branch}","tags_url":"https://api.github.com/repos/JakeWharton/butterknife/tags","blobs_url":"https://api.github.com/repos/JakeWharton/butterknife/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/JakeWharton/butterknife/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/JakeWharton/butterknife/git/refs{/sha}","trees_url":"https://api.github.com/repos/JakeWharton/butterknife/git/trees{/sha}","statuses_url":"https://api.github.com/repos/JakeWharton/butterknife/statuses/{sha}","languages_url":"https://api.github.com/repos/JakeWharton/butterknife/languages","stargazers_url":"https://api.github.com/repos/JakeWharton/butterknife/stargazers","contributors_url":"https://api.github.com/repos/JakeWharton/butterknife/contributors","subscribers_url":"https://api.github.com/repos/JakeWharton/butterknife/subscribers","subscription_url":"https://api.github.com/repos/JakeWharton/butterknife/subscription","commits_url":"https://api.github.com/repos/JakeWharton/butterknife/commits{/sha}","git_commits_url":"https://api.github.com/repos/JakeWharton/butterknife/git/commits{/sha}","comments_url":"https://api.github.com/repos/JakeWharton/butterknife/comments{/number}","issue_comment_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/comments{/number}","contents_url":"https://api.github.com/repos/JakeWharton/butterknife/contents/{+path}","compare_url":"https://api.github.com/repos/JakeWharton/butterknife/compare/{base}...{head}","merges_url":"https://api.github.com/repos/JakeWharton/butterknife/merges","archive_url":"https://api.github.com/repos/JakeWharton/butterknife/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/JakeWharton/butterknife/downloads","issues_url":"https://api.github.com/repos/JakeWharton/butterknife/issues{/number}","pulls_url":"https://api.github.com/repos/JakeWharton/butterknife/pulls{/number}","milestones_url":"https://api.github.com/repos/JakeWharton/butterknife/milestones{/number}","notifications_url":"https://api.github.com/repos/JakeWharton/butterknife/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/JakeWharton/butterknife/labels{/name}","releases_url":"https://api.github.com/repos/JakeWharton/butterknife/releases{/id}","deployments_url":"https://api.github.com/repos/JakeWharton/butterknife/deployments","created_at":"2013-03-05T08:18:59Z","updated_at":"2019-07-17T07:15:01Z","pushed_at":"2019-06-10T12:43:20Z","git_url":"git://github.com/JakeWharton/butterknife.git","ssh_url":"git@github.com:JakeWharton/butterknife.git","clone_url":"https://github.com/JakeWharton/butterknife.git","svn_url":"https://github.com/JakeWharton/butterknife","homepage":"http://jakewharton.github.io/butterknife/","size":3814,"stargazers_count":24215,"watchers_count":24215,"language":"Java","has_issues":true,"has_projects":false,"has_downloads":true,"has_wiki":false,"has_pages":true,"forks_count":4572,"mirror_url":null,"archived":false,"disabled":false,"open_issues_count":109,"license":{"key":"apache-2.0","name":"Apache License 2.0","spdx_id":"Apache-2.0","url":"https://api.github.com/licenses/apache-2.0","node_id":"MDc6TGljZW5zZTI="},"forks":4572,"open_issues":109,"watchers":24215,"default_branch":"master","score":186.7392}]
     */

    private int total_count;
    private boolean incomplete_results;
    private List<ItemsBean> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

     
    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

     
    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return super.equals(obj);
        }

        /**
         * id : 8575137
         * node_id : MDEwOlJlcG9zaXRvcnk4NTc1MTM3
         * name : butterknife
         * full_name : JakeWharton/butterknife
         * private : false
         * owner : {"login":"JakeWharton","id":66577,"node_id":"MDQ6VXNlcjY2NTc3","avatar_url":"https://avatars0.githubusercontent.com/u/66577?v=4","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","html_url":"https://github.com/JakeWharton","followers_url":"https://api.github.com/users/JakeWharton/followers","following_url":"https://api.github.com/users/JakeWharton/following{/other_user}","gists_url":"https://api.github.com/users/JakeWharton/gists{/gist_id}","starred_url":"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/JakeWharton/subscriptions","organizations_url":"https://api.github.com/users/JakeWharton/orgs","repos_url":"https://api.github.com/users/JakeWharton/repos","events_url":"https://api.github.com/users/JakeWharton/events{/privacy}","received_events_url":"https://api.github.com/users/JakeWharton/received_events","type":"User","site_admin":false}
         * html_url : https://github.com/JakeWharton/butterknife
         * description : Bind Android views and callbacks to fields and methods.
         * fork : false
         * url : https://api.github.com/repos/JakeWharton/butterknife
         * forks_url : https://api.github.com/repos/JakeWharton/butterknife/forks
         * keys_url : https://api.github.com/repos/JakeWharton/butterknife/keys{/key_id}
         * collaborators_url : https://api.github.com/repos/JakeWharton/butterknife/collaborators{/collaborator}
         * teams_url : https://api.github.com/repos/JakeWharton/butterknife/teams
         * hooks_url : https://api.github.com/repos/JakeWharton/butterknife/hooks
         * issue_events_url : https://api.github.com/repos/JakeWharton/butterknife/issues/events{/number}
         * events_url : https://api.github.com/repos/JakeWharton/butterknife/events
         * assignees_url : https://api.github.com/repos/JakeWharton/butterknife/assignees{/user}
         * branches_url : https://api.github.com/repos/JakeWharton/butterknife/branches{/branch}
         * tags_url : https://api.github.com/repos/JakeWharton/butterknife/tags
         * blobs_url : https://api.github.com/repos/JakeWharton/butterknife/git/blobs{/sha}
         * git_tags_url : https://api.github.com/repos/JakeWharton/butterknife/git/tags{/sha}
         * git_refs_url : https://api.github.com/repos/JakeWharton/butterknife/git/refs{/sha}
         * trees_url : https://api.github.com/repos/JakeWharton/butterknife/git/trees{/sha}
         * statuses_url : https://api.github.com/repos/JakeWharton/butterknife/statuses/{sha}
         * languages_url : https://api.github.com/repos/JakeWharton/butterknife/languages
         * stargazers_url : https://api.github.com/repos/JakeWharton/butterknife/stargazers
         * contributors_url : https://api.github.com/repos/JakeWharton/butterknife/contributors
         * subscribers_url : https://api.github.com/repos/JakeWharton/butterknife/subscribers
         * subscription_url : https://api.github.com/repos/JakeWharton/butterknife/subscription
         * commits_url : https://api.github.com/repos/JakeWharton/butterknife/commits{/sha}
         * git_commits_url : https://api.github.com/repos/JakeWharton/butterknife/git/commits{/sha}
         * comments_url : https://api.github.com/repos/JakeWharton/butterknife/comments{/number}
         * issue_comment_url : https://api.github.com/repos/JakeWharton/butterknife/issues/comments{/number}
         * contents_url : https://api.github.com/repos/JakeWharton/butterknife/contents/{+path}
         * compare_url : https://api.github.com/repos/JakeWharton/butterknife/compare/{base}...{head}
         * merges_url : https://api.github.com/repos/JakeWharton/butterknife/merges
         * archive_url : https://api.github.com/repos/JakeWharton/butterknife/{archive_format}{/ref}
         * downloads_url : https://api.github.com/repos/JakeWharton/butterknife/downloads
         * issues_url : https://api.github.com/repos/JakeWharton/butterknife/issues{/number}
         * pulls_url : https://api.github.com/repos/JakeWharton/butterknife/pulls{/number}
         * milestones_url : https://api.github.com/repos/JakeWharton/butterknife/milestones{/number}
         * notifications_url : https://api.github.com/repos/JakeWharton/butterknife/notifications{?since,all,participating}
         * labels_url : https://api.github.com/repos/JakeWharton/butterknife/labels{/name}
         * releases_url : https://api.github.com/repos/JakeWharton/butterknife/releases{/id}
         * deployments_url : https://api.github.com/repos/JakeWharton/butterknife/deployments
         * created_at : 2013-03-05T08:18:59Z
         * updated_at : 2019-07-17T07:15:01Z
         * pushed_at : 2019-06-10T12:43:20Z
         * git_url : git://github.com/JakeWharton/butterknife.git
         * ssh_url : git@github.com:JakeWharton/butterknife.git
         * clone_url : https://github.com/JakeWharton/butterknife.git
         * svn_url : https://github.com/JakeWharton/butterknife
         * homepage : http://jakewharton.github.io/butterknife/
         * size : 3814
         * stargazers_count : 24215
         * watchers_count : 24215
         * language : Java
         * has_issues : true
         * has_projects : false
         * has_downloads : true
         * has_wiki : false
         * has_pages : true
         * forks_count : 4572
         * mirror_url : null
         * archived : false
         * disabled : false
         * open_issues_count : 109
         * license : {"key":"apache-2.0","name":"Apache License 2.0","spdx_id":"Apache-2.0","url":"https://api.github.com/licenses/apache-2.0","node_id":"MDc6TGljZW5zZTI="}
         * forks : 4572
         * open_issues : 109
         * watchers : 24215
         * default_branch : master
         * score : 186.7392
         */


        private int id;
        private String node_id;
        private String full_name;
        private OwnerBean owner;

         
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

         
        public String getNode_id() {
            return node_id;
        }

        public void setNode_id(String node_id) {
            this.node_id = node_id;
        }

         
        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

         
        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public static class OwnerBean  {
            /**
             * login : JakeWharton
             * id : 66577
             * node_id : MDQ6VXNlcjY2NTc3
             * avatar_url : https://avatars0.githubusercontent.com/u/66577?v=4
             * gravatar_id : 
             * url : https://api.github.com/users/JakeWharton
             * html_url : https://github.com/JakeWharton
             * followers_url : https://api.github.com/users/JakeWharton/followers
             * following_url : https://api.github.com/users/JakeWharton/following{/other_user}
             * gists_url : https://api.github.com/users/JakeWharton/gists{/gist_id}
             * starred_url : https://api.github.com/users/JakeWharton/starred{/owner}{/repo}
             * subscriptions_url : https://api.github.com/users/JakeWharton/subscriptions
             * organizations_url : https://api.github.com/users/JakeWharton/orgs
             * repos_url : https://api.github.com/users/JakeWharton/repos
             * events_url : https://api.github.com/users/JakeWharton/events{/privacy}
             * received_events_url : https://api.github.com/users/JakeWharton/received_events
             * type : User
             * site_admin : false
             */

            private String login;
            private int id;
            private String node_id;
            private String url;
            private String html_url;
            private String received_events_url;
            private String type;
            private boolean site_admin;

             
            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

             
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

             
            public String getNode_id() {
                return node_id;
            }

            public void setNode_id(String node_id) {
                this.node_id = node_id;
            }

             
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

             
            public String getHtml_url() {
                return html_url;
            }

            public void setHtml_url(String html_url) {
                this.html_url = html_url;
            }

             
            public String getReceived_events_url() {
                return received_events_url;
            }

            public void setReceived_events_url(String received_events_url) {
                this.received_events_url = received_events_url;
            }

             
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

             
            public boolean isSite_admin() {
                return site_admin;
            }

            public void setSite_admin(boolean site_admin) {
                this.site_admin = site_admin;
            }
        }

        public static class LicenseBean  {
            /**
             * key : apache-2.0
             * name : Apache License 2.0
             * spdx_id : Apache-2.0
             * url : https://api.github.com/licenses/apache-2.0
             * node_id : MDc6TGljZW5zZTI=
             */

            private String key;
            private String name;
            private String spdx_id;
            private String url;
            private String node_id;

             
            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

             
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

             
            public String getSpdx_id() {
                return spdx_id;
            }

            public void setSpdx_id(String spdx_id) {
                this.spdx_id = spdx_id;
            }

             
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

             
            public String getNode_id() {
                return node_id;
            }

            public void setNode_id(String node_id) {
                this.node_id = node_id;
            }
        }
    }
}
