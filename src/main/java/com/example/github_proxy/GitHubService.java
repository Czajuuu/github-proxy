package com.example.github_proxy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GitHubService {
    private final GitHubClient gitHubClient;

    GitHubService(GitHubClient gitHubClient) {

        this.gitHubClient = gitHubClient;
    }

    List<GitHubRepo> getUserRepos(String username) {
        return gitHubClient.getUserRepos(username).stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    List<GitHubBranch> branches = gitHubClient.getRepoBranches(username, repo.getName())
                            .stream()
                            .map(b -> new GitHubBranch(b.getName(), b.getCommit().getSha()))
                            .toList();
                    return new GitHubRepo(repo.getName(), repo.getOwner().getLogin(), branches);
                })
                .toList();
    }
}