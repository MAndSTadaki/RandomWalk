
import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import model.PositionHistogram;
import model.Simulation;

/**
 *
 * @author tadaki
 */
public class CLIMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 100000;
        int tmax = 500;
        double p = 0.60;
        Simulation sys = new Simulation(n, p, new Random(48L));
        for (int t = 0; t < tmax; t++) {
            sys.oneStep();
        }
        List<Point2D.Double> plist
                = PositionHistogram.getHist(sys.getWalkers());
        String filename = "output-500.txt";
        try ( PrintStream out
                = new PrintStream(new FileOutputStream(filename))) {
            for (Point2D.Double q : plist) {
                out.println(q.x + " " + q.y);
            }
        }
        for (int t = 0; t < tmax; t++) {
            sys.oneStep();
        }
        plist
                = PositionHistogram.getHist(sys.getWalkers());
        filename = "output-1000.txt";
        try ( PrintStream out
                = new PrintStream(new FileOutputStream(filename))) {
            for (Point2D.Double q : plist) {
                out.println(q.x + " " + q.y);
            }
        }
    }

}
