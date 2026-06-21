# GitHub Proxy

A Spring Boot application that acts as a proxy for the GitHub API.

## Description

Exposes a REST endpoint to list all non-fork repositories of a given GitHub user, including branch names and their last commit SHA.

## Tech Stack

- Java 25
- Spring Boot 4.1.0
- Gradle (Kotlin DSL)

## How to run

```bash
./gradlew bootRun
```

## Endpoints

### GET /users/{username}/repositories

Returns all non-fork repositories for the given GitHub user.

**Success response (200):**
```json
[
  {
    "name": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "abc123"
      }
    ]
  }
]
```

**User not found (404):**
```json
{
  "status": 404,
  "message": "GitHub user not found"
}
```

## Running tests

```bash
./gradlew test
```