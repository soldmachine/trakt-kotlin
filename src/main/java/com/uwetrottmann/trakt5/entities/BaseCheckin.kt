package com.uwetrottmann.trakt5.entities

abstract class BaseCheckin {

    /** Control sharing to any connected social networks.  */
    var sharing: ShareSettings? = null
    /** Message used for sharing. If not sent, it will use the watching string in the user settings.  */
    var message: String? = null
    /** Foursquare venue ID.  */
    var venue_id: String? = null
    /** Foursquare venue name.  */
    var venue_name: String? = null
    var app_version: String? = null
    /** Build date of the app.  */
    var app_date: String? = null

}
