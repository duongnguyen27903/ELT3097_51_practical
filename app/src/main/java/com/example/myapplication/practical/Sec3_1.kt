package com.example.myapplication.practical

//nhiem vu 1
data class Event(
    val title: String,
    val description: String? = null,
    val dayPart: DayPart,
    val durationInMinutes: Int,
)

//nhiem vu 2
enum class DayPart {
    MORNING,
    AFTERNOON,
    EVENING,
}

fun main(){
    val event1 = Event(title = "Wake up", description = "Time to get up", dayPart = DayPart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", dayPart = DayPart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", dayPart = DayPart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", dayPart = DayPart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", dayPart = DayPart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", dayPart = DayPart.EVENING, durationInMinutes = 45)

    //nhiem vu 3
    val events = mutableListOf(event1, event2, event3, event4, event5, event6)

    //nhiem vu 4
    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} short events.")

    //nhiem vu 5
    val groupedEvents = events.groupBy { it.dayPart }
    groupedEvents.forEach { (dayPart, events) ->
        println("$dayPart: ${events.size} events")
    }

    //nhiem vu 6
    println("Last event of the day: ${events.last().title}")

    //nhiem vu 7
    val durationOfEvent = if (events[3].durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }
    println("Duration of first event of the day: $durationOfEvent")

}

val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }