package pl.solutions.software.sokolik.bartosz.github.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import java.util.List;

@FeignClient(url = "${github.api.url}", name = "githubClient")
interface GithubClient {

    @GetMapping("/repos/{owner}/{repositoryName}")
    GithubResponse findByOwnerAndRepositoryName(@PathVariable String owner, @PathVariable String repositoryName);

    @GetMapping("/users/{owner}/repos")
    List<GithubResponse> findAllRepositoriesByUsername(@PathVariable String owner);
}
