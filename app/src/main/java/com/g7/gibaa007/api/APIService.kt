package com.g7.gibaa007.api

import com.g7.gibaa007.pojo.CommonPojo
import com.g7.gibaa007.pojo.ProfilePojo

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by gibaa007 on 26/2/18.
 */

interface APIService {
    @POST("login")
    @FormUrlEncoded
    fun login(
            @Field("user_name") uname: String,
            @Field("password") pass: String,
            @Field("device_token") device: String,
            @Field("platform") plat: String): Call<CommonPojo<ProfilePojo>>


    //    @POST("forgot_password")
    //    @FormUrlEncoded
    //    Observable<CommonPojo<ProfilePojo>> forgot(@Field("email") String uname);


    /* @POST("change_password")
    @FormUrlEncoded
    Observable<CommonPojo<ProfilePojo>> changePassword(@Field("password") String password, @HeaderMap() Map<String, String> header);


    @POST("registration")
    @FormUrlEncoded
    Observable<CommonPojo<ProfilePojo>> signUp(@Field("username") String uname,
                                               @Field("email") String email,
                                               @Field("password") String pass,
                                               @Field("verify_me") String verify,
                                               @Field("device") String android);


    @POST("update_profile")
    @FormUrlEncoded
    Observable<CommonPojo<ProfilePojo>> updateProfile(@Field("email") String email,
                                                      @Field("nickname") String nickname,
                                                      @Field("address") String address,
                                                      @Field("about_me") String about_me,
                                                      @Field("verify_me") String verify,
                                                      @HeaderMap() Map<String, String> header);

    @GET("upload_profile_image_notify")
    Observable<CommonPojo> updateProfilePic(@HeaderMap() Map<String, String> header);*/

    //
    //    @GET("user")
    //    Call<CommonPojo<Contributor>> getUser(@QueryMap Map<String, String> params);
    //
    //    @POST("user")
    //    Call<Contributor> postUser(@QueryMap Map<String, String> params);
    //
    //    @PUT("user")
    //    Call<Contributor> updateUser(@QueryMap Map<String, String> params);
    //
    //
    //    @GET("users")
    //    Call<List<Contributor>> getUsers();
    //
    //    @GET("users/{name}/commits")
    //    Call<List<Contributor>> getCommitsByName(@Path("name") String name);
    //
    //    @GET("users")
    //    Call<Contributor> getUserById(@Query("id") Integer id);
    //
    //    @POST("users")
    //    Call<Contributor> postUser(@Body Contributor user);
    //
    //
    //    @Multipart
    //    @POST("image/upload")
    //    Observable<Contributor> uploadImage(@Header("Header") String token,
    //                                        @Part("userId") String userId,
    //                                        @Part("images") RequestBody file);
    //
    //
    //    @GET("books")
    //    Call<ArrayList<ProfilePojo>> listBooks();
    //
    //    @POST("books")
    //    Call<ProfilePojo> addBook(@Body ProfilePojo book);
    //
    //    @GET("books/{id}/")
    //    Call<ProfilePojo> getBookInfo(@Path("id") int bookId);
    //
    //    @DELETE("books/{id}/")
    //    Call<Void> deleteBook(@Path("id") int bookId);
    //
    //    @PUT("books/{id}/")
    //    Call<ProfilePojo> updateBook(@Path("id") int bookId, @Body ProfilePojo book);
    //
    //    @DELETE("clean/")
    //    Call<Void> deleteAll();
    //
    //
    //    @DELETE("/api/item/{id}")
    //    Call<Contributor> deleteItem(@Path("id") int itemId);
    //
    //
    //    @Multipart
    //    @POST("/post")
    //    Call<Contributor> addBookCover(@Part("id") RequestBody id, @Part MultipartBody.Part photo);


}
