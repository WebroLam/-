
class Hero
{
	private char name;
	private int atk;
	private int hp;
	private int ex;
	private int x,y;
	Hero()
	{
		
	}
	/**
	 * 初始化
	 * @param name
	 * @param atk
	 * @param hp
	 * @param ex
	 * @param x 位置x
	 * @param y	位置y
	 */
	public void ChuShiHua(char name,int atk,int hp,int ex,int x,int y)
	{
		this.name=name;
		this.atk = atk;
		this.hp = hp;
		this.ex = ex;
		this.x = x;
		this.y = y;
	}
	public char getName()
	{
		return name;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}