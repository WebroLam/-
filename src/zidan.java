import java.awt.*;
/**
 * �ӵ��࣬���ڷ����ʸ
 *
 * @author SZU
 */
class zidan {
    private int m = 12;
    private  int n = 12;
    private int x;
    private int y;

    zidan(int xx, int yy) {
        x = xx;
        y = yy;
    }

    /**
     * @param hero Ӣ�����ݴ���
     * @param FangXiang ��������
     * @param maps  ��ͼ���ݴ���
     * @return ���ر�������Ӣ����ţ���0��ʼ��;-1����δ����
     */
    public int fire(Hero[] hero, char FangXiang, char[][] maps)
    {
        switch (FangXiang) {
            case 'u':
            case 'U':
                for (int i = x - 1; i >= 0; i--) {
                    if (maps[i][y] == '*') {
                        break;
                    } else if (maps[i][y] == '.') {
                        maps[i][y] = '��';
                        print(maps);
                        maps[i][y] = '.';
                    } else {
                        for (int j = 0; true; j++) {
                            if (maps[i][y] == hero[j].getName()) {
                                print(maps);
                                return j;
                            }
                        }
                    }
                }
                break;

            case 'd':
            case 'D':
                for (int i = x + 1; i < m; i++) {
                    if (maps[i][y] == '*') {
                        break;
                    } else if (maps[i][y] == '.') {
                        maps[i][y] = '��';
                        print(maps);
                        maps[i][y] = '.';
                    } else {
                        for (int j = 0; true; j++) {
                            if (maps[i][y] == hero[j].getName()) {
                                print(maps);
                                return j;
                            }
                        }
                    }
                }
                break;
            case 'l':
            case 'L':
                for (int i = y - 1; i >= 0; i--) {
                    if (maps[x][i] == '*') {
                        break;
                    } else if (maps[x][i] == '.') {
                        maps[x][i] = '��';
                        print(maps);
                        maps[x][i] = '.';
                    } else {
                        for (int j = 0; true; j++) {
                            if (maps[x][i] == hero[j].getName()) {
                                print(maps);
                                return j;
                            }
                        }
                    }
                }
                break;
            case 'r':
            case 'R':
                for (int i = y + 1; i < n; i++) {
                    if (maps[x][i] == '*') {
                        break;
                    } else if (maps[x][i] == '.') {
                        maps[x][i] = '��';
                        print(maps);
                        maps[x][i] = '.';
                    } else {
                        for (int j = 0; true; j++) {
                            if (maps[x][i] == hero[j].getName()) {
                                print(maps);
                                return j;
                            }
                        }
                    }
                }
                break;
        }
        print(maps);
        return -1;
    }

    private void print(char[][] Operator) {
        /* TODO Auto-generated method stub */
        Robot one = null;
        try {
            one = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        one.delay(500);/*
        System.out.println("===============");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(Operator[i][j]);
            System.out.println();
        }
        System.out.println("===============");*/
    }
}