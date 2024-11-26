package com.ajtech.cxinifnity.event

sealed interface Event {
    data class OnViewDisplayed(val deviceId: String) : Event
}