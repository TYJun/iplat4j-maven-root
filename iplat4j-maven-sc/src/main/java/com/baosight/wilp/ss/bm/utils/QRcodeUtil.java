package com.baosight.wilp.ss.bm.utils;

import com.baosight.wilp.ss.bm.config.FontConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class QRcodeUtil 
{
	/**
	 * 字符编码
	 */
	private static final String CHARSET = "utf-8";
	
	/**
	 *文件格式 
	 */
    private static final String FORMAT_NAME = "JPG";
    
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 350;
    
    /**
     * 内嵌Logo图片的宽度
     */   
    private static final int WIDTH = 95;
    
    /**
     * 内嵌Logo图片的高度
     */
    private static final int HEIGHT = 90;
    
    /**
     * 内嵌第一行文字的大小
     */
    // private static final int TOP_FONT_SIZE = 40;
    
    /**
     * 内嵌第二行文字的大小
     */
	private static final int BOTTOM_FONT_SIZE = 17;
    
	/**默认编码类型*/
	public static final Charset defaultCharset = Charset.defaultCharset();
	
    /**
     * 生成带Logo的二维码
     * @param content 二维码的内容
     * @param imgPath 内嵌Logo的
     * @param needCompress 是否进行压缩Logo
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static BufferedImage createImage(String content,String imgPath,Boolean needCompress) throws Exception 
    {
    	BufferedImage image = null;
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		System.out.println(width + "" + height);
		image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000: 0xFFFFFFFF);
			}
		}
		/**
		 * 不存在 Logo图片直接返回
		 */
		if (imgPath == null || "".equals(imgPath))
		{
			return image;
		}
		QRcodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}

 
    
    /**
     * 内嵌图片
     * @param source 需要内嵌图片的图片流对象
     * @param src    内嵌图片
     * @param needCompress 是否需要进行缩放操作
     * @throws Exception
     */
    @SuppressWarnings("unused")
	private static BufferedImage insertImage(BufferedImage source,String imgPath,boolean needCompress) throws Exception 
    {
    	File file = new File(imgPath);
		if (!file.exists()) 
		{
			System.err.println("" + imgPath + "   该文件不存在！");
			return null;
		}
    	//获取原图片的宽高
		int width2 = source.getWidth();
		int height2 = source.getHeight();
		Image src = ImageIO.read(new File(imgPath));
		//获取内嵌图片的宽高
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) 
		{ 
			// 压缩LOGO
			if (width > WIDTH) 
			{
				width = WIDTH;
			}
			if (height > HEIGHT) 
			{
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE  - width) / 2;
		int y = (QRCODE_SIZE  - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		graph.dispose();
		return source;
	}
    
    /**
     * 内嵌图片
     * @param source 需要内嵌的流对象
     * @param src    内嵌的对象
     * @param needCompress 是否进行压缩图片
     * @param content 内嵌文字
     * @throws Exception
     */
    private static void insertImage(BufferedImage source,Image src,boolean needCompress,Map<String,String> map ) throws Exception
    {
        //获取原图片的宽高
        int width2 = source.getWidth();
        int height2 = source.getHeight();
        //获取内嵌图片的宽高
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress)
        {
            // 压缩LOGO
            if (width > WIDTH)
            {
                width = WIDTH;
            }
            if (height > HEIGHT)
            {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 内嵌图片
        Graphics2D graph = source.createGraphics();
        //内嵌图片的位置
		/*int x = width2/2;
		int y = 50;*/
        graph.drawImage(src, 0, 0, width, height, null);
        //Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        //graph.setStroke(new BasicStroke(3f));
        graph.setColor(Color.BLACK);
        String building = map.get("building");
        String floor = map.get("floor");
        String deptName = map.get("deptName");
        String room = map.get("room");
        if(null == building || null == floor || null == map.get("room") || null == map.get("deptName"))
        {
            System.out.println("有空的数据....");
            return;
        }
        //graph.setFont(new Font("黑体", Font.BOLD, BOTTOM_FONT_SIZE));
        Font definedFont2 = FontConfig.getSimheiFont(Font.BOLD,BOTTOM_FONT_SIZE);
        graph.setFont(definedFont2);

        // 显示一层
        StringBuffer oneSb = new StringBuffer();
        oneSb.append(building).append(" ").append(floor);
        // 居中显示
        int oneSize = (int) (width2/2-0.90*graph.getFontMetrics().stringWidth(oneSb.toString())/2);
        int twoSize = (int) (width2/2-0.90*graph.getFontMetrics().stringWidth(deptName)/2);
        int threeSize = (int) (width2/2-0.90*graph.getFontMetrics().stringWidth(room)/2);
        String str = oneSb.toString();
        str = new String(str.getBytes(defaultCharset),"UTF-8");
        
        graph.drawString(str,oneSize,height2-55);
        graph.drawString(new String(deptName.getBytes(defaultCharset),"UTF-8"),twoSize,height2-30);
        graph.drawString(new String(room.getBytes(defaultCharset),"UTF-8"),threeSize,height2-5);
        graph.dispose();
    }
    public static void main(String[] args) {
    	System.out.println(FontConfig.getSimheiFont(Font.BOLD,BOTTOM_FONT_SIZE));
	}
    
    /*public static Font definedFont = null; 
	public static Font getSimheiFont(int style,float size) {
        if (definedFont == null) {  
            try {  
                definedFont = Font.createFont(Font.TRUETYPE_FONT, QRcodeUtil.class.getResourceAsStream("simhei.ttf"));
                definedFont = definedFont.deriveFont(style,size);
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
        }  
        return definedFont;  
    }*/ 
    
    /**
     * 创建文件夹
     * @param destPath
     */
    public static void mkdirs(String destPath) 
    {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) 
        {
            file.mkdirs();
        }
    }
    

    /**
     * 编码操作  用输出流输出到destPath路径下
     * @param content 二维码内容
     * @param imgPath Logo地址
     * @param needCompress 是否进行压缩
     * @param destPath  图片文件保存地址
     * @param font   内嵌文字
     * @throws Exception
     */
    public static void encode(String content,String imgPath,Boolean needCompress,String destPath,Map<String,String> map) throws Exception 
    {
    	
    	BufferedImage img = QRcodeUtil.createImage(content, imgPath, needCompress);
    	BufferedImage read = ImageIO.read(new File(System.getProperty("user.dir")+"/data/code.jpg"));
    	QRcodeUtil.insertImage(read, img, false,map);
		mkdirs(destPath);
		String file = new Random().nextInt(99999999) + ".jpg";
		ImageIO.write(read, FORMAT_NAME, new File(destPath + "/" + file));
    }

    /**
     * 编码操作
     * @param content      二维码内容
     * @param imgPath      内嵌图片地址
     * @param needCompress 是否进行压缩图片
     * @param output       输出流
     * @param destPath	      图片保存地址
     * @param font     	      内嵌文字
     * @return             返回内嵌之后的图片流对象
     * @throws Exception
     */
    public static BufferedImage encode(String content, String imgPath,boolean needCompress,String destPath,Map<String,String> map) throws Exception 
    {
    	BufferedImage read = null;  
    	BufferedImage img = QRcodeUtil.createImage(content, imgPath, needCompress);
    	read = ImageIO.read(new File(destPath));
    	QRcodeUtil.insertImage(read, img, false,map);
    	return read;	
    }
    
}
