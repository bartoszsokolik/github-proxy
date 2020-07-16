package pl.solutions.software.sokolik.bartosz.github.domain;

import io.vavr.collection.List;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import static pl.solutions.software.sokolik.bartosz.SpringProfiles.MOCK;

@Primary
@Service
@Profile(MOCK)
public class MockGithubOperation implements GithubOperation {

    @Override
    public GithubResponse findByOwnerAndRepositoryName(String owner, String repositoryName) {
        return null;
    }

    @Override
    public GithubResponse findByOwnerAndRepositoryNameWithRetrofit(String owner, String repositoryName) {
        return null;
    }

    @Override
    public List<GithubResponse> findByOwnerName(String owner) {
        return null;
    }
}
