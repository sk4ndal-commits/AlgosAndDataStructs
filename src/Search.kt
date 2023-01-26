object Search {

    @JvmStatic
    fun main(args: Array<String>) {
        stringSearch("omg", "wowomgzomg")
    }

    private fun findIndexOf(a: IntArray, n: Int): Int {
        for (i in a.indices) {
            if (a[i] == n) return i
        }
        return -1
    }

    private fun stringSearch(key: String, text: String) {
        var good: Boolean
        var ans = 0
        for (textIterator in 0..text.length - key.length) {
            good = true
            var keyIterator = 0
            while (keyIterator < key.length && good) {
                if (text[textIterator + keyIterator] != key[keyIterator]) good = false
                keyIterator++
            }
            ans += if (good) 1 else 0
        }
        println("Found $ans occurences")
    }

    private fun binarySearchIndexOf(a: IntArray, n: Int): Int {
        var l = 0
        var r = a.size - 1
        while (l <= r) {
            val m = l + r shr 1
            if (a[m] == n) return m else if (a[m] > n) r = m - 1 else l = m + 1
        }
        return -1
    }
}