package com.szoldapps.trakt.entities

/**
 * User slug to pass to the API, performs some simple null and empty checks.
 *
 * @param userSlug A user slug as returned by the trakt API: [User.UserIds].
 * @see .ME
 */
class UserSlug(userSlug: String?) {

    private val userSlug: String?

    init {
        var userSlug: String? = userSlug ?: throw IllegalArgumentException("trakt user slug can not be null.")
        userSlug = userSlug?.trim { it <= ' ' }
        if (userSlug?.isEmpty() == true) {
            throw IllegalArgumentException("trakt user slug can not be empty.")
        }
        this.userSlug = userSlug
    }

    override fun toString(): String {
        return userSlug ?: ""
    }

    companion object {

        /**
         * Special user slug for the current user (determined by auth data).
         */
        val ME: UserSlug? = UserSlug("me")

        /**
         * Encodes the username returned from trakt so it is API compatible (currently replaces "." and spaces with "-").
         */
        fun fromUsername(username: String?): UserSlug? {
            if (username == null) {
                throw IllegalArgumentException("trakt username can not be null.")
            }
            // trakt encodes some special chars in usernames
            // - points "." as a dash "-"
            // - spaces " " as a dash "-"
            // - multiple dashes are reduced to one
            val slug = username.replace(".", "-").replace(" ", "-").replace("(-)+".toRegex(), "-")
            return UserSlug(slug)
        }
    }
}
