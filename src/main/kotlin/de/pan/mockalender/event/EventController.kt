package de.pan.mockalender.event

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RequestMapping("public")
@RestController
class EventController(
    private val eventRepository: EventRepository
) {
    class NotFoundException(message: String) : Exception(message)

    @GetMapping("event")
    fun getEvents() : List<CalendarEvent> {
        return eventRepository
            .findAll()
            .toList()
    }

    @GetMapping("event/{eventId}")
    fun getEventById(eventId: String) : CalendarEvent {
        val maybeEvent = eventRepository.findById(eventId)

        if(maybeEvent.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Event with id $eventId not found")
        }

        return maybeEvent.get()
    }

    @PostMapping("event")
    fun postEvent(@RequestBody calendarEvent: CalendarEvent) : CalendarEvent {
        return eventRepository.save(calendarEvent)
    }

    @PutMapping("event/{eventId}")
    fun putEventById(eventId: String, calendarEvent: CalendarEvent) : CalendarEvent {
        return eventRepository.save(calendarEvent)
    }

    @DeleteMapping("event/{eventId}")
    fun deleteEventById(eventId: String) {
        eventRepository.deleteById(eventId)
    }
}