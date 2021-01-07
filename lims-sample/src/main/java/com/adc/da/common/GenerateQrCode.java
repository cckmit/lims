package com.adc.da.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: GenerateQrCode 
 * @Description: 制作二维码
 * @author: lb
 * @date: 2017年6月9日 下午12:30:36
 */
public class GenerateQrCode {

    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    private String content = "contents is empty";

    private int width = 430;

    private int height = 430;

    private String format;

    /**
     * 
     * @Title: init 
     * @Description: 初始化
     * @return
     * @return: GenerateQrCode
     */
    public static GenerateQrCode init() {
        return new GenerateQrCode();
    }
    /**
     * 
     * @Title: width 
     * @Description: 设置宽度
     * @param width
     * @return
     * @return: GenerateQrCode
     */
    public GenerateQrCode width(int width) {
        this.width = width;
        return this;
    }
    /**
     * 
     * @Title: height 
     * @Description: 设置高度
     * @param height
     * @return
     * @return: GenerateQrCode
     */
    public GenerateQrCode height(int height) {
        this.height = height;
        return this;
    }

    public GenerateQrCode format(String format) {
        this.format = format;
        return this;
    }

    public GenerateQrCode content(String content) {
        this.content = content;
        return this;
    }

    public BufferedImage generate() throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);//设置二维码边的空度，非负数
        //生成条形码时的一些配置,此项可选
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return toBufferedImage(bitMatrix);
    }


    /**
     * 设置 logo
     *
     * @param matrixImage 源二维码图片
     * @return 返回带有logo的二维码图片
     * @throws IOException
     * @author Administrator sangwenhao
     */
    public BufferedImage logoMatrix(BufferedImage matrixImage) {
        /**
         * 读取二维码图片，并构建绘图对象
         */
        Graphics2D g2 = matrixImage.createGraphics();

        g2.dispose();
        matrixImage.flush();
        return matrixImage;
    }


    public BufferedImage toBufferedImage(BitMatrix matrix) {
        int myWidth = matrix.getWidth();
        int myHeight = matrix.getHeight();
        BufferedImage image = new BufferedImage(myWidth, myHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                image.setRGB(x, y, (matrix.get(x, y) ? BLACK : WHITE));
            }
        }
        return logoMatrix(image);
    }

    public void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }
}
