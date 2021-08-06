package com.lixinxinlove.all.opencv;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import static org.opencv.core.CvType.CV_8UC4;

public class ImageUtils {

    /**
     * 怀旧滤镜
     */
    public static Bitmap remin2(Bitmap srcBitmap) {

        Mat mat = new Mat();

        Utils.bitmapToMat(srcBitmap, mat);
        //获取通道数
        int channel = mat.channels();
        //获取通道数

        Log.e("ImageUtils", "通道数=$channel");
        //获取通道数

        int width = mat.width();
        //获取通道数

        int height = mat.height();
        //获取通道数
        //保存一个像素点的信息
        byte p[] = new byte[channel];
        //获取通道数
        byte data[] = new byte[channel * width];

        Mat dstMat = new Mat(mat.width(), mat.height(), CV_8UC4);


        int b = 0;
        int g = 0;
        int r = 0;

        for (int row = 0; row < height; row++) {

            mat.get(row, 0, data);


            for (int col = 0; col < data.length; col += channel) {


                p[0] = data[col];
                p[1] = data[col + 1];
                p[2] = data[col + 2];
                p[3] = data[col + 3];


                b = p[0] & 0xff;
                g = p[1] & 0xff;
                r = p[2] & 0xff;

                int AB = (int) (0.272 * r + 0.534 * g + 0.131 * b);
                int AG = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int AR = (int) (0.393 * r + 0.769 * g + 0.189 * b);

                if (AB > 255) {
                    AB = 255;
                }
                if (AB < 0) {
                    AB = 0;
                }

                if (AG > 255) {
                    AG = 255;
                }
                if (AG < 0) {
                    AG = 0;
                }

                if (AR > 255) {
                    AR = 255;
                }
                if (AR < 0) {
                    AR = 0;
                }


                p[0] = (byte) AB;
                p[1] = (byte) AG;
                p[2] = (byte) AR;


                dstMat.put(row, col, p);

            }
        }


        Bitmap resultBitmap =
                Bitmap.createBitmap(dstMat.width(), dstMat.height(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(dstMat, resultBitmap);

        mat.release();
        dstMat.release();

        return resultBitmap;

    }

    /**
     * 怀旧滤镜
     */
    public static Bitmap remin3(Bitmap srcBitmap) {

        Mat mat = new Mat();

        Utils.bitmapToMat(srcBitmap, mat);
        //获取通道数
        int channel = mat.channels();
        //获取通道数

        Log.e("ImageUtils", "通道数="+channel);
        //获取通道数

        int width = mat.width();
        //获取通道数

        int height = mat.height();

        Log.e("ImageUtils", "width="+width);
        Log.e("ImageUtils", "height="+height);

        //获取通道数
        //保存一个像素点的信息
        byte p[] = new byte[channel];
        //获取通道数
        byte data[] = new byte[channel * width * height];

        Mat dstMat = new Mat(mat.width(), mat.height(), CV_8UC4);


        int b = 0;
        int g = 0;
        int r = 0;


        mat.get(0, 0, data);

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {



                p[0] = data[col];
                p[1] = data[col + 1];
                p[2] = data[col + 2];
                p[3] = data[col + 3];

                b = p[0] & 0xff;
                g = p[1] & 0xff;
                r = p[2] & 0xff;

                int AB = (int) (0.272 * r + 0.534 * g + 0.131 * b);
                int AG = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int AR = (int) (0.393 * r + 0.769 * g + 0.189 * b);

                if (AB > 255) {
                    AB = 255;
                }
                if (AB < 0) {
                    AB = 0;
                }

                if (AG > 255) {
                    AG = 255;
                }
                if (AG < 0) {
                    AG = 0;
                }

                if (AR > 255) {
                    AR = 255;
                }
                if (AR < 0) {
                    AR = 0;
                }


                p[0] = (byte) AB;
                p[1] = (byte) AG;
                p[2] = (byte) AR;


                dstMat.put(row, col, p);

            }
        }


        Bitmap resultBitmap =
                Bitmap.createBitmap(dstMat.width(), dstMat.height(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(dstMat, resultBitmap);

        mat.release();
        dstMat.release();

        return resultBitmap;

    }


}
