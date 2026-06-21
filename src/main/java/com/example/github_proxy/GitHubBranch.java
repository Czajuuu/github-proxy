package com.example.github_proxy;

public class GitHubBranch {
    private final String name;
    private final String lastCommitSha;

    GitHubBranch(String name, String lastCommitSha){
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }
    public String getName() {return name;}
    public String getLastCommitSha() {return lastCommitSha; }
}
