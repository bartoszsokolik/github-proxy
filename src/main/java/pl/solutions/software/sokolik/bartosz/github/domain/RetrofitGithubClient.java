package pl.solutions.software.sokolik.bartosz.github.domain;

import io.reactivex.Single;
import pl.solutions.software.sokolik.bartosz.github.domain.dto.GithubResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface RetrofitGithubClient {

  @GET("/repos/{owner}/{repositoryName}")
  Single<GithubResponse> findByOwnerAndRepositoryName(@Path("owner") String owner, @Path("repositoryName") String repositoryName);

}
