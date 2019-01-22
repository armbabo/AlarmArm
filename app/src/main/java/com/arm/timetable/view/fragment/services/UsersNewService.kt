package com.arm.timetable.view.fragment.services

import asia.teqnological.bittoco.data.dto.ObjectDto
import com.arm.timetable.view.fragment.data.User
import com.arm.timetable.view.fragment.dto.ObjectDtoNew
import io.reactivex.Observable
import retrofit2.http.*
import javax.annotation.Nullable

interface UsersNewService {
    @FormUrlEncoded
    @POST("users.json")
    fun register(@Field("email") email:String,
                 @Field("nickname") nickname:String,
                 @Field("password") password:String,
                 @Field("password_confirm") password_confirm:String): Observable<ObjectDtoNew<User>>

    @POST("users/signin.json")
    fun signIn(@Body user: User): Observable<ObjectDto<User>>

    @GET("questions.json")
    fun getQuestion(): Observable<ObjectDto<Any>>

    @FormUrlEncoded
    @POST("users/verify.json")
    fun verifyCode(@Field("email") email:String,
                   @Field("verify_code") code:String) : Observable<ObjectDto<User>>

    @FormUrlEncoded
    @POST("users/resend_code.json")
    fun resendCode(@Nullable @Field("email") email: String?,
                   @Nullable @Field("new_email") new_email: String?): Observable<ObjectDto<Any>>
}