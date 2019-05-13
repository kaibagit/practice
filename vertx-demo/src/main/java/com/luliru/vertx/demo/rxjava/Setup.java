package com.luliru.vertx.demo.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.file.FileSystem;
import io.vertx.reactivex.core.http.HttpClient;
import io.vertx.reactivex.core.http.HttpClientRequest;
import io.vertx.reactivex.core.http.HttpClientResponse;
import org.reactivestreams.Publisher;

import java.time.ZonedDateTime;

import static java.lang.String.format;

public class Setup {

    public static void main(String[] args){
        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        HttpClient httpClient = vertx.createHttpClient();

//        fileSystem
//                .rxReadFile("cities.txt").toFlowable()
//                .flatMap(buffer -> Flowable.fromArray(buffer.toString().split("\\r?\\n")))
//                .flatMap(city -> searchByCityName(httpClient, city))
//                .flatMap(HttpClientResponse::toFlowable)
//                .map(extractingWoeid())
//                .flatMap(cityId -> getDataByPlaceId(httpClient, cityId))
//                .flatMap(toBufferFlowable())
//                .map(Buffer::toJsonObject)
//                .map(toCityAndDayLength())
//                .subscribe(System.out::println, Throwable::printStackTrace);

    }

    public static Flowable<HttpClientResponse> searchByCityName(HttpClient httpClient, String cityName) {
        HttpClientRequest req = httpClient.get(
                new RequestOptions()
                        .setHost("www.metaweather.com")
                        .setPort(443)
                        .setSsl(true)
                        .setURI(format("/api/location/search/?query=%s", cityName)));
        return req
                .toFlowable()
                .doOnSubscribe(subscription -> req.end());
    }

    private static Function<Buffer, Long> extractingWoeid() {
        return cityBuffer -> cityBuffer
                .toJsonArray()
                .getJsonObject(0)
                .getLong("woeid");
    }

//    static Flowable<HttpClientResponse> getDataByPlaceId(
//            HttpClient httpClient, long placeId) {
//
//        return autoPerformingReq(
//                httpClient,
//                format("/api/location/%s/", placeId));
//    }

    static Function<HttpClientResponse, Publisher<? extends Buffer>>
    toBufferFlowable() {
        return response -> response
                .toObservable()
                .reduce(
                        Buffer.buffer(),
                        Buffer::appendBuffer).toFlowable();
    }

//    static Function<JsonObject, CityAndDayLength> toCityAndDayLength() {
//        return json -> {
//            ZonedDateTime sunRise = ZonedDateTime.parse(json.getString("sun_rise"));
//            ZonedDateTime sunSet = ZonedDateTime.parse(json.getString("sun_set"));
//            String cityName = json.getString("title");
//            return new CityAndDayLength(
//                    cityName, sunSet.toEpochSecond() - sunRise.toEpochSecond());
//        };
//    }
}
