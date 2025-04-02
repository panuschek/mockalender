package de.pan.mockalender.event

data class Event(
    var eventId: String? = null,
    val startDate: String = "",
    val endDate: String = "",
    val timeZone: String = "",
    val title: String = "",
    val subCalendars: List<Any>,
    val description: String = "",
    val who: String = "",
    val where: String = "",
    val wholeDay: Boolean = false,
    val links: List<String> = emptyList()
) {
}