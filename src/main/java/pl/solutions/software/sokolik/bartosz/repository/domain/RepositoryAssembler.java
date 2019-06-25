package pl.solutions.software.sokolik.bartosz.repository.domain;

import org.springframework.stereotype.Component;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;

@Component
class RepositoryAssembler {

    RepositoryResponse fromGithubResponse(GithubResponse githubResponse) {
        return RepositoryResponse.builder()
                .fullName(githubResponse.getFullName())
                .name(githubResponse.getName())
                .description(githubResponse.getDescription())
                .cloneUrl(githubResponse.getCloneUrl())
                .stars(githubResponse.getStars())
                .createdAt(githubResponse.getCreatedAt())
                .url(githubResponse.getHtmlUrl())
                .build();
    }
}
