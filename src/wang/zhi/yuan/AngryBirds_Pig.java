package wang.zhi.yuan;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class AngryBirds_Pig extends Thread{
	
	private BufferedImage[] image; 					//����ز���
	
	private double x;								//��ͼƬX������
	private double y;								//��ͼƬY������
	private double width;							//��ͼƬ���
	private double height;							//��ͼƬ�߶�
	private boolean alived;							//���Ƿ񱻻�ɱ
	private boolean exited;							//��ͼƬ�Ƿ����	
	private BufferedImage present;					//���زĵĵ�ǰͼƬ
	private AudioClip sound;						//������ʱ������
	
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
		/**�����ز�ͼƬ*/
		this.image = new BufferedImage[5];
		this.image[0] = ImageIO.read(new File("src/picture/pig.png"));
		for(int i = 1; i < 5; i++)
		{
			this.image[i] = ImageIO.read(new File("src/picture/����" + i +".png"));
		}
		
		this.width = image[0].getWidth();
		this.height = image[0].getHeight();
		this.x = x;
		this.y = y;
		this.present = image[0];
		this.alived = true;
		this.exited = true;
	}
	
	/**������ʱ������*/
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
	
	/**��������������ʱ 
	 * 
	 * �ػ�ͼƬ�������ɢ�������ӱ���
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
		
		present = null;							//��ǰͼƬ����Ϊ��
		exited = false;							//�ػ������ݺ�������
	}
	
	/**����߳� ϵͳ�Զ�����*/
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

