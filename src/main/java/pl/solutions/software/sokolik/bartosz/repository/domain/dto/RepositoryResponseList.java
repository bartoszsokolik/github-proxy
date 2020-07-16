package pl.solutions.software.sokolik.bartosz.repository.domain.dto;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryResponseList {

    List<RepositoryResponse> repositories;
}
