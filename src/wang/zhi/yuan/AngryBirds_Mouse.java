package wang.zhi.yuan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AngryBirds_Mouse extends Thread{
	
	private int x;								//���ͼƬ��x������
	private int y;								//���ͼƬ��Y������
	private BufferedImage[] image;				//���Ķ����ز�ͼƬ
	private BufferedImage present;				//���ĵ�ǰ�ز�ͼƬ

	
	/**���캯���������ĳ�ʼ״̬*/
	AngryBirds_Mouse() throws IOException
	{
		x = 0;
		y = 0;
		image = new BufferedImage[2];
		image[0] = ImageIO.read(new File("src/picture/mouse1.png"));
		image[1] = ImageIO.read(new File("src/picture/mouse3.png"));
		
		present = image[0];
	}
	
	/**����ƶ���λ��*/
	public void moveto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**��������ɿ���ͼƬΪ��ǰͼƬ*/
	public void mouseLoose()
	{
		present = image[0];
	}
	
	/**������갴ס��ͼƬΪ��ǰͼƬ*/
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

