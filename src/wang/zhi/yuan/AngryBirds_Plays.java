package wang.zhi.yuan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AngryBirds_Plays extends JPanel{
	
	private BufferedImage background;		//背景图片
	private AngryBirds_Button button; 		//按钮图片
	private AngryBirds_Bird bird;			//加载基类鸟
	private AngryBirds_Redbird redbird; 	//加载红鸟			
	private AngryBirds_Yellowbird yellowbird;//加载黄鸟	
	private AngryBirds_Pig[] pig;			//加载猪
	private AngryBirds_Mouse mouse;			//加载鼠标
	private BufferedImage[] branch;			//树枝分解图片
	private Graphics2D gra;					//绘画图片对象
	private float width;					//画线的宽度
	private double distence;				//小鸟离中心距离
	private int centerX;                    //弹弓中心X坐标
	private int centerY;					//弹弓中心Y坐标
	private final int pigcount = 4; 		//猪的初始数量
	int pigcountpre;						//当前猪的数量
	int score;								//得分
	boolean run;
	String choice;
	
	public AngryBirds_Plays(boolean run) throws IOException
	{
		//this.bird = new AngryBirds_Bird();
		this.redbird = new AngryBirds_Redbird(105,770);			//构造一个小鸟，带坐标
		this.yellowbird = new AngryBirds_Yellowbird(35,760);
		this.button = new AngryBirds_Button(1450,0);
		
		this.pig = new AngryBirds_Pig[pigcount];
		for(int i = 0; i < pigcount; i++)
		{
			this.pig[i] = new AngryBirds_Pig(800 + i * 200, 300 + i * 100);
		}
		
		this.mouse = new AngryBirds_Mouse();					//构造鼠标
		this.background = ImageIO.read(new File("src/picture/background2.png")); 				//设置背景图片
		this.branch = new BufferedImage[2];
		this.branch[0] = ImageIO.read(new File("src/picture/branch1.png"));
		this.branch[1] = ImageIO.read(new File("src/picture/branch2.png"));
		this.width = 5.0f;
		
		this.centerX = 200;
		this.centerY = 630;
		this.score = 0;
		this.pigcountpre = pigcount;
		bird = yellowbird;
		this.run = run;
		this.choice = "p_2";
	}
	
	
	/**设置重绘的界面，系统自动调用，刷新顺序构造图层重叠效果
	 * 
	 * 每次刷新判断猪和鸟的位置是否碰撞
	 * 以及鸟的位置信息
	 * 先画背景按钮以及图标
	 * 最后画
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		score = (pigcount - pigcountpre) * 100;
		pigcountpre = pigcount;
		
		
		for(int i = 0; i < pigcount; i++)
		{
			if(bird.isCollide(pig[i]))
			{
				pig[i].setAlived(false);
			}	
		}
		
		for(int i = 0; i < pigcount; i++)
		{
			if(pig[i].isAlived() == false)
			{
				pigcountpre--;
			}
		}	
		
		if(pigcountpre == 0)
		{
			//System.exit(0);
			run = false;
		}

		if(bird.getX() > 200 && !bird.isDrug() && bird.chosen)
		{
			bird.setFly(true);
		}
		
		/**设置画线颜色以及宽度*/
		this.gra = (Graphics2D)g;
		this.gra.setStroke(new BasicStroke(width));
		this.gra.setColor(new Color(48,22,8));
		g.drawImage(background, 0, 0, 1600, 900, null);							//背景图片拉伸到屏幕设定大小
		g.drawImage(button.getButtonImage(), button.getX(), button.getY(), button.getWeight(), button.getHeight(),null);
		
		/**将树枝截图开再进行拼接，构造图形层叠顺序，小鸟可以从两个树枝中间经过*/
		g.drawImage(branch[1], 200, 610, null);
		g.drawImage(redbird.present, (int)redbird.getX(), (int)redbird.getY(), null);
		g.drawImage(yellowbird.present, (int)yellowbird.getX(), (int)yellowbird.getY(), null);
		g.drawImage(branch[0], 169, 598, null);
		
		/**画出两条线*/
		if(bird.isChosen() && !bird.isFly())
		{
			this.gra.drawLine((int)bird.getX(), (int)bird.getY() + bird.getHeight()/2, 179, 636);
			this.gra.drawLine((int)bird.getX(), (int)bird.getY() + bird.getHeight()/2, 226, 639);	
		}

		/**绘制猪的图片
		 * 
		 * 判断猪是否还存在，不存在不重绘图片
		 * 
		 * */
		for(int i = 0; i < pigcount; i++)
		{
			if(pig[i].isExited())
			{
				g.drawImage(pig[i].getPresent(), (int)pig[i].getX(), (int)pig[i].getY(), null);
			}
		}
		
		/**绘制得分*/
		score(g);

		g.drawImage(mouse.getPresent(), mouse.getX(), mouse.getY(), null);			//画出鼠标当前状态
	}
	
	private void score(Graphics g)
	{
		Font f = new Font("", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.BLUE);
		g.drawString("count:" + pigcountpre, 20, 30);
		g.drawString("score:" + score , 20, 60);
	}

	/**设置鼠标事件监听，包括鼠标拖动和鼠标点击事件*/
	public String action()
	{
		MouseMotionListener ma = new MouseMotionListener() {						//鼠标拖动事件

			@Override
			public void mouseDragged(MouseEvent e) {		
				// TODO Auto-generated method stub
				if(bird.isDrug())
				{
					distence = Math.sqrt((e.getX() - centerX)*(e.getX() - centerX) + (e.getY() - centerY)*(e.getY() - centerY)); //设置60 - 120
					if(distence > 160)
					{
						bird.moveTo(160 / distence * (e.getX() - centerX) + centerX, 160 / distence * (e.getY() - centerY) + centerY);
						//System.out.println("1");
					}
					else
					{
						bird.moveTo(e.getX(), e.getY());
					}
					
					/**
					 * 设置距离为合理的范围
					 */
					distence = distence > 120 ? 120 : distence;
					distence = distence < 60 ? 60 : distence;
					
					/**确保绘画时的线的宽度在合理范围内*/
					width =  (float) (600.0/distence); // 5 - 10;
				}
				
				mouse.moveto(e.getX(), e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.moveto(e.getX(), e.getY());
			}		
		};
		
		MouseListener mb = new MouseListener(){   			//鼠标点击事件

			@Override
			/**点击刷新界面*/
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(bird.fly && bird.vy > 0)
				{
					bird.special = true;
					if(bird == yellowbird)
					{
						((AngryBirds_Yellowbird) bird).changeFast();
						((AngryBirds_Yellowbird) bird).setNewVx(bird.vx * 2);
						((AngryBirds_Yellowbird) bird).setNewVy(bird.vy * 2);
					}
				}
				
				if(redbird.isChosen(e.getX(), e.getY()))
				{
					redbird.setChosen(true);
					yellowbird.setChosen(false);
					bird = redbird;
					bird.moveTo(140, 700);
					new Thread(bird).start();
				}
				
				if(yellowbird.isChosen(e.getX(), e.getY()))
				{
					yellowbird.setChosen(true);
					redbird.setChosen(false);
					bird = yellowbird;
					bird.moveTo(140, 700);
					new Thread(bird).start();
				}
				
				if(button.isChonse(e.getX(),e.getY()))
				{
					try {
						reFresh();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
				
				if(bird.isChosen(e.getX(), e.getY()))
				{
					//System.out.println(Bird.isChosen());
					if(bird.isChosen())
					{
						bird.setDrug(true);
					}
				}
			}

			@Override
			/**
			 * 松开时设置鸟的初始速度 播放鸟飞翔的音乐
			 * */
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse.mouseLoose();
				if(bird.isDrug())
				{
					bird.setVx((centerX - bird.getX())/13.0);
					bird.setVy((centerY - bird.getY())/13.0);
					
					bird.setDrug(false);
					//Bird.setUnmove(false);
					try {
						bird.birdFly();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		};
		
		/**添加鼠标事件监听*/
		this.addMouseMotionListener(ma);    //鼠标移动事件监听
		this.addMouseListener(mb); 			//鼠标事件监听
		
		/**启动线程*/
		

		//mouse.start();
		for(int i = 0; i < pigcount; i++)
		{
			//pig[i].start();
			new Thread(pig[i]).start();
		}
		
		/**无限循环一直刷新屏幕，构造图形运动*/
		while(run)
		{
			repaint();						//重绘Jpanel界面
			try {
				Thread.sleep(10);			//设置线程休眠时间
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return choice;
	}
	
	private void reFresh() throws IOException
	{
		this.choice = "p_3";
		run = false;
	}
}
