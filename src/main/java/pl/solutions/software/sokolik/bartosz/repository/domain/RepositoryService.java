package pl.solutions.software.sokolik.bartosz.repository.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.github.domain.DefaultGithubOperation;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponseList;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final DefaultGithubOperation defaultGithubOperation;
    private final RepositoryAssembler repositoryAssembler;

    public RepositoryResponse findByOwnerAndRepositoryName(final String owner, final String repositoryName) {
        return repositoryAssembler.fromGithubResponse(defaultGithubOperation.findByOwnerAndRepositoryName(owner, repositoryName));
    }

    public RepositoryResponse findByOwnerAndRepositoryNameRetro(final String owner, final String repositoryName) {
        return repositoryAssembler.fromGithubResponse(defaultGithubOperation.findByOwnerAndRepositoryNameWithRetrofit(owner, repositoryName));
    }

    public RepositoryResponseList findByOwner(final String owner) {
        List<RepositoryResponse> repositories = defaultGithubOperation.findByOwnerName(owner).stream()
                .map(repositoryAssembler::fromGithubResponse)
                .collect(Collectors.toList());
        return new RepositoryResponseList(repositories);
    }
}
