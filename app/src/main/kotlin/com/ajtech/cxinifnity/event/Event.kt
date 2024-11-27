package com.ajtech.cxinifnity.event

sealed interface Event {
    data class OnViewDisplayed(val deviceId: String) : Event

    data class OnButtonClicked(val deviceId: String, val name: String) : Event
}