package pl.solutions.software.sokolik.bartosz.github.domain;

import io.vavr.collection.List;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

public interface GithubOperation {

    GithubResponse findByOwnerAndRepositoryName(final String owner, final String repositoryName);

    GithubResponse findByOwnerAndRepositoryNameWithRetrofit(final String owner, final String repositoryName);

    List<GithubResponse> findByOwnerName(final String owner);

}
