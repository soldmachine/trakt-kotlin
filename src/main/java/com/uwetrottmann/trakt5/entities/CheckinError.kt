package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

/**
 * Type to use for parsing check in error response (call [com.uwetrottmann.trakt5.TraktV2.checkForCheckinError]
 * with this class) if you get a 409 HTTP status code when checking in.
 */
data class CheckinError(

    /** Timestamp which is when the user can check in again.  */
    var expires_at: OffsetDateTime? = null

)
