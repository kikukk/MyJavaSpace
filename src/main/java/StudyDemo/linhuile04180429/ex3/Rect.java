package ex3;

public class Rect {
    private double row;
    private double col;
    public double getPerimeter(){
        return 2*(row+col);
    }
    public double getArea(){
        return row*col;
    }

    public void getAllInfo(){
        System.out.println("面积："+getArea());
        System.out.println("周长："+getPerimeter());
    }

    public void setRow(double row) {
        this.row = row;
    }
    public double getRow() {
        return row;
    }
    public void setCol(double col) {
        this.col = col;
    }
    public double getCol() {
        return col;
    }

}
