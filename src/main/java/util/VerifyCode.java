package util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {
    private int width = 70;
    private int height = 35;
    private Random r = new Random();
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑"};
    private Color background = new Color(255, 255, 255);
    private String selectable = "23456789zxcvbnmasdfgjkqwertyui";
    private String realText;

    /**
     * 外部调用, 返回一张验证码
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();//1.调用创建图片缓冲区方法
        Graphics g = image.getGraphics();//3.得到绘制环境
        StringBuilder sb = new StringBuilder();//用来装载生成的验证码文本
        /*
        循环四次
        每次生成一个字符
         */
        for (int i = 0; i < 4; i++) {
            String str = randomChar() + "";
            sb.append(str);
            g.setFont(randomFont());
            g.setColor(randomColor());
            g.drawString(str, i * width / 5, height - 5);
        }
        this.realText = sb.toString();//把生成字符串赋给文本
        drawLine(image);//调用添加干扰线方法对图片中的文本进行干扰
        return image;
    }


    public String getText() {
        return this.realText;
    }

    /**
     * 保存图片到指定的输出流
     */
    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    /**
     *创建图片缓冲区方法
     */
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(background);
        g.fillRect(0, 0, width, height);
        return image;
    }


    private char randomChar() {
        int index = r.nextInt(selectable.length());
        return selectable.charAt(index);
    }

    /**
     * 随机自体
     *
     * @return A random font
     */
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        int style = r.nextInt(4);//设置字体样式，0表示无样式，1表示粗体，2表示斜体，3表示粗体加斜体
        int size = r.nextInt(4) + 19;//设置字体大小，范围在24-27之间
        return new Font(fontNames[index], style, size);
    }


    /**
     * 随机色
     *
     * @return a random color
     */
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }


    /**
     * 干扰线
     *
     * @param image
     */
    private void drawLine(BufferedImage image) {
        //循环三次，画三条干扰线
        //先取到画笔，然后才能画线嘛！
        Graphics graphics = image.getGraphics();
        for (int i = 0; i < 3; i++) {
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);//得到所要划线的起点和终点坐标
            graphics.setColor(Color.BLUE);//设置干扰线颜色
            graphics.drawLine(x1, y1, x2, y2);
        }
    }
}

