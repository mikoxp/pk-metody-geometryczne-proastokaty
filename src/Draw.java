
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Draw {

    private final File drawFile, temp;
    private final String tempN="temp";
    private final Set points;
    private final List<Quad> quadList;
    private PrintWriter printWriter;

    public List<Quad> getQuadList() {
        return quadList;
    }

    public Set getPoints() {
        return points;
    }

    public Draw(String fileName) {
        drawFile = new File(fileName);
        temp = new File(tempN);
        points = new HashSet();
        quadList = new ArrayList<>();
    }

    public void loadQuad() throws IOException {
        PrepareFile.changeChar('.', ',', drawFile, temp);
        Point t1, t2, t3, t4;
        Color color;
        int cX = 5;
        int cY = 70;
        Scanner scan = new Scanner(new FileReader(temp));
        scan.nextLine();

        for (int i = 0; i < 27; i++) {

            t1 = new Point(scan.nextDouble() + cX, scan.nextDouble() + cY);
            points.add(t1);
            scan.nextLine();

            t2 = new Point(scan.nextDouble() + cX, scan.nextDouble() + cY);
            points.add(t2);
            scan.nextLine();

            t3 = new Point(scan.nextDouble() + cX, scan.nextDouble() + cY);
            points.add(t3);
            scan.nextLine();

            t4 = new Point(scan.nextDouble() + cX, scan.nextDouble() + cY);
            points.add(t4);
            scan.nextLine();

            scan.nextLine();

            color = new Color(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());
            quadList.add(new Quad(t1, t2, t3, t4, color));
            for (int j = 0; j < 10; j++) {
                scan.nextLine();
            }
        }

    }

    public void drawQuad(Quad q) {

        printWriter.printf("%f %f moveto\n", q.getTop1().getX(), q.getTop1().getY());
        printWriter.printf("%f %f lineto\n", q.getTop2().getX(), q.getTop2().getY());
        printWriter.printf("%f %f lineto\n", q.getTop3().getX(), q.getTop3().getY());
        printWriter.printf("%f %f lineto\n", q.getTop4().getX(), q.getTop4().getY());
        printWriter.printf("%f %f lineto\n", q.getTop1().getX(), q.getTop1().getY());
    }

    public void drawing(String destinyName, Point point) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        printWriter = new PrintWriter(tempN, "UTF-8");
        printWriter.println("%! Adobe -PS");

        for (Quad q : quadList) {
            drawQuad(q);
            printWriter.printf("%f %f %f setrgbcolor\n", q.getColor().getR(), q.getColor().getG(), q.getColor().getB());
            printWriter.println("closepath");
            printWriter.println("fill");
            printWriter.printf("%d setgray\n", 0);
            drawQuad(q);
            printWriter.println("stroke");
        }
        drawPoints(point, printWriter);
        printWriter.println("showpage");
        printWriter.close();
        PrepareFile.changeChar(',', '.', new File(tempN), new File(destinyName));
    }

    private void drawPoints(Point p, PrintWriter printWriter) {
        List<Point> pointList = designatePointsToSelection(p);
        printWriter.printf("%f %f %f setrgbcolor\n", 1.0, 0.0, 0.0);
        printWriter.printf("%f %f moveto\n", p.getX(), p.getY());
        printWriter.printf("%f %f %f %d %d arc fill\n", p.getX(), p.getY(), 5.0, 0, 360);
        printWriter.printf("%f %f %f setrgbcolor\n", 0.0, 0.0, 1.0);
        for (Point point : pointList) {
            printWriter.printf("%f %f moveto\n", point.getX(), point.getY());
            printWriter.printf("%f %f %f %d %d arc fill\n", point.getX(), point.getY(), 5.0, 0, 360);
        }
    }

    private void addPoints(Quad q, Point p, Set<Point> pointToDraw) {

        if (q.getTop1().equals(p)) {
            pointToDraw.add(q.getTop2());
            pointToDraw.add(q.getTop3());
            pointToDraw.add(q.getTop4());
        } else if (q.getTop2().equals(p)) {
            pointToDraw.add(q.getTop1());
            pointToDraw.add(q.getTop3());
            pointToDraw.add(q.getTop4());
        } else if (q.getTop3().equals(p)) {
            pointToDraw.add(q.getTop2());
            pointToDraw.add(q.getTop1());
            pointToDraw.add(q.getTop4());
        } else {
            pointToDraw.add(q.getTop2());
            pointToDraw.add(q.getTop3());
            pointToDraw.add(q.getTop1());
        }

    }

    private List<Point> designatePointsToSelection(Point p) {
        Set<Point> pointToDraw;
        pointToDraw = new HashSet();
        for (Quad q : quadList) {
            if (q.pointIsTop(p)) {
                addPoints(q, p, pointToDraw);
            }
        }
        return new ArrayList<>(pointToDraw);
    }
}
