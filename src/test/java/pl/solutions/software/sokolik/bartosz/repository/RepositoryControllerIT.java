package pl.solutions.software.sokolik.bartosz.repository;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import pl.solutions.software.sokolik.bartosz.repository.domain.dto.RepositoryResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "github.api.url=http://localhost:9090/githubserver"
})
public class RepositoryControllerIT {

  @LocalServerPort
  private int port;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(9090);

  @Before
  public void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  public void shouldReturnOwnersRepository() throws Exception {
    String owner = "test-owner";
    String repositoryName = "test-repo";

    GithubResponse expectedGithubResponse = GithubResponse.builder()
        .cloneUrl("test-clone-url")
        .createdAt(LocalDateTime.of(2019, 8, 1, 1, 1, 1))
        .description("description")
        .fullName("full-name")
        .htmlUrl("test-html-url")
        .name("name")
        .stars(5)
        .build();

    RepositoryResponse expected = RepositoryResponse.builder()
        .cloneUrl(expectedGithubResponse.getCloneUrl())
        .createdAt(expectedGithubResponse.getCreatedAt())
        .description(expectedGithubResponse.getDescription())
        .fullName(expectedGithubResponse.getFullName())
        .name(expectedGithubResponse.getName())
        .url(expectedGithubResponse.getHtmlUrl())
        .stars(expectedGithubResponse.getStars())
        .build();

    wireMockRule.stubFor(
        get(urlEqualTo("/githubserver/repos/" + owner + "/" + repositoryName))
            .willReturn(aResponse().withStatus(HttpStatus.OK.value())
                .withHeader("Content-type", APPLICATION_JSON_VALUE)
                .withBody(writeObjectToString(expectedGithubResponse))));

    RepositoryResponse actual = RestAssured.given()
        .when()
        .get("http://localhost:" + port + "/api/repos/" + owner + "/" + repositoryName)
        .then()
        .statusCode(HttpStatus.OK.value())
        .and()
        .extract()
        .body()
        .as(RepositoryResponse.class);

    assertEquals(expected, actual);
  }

  private String writeObjectToString(Object object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }
}
