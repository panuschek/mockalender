package de.pan.mockalender.event

import jakarta.persistence.*
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.hibernate.proxy.HibernateProxy

@Entity
data class CalendarEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var calendarEventId: Long? = null,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val timeZone: String = "",
    val title: String = "",
    @ElementCollection(fetch = FetchType.EAGER)
    val subCalendars: List<String> = emptyList(),
    val description: String = "",
    val who: String = "",
    @Column(name = "place")
    val where: String = "",
    val wholeDay: Boolean = false,
    @ElementCollection(fetch = FetchType.EAGER)
    val links: List<String> = emptyList()
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as CalendarEvent

        return calendarEventId != null && calendarEventId == other.calendarEventId
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  eventId = $calendarEventId )"
    }
}

@Converter(autoApply = true)
class LocalDateTimeConverter : AttributeConverter<LocalDateTime, java.time.LocalDateTime> {
    override fun convertToDatabaseColumn(attribute: LocalDateTime?): java.time.LocalDateTime? {
        return attribute?.toJavaLocalDateTime()
    }

    override fun convertToEntityAttribute(dbData: java.time.LocalDateTime?): LocalDateTime? {
        return dbData?.toKotlinLocalDateTime()
    }
}