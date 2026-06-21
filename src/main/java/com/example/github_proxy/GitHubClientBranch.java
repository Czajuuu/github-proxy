package com.example.github_proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

class GitHubClientBranch {

    private String name;

    @JsonProperty("commit")
    private Commit commit;

    String getName() { return name; }
    Commit getCommit() { return commit; }

    void setName(String name) { this.name = name; }
    void setCommit(Commit commit) { this.commit = commit; }

    static class Commit {
        private String sha;
        String getSha() { return sha; }
        void setSha(String sha) { this.sha = sha; }
    }
}