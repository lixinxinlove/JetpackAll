package com.lixinxinlove.all

import android.util.Log
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


    //选择排序
    @Test
    fun selectionSort() {

        val arr = arrayOf(
            1,
            3,
            2,
            43,
            6,
            5,
            65,
            78,
            6,
            5,
            4,
            23,
            46,
            346,
            34,
            6,
            6,
            346,
            46,
            34,
            6,
            76,
            765,
            8,
            78,
            5,
            95,
            845,
            35,
            64,
            76,
            87587,
            9
        )

        for (i in 0 until arr.size) {
            var minIndex = i
            for (j in i + 1 until arr.size) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }

            val temp = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = temp

        }



        arr.forEach {
            println(it)
        }


    }


    //插入排序
    @Test
    fun insertionSort() {
        val arr = arrayOf(
            1,
            3,
            2,
            43,
            6,
            5,
            65,
            78,
            6,
            5,
            4,
            23,
            46,
            346,
            34,
            6,
            6,
            346,
            46,
            34,
            6,
            76,
            765,
            8,
            78,
            5,
            95,
            845,
            35,
            64,
            76,
            87587,
            9
        )

        for (i in 1 until arr.size) {

            for (j in i downTo 1) {
                if (arr[j] < arr[j - 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j - 1]
                    arr[j - 1] = temp
                }
            }
        }

        arr.forEach {
            print(it)
            print(" ")
        }

    }

    //插入排序优化
    @Test
    fun insertionSort1() {
        val arr = arrayOf(
            1,
            3,
            2,
            43,
            6,
            5,
            65,
            78,
            6,
            5,
            4,
            23,
            46,
            346,
            34,
            6,
            6,
            346,
            46,
            34,
            6,
            76,
            765,
            8,
            78,
            5,
            95,
            845,
            35,
            64,
            76,
            87587,
            9
        )

        for (i in 1 until arr.size) {

            val e = arr[i]
            var jj = i
            for (j in i downTo 1) {
                if (arr[j] < arr[j - 1]) {
                    jj = j
                    arr[j] = arr[j - 1]
                }
            }
            arr[jj] = e
        }

        arr.forEach {
            print(it)
            print(" ")
        }

    }

}