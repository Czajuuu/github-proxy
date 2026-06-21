package com.example.github_proxy;

import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;
import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "github.api.url=${wiremock.server.baseUrl}")
@AutoConfigureMockMvc
@EnableWireMock
class GithubProxyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnRepositoriesForExistingUser() throws Exception {
		stubFor(WireMock.get("/users/octocat/repos")
				.willReturn(okJson("""
                        [
                          {"name": "Hello-World", "fork": false, "owner": {"login": "octocat"}},
                          {"name": "forked-repo", "fork": true, "owner": {"login": "octocat"}}
                        ]
                        """)));

		stubFor(WireMock.get("/repos/octocat/Hello-World/branches")
				.willReturn(okJson("""
                        [
                          {"name": "main", "commit": {"sha": "abc123"}}
                        ]
                        """)));

		mockMvc.perform(get("/users/octocat/repositories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Hello-World"))
				.andExpect(jsonPath("$[0].ownerLogin").value("octocat"))
				.andExpect(jsonPath("$[0].branches[0].name").value("main"))
				.andExpect(jsonPath("$[0].branches[0].lastCommitSha").value("abc123"))
				.andExpect(jsonPath("$.length()").value(1));
	}

	@Test
	void shouldReturnErrorForNonExistingUser() throws Exception {
		stubFor(WireMock.get("/users/nonexistent/repos")
				.willReturn(notFound()));

		mockMvc.perform(get("/users/nonexistent/repositories"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.message").value("GitHub user not found"));
	}
}