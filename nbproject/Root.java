package neuronbuilder;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author Leo Zhou
 */
public class Root extends Arbor {
    private int radius;
    public Root(int x, int y, int rad, Color c) {
        super(x, y, c);
        radius = rad;
    }
    public void draw(Graphics2D g) {
        g.setColor(color.darker());
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}
