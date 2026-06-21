package com.example.github_proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
class GitHubClient {

    private final RestClient restClient;

    GitHubClient(@Value("${github.api.url:https://api.github.com}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/vnd.github.v3+json")
                .defaultHeader("User-Agent", "github-proxy")
                .build();
    }

    List<GitHubClientRepo> getUserRepos(String username) {
        GitHubClientRepo[] repos = restClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .body(GitHubClientRepo[].class);
        return repos == null ? List.of() : Arrays.asList(repos);
    }

    List<GitHubClientBranch> getRepoBranches(String username, String repoName) {
        GitHubClientBranch[] branches = restClient.get()
                .uri("/repos/{username}/{repo}/branches", username, repoName)
                .retrieve()
                .body(GitHubClientBranch[].class);
        return branches == null ? List.of() : Arrays.asList(branches);
    }
}