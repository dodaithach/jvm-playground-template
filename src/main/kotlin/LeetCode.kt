import java.util.*

fun main() {
    println(topKFrequent(arrayOf("the","day","is","sunny","the","the","the","sunny","is","is"), 4))
}

private fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val freqs = mutableMapOf<String, Int>()
    for (word in words) {
        val currentCount = freqs[word] ?: 0
        freqs[word] = currentCount + 1
    }

    val pQueue = PriorityQueue<Pair<String, Int>>(k) { p1, p2 ->
        if (p1.second == p2.second) -p1.first.compareTo(p2.first) else p1.second - p2.second
    }

    for (entry in freqs) {
        pQueue.offer(entry.key to entry.value)
        if (pQueue.size > k) pQueue.poll()
    }

    val result = mutableListOf<String>()
    while (pQueue.isNotEmpty()) {
        result.add(pQueue.poll().first)
    }

    return result.reversed()
}