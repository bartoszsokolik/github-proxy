package pl.solutions.software.sokolik.bartosz.github.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubResponse {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "clone_url")
    private String cloneUrl;

    @JsonProperty(value = "stargazers_count")
    private int stars;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "html_url")
    private String htmlUrl;
}
