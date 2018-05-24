package app.regime.com.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroProvider {
    public static final String BASE_URL = "https://regim.herokuapp.com/";

    private static Retrofit retroInstance;
    private static ApiInterface client;
    public static  ApiInterface getClient() {
//
//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(100, TimeUnit.SECONDS)
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .build();
//
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .client(okHttpClient)
//                    .build();
//        }
//        return retrofit;
//    }

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("x-access-key", "UUAU-13T6-10R9-L6R5")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
        if (retroInstance == null) {
            retroInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()
                    )
                    .build();
        client = retroInstance.create(ApiInterface.class);

        }
        return client;
}





}
