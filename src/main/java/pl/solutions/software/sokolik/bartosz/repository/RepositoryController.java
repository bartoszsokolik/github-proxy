package pl.solutions.software.sokolik.bartosz.repository;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solutions.software.sokolik.bartosz.repository.domain.RepositoryService;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponseList;

@RestController
@RequestMapping("/api/repos")
@RequiredArgsConstructor
class RepositoryController {

  private final RepositoryService repositoryService;

  @GetMapping("/{owner}/{repositoryName}")
  ResponseEntity<RepositoryResponse> findByOwnerAndRepositoryName(@PathVariable String owner, @PathVariable String repositoryName) {
    return new ResponseEntity<>(repositoryService.findByOwnerAndRepositoryName(owner, repositoryName), OK);
  }

  @GetMapping("/retro/{owner}/{repositoryName}")
  ResponseEntity<RepositoryResponse> findByOwnerAndRepositoryNameRetro(@PathVariable String owner, @PathVariable String repositoryName) {
      return new ResponseEntity<>(repositoryService.findByOwnerAndRepositoryNameRetro(owner, repositoryName), OK);
  }

  @GetMapping("/{owner}")
  ResponseEntity<RepositoryResponseList> findByOwner(@PathVariable String owner) {
    return new ResponseEntity<>(repositoryService.findByOwner(owner), OK);
  }

}
