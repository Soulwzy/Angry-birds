package wang.zhi.yuan;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AngryBirds_Lodin extends JPanel{
	
	private BufferedImage background;		//����ͼƬ
	private BufferedImage button;			//��ťͼƬ
	private int height;						//��ťͼƬ�߶�
	private int weight;						//��ťͼƬ���
	private AngryBirds_Mouse mouse;			//���	
	boolean run;							//�����Ƿ���������
	AudioClip sound;						//��������
	
	AngryBirds_Lodin(boolean run) throws IOException
	{
		background = ImageIO.read(new File("src/picture/background.png"));
		button = ImageIO.read(new File("src/picture/startbutton.png"));
		this.height = button.getHeight();
		this.weight = button.getWidth();
		this.run = run;
		this.sound = Applet.newAudioClip(new File("src/music/backmusic.wav").toURI().toURL());
		this.sound.loop();
		this.mouse = new AngryBirds_Mouse();
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
		g.drawImage(button, 600, 550, null);
		g.drawImage(mouse.getPresent(), mouse.getX(), mouse.getY(), null);
	}
	
	public String action() throws MalformedURLException
	{	
		MouseListener ma = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getX() > 670 && e.getX() < 850 && e.getY() > 660 && e.getY() < 740)
				{
					run =  false;
					sound.stop();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.mousePress();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.mouseLoose();
			}
		};
		
		MouseMotionListener mb = new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.moveto(e.getX(),e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.moveto(e.getX(),e.getY());
			}
			
		};
		
		addMouseListener(ma);
		addMouseMotionListener(mb);
		
		while(run)
		{
			repaint();							//�ػ�Jpanel����
			try {
				Thread.sleep(10);				//�����߳�����ʱ��
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return "p_2";
	}
}

