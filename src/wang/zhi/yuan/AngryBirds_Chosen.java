package wang.zhi.yuan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AngryBirds_Chosen extends JPanel{
	
	private BufferedImage background;		//背景图片
	private AngryBirds_Mouse mouse;			//鼠标	
	boolean run;							//界面是否处在运行中
	
	AngryBirds_Chosen(boolean run) throws IOException
	{
		background = ImageIO.read(new File("src/picture/chosen1.png"));
		this.run = run;
		this.mouse = new AngryBirds_Mouse();
	}
	
	public void paintComponent(Graphics g)
	{
//		g.drawRect(0,0,1600,900);//画线框
//		g.setColor(Color.black);g.fillRect(0,0,1600,900);//
		g.drawImage(background, 0, 0, null);
		g.drawImage(mouse.getPresent(), mouse.getX(), mouse.getY(), null);
	}
	
	public String action() throws MalformedURLException
	{	
		MouseListener ma = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getPoint());
				if(e.getX() > 260 && e.getX() < 360 && e.getY() > 250 && e.getY() < 350)
				{
					run =  false;
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
			repaint();							//重绘Jpanel界面
			try {
				Thread.sleep(10);				//设置线程休眠时间
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return "p_3";
	}
}

