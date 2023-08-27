package com.baosight.wilp.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public final class QrUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	static final int LOGO_WIDTH = 64;
	static final int LOGO_HEIGHT = 64;

	private QrUtil() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix,Image logoImage) throws IOException {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}

		/*
		插入 医院 LOGO  缩放到 64*64 分辨率
		*/
		if (logoImage != null) {
			// 插入LOGO
			Graphics2D graph = image.createGraphics();
			int x = (400 - LOGO_WIDTH) / 2;
			int y = (400 - LOGO_HEIGHT) / 2;
			graph.drawImage(logoImage, x, y, LOGO_WIDTH, LOGO_HEIGHT, null);
			graph.dispose();
		}

		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix,null);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix,null);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 2; i++) {
				String content = String.valueOf(i);
				String path = "C:/Users/jzm/Desktop";

				MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

				Hashtable hints = new Hashtable();
				hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
				hints.put(EncodeHintType.MARGIN, 1);
				BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
				BufferedImage bufferedImage = toBufferedImage(bitMatrix,null);

				Graphics graphics = bufferedImage.getGraphics();



				graphics.setFont(new Font("黑体",Font.BOLD,16));
				int oneSize = (int) (bufferedImage.getWidth()/2-0.90*graphics.getFontMetrics().stringWidth("No000001")/2);
				graphics.drawString("No000001",oneSize,bufferedImage.getHeight() - 20);
				graphics.dispose();

				File file1 = new File(path, "餐巾纸"+i+".jpg");
				writeToFile(bitMatrix, "jpg", file1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
