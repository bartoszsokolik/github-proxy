package pl.solutions.software.sokolik.bartosz.github.domain;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

@Service
@CircuitBreaker(name = "github", fallbackMethod = "fallback")
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public GithubResponse findByOwnerAndRepositoryName(final String owner, final String repositoryName) {
        return githubClient.findByOwnerAndRepositoryName(owner, repositoryName);
    }

    public List<GithubResponse> findByOwnerName(final String owner) {
        if (owner.equals("another")) {
            throw new RuntimeException("Wrong owner name");
        }
        return githubClient.findAllRepositoriesByUsername(owner);
    }

    public List<GithubResponse> fallback(String owner, RuntimeException ex) {
        return List.of(new GithubResponse());
    }
}
