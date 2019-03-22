package wang.zhi.yuan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AngryBirds_Yellowbird extends AngryBirds_Bird{

	private BufferedImage[] image;				//鸟的图片
	private double newVx;						//鸟的速度
	private double newVy;						//鸟的速度
	
	public AngryBirds_Yellowbird(int x, int y) throws IOException {
		super(x, y);
		// TODO Auto-generated constructor stub
		image = new BufferedImage[2];
		for(int i = 0; i < 2; i++)
		{
			this.image[i] = ImageIO.read(new File("src/picture/BIRD_YELLOW" + i + ".png"));
		}
		present = image[0];
		this.width = present.getWidth();
		this.height = present.getHeight();
		newVx = 0;
		newVy = 0;
	}

	@Override
	public void special() {
		// TODO Auto-generated method stub
		vx = newVx;
		vy = newVy;
		
		if(y > 813 - height - 1) //813为地面高度
		{
			special = false;
		}
	}

	public double getNewVx() {
		return newVx;
	}

	public void setNewVx(double newVx) {
		this.newVx = newVx;
	}

	public double getNewVy() {
		return newVy;
	}

	public void setNewVy(double newVy) {
		this.newVy = newVy;
	}

	@Override
	protected void reSet() {
		// TODO Auto-generated method stub
		this.present = null;
		this.fly = false;
		this.chosen = false;
	}
	
	public void changeFast()
	{
		present = image[1];
	}
	
	public void changeSlow()
	{
		present = image[0];
	}
}
