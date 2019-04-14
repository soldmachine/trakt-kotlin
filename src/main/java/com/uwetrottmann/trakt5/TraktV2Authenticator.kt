package com.uwetrottmann.trakt5

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TraktV2Authenticator(val trakt: TraktV2) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        return handleAuthenticate(response, trakt)
    }

    companion object {

        /**
         * If not doing a trakt [TraktV2.API_URL] request tries to refresh the access token with the refresh token.
         *
         * @param response The response passed to [.authenticate].
         * @param trakt The [TraktV2] instance to get the API key from and to set the updated JSON web token on.
         * @return A request with updated authorization header or null if no auth is possible.
         */
        @Throws(IOException::class)
        fun handleAuthenticate(response: Response, trakt: TraktV2): Request? {
            if (TraktV2.API_HOST != response.request().url().host()) {
                return null // not a trakt API endpoint (possibly trakt OAuth or other API), give up.
            }
            if (responseCount(response) >= 2) {
                return null // failed 2 times, give up.
            }
            val refreshToken = trakt.refreshToken()
            if (refreshToken == null || refreshToken.isEmpty()) {
                return null // have no refresh token, give up.
            }

            // try to refresh the access token with the refresh token
            val refreshResponse = trakt.refreshAccessToken(refreshToken)
            val body = refreshResponse.body()
            if (!refreshResponse.isSuccessful || body == null) {
                return null // failed to retrieve a token, give up.
            }

            // store the new tokens
            val accessToken = body.accessToken
            trakt.accessToken(accessToken)
            trakt.refreshToken(body.refreshToken)

            // retry request
            return response.request().newBuilder()
                    .header(TraktV2.HEADER_AUTHORIZATION, "Bearer $accessToken")
                    .build()
        }

        private fun responseCount(response: Response?): Int {
            var response = response
            var result = 1
            while (response != null) {
                response = response.priorResponse()
                result++
            }
            return result
        }
    }

}
