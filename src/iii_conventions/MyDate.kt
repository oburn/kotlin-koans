package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

//class DateRange(val start: MyDate, val endInclusive: MyDate) {
//    operator fun contains(d: MyDate) = start <= d && d <= endInclusive
//}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>

