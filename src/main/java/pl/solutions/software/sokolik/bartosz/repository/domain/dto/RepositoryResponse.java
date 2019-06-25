package pl.solutions.software.sokolik.bartosz.repository.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RepositoryResponse {

    private String fullName;

    private String name;

    private String description;

    private String cloneUrl;

    private int stars;

    private LocalDateTime createdAt;

    private String url;

}
