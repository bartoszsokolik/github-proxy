package pl.solutions.software.sokolik.bartosz.repository.domain;

import io.vavr.collection.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.solutions.software.sokolik.bartosz.github.domain.DefaultGithubOperation;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponseList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    @Mock
    private DefaultGithubOperation defaultGithubOperation;

    @Mock
    private RepositoryAssembler repositoryAssembler;

    @InjectMocks
    private RepositoryService repositoryService;

    @Test
    public void testFindByOwnerAndRepositoryName() {
        final String owner = "Owner";
        final String repositoryName = "Repo";

        GithubResponse githubResponse = new GithubResponse();
        RepositoryResponse expected = new RepositoryResponse();

        when(defaultGithubOperation.findByOwnerAndRepositoryName(anyString(), anyString())).thenReturn(githubResponse);
        when(repositoryAssembler.fromGithubResponse(any(GithubResponse.class))).thenReturn(expected);

        RepositoryResponse actual = repositoryService.findByOwnerAndRepositoryName(owner, repositoryName);

        assertEquals(expected, actual);
        verify(defaultGithubOperation).findByOwnerAndRepositoryName(owner, repositoryName);
        verify(repositoryAssembler).fromGithubResponse(githubResponse);
    }

    @Test
    public void testFindByOwner() {
        final String owner = "Owner";

        GithubResponse githubResponse = new GithubResponse();
        RepositoryResponse repositoryResponse = new RepositoryResponse();
        RepositoryResponseList expected = new RepositoryResponseList(List.of(repositoryResponse));

        when(defaultGithubOperation.findByOwnerName(anyString())).thenReturn(List.of(githubResponse));
        when(repositoryAssembler.fromGithubResponse(any(GithubResponse.class))).thenReturn(repositoryResponse);

        RepositoryResponseList actual = repositoryService.findByOwner(owner);

        assertThat(actual.getRepositories().asJava(), hasSize(1));
        assertEquals(expected.getRepositories().get(0), actual.getRepositories().get(0));
    }

}
