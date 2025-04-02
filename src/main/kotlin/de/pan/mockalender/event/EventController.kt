package de.pan.mockalender.event

import org.springframework.web.bind.annotation.*

@RequestMapping("public")
@RestController
class EventController() {
    @GetMapping("event")
    fun getEvents() : List<Event> {
        return arrayListOf()
    }

    @GetMapping("event/{eventId}")
    fun getEventById(eventId: String) : Event {
        throw NotImplementedError()
    }

    @PostMapping("event")
    fun postEvent(@RequestBody event: Event) {
        throw NotImplementedError()
    }

    @PutMapping("event/{eventId}")
    fun putEventById(eventId: String) : Event {
        throw NotImplementedError()
    }

    @DeleteMapping("event/{eventId}")
    fun deleteEventById(eventId: String) {
        throw NotImplementedError()
    }
}