package com.uwetrottmann.trakt5.entities

class MovieCheckin : BaseCheckin() {

    var movie: SyncMovie? = null

    class Builder(
        private val movie: SyncMovie,
        protected var app_version: String?,
        protected var app_date: String?
    ) {
        protected var sharing: ShareSettings? = null
        protected var message: String? = null
        protected var venue_id: String? = null
        protected var venue_name: String? = null

        fun sharing(shareSettings: ShareSettings?): Builder? {
            this.sharing = shareSettings
            return this
        }

        fun message(message: String?): Builder? {
            this.message = message
            return this
        }

        fun venueId(venueId: String?): Builder? {
            this.venue_id = venueId
            return this
        }

        fun venueName(venueName: String?): Builder? {
            this.venue_name = venueName
            return this
        }

        fun build(): MovieCheckin? {
            val checkin = MovieCheckin()
            checkin.movie = movie
            checkin.sharing = sharing
            checkin.message = message
            checkin.venue_id = venue_id
            checkin.venue_name = venue_name
            checkin.app_date = app_date
            checkin.app_version = app_version
            return checkin
        }
    }

}
