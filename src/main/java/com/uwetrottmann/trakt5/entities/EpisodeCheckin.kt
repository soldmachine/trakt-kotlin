package com.uwetrottmann.trakt5.entities

class EpisodeCheckin : BaseCheckin() {

    var show: Show? = null
    var episode: SyncEpisode? = null

    class Builder(
        private val episode: SyncEpisode,
        private var app_version: String?,
        private var app_date: String?
    ) {

        private var show: Show? = null
        private var sharing: ShareSettings? = null
        private var message: String? = null
        private var venue_id: String? = null
        private var venue_name: String? = null

        fun show(show: Show?): Builder? {
            this.show = show
            return this
        }

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

        fun build(): EpisodeCheckin? {
            val checkin = EpisodeCheckin()
            checkin.show = show
            checkin.episode = episode
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
