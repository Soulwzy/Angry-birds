package wang.zhi.yuan;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public abstract class AngryBirds_Bird implements Runnable{
	
    BufferedImage present; 							//加载当前图片图片		
	
	protected double x;								//图片加载的x坐标
	protected double y;								//图片加载的y坐标	
	protected int width;							//图片宽度
	protected int height;							//图片高度
	protected double vx;							//鸟运动时的x轴速度
	protected double vy;							//鸟运动时的y轴速度
	protected boolean drag;							//鸟是否处于拖拽中
	protected boolean fly;							//鸟否处于飞翔状态
	protected boolean chosen;					    //鸟是否被选择
	protected boolean special;						//特殊飞行
	protected AudioClip sound;						//鸟飞翔音乐
	
	
	/**初始化函数，设置鸟的初始属性*/
	public AngryBirds_Bird(int x, int y) throws IOException
	{
		this.vx = vy = 0;
		this.x = x;
		this.y = y;
		this.drag = false;
		this.fly = false;
		this.chosen = false;
		this.special = false;
	}
	
	/**鸟移动到的位置*/
	public void moveTo(double x, double y)
	{
		this.x = x - width / 2 ;
		this.y = y - height / 2;
	}
	
	abstract public void special();
	
	/**判断鸟是否被选中
	 * 
	 * 图标像素和鼠标坐标重合即被选中
	 * 
	 * */
	public boolean isChosen(int x, int y)
	{
		int dx = (int) (x - this.x);
		int dy = (int) (y - this.y);
		return (dx > 0 && dx < width) && (dy > 0 && dy < height);
	}
	
	/**开始线程后，系统自动调用run()函数*/
	public void run()
	{
		while(this.chosen)
		{
			
			move();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**鸟的运行轨迹*/
	public void move()
	{	
		if(fly)
		{
			if(special)
			{	
				special();
			}
			else
			{
				if(y > 813 - height - 1)
				{
					vx *= 0.9;
				}
				
				vx *= 0.995;
				vy += 0.08;
			}
		}
		
		if(y > 813 - height || y < 0)
		{
			if(y > 813 - height)
			{
				y = 813 - height;
			}
			
			if(y < 0)
			{
				y = 0;
			}
			
			vy *= -0.5;
		}
		
		if(Math.abs(vx) <= 0.1 && Math.abs(vy) <= 0.08)
		{
			vx = vy = 0;
			
			//fly = false;
			if(fly)
			{
				reSet();
			}
		}
			
		x += vx;
		y += vy;
		
		//System.out.println("vx:"+vx+"vy"+vy);
	}
	
	/**判断是否鸟和猪相撞*/
	public boolean isCollide(AngryBirds_Pig pig)
	{
		if( distence(pig) < 50)
		{
			return true;
		}
		
		return false;
	}
	
	/**判断鸟和猪的距离*/
	public double distence(AngryBirds_Pig pig)
	{
		/**sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))*/
		return Math.sqrt(
				((pig.getX() + pig.getWidth() / 2) - (this.getX() + this.getWidth() / 2)) * 
				((pig.getX() + pig.getWidth() / 2) - (this.getX() + this.getWidth() / 2)) +
				((pig.getY() + pig.getHeight() / 2) - (this.getY() + this.getHeight() / 2)) * 
				((pig.getY() + pig.getHeight() / 2) - (this.getY() + this.getHeight() / 2))
				);
	}
	
	/**鸟飞翔时的音乐*/
	public void birdFly() throws MalformedURLException
	{
		this.sound = Applet.newAudioClip(new File("src/music/birdflying.wav").toURI().toURL());
		sound.play();
	}
	
	abstract protected void reSet();

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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public boolean isDrug() {
		return drag;
	}

	public void setDrug(boolean drug) {
		this.drag = drug;
	}

	public boolean isFly() {
		return fly;
	}

	public void setFly(boolean fly) {
		this.fly = fly;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chonsen) {
		this.chosen = chonsen;
	}	
}

