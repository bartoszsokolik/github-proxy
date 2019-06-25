package pl.solutions.software.sokolik.bartosz.repository.domain;

import org.junit.Before;
import org.junit.Test;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class RepositoryAssemblerTest {

    private static final String FULL_NAME = "name";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String HTML_URL = "html url";
    private static final String CLONE_URL = "clone url";
    private static final int STARS = 5;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2019, 1, 1, 1, 1, 1);

    private RepositoryAssembler repositoryAssembler;

    @Before
    public void setUp() {
        repositoryAssembler = new RepositoryAssembler();
    }

    @Test
    public void testFromGithubResponse() {
        GithubResponse githubResponse = GithubResponse.builder()
                .fullName(FULL_NAME)
                .name(NAME)
                .description(DESCRIPTION)
                .htmlUrl(HTML_URL)
                .stars(STARS)
                .cloneUrl(CLONE_URL)
                .createdAt(CREATED_AT)
                .build();

        RepositoryResponse expected = RepositoryResponse.builder()
                .fullName(FULL_NAME)
                .name(NAME)
                .description(DESCRIPTION)
                .url(HTML_URL)
                .stars(STARS)
                .cloneUrl(CLONE_URL)
                .createdAt(CREATED_AT)
                .build();


        RepositoryResponse actual = repositoryAssembler.fromGithubResponse(githubResponse);

        assertEquals(expected, actual);
    }
}
