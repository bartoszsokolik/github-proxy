package pl.solutions.software.sokolik.bartosz.github.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GithubServiceTest {

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubService githubService;

    @Test
    public void findByOwnerAndRepositoryName() {
        final String owner = "Owner";
        final String repo = "Repository";

        GithubResponse expected = new GithubResponse();

        when(githubClient.findByOwnerAndRepositoryName(anyString(), anyString())).thenReturn(expected);

        GithubResponse actual = githubService.findByOwnerAndRepositoryName(owner, repo);

        assertEquals(expected, actual);
        verify(githubClient).findByOwnerAndRepositoryName(owner, repo);
    }

    @Test
    public void findByOwnerName() {
        final String owner = "Owner";
        List<GithubResponse> expected = Collections.singletonList(new GithubResponse());

        when(githubClient.findAllRepositoriesByUsername(anyString())).thenReturn(expected);

        List<GithubResponse> actual = githubService.findByOwnerName(owner);

        assertEquals(expected, actual);
        verify(githubClient).findAllRepositoriesByUsername(owner);
    }
}