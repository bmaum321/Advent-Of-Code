import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun main() {

    val clockFormat = "HH:mm"
    val clockFormat2 = "hh:mm a"
    val time = "03:30 PM"
    val formattedtime = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a" , Locale.US))
        .format(DateTimeFormatter.ofPattern(clockFormat))

    println(formattedtime)
}