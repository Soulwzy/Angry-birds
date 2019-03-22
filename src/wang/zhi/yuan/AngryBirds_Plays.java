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
	
	private BufferedImage background;		//����ͼƬ
	private AngryBirds_Button button; 		//��ťͼƬ
	private AngryBirds_Bird bird;			//���ػ�����
	private AngryBirds_Redbird redbird; 	//���غ���			
	private AngryBirds_Yellowbird yellowbird;//���ػ���	
	private AngryBirds_Pig[] pig;			//������
	private AngryBirds_Mouse mouse;			//�������
	private BufferedImage[] branch;			//��֦�ֽ�ͼƬ
	private Graphics2D gra;					//�滭ͼƬ����
	private float width;					//���ߵĿ��
	private double distence;				//С�������ľ���
	private int centerX;                    //��������X����
	private int centerY;					//��������Y����
	private final int pigcount = 4; 		//��ĳ�ʼ����
	int pigcountpre;						//��ǰ�������
	int score;								//�÷�
	boolean run;
	String choice;
	
	public AngryBirds_Plays(boolean run) throws IOException
	{
		//this.bird = new AngryBirds_Bird();
		this.redbird = new AngryBirds_Redbird(105,770);			//����һ��С�񣬴�����
		this.yellowbird = new AngryBirds_Yellowbird(35,760);
		this.button = new AngryBirds_Button(1450,0);
		
		this.pig = new AngryBirds_Pig[pigcount];
		for(int i = 0; i < pigcount; i++)
		{
			this.pig[i] = new AngryBirds_Pig(800 + i * 200, 300 + i * 100);
		}
		
		this.mouse = new AngryBirds_Mouse();					//�������
		this.background = ImageIO.read(new File("src/picture/background2.png")); 				//���ñ���ͼƬ
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
	
	
	/**�����ػ�Ľ��棬ϵͳ�Զ����ã�ˢ��˳����ͼ���ص�Ч��
	 * 
	 * ÿ��ˢ���ж�������λ���Ƿ���ײ
	 * �Լ����λ����Ϣ
	 * �Ȼ�������ť�Լ�ͼ��
	 * ���
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
		
		/**���û�����ɫ�Լ����*/
		this.gra = (Graphics2D)g;
		this.gra.setStroke(new BasicStroke(width));
		this.gra.setColor(new Color(48,22,8));
		g.drawImage(background, 0, 0, 1600, 900, null);							//����ͼƬ���쵽��Ļ�趨��С
		g.drawImage(button.getButtonImage(), button.getX(), button.getY(), button.getWeight(), button.getHeight(),null);
		
		/**����֦��ͼ���ٽ���ƴ�ӣ�����ͼ�β��˳��С����Դ�������֦�м侭��*/
		g.drawImage(branch[1], 200, 610, null);
		g.drawImage(redbird.present, (int)redbird.getX(), (int)redbird.getY(), null);
		g.drawImage(yellowbird.present, (int)yellowbird.getX(), (int)yellowbird.getY(), null);
		g.drawImage(branch[0], 169, 598, null);
		
		/**����������*/
		if(bird.isChosen() && !bird.isFly())
		{
			this.gra.drawLine((int)bird.getX(), (int)bird.getY() + bird.getHeight()/2, 179, 636);
			this.gra.drawLine((int)bird.getX(), (int)bird.getY() + bird.getHeight()/2, 226, 639);	
		}

		/**�������ͼƬ
		 * 
		 * �ж����Ƿ񻹴��ڣ������ڲ��ػ�ͼƬ
		 * 
		 * */
		for(int i = 0; i < pigcount; i++)
		{
			if(pig[i].isExited())
			{
				g.drawImage(pig[i].getPresent(), (int)pig[i].getX(), (int)pig[i].getY(), null);
			}
		}
		
		/**���Ƶ÷�*/
		score(g);

		g.drawImage(mouse.getPresent(), mouse.getX(), mouse.getY(), null);			//������굱ǰ״̬
	}
	
	private void score(Graphics g)
	{
		Font f = new Font("", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.BLUE);
		g.drawString("count:" + pigcountpre, 20, 30);
		g.drawString("score:" + score , 20, 60);
	}

	/**��������¼���������������϶���������¼�*/
	public String action()
	{
		MouseMotionListener ma = new MouseMotionListener() {						//����϶��¼�

			@Override
			public void mouseDragged(MouseEvent e) {		
				// TODO Auto-generated method stub
				if(bird.isDrug())
				{
					distence = Math.sqrt((e.getX() - centerX)*(e.getX() - centerX) + (e.getY() - centerY)*(e.getY() - centerY)); //����60 - 120
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
					 * ���þ���Ϊ����ķ�Χ
					 */
					distence = distence > 120 ? 120 : distence;
					distence = distence < 60 ? 60 : distence;
					
					/**ȷ���滭ʱ���ߵĿ���ں���Χ��*/
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
		
		MouseListener mb = new MouseListener(){   			//������¼�

			@Override
			/**���ˢ�½���*/
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
			 * �ɿ�ʱ������ĳ�ʼ�ٶ� ��������������
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
		
		/**�������¼�����*/
		this.addMouseMotionListener(ma);    //����ƶ��¼�����
		this.addMouseListener(mb); 			//����¼�����
		
		/**�����߳�*/
		

		//mouse.start();
		for(int i = 0; i < pigcount; i++)
		{
			//pig[i].start();
			new Thread(pig[i]).start();
		}
		
		/**����ѭ��һֱˢ����Ļ������ͼ���˶�*/
		while(run)
		{
			repaint();						//�ػ�Jpanel����
			try {
				Thread.sleep(10);			//�����߳�����ʱ��
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
