/**
 * 地图类
 * 用于保存地图数据
 * 输出地图
 */
class Map{
    private int m = 12;
    private int n = 12;
    char[][] maps;

    Map()
    {
        maps = new char[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                maps[i][j] = '.';
        for(int i = 2; i < 5; i++)
            maps[3][i]='*';
    }
    public void print() {
        System.out.println("===============");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(maps[i][j]);
            System.out.println();
        }
        System.out.println("===============");
    }

}