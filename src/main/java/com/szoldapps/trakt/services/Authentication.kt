package com.szoldapps.trakt.services

import com.szoldapps.trakt.TraktV2
import com.szoldapps.trakt.entities.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Authentication {

    @FormUrlEncoded
    @POST(com.szoldapps.trakt.TraktV2.OAUTH2_TOKEN_URL)
    fun exchangeCodeForAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>

    @FormUrlEncoded
    @POST(com.szoldapps.trakt.TraktV2.OAUTH2_TOKEN_URL)
    fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refreshToken") refreshToken: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>

}
