package com.example.github_proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class GitHubController {

    private final GitHubService gitHubService;

    GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/users/{username}/repositories")
    ResponseEntity<?> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(gitHubService.getUserRepos(username));
    }
}