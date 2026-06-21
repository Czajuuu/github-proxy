package com.example.github_proxy;

import java.util.List;

public class GitHubRepo {
    private final String name;
    private final String ownerLogin;
    private final List<GitHubBranch> branches;

    GitHubRepo(String name, String ownerLogin, List<GitHubBranch> branches){
        this.name = name;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }
    public String getName() { return name;}
    public String getOwnerLogin() {return ownerLogin;}
    public List<GitHubBranch> getBranches() {return branches; }


}
