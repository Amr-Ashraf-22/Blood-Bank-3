package com.elatienda.kaytamarka.bloodbank.data.api;

import com.elatienda.kaytamarka.bloodbank.data.model.general_response.GeneralResponse;
import com.elatienda.kaytamarka.bloodbank.data.model.login.Login;
import com.elatienda.kaytamarka.bloodbank.data.model.new_password.NewPassword;
import com.elatienda.kaytamarka.bloodbank.data.model.post.Post;
import com.elatienda.kaytamarka.bloodbank.data.model.register.Register;
import com.elatienda.kaytamarka.bloodbank.data.model.reset_password.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("blood-types")
    Call<GeneralResponse> getBloodTypes();

    @GET("governorates")
    Call<GeneralResponse> getGovernorates();

    @GET("cities")
    Call<GeneralResponse> getCities(@Query("governorate_id") int governorateId);

    @POST("signup")
    @FormUrlEncoded
    Call<Register> onRegister(@Field("name") String name,
                              @Field("email") String email,
                              @Field("birth_date") String birthDate,
                              @Field("city_id") int cityId,
                              @Field("phone") String phone,
                              @Field("donation_last_date") String donationLastDate,
                              @Field("password") String password,
                              @Field("password_confirmation") String passwordConfirmation,
                              @Field("blood_type_id") int bloodTypeId);

    @POST("login")
    @FormUrlEncoded
    Call<Login> onLogin(@Field("phone") String phone,
                        @Field("password") String password);

    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> onResetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> setNewPassword(@Field("password") String password,
                                     @Field("password_confirmation") String passwordConfirmation,
                                     @Field("pin_code") int pinCode,
                                     @Field("phone") String phone);

    @GET("categories")
    Call<GeneralResponse> getCategories();

    @GET("posts")
    Call<Post> getAllPosts(@Query("api_token") String apiToken,
                           @Query("page") int page);


}
