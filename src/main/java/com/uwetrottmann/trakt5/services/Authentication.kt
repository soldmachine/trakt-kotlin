package com.uwetrottmann.trakt5.services

import com.uwetrottmann.trakt5.TraktV2
import com.uwetrottmann.trakt5.entities.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Authentication {

    @FormUrlEncoded
    @POST(TraktV2.OAUTH2_TOKEN_URL)
    fun exchangeCodeForAccessToken(
            @Field("grant_type") grantType: String,
            @Field("code") code: String,
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>

    @FormUrlEncoded
    @POST(TraktV2.OAUTH2_TOKEN_URL)
    fun refreshAccessToken(
            @Field("grant_type") grantType: String,
            @Field("refresh_token") refreshToken: String,
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>

}
