package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.network.ApiClient
import com.jujodevs.marvelcompose.data.network.entities.Result

object EventRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient.eventsService.getEvents(
            super.offset,
            super.limit,
        ).data.results.map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient.eventsService.findEvent(id).data.results.first().asEvent()
        },
    )
}
