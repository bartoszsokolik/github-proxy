package pl.solutions.software.sokolik.bartosz.github.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitGithubClientConfig {

  private String url;

  public RetrofitGithubClientConfig(@Value("${github.api.url}") String url) {
    this.url = url;
  }

  @Bean
  public RetrofitGithubClient retrofitGithubClient(ObjectMapper objectMapper) {
    OkHttpClient okHttpClient = new OkHttpClient();
    return new Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .client(okHttpClient)
        .build()
        .create(RetrofitGithubClient.class);
  }
}
