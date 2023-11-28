package com.jujodevs.marvelcompose.data.repositories

import com.jujodevs.marvelcompose.data.entities.Event
import com.jujodevs.marvelcompose.data.network.entities.Result
import com.jujodevs.marvelcompose.data.network.remote.EventsService
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventsService: EventsService
) : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        eventsService.getEvents(
            super.offset,
            super.limit,
        ).data.results.map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            eventsService.findEvent(id).data.results.first().asEvent()
        },
    )
}
