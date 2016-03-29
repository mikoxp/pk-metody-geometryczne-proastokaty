
import java.io.IOException;
import java.util.ArrayList;
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
public class Runner {

    public static void main(String[] args) throws IOException {

        String nameFile = "prostokaty.ps";
        int nr;
        Draw draw = new Draw(nameFile);
        draw.loadQuad();
        ArrayList<Point> arrayList = new ArrayList<>(draw.getPoints());
        Point p;
        for (int i = 0; i < arrayList.size(); i++) {
            p = arrayList.get(i);
            System.out.printf("Nr. %d (%.3f , %.3f)\n", i, p.getX(), p.getY());
        }
        System.out.print("Wybierz numer punktu: ");
        Scanner scanner = new Scanner(System.in);
        try {
           nr = scanner.nextInt();
            if (nr >= arrayList.size() || nr < 0) {
                throw new IllegalArgumentException("Nie ma takiego numeru");
            }
            draw.drawing("destiny.ps", arrayList.get(nr));
            System.out.println("Sukces !!!");
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
        }

    }
}
