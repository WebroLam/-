/**
 * 个性化英雄后裔，属于远程攻击。
 * @author SZU
 *
 */
class zidan{
	private int x;
	private int y;
	zidan(int xx,int yy)
	{
		x=xx;y=yy;
	}
	/**
	 * 
	 * @param hero
	 * @param FangXiang
	 * @param map
	 * @param n
	 * @param m
	 * @return 返回被攻击的英雄序号（从0开始）;-1代表未击中
	 */
	public int fire(Hero []hero,char FangXiang,char [][]map,int m,int n)
	{
		switch(FangXiang){
		case 'u':
		case 'U':
			for(int i = x-1;i>=0;i--){
				if(map[i][y]=='*')
				{
					break;
				}
				else if(map[i][y]=='.')
				{
					map[i][y]='↑';
					print(map,m,n);
					map[i][y]='.';
				}
				else
				{
					for(int j=0;true;j++)
					{
						if(map[i][y]==hero[j].getName())
							return j;
					}
				}
			}
		
			break;
			
		case 'd':
		case 'D':
			for(int i = x+1;i<m;i++){
				if(map[i][y]=='*')
				{
					break;
				}
				else if(map[i][y]=='.')
				{
					map[i][y]='↓';
					print(map,m,n);
					map[i][y]='.';
				}
				else
				{
					for(int j=0;true;j++)
					{
						if(map[i][y]==hero[j].getName())
							return j;
					}
				}
			}
			break;
		case 'l':
		case 'L':
			for(int i = y-1;i>=0;i--){
				if(map[x][i]=='*')
				{
					break;
				}
				else if(map[x][i]=='.')
				{
					map[x][i]='←';
					print(map,m,n);
					map[x][i]='.';
				}
				else
				{
					for(int j=0;true;j++)
					{
						if(map[x][i]==hero[j].getName())
							return j;
					}
				}
			}
			break;
		case 'r':
		case 'R':
			for(int i = y+1;i<n;i++){
				if(map[x][i]=='*')
				{
					break;
				}
				else if(map[x][i]=='.')
				{
					map[x][i]='→';
					print(map,m,n);
					map[x][i]='.';
				}
				else
				{
					for(int j=0;true;j++)
					{
						if(map[x][i]==hero[j].getName())
							return j;
					}
				}
			}
			break;		
		}
		return -1;
	}

	private void print(char[][] map, int m, int n) {
		// TODO Auto-generated method stub
		System.out.println("===============");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
        System.out.println("===============");
	}
}