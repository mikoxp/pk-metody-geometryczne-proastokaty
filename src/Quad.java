/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Quad {
    Point top1,top2,top3,top4;
    Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Quad(Point top1, Point top2, Point top3, Point top4, Color color) {
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
        this.top4 = top4;
        this.color = color;
    }

    public Point getTop1() {
        return top1;
    }

    public void setTop1(Point top1) {
        this.top1 = top1;
    }

    public Point getTop2() {
        return top2;
    }

    public void setTop2(Point top2) {
        this.top2 = top2;
    }

    public Point getTop3() {
        return top3;
    }

    public void setTop3(Point top3) {
        this.top3 = top3;
    }

    public Point getTop4() {
        return top4;
    }

    public void setTop4(Point top4) {
        this.top4 = top4;
    }

    public boolean pointIsTop(Point p){
        return p.equals(top1)||p.equals(top2)||p.equals(top3)||p.equals(top4);
    }
}
