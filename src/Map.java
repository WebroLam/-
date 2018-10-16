
class Map
{
	private int m;
	private int n;
	private char[][] diTu;
	private Hero[] hero;
	
	Map(int m,int n,int HeroNum)
	{
		this.m = m;
		this.n = n;
		diTu = new char[m][n];
		
		for(int i = 0; i<m; i++)
			for(int j=0; j<n; j++)
				diTu[i][j]='.';
		
		hero = new Hero[HeroNum];
		for(int i=0;i<HeroNum;i++)
			hero[i] = new Hero();
	}
	
	public void print()
	{
		for(int i = 0; i<m;i++)
		{
			for(int j=0; j<n; j++)
				System.out.print(diTu[i][j]);
			System.out.println();
		}
	}
	public void ChuShiHua(int n,char name,int atk,int hp,int ex,int x,int y)//n表示第几个英雄,从1开始
	{
		hero[n-1].ChuShiHua(name,atk, hp, ex,x,y);
		GengXinWeiZhi(n);
	}
	public void GengXinWeiZhi(int n){
		diTu[hero[n-1].getX()][hero[n-1].getY()] = hero[n-1].getName();
	}
	
}