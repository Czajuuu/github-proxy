package com.example.github_proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

class GitHubClientRepo {

    private String name;
    private boolean fork;

    @JsonProperty("owner")
    private Owner owner;

    String getName() { return name; }
    boolean isFork() { return fork; }
    Owner getOwner() { return owner; }

    void setName(String name) { this.name = name; }
    void setFork(boolean fork) { this.fork = fork; }
    void setOwner(Owner owner) { this.owner = owner; }

    static class Owner {
        private String login;
        String getLogin() { return login; }
        void setLogin(String login) { this.login = login; }
    }
}