package wang.zhi.yuan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AngryBirds_Button {
	
	private BufferedImage buttonImage; 			//°´Å¥Í¼Æ¬
	private int x;
	private int y;
	private int weight;
	private int height;
	
	public BufferedImage getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(BufferedImage buttonImage) {
		this.buttonImage = buttonImage;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	AngryBirds_Button(int x, int y) throws IOException
	{
		this.buttonImage = ImageIO.read(new File("src/picture/refresh.png"));
		this.x = x;
		this.y = y;
		this.weight = buttonImage.getWidth();
		this.height = buttonImage.getHeight();
	}
	
	public boolean isChonse(int x, int y)
	{
		return x > this.x && x < this.x + weight && y > this.y && y < this.y + height;
	}
}
