
public class WaveEquation 
{
	public WaveEquation(double[][] intensity, double[][] intensityt)
	{
		this.intensity = intensity;
		this.intensityt = intensityt;
		
	}
	public double intensity[][], intensityt[][];
	private static double c = 2;
	private static double l = 1;
	
	public void step(double dt)
	{
		for(int x = 1; x<intensity.length-1; x++)
		{
			for(int y = 1; y<intensity[0].length-1; y++)
			{
				double dIx = (intensity[x+1][y]+intensity[x-1][y]-2*intensity[x][y])/(l*l);
				double dIy = (intensity[x][y+1]+intensity[x][y-1]-2*intensity[x][y])/(l*l);
				
				double v = getV(x, y);
				
				intensityt[x][y]+=(dIx+dIy)*v*v*dt;
				intensity[x][y]+=intensityt[x][y]*dt;
				
				intensity[x][y]/=1.00001;
				intensityt[x][y]/=1.00001;
			}
			
		}
		//cleanBord();
		
	}
	private void cleanBord()
	{
		for(int x = 0; x<intensity.length; x++)
		{
			intensity[x][1] = 0;
			intensity[x][intensity[0].length-2] = 0;
			intensityt[x][1] = 0;
			intensityt[x][intensity[0].length-2] = 0;
			
		}
		for(int x = 0; x<intensity[0].length; x++)
		{
			intensity[1][x] = 0;
			intensity[intensity.length-2][x] = 0;
			intensityt[1][x] = 0;
			intensityt[intensity.length-2][x] = 0;
			
		}
		
	}
	public double getV(int x, int y)
	{
		/*if(x<=130&&x>=125&&((y<=125+15&&y>=125-15)||(y<=80)||(y>=170)))
			return 0;
		return c;*/
		if(x<=130&&x>=125)
			if((y<=125+15&&y>=125-15)||(y<=80)||(y>=170))
					return 0;
			else return c;
		return c;
		//return c;
		//return x>150 & x<175 ? c*1.5 : c;
		//return x/100;
		
		
		/*if(x<=130&&x>=125&&(y>=17+125||y<=125-17))
			return 0;
		if(x<=35+1.0/128*(y-125)*(y-125)&&x<75)
			return 0;
		if(x<125&&(y>=125+32*Math.sqrt(5)||y<=125-32*Math.sqrt(5)))
			return 0;
		if(x<125)
			return c;
		
		return (x-75)*(x-75)+(y-125)*(y-125)<=125*125/4&&(x-180)*(x-180)+(y-125)*(y-125)<=125*125/4 ? c*1.5 : c;*/
		
	}
	
}
