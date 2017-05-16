package neuronbuilder;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Leo Zhou
 */
public class Arbor {
    protected int x, y, children;
    protected Color color;
    public Arbor(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        color = c;
        children = 0;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}