package com.adc.da.login.security.validatecode;

import com.adc.da.util.utils.RandomUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class SimpleCharVerifyCodeGenImpl implements IVerifyCodeGen {
    
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SimpleCharVerifyCodeGenImpl.class);
    
    /**
     * 字体
     */
    private static final String[] FONT_TYPES = {"宋体", "新宋体", "黑体", "楷体", "隶书" };

    /**
     * 验证码生成
     *
     * @param width
     * @param height
     * @param os
     * @param number
     * @return
     * @throws IOException
     */
    @Override
    public String generate(int width, int height, OutputStream os, int number) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(number);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    /**
     * 验证码生成模块
     * 改为try-with-resource写法
     *
     * @param width  宽度
     * @param height 高度
     * @param number 数字
     * @return 验证码
     * @author Lee Kwanho 李坤澔
     * date 2018-08-29
     **/
    @Override
    public VerifyCode generate(int width, int height, int number) throws IOException {
        VerifyCode verifyCode = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            String code = generate(width, height, baos, number);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            verifyCode = null;
        }
        return verifyCode;
        
    }

    /**
     * 验证码背景填充
     *
     * @param graphics 二维码
     * @param width    宽度
     * @param height   高度
     * @author comments created by Lee Kwanho
     * date 2018-08-29
     **/
    private static void fillBackground(Graphics graphics, int width, int height) {
        // 填充背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }
     /**
     * 生成随机字
     *
     * @param g         验证码
     * @param randomStr 随机字
     * @author comments created by Lee Kwanho
     * date 2018-08-29
     **/
    private void createCharacter(Graphics g, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            g.setColor(new Color(50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100),
                    50 + RandomUtils.nextInt(100)));
            g.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)], Font.BOLD, 26));
            g.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }
}
