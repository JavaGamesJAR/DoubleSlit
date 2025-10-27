import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Solver extends JPanel implements Runnable
{
	private static final int WIDTH = /*1920/10*/1024, HEIGHT = /*1050*2/3*/768, N = 250;

	
	//lept
    private final WaveEquation we;
    public static double pow(double x, int n)
    {
    	double res = x;
    	
    	for(int i = 1; i<n;i++)
    		res*=x;
    	return res;
    	
    }
    public static int fact(int x)
    {
    	int res = 1;
    	
    	for(int i = 1; i<x+1;i++)
    		res*=i;
    	return res;
    	
    }
    JSlider y;
    double t = 0;
    public Solver()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        int xd = N, yd = N;
        
        double[][] intensity = new double[xd][yd];
        double[][] intensityt = new double[xd][yd];
        
        for(int x = 0; x<N; x++)
        {
        	for(int y = 0; y<yd; y++)
            {
        		//intensity[x][y] = 25*Math.exp(-0.005*((x-N/2-50)*(x-N/2-50)+(y-N/2)*(y-N/2)))*Math.sin(x*0.25+y*0.25*Math.tan(Math.asin(1/1.5)));
        		intensityt[x][y] = 0/*25*Math.exp(-0.005*((x-N/2-50)*(x-N/2-50)+0.05*(y-N/2)*(y-N/2)))*Math.cos(x*0.5)*/;
        		intensity[x][y] = 125*Math.exp(-0.005*((x-N/2-50)*(x-N/2-50)+/*0.05*/(y-N/2)*(y-N/2)))/*Math.sin(x*0.5)*/;
        		//intensity[x][y] += 3*25*Math.exp(-0.005*((x-N/2+50)*(x-N/2+50)+5*(y-N/2)*(y-N/2)))*Math.sin(-y*0.25);
        		
            }
        	
        }
        
        we = new WaveEquation(intensity, intensityt);
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("It's all wiggily!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Solver panel = new Solver();
        frame.add(panel);
        frame.setBackground(Color.BLACK);
        frame.pack();
        //position frame to the center of screen
        java.awt.Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int)(screen.getWidth() / 2 - frame.getWidth() / 2), 
                        (int)(screen.getHeight() / 2- frame.getHeight() / 2+50),
                        frame.getWidth(), frame.getHeight());
        frame.setVisible(true);
        
        new Thread(panel).start();
        
        while(true){
            //panel.repaint();
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){}
        }
    }

    @Override
    public void run(){
        System.out.println("Starting loop");
        
        double dt = 3E-3;
		
		while(true)
		{
			t++;
			repaint();
			we.step(dt);
			we.step(dt);
			we.step(dt);
			we.step(dt);
			we.step(dt);
			
		}
		 
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
    	//System.out.println("PaintComp");
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        //g2.fillRect(0, 0, WIDTH, HEIGHT);
        //System.out.println(particles[0].vy);
        g2.setColor(Color.GREEN);
        
        for(int i = 0; i < N; i++)
        {
        	for(int j = 0; j<N; j++)
        	{
        		g2.setColor(getColor(500-15*we.intensity[i][j]/*we.intensity[i][j]*/));
        		
        		
        		/*if((i-75)*(i-75)+(j-125)*(j-125)<=125*125/4&&(i-180)*(i-180)+(j-125)*(j-125)<=125*125/4)
        			g2.setColor(Color.BLACK);*/
        		/*if(i<=130&&i>=125&&(j>=17+125||j<=125-17))
        			g2.setColor(Color.BLACK);
        		/*if(i<=130&&i>=125&&((j<=125+15&&j>=125-15)||(j<=80)||(j>=170)))
        			g2.setColor(Color.BLACK);*/
        		/*if(i<=35+1.0/128*(j-N/2)*(j-N/2)&&i<75)
        			g2.setColor(Color.BLACK);
        		if(i<125&&(j>=N/2+32*Math.sqrt(5)||j<=N/2-32*Math.sqrt(5)))
        			g2.setColor(Color.BLACK);*/
        		g2.fillRect(i*2, j*2, 2, 2);
        		
        		/*g2.setColor(new Color(0, 0, 0, 1-(float)(we.getV(i, j)/2.0)));
        		g2.fillRect(i*2, j*2, 2, 2);*/
        		
        	}
        	
        }
        //g2.drawString("t="+this.t+"dt", 100, 100);
        g2.setColor(Color.RED);
        /*g2.drawLine(150*2, 0, 150*2, 2*N);
        g2.drawLine(175*2, 0, 175*2, 2*N);*/
        
        /*g2.drawOval(2*(180-N/4), 2*(N/4), 2*N/2, 2*N/2);
        g2.drawOval(2*(75-N/4), 2*(N/4), 2*N/2, 2*N/2);*/
    }
    public Color getColor(double y)
    {
    	//return new Color((float)(12.75*y+97.5)/255f, (float)(-12.75*y+127.5)/255f, 0);
    	return Color.getHSBColor((float)(0.25+3*y/(4*HEIGHT)), 1, 1);
    	
    }

}

