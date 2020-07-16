package pl.solutions.software.sokolik.bartosz.github.domain;

import io.vavr.collection.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultGithubOperationTest {

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private DefaultGithubOperation defaultGithubOperation;

    @Test
    public void findByOwnerAndRepositoryName() {
        final String owner = "Owner";
        final String repo = "Repository";

        GithubResponse expected = new GithubResponse();

        when(githubClient.findByOwnerAndRepositoryName(anyString(), anyString())).thenReturn(expected);

        GithubResponse actual = defaultGithubOperation.findByOwnerAndRepositoryName(owner, repo);

        assertEquals(expected, actual);
        verify(githubClient).findByOwnerAndRepositoryName(owner, repo);
    }

    @Test
    public void findByOwnerName() {
        final String owner = "Owner";
        List<GithubResponse> expected = List.of(new GithubResponse());

        when(githubClient.findAllRepositoriesByUsername(anyString())).thenReturn(expected);

        List<GithubResponse> actual = defaultGithubOperation.findByOwnerName(owner);

        assertEquals(expected, actual);
        verify(githubClient).findAllRepositoriesByUsername(owner);
    }
}