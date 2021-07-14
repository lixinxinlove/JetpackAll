package com.lixinxinlove.all;

import org.junit.Test;

/**
 * author ： lixinxin
 * time    ： 2021/7/13
 * email：895330766@qq.com
 */
public class MyUnitTest {


//    一个数组，先生序到i，再降序。i表示数组中max的标记。想请教下那种查找再这种情况下那个更快？
//    我用的二分法，大家帮忙看看


    @Test
    public void Binary() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 5, 4, 3, 2, 1, 0};
        int l = 0;
        int r = arr.length - 1;
        int v = BinarySearch(arr, l, r);
        System.out.println(v);
    }

    public int BinarySearch(int a[], int l, int r) {
        int mid = l + (r - l) / 2;
        if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) {
            return BinarySearch(a, mid + 1, r);
        } else if (a[mid] < a[mid - 1] && a[mid] > a[mid + 1]) {
            return BinarySearch(a, l, mid - 1);
        } else {
            return a[mid];
        }
    }


}
