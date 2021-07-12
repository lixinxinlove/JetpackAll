package com.lixinxinlove.all

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun bubbleSort() {

        val arr = arrayOf(
            2,
            3,
            4,
            5,
            3,
            54,
            54,
            2345,
            4,
            457,
            568,
            35,
            65,
            6,
            5,
            6,
            7,
            47,
            25,
            7,
            6,
            45,
            7,
            5,
            6,
            456,
            456456,
            56,
            3,
            456
        )

        for (i in 0 until arr.size) {

            for (j in 0 until arr.size - i - 1) {
                if (arr[j] < arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp

                }
            }

        }


        arr.forEach {
            print(it)
            print(" ")
        }

    }





    //对 arr[l..r]的范围进行排序
    private fun __mergeSort(arr: Array<Int>, l: Int, r: Int) {
        if (l >= r) {
            return
        }
        val mid = (l + r) / 2
        __mergeSort(arr, l, mid)
        __mergeSort(arr, mid + 1, r)
        merge(arr, l, mid, r)

    }


    //将 arr[l..mid] 和 arr[mid+1,r] 的两部分进行合并
    private fun merge(arr: Array<Int>, l: Int, mid: Int, r: Int) {
        val aux: Array<Int> = Array(r - l + 1) { 0 }
        for (i in l..r) {
            aux[i - l] = arr[i]
        }

        var i = l
        var j = mid + 1
        for (k in l..r) {

            if (i > mid) {
                arr[k] = aux[j - l]
                j++
            } else if (j > r) {
                arr[k] = aux[i - l]
                i++
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l]
                i++
            } else {
                arr[k] = aux[j - l]
                j++
            }
        }


    }


    //并归排序
    @Test
    fun mergeSort() {

        val arr = arrayOf(
            3,
            4,
            3,
            5,
            345,
            46,
            57,
            6,
            73,
            67,
            6,
            73,
            67,
            6,
            735,
            67,
            245,
            72,
            7,
            567,
            35678,
            467,
            89678,
            96,
            72,
            56,
            2,
            254,
            6,
            257,
            2,
            57,
            268,
            365,
            8
        )
        val n=arr.size


        __mergeSort(arr, 0, n - 1)


        arr.forEach {
            print(it)
            print(" ")
        }

    }

}