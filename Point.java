public class Point {
    int x;
    int y;
    boolean c;

    public Point(int a, int b){
        x = a;
        y = b;
        c = false;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isC(){
        return c;
    }
    public void setC(boolean c){
        this.c = c;
    }
}