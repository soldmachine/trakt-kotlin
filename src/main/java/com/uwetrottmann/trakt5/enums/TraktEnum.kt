package com.uwetrottmann.trakt5.enums

interface TraktEnum {

    /**
     * Return the value to be used by retrofit when building a request.
     */
    override fun toString(): String

}
