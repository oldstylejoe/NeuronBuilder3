package neuronbuilder;
import java.util.ArrayList;

/**
 * Utility class for calculating enclosure area.
 * @author Leo Zhou
 */
public class Enclosure {
    // not used
    public static double leastSquareCircle(PunctaDisplay display, ArrayList<Branch> branch) {

        double xbar = 0, ybar = 0;
        double[][] newCoords = new double[branch.size()][2];
        for (Branch i : branch)
        {
            xbar += i.x2();
            ybar += i.y2();
        }
        xbar /= branch.size();
        ybar /= branch.size();
        double uu = 0, uv = 0, vv = 0, uuu = 0, uvv = 0, vvv = 0, vuu = 0;
        for (int i = 0; i < branch.size(); i++)
        {
            double u = branch.get(i).x2() - xbar;
            double v = branch.get(i).y2() - ybar;
            newCoords[i][0] = u;
            newCoords[i][1] = v;
            uu += u * u;
            uv += u * v;
            vv += v * v;
            uuu += u * u * u;
            uvv += u * v * v;
            vvv += v * v * v;
            vuu += v * u * u;
        }
        double uc = (vv*(uuu+uvv)-uv*(vvv+vuu))/(2*(uu*vv-uv*uv));
        double vc = (uu*(vvv+vuu)-uv*(uuu+uvv))/(2*(uu*vv-uv*uv));
        double circRadius = Math.sqrt(uc*uc + vc*vc + (uu+vv)/branch.size());
        double xc = xbar + uc;
        double yc = ybar + vc;
        display.setEnclosure(xc, yc, circRadius*2, circRadius*2);
        return circRadius * circRadius * Math.PI;
    }
    // compute a rectangle based on minimal and maximal (x,y) coords
    public static double rectangle(PunctaDisplay display, ArrayList<Branch> branch) {
        double minX = display.getWidth(), minY = display.getHeight();
        double maxX = 0, maxY = 0;
        for (Branch i : branch)
        {
            minX = Math.min(minX, i.x2());
            minY = Math.min(minY, i.y2());
            maxX = Math.max(maxX, i.x2());
            maxY = Math.max(maxY, i.y2());
        }
        double w = maxX - minX, h = maxY - minY;
//        display.drawEnclosure((minX+maxX)/2, (minY+maxY)/2, w, h);
        return w * h;
    }
    // compute a minimal enclosing circle based on the convex hull
    public static double enclosingCircle(PunctaDisplay display, ArrayList<int[]> hull, boolean draw) {
        if (hull == null)
            return 0;
        int x1, y1, x2, y2, x3, y3, minI;
        int index = 0;
        int N = hull.size();
        double xc = 0, yc = 0, D = 0;
        int si1 = 0, si2 = 1;
        double angle, newAngle;
        while (true)
        {
            x1 = hull.get(si1)[0];
            y1 = hull.get(si1)[1];
            x2 = hull.get(si2)[0];
            y2 = hull.get(si2)[1];
            angle = Math.PI;
            minI = -1;
            for (int j = 0; j < hull.size(); j++)
            {
                if (j == si1 || j == si2) continue;
                x3 = hull.get(j)[0];
                y3 = hull.get(j)[1];
                newAngle = Math.acos(((x3-x1)*(x3-x2) + (y3-y1)*(y3-y2))
                        / (2*Math.hypot(x3-x1,y3-y1)*Math.hypot(x3-x2,y3-y2)));
                if (angle > newAngle)
                {
                    angle = newAngle;
                    minI = j;
                }
            }
            if (angle >= Math.PI / 2)
            {
                xc = (x1 + x2) / 2D;
                yc = (y1 + y2) / 2D;
                D = Math.hypot(x2-x1, y2-y1);
                break;
            }
            else
            {
                x3 = hull.get(minI)[0];
                y3 = hull.get(minI)[1];
                double a1 = Math.acos(((x1-x2)*(x3-x2) + (y1-y2)*(y3-y2))
                        / (2*Math.hypot(x1-x2,y1-y2)*Math.hypot(x3-x2,y3-y2)));
                double a2 = Math.acos(((x2-x1)*(x3-x1) + (y2-y1)*(y3-y1))
                        / (2*Math.hypot(x2-x1,y2-y1)*Math.hypot(x3-x1,y3-y1)));
                if (a1 <= Math.PI / 2 && a2 <= Math.PI / 2)
                {
                    double denom = 2*(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2));
                    xc = ((y2-y3)*(x1*x1+y1*y1) + (y3-y1)*(x2*x2+y2*y2) + (y1-y2)*(x3*x3+y3*y3))/denom;
                    yc = -((x2-x3)*(x1*x1+y1*y1) + (x3-x1)*(x2*x2+y2*y2) + (x1-x2)*(x3*x3+y3*y3))/denom;
                    D = 2* Math.hypot(x1-x3, y1-y3)*Math.hypot(x1-x2, y1-y2)*Math.hypot(x2-x3,y2-y3)/Math.abs(denom);
                    break;
                }
                else if (a1 > Math.PI / 2)
                    si2 = minI;
                else
                    si1 = minI;
            }
        }
        if (draw)
            display.setEnclosure(xc, yc, D, D);
        return Math.PI * D * D / 4D;
    }
    // compute the area enclosed by the convex hull and draws it
    public static double convexHull(PunctaDisplay display, ArrayList<int[]> hull, boolean draw) {
        if (hull == null)
            return 0;
        if (draw)
            display.setConvexHull(hull);
//        try{
//            for (int i = 2; i <= hull.size();i++){
//                Thread.sleep(100);
//            display.setConvexHull(new ArrayList<int[]>(hull.subList(0, i)));
//        display.repaint();}
//        } catch (InterruptedException e) {e.printStackTrace();}
        double area = 0;
        for (int i = 0; i < hull.size(); i++)
        {
            int j = (i + 1) % hull.size();
            area += hull.get(i)[0]*hull.get(j)[1] - hull.get(j)[0]*hull.get(i)[1];
        }
        return area / 2D;
    }
    // implements Graham Scan Algorithm to find the convex hull
    public static ArrayList<int[]> getConvexHull(PunctaDisplay display, ArrayList<Branch> branch) {
        if (branch.size() < 3) return null;
        ArrayList<int[]> set = new ArrayList<int[]>(branch.size());
        int minX = display.getWidth(), minY = display.getHeight();
        int index = -1;
        for (int i = 0; i < branch.size(); i++)
        {
            set.add(new int[]{branch.get(i).x2(), branch.get(i).y2()});
            if ((minY > set.get(i)[1]) ||
                    (minY == set.get(i)[1] && minX > set.get(i)[0]))
            {
                index = i;
                minX = set.get(i)[0];
                minY = set.get(i)[1];
            }
        }
        set.add(0,set.remove(index));
        java.util.Comparator<int[]> comp = new IntComparator(minX, minY);
        java.util.Collections.sort(set,comp);

        for (int i = 1; i < set.size(); i++)
        {
            int c = comp.compare(set.get(i-1), set.get(i));
            if (c == 0)
                set.remove(i--);
            else if (i != 1 && c == -1)
                set.remove(i-- - 1);
        }

        ArrayList<int[]> w = new java.util.ArrayList<int[]>(set.size());
        w.add(set.get(0));
        w.add(set.get(1));
        for (int i = 2; i < set.size(); )
        {
            int last = w.size() - 1;
            int[] pt1 = w.get(last);
            int[] pt2 = w.get(last - 1);
            if (isLeft(pt2, pt1, set.get(i)) > 0)
                w.add(set.get(i++));
            else
                w.remove(last);
        }
        return w;
    }
    // return > 0 is p3 is left of p1->p2
    private static double isLeft(int[] p1, int[] p2, int[] p3) {
        return (p2[0] - p1[0])*(p3[1] - p1[1]) - (p3[0] - p1[0])*(p2[1] - p1[1]);
    }
    // compare two points based on angle from the horizon centered at the minimal (x,y) point
    private static class IntComparator implements java.util.Comparator<int[]> {
        private int mX, mY;
        IntComparator(int x, int y) {mX = x; mY = y;}
        public int compare(int[] o1, int[] o2) {
            double d = Math.atan2(o1[1] - mY, o1[0] - mX)
                    - Math.atan2(o2[1] - mY, o2[0] - mX);
            if (d < 0) return -2;
            else if(d > 0) return 2;
            double dx = o1[0]-o2[0];
            if (dx < 0) return -1;
            else if (dx > 0) return 1;
            else return 0;
        }
    }
    // not used; attempt to implement a more efficient Megiddo's algorithm
    public static ArrayList<int[]> getConvexHull2(PunctaDisplay display, ArrayList<Branch> branch) {
        if (branch.size() < 3)
            return null;
        ArrayList<int[]> set = new ArrayList<int[]>(branch.size());
        int[][] deque = new int[branch.size()+1][2];
        int front = 3, back = 0;
        int second, secondLast;
        int start = 3;

        for (int i = 0; i < branch.size(); i++)
            set.add(new int[]{branch.get(i).x2(), branch.get(i).y2()});

        double k = isLeft(set.get(0), set.get(1), set.get(2));
        if (k == 0)
        {
            deque[0] = set.get(0);
            deque[1] = set.get(2);
            deque[2] = set.get(0);
            front = 2;
            for (start = 3; start < set.size(); start++)
                if (isLeft(deque[0],deque[1], set.get(start)) == 0)
                    deque[1] = set.get(start);
                else
                    break;
        }
        else
        {
            deque[0] = set.get(2);
            deque[3] = set.get(2);
            if (k > 0)
            {
                deque[1] = set.get(0);
                deque[2] = set.get(1);
            }
            else
            {
                deque[1] = set.get(1);
                deque[2] = set.get(0);
            }
        }

        second = front == 0 ? deque.length - 1 : front - 1;
        secondLast = back == deque.length - 1 ? 0 : back + 1;

        for (int i = start; i < set.size(); i++)
        {
            int[] nextPt = set.get(i);
            if (isLeft(deque[second], deque[front], nextPt) > 0 &&
                    isLeft(deque[back], deque[secondLast], nextPt) > 0)
                continue;

            while (isLeft(deque[second], deque[front], nextPt) <= 0)
            {
                front = second;
                second = front == 0? deque.length - 1 : front - 1;
            }

            front = front == deque.length - 1 ? 0 : front + 1;
            second = front == 0 ? deque.length - 1 : front - 1;
            deque[front] = nextPt;

            while (isLeft(deque[back], deque[secondLast], nextPt) <= 0)
            {
                back = secondLast;
                secondLast = back == deque.length - 1 ? 0 : back + 1;
            }

            back = back == 0 ? deque.length - 1 : back - 1;
            secondLast = back == deque.length - 1 ? 0 : back + 1;
            deque[back] = nextPt;
        }
        ArrayList<int[]> hull = new ArrayList<int[]>();
        if (back < front)
            for (int i = back; i < front; i++)
                hull.add(deque[i]);
        else
        {
            for (int i = 0; i < front; i++)
                hull.add(deque[i]);
            for (int i = back; i < deque.length; i++)
                hull.add(deque[i]);
        }
        return hull;
    }
}