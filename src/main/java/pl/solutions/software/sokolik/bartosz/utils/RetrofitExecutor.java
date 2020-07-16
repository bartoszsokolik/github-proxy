package pl.solutions.software.sokolik.bartosz.utils;

import io.vavr.control.Try;
import lombok.experimental.UtilityClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@UtilityClass
public class RetrofitExecutor {

    public static <T> T execute(Call<T> call) {
        return tryExecute(call).get();
    }

    public static <T> Try<T> tryExecute(Call<T> call) {
        return Try.of(call::execute)
                .flatMap(r -> r.isSuccessful() ?
                        Try.success(r.body()) :
                        Try.failure(new RuntimeException("External service error: " + getResponseBody(r))));
    }

    private static String getResponseBody(Response<?> response) {
        return Try.of(response::errorBody)
                .mapTry(ResponseBody::string)
                .recoverWith(e -> Try.success(e.getMessage()))
                .get();
    }
}
