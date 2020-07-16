package pl.solutions.software.sokolik.bartosz.github.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitGithubClientConfig {

    private final Duration readTimeout;

    public RetrofitGithubClientConfig(@Value("${http.read.timeout:5s}") Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Bean
    public RetrofitGithubClient retrofitGithubClient(@Value("${github.api.url}") String url, ObjectMapper objectMapper) {
        return builder(jacksonConverterFactory(objectMapper), url)
                .build()
                .create(RetrofitGithubClient.class);
    }

    private Retrofit.Builder builder(Converter.Factory converterFactory, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .client(httpClient());
    }

    private JacksonConverterFactory jacksonConverterFactory(ObjectMapper objectMapper) {
        return JacksonConverterFactory.create(objectMapper);
    }

    private OkHttpClient httpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(readTimeout.toMillis(), TimeUnit.SECONDS)
                .build();
    }
}
