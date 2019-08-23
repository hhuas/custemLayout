package com.hua.a13qrcode.zxing.encode;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class CodeCreator {

	/**
	 * 生成QRCode（二维码）
	 * 
	 * @param str
	 * @return
	 * @throws WriterException
	 */
	public static Bitmap createQRCode(String url) throws WriterException {

		if (url == null || url.equals("")) {
			return null;
		}

		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = new MultiFormatWriter().encode(url,
				BarcodeFormat.QR_CODE, 300, 300);

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}

			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}


	/**
	 * 生成带logo的二维码，logo默认为二维码的1/5
	 *
	 * @param text    需要生成二维码的文字、网址等
	 * @param size    需要生成二维码的大小（）
	 * @param mBitmap logo文件
	 * @return bitmap
	 */

	private static int MY_WIDTH = 50;//宽度值，影响中间图片大小
	public static Bitmap createQRCodeWithLogo(String text, int size, Bitmap mBitmap) {
		try {
			MY_WIDTH = size / 10;
			Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			/*
			 * 设置容错级别，默认为ErrorCorrectionLevel.L
			 * 因为中间加入logo所以建议你把容错级别调至H,否则可能会出现识别不了
			 */
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, size, size, hints);

			int width = bitMatrix.getWidth();//矩阵高度
			int height = bitMatrix.getHeight();//矩阵宽度
			int halfW = width / 2;
			int halfH = height / 2;

			Matrix m = new Matrix();
			float sx = (float) 2 * MY_WIDTH / mBitmap.getWidth();
			float sy = (float) 2 * MY_WIDTH
					/ mBitmap.getHeight();
			m.setScale(sx, sy);
			//设置缩放信息
			//将logo图片按martix设置的信息缩放
			mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
					mBitmap.getWidth(), mBitmap.getHeight(), m, false);

			int[] pixels = new int[size * size];
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if (x > halfW - MY_WIDTH && x < halfW + MY_WIDTH
							&& y > halfH - MY_WIDTH
							&& y < halfH + MY_WIDTH) {
						//该位置用于存放图片信息
						//记录图片每个像素信息
						pixels[y * width + x] = mBitmap.getPixel(x - halfW
								+ MY_WIDTH, y - halfH + MY_WIDTH);
					} else {
						if (bitMatrix.get(x, y)) {
							pixels[y * size + x] = 0xff000000;
						} else {
							pixels[y * size + x] = 0xffffffff;
						}
					}
				}
			}
			Bitmap bitmap = Bitmap.createBitmap(size, size,
					Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
			return bitmap;
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		}
	}
}
