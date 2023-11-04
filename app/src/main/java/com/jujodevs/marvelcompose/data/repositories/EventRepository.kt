package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.network.ApiClient

object EventRepository : Repository<Event>() {

    suspend fun get(): List<Event> = super.get {
        ApiClient.eventsService.getEvents(
            super.offset,
            super.limit
        ).data.results.map { it.asEvent() }
    }

    suspend fun find(id: Int): Event = super.find(
        id,
        findActionRemote = {
            ApiClient.eventsService.findEvent(id).data.results.first().asEvent()
        }
    )
}
