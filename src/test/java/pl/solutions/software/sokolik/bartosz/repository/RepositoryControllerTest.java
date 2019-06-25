package pl.solutions.software.sokolik.bartosz.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.solutions.software.sokolik.bartosz.repository.domain.RepositoryService;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponseList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryControllerTest {

    private static final String OWNER = "owner";
    private static final String REPOSITORY_NAME = "repo";

    @Mock
    private RepositoryService repositoryService;

    @InjectMocks
    private RepositoryController repositoryController;

    @Test
    public void testFindByOwnerAndRepositoryName() {
        RepositoryResponse response = mock(RepositoryResponse.class);

        when(repositoryService.findByOwnerAndRepositoryName(anyString(), anyString())).thenReturn(response);

        ResponseEntity<RepositoryResponse> expected = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<RepositoryResponse> actual = repositoryController.findByOwnerAndRepositoryName(OWNER, REPOSITORY_NAME);

        assertEquals(expected, actual);
        verify(repositoryService).findByOwnerAndRepositoryName(OWNER, REPOSITORY_NAME);
    }

    @Test
    public void findByOwnerTest() {
        RepositoryResponseList response = mock(RepositoryResponseList.class);

        when(repositoryService.findByOwner(anyString())).thenReturn(response);

        ResponseEntity<RepositoryResponseList> expected = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<RepositoryResponseList> actual = repositoryController.findByOwner(OWNER);

        assertEquals(expected, actual);
        verify(repositoryService).findByOwner(OWNER);

    }
}
