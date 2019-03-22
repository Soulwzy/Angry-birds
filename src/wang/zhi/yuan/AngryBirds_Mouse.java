package wang.zhi.yuan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AngryBirds_Mouse extends Thread{
	
	private int x;								//鼠标图片的x轴坐标
	private int y;								//鼠标图片的Y轴坐标
	private BufferedImage[] image;				//鼠标的动画素材图片
	private BufferedImage present;				//鼠标的当前素材图片

	
	/**构造函数设置鼠标的初始状态*/
	AngryBirds_Mouse() throws IOException
	{
		x = 0;
		y = 0;
		image = new BufferedImage[2];
		image[0] = ImageIO.read(new File("src/picture/mouse1.png"));
		image[1] = ImageIO.read(new File("src/picture/mouse3.png"));
		
		present = image[0];
	}
	
	/**鼠标移动的位置*/
	public void moveto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**设置鼠标松开的图片为当前图片*/
	public void mouseLoose()
	{
		present = image[0];
	}
	
	/**设置鼠标按住的图片为当前图片*/
	public void mousePress()
	{
		present = image[1];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage[] getImage() {
		return image;
	}

	public void setImage(BufferedImage[] image) {
		this.image = image;
	}

	public BufferedImage getPresent() {
		return present;
	}

	public void setPresent(BufferedImage present) {
		this.present = present;
	}
}

