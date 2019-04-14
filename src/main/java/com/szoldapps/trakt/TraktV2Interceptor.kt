package com.szoldapps.trakt

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TraktV2Interceptor(private val trakt: com.szoldapps.trakt.TraktV2) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return handleIntercept(chain, trakt.apiKey(), trakt.accessToken())
    }

    companion object {

        /**
         * If the host matches [TraktV2.API_HOST] adds a header for the current [TraktV2.API_VERSION],
         * [TraktV2.HEADER_TRAKT_API_KEY] with the given api key, [TraktV2.HEADER_CONTENT_TYPE] and if not present an
         * Authorization header using the given access token.
         */
        @Throws(IOException::class)
        fun handleIntercept(
            chain: Interceptor.Chain, apiKey: String,
            accessToken: String?
        ): Response {
            val request = chain.request()
            if (com.szoldapps.trakt.TraktV2.API_HOST != request.url().host()) {
                // do not intercept requests for other hosts
                // this allows the interceptor to be used on a shared okhttp client
                return chain.proceed(request)
            }

            val builder = request.newBuilder().apply {
                // set required content type, api key and request specific API version
                header(com.szoldapps.trakt.TraktV2.HEADER_CONTENT_TYPE, com.szoldapps.trakt.TraktV2.CONTENT_TYPE_JSON)
                header(com.szoldapps.trakt.TraktV2.HEADER_TRAKT_API_KEY, apiKey)
                header(com.szoldapps.trakt.TraktV2.HEADER_TRAKT_API_VERSION, com.szoldapps.trakt.TraktV2.API_VERSION)

                // add authorization header
                if (hasNoAuthorizationHeader(request) && accessTokenIsNotEmpty(accessToken)) {
                    header(com.szoldapps.trakt.TraktV2.HEADER_AUTHORIZATION, "Bearer $accessToken")
                }
            }
            return chain.proceed(builder.build())
        }

        private fun hasNoAuthorizationHeader(request: Request): Boolean {
            return request.header(com.szoldapps.trakt.TraktV2.HEADER_AUTHORIZATION) == null
        }

        private fun accessTokenIsNotEmpty(accessToken: String?): Boolean {
            return accessToken != null && accessToken.isNotEmpty()
        }
    }
}
