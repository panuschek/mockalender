package de.pan.mockalender.event

import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<CalendarEvent, String> {
}