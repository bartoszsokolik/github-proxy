package pl.solutions.software.sokolik.bartosz.github.domain;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import static pl.solutions.software.sokolik.bartosz.utils.RetrofitExecutor.execute;

@Service
@RequiredArgsConstructor
public class DefaultGithubOperation implements GithubOperation {

    private final GithubClient githubClient;
    private final RetrofitGithubClient retrofitGithubClient;

    public GithubResponse findByOwnerAndRepositoryName(final String owner, final String repositoryName) {
        return githubClient.findByOwnerAndRepositoryName(owner, repositoryName);
    }

    public GithubResponse findByOwnerAndRepositoryNameWithRetrofit(final String owner, final String repositoryName) {
        return execute(retrofitGithubClient.findByOwnerAndRepositoryName(owner, repositoryName));
    }

    public List<GithubResponse> findByOwnerName(final String owner) {
        return githubClient.findAllRepositoriesByUsername(owner);
    }
}
