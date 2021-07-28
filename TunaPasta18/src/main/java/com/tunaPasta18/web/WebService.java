package com.tunaPasta18.web;

import com.tunaPasta18.bean.ResponseBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <p>所有的服务器接口集合。</p>
 */
public interface WebService {

    @GET("api/token")
    Observable<ResponseBean> getToken(@Query("clientId") String clientId, @Query("channelId") String channelId,
                                      @Query("SECOND_CHANNEL") String secondChannel, @Query("APP_ID") String appId);

    @POST("api/recognition")
    Observable<ResponseBean> postFaceDetectByJSON(@Body RequestBody body);

}
