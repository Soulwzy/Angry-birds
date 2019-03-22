package wang.zhi.yuan;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class AngryBirds_Pig extends Thread{
	
	private BufferedImage[] image; 					//猪的素材组
	
	private double x;								//猪图片X轴坐标
	private double y;								//猪图片Y轴坐标
	private double width;							//猪图片宽度
	private double height;							//猪图片高度
	private boolean alived;							//猪是否被击杀
	private boolean exited;							//猪图片是否存在	
	private BufferedImage present;					//猪素材的当前图片
	private AudioClip sound;						//猪死亡时的音乐
	
	AngryBirds_Pig() throws IOException
	{
		Init(0,0);
	}
	
	AngryBirds_Pig(int x, int y) throws IOException
	{
		Init(x,y);
	}
	
	public void Init(int x, int y) throws IOException
	{
		/**加载素材图片*/
		this.image = new BufferedImage[5];
		this.image[0] = ImageIO.read(new File("src/picture/pig.png"));
		for(int i = 1; i < 5; i++)
		{
			this.image[i] = ImageIO.read(new File("src/picture/气泡" + i +".png"));
		}
		
		this.width = image[0].getWidth();
		this.height = image[0].getHeight();
		this.x = x;
		this.y = y;
		this.present = image[0];
		this.alived = true;
		this.exited = true;
	}
	
	/**猪死亡时的音乐*/
	private void pigdeathMusic()
	{
		try {
			this.sound = Applet.newAudioClip(new File("src/music/pigdeath2.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sound.play();
	}
	
	/**猪死亡但还存在时 
	 * 
	 * 重绘图片变成气泡散发，更加逼真
	 * 
	 * */
	public void death()
	{
		for(int i = 1 ; i < 5; i++)
		{
			present = image[i];
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		present = null;							//当前图片设置为空
		exited = false;							//重绘完气泡后猪死亡
	}
	
	/**猪的线程 系统自动调用*/
	public void run()
	{
		while(true)
		{
			//System.out.println("");
			if(!alived)
			{
				pigdeathMusic();
				death();
				
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public BufferedImage[] getImage() {
		return image;
	}

	public void setImage(BufferedImage[] image) {
		this.image = image;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public BufferedImage getPresent() {
		return present;
	}

	public void setPresent(BufferedImage present) {
		this.present = present;
	}

	public boolean isAlived() {
		return alived;
	}

	public void setAlived(boolean alived) {
		this.alived = alived;
	}

	public boolean isExited() {
		return exited;
	}

	public void setExited(boolean exited) {
		this.exited = exited;
	}
	
	
}

