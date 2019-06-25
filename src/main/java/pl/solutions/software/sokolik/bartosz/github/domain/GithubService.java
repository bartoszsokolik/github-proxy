package pl.solutions.software.sokolik.bartosz.github.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public GithubResponse findByOwnerAndRepositoryName(final String owner, final String repositoryName) {
        return githubClient.findByOwnerAndRepositoryName(owner, repositoryName);
    }

    public List<GithubResponse> findByOwnerName(final String owner) {
        return githubClient.findAllRepositoriesByUsername(owner);
    }
}
