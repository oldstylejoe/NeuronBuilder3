package neuronbuilder;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Leo Zhou
 */
public class Branch {
    private int x1, y1, x2, y2, minX, minY;
    private Color color, markColor;
    private Arbor endArbor;

    public Branch(int x1, int y1, int x2, int y2, Arbor arb) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = arb.color;
        markColor = new Color(color.getBlue(), color.getRed(), color.getGreen()).darker();
        endArbor = new Arbor(x2, y2, color);
    }
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        g.setColor(markColor);
        g.fillOval(x1 - 3, y1 - 3, 6, 6);
        g.setColor(Color.blue);
        g.fillOval(x2 - 3, y2 - 3, 6, 6);
    }
    public int x1() {
        return x1;
    }
    public int y1() {
        return y1;
    }
    public int x2() {
        return x2;
    }
    public int y2() {
        return y2;
    }
    public double length() {
        return Math.hypot(x2 - x1, y2 - y1);
    }
    /**
     * @param puncta
     * @return the arbors corresponding the adjacent puncta from this branch;
     */
    public ArrayList<Arbor> adjacentArbors(ArrayList<Point> puncta) {
        if (puncta.isEmpty())
            throw new RuntimeException("Error in branch selection!");
        ArrayList<Arbor> arb = new ArrayList<Arbor>();
        double x, y, u;
        int newX, newY;
        int minX = x1, minY = y1;
        for (Point pt : puncta)
        {
            x = pt.x;
            y = pt.y;
            u = ((x-x1)*(x2-x1)+(y-y1)*(y2-y1))/((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
            newX = (int)(x1 + u * (x2 - x1) + 0.5);
            newY = (int)(y1 + u * (y2 - y1) + 0.5);
            arb.add(new Arbor(newX, newY, color));
            if (Math.abs(x2 - newX) < Math.abs(x2 - minX))
            {
                minX = newX;
                minY = newY;
            }
        }
        this.minX = minX;
        this.minY = minY;
        arb.add(endArbor);
        return arb;
    }
    /**
     * trim the branch so that it falls back to the farthest adjacent puncta;
     */
    public void trim() {
        x2 = minX;
        y2 = minY;
        endArbor.x = x2;
        endArbor.y = y2;
    }
}