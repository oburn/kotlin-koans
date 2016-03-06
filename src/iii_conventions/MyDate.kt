package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(start = this, endInclusive = other)

operator fun MyDate.plus(with: TimeInterval): MyDate = this.addTimeIntervals(with, 1)

operator fun MyDate.plus(with: RepeatedTimeInterval): MyDate = this.addTimeIntervals(with.ti, with.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(ti = this, n = n)

//class DateRange(val start: MyDate, val endInclusive: MyDate) {
//    operator fun contains(d: MyDate) = start <= d && d <= endInclusive
//}

class MyDateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.addTimeIntervals(TimeInterval.DAY, 1)
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = MyDateIterator(this)
}

