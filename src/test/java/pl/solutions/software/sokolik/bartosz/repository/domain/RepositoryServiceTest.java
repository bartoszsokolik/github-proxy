package pl.solutions.software.sokolik.bartosz.repository.domain;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.solutions.software.sokolik.bartosz.github.domain.GithubService;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponseList;

import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    @Mock
    private GithubService githubService;

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

        when(githubService.findByOwnerAndRepositoryName(anyString(), anyString())).thenReturn(githubResponse);
        when(repositoryAssembler.fromGithubResponse(any(GithubResponse.class))).thenReturn(expected);

        RepositoryResponse actual = repositoryService.findByOwnerAndRepositoryName(owner, repositoryName);

        assertEquals(expected, actual);
        verify(githubService).findByOwnerAndRepositoryName(owner, repositoryName);
        verify(repositoryAssembler).fromGithubResponse(githubResponse);
    }

    @Test
    public void testFindByOwner() {
        final String owner = "Owner";

        GithubResponse githubResponse = new GithubResponse();
        RepositoryResponse repositoryResponse = new RepositoryResponse();
        RepositoryResponseList expected = new RepositoryResponseList(Collections.singletonList(repositoryResponse));

        when(githubService.findByOwnerName(anyString())).thenReturn(Collections.singletonList(githubResponse));
        when(repositoryAssembler.fromGithubResponse(any(GithubResponse.class))).thenReturn(repositoryResponse);

        RepositoryResponseList actual = repositoryService.findByOwner(owner);

        assertThat(actual.getRepositories(), hasSize(1));
        assertEquals(expected.getRepositories().get(0), actual.getRepositories().get(0));
    }

}
