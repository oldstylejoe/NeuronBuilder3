package neuronbuilder;
/**
 *
 * @author Leo Zhou
 */
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.awt.Color;
import static java.lang.Math.exp;
import static java.lang.Math.sqrt;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Simulator extends javax.swing.JFrame implements Runnable {

    private int puncta;
    private int punctaSize;
    private int removeRadius;
    private int numberRoots;
    private int maxChildren;
    private int maxBranches;
    private int averageLength;
    private int lineWidth;
    private int delay;
    private Point[] punctaPos;
    private Root[] rootPos;
    private boolean suspended, drawCH, drawEC;
    private static final int NO = 0, YES = 1, INTERRUPTED = 2;
    private static final Color[] colors = new Color[] {
        Color.red, Color.green.darker(), Color.magenta, Color.blue,
        new Color(200, 150, 50)};

    private ArrayList<Point> occupiedPunctaPos;
    private ArrayList<Point> availablePunctaPos;
    private ArrayList<Branch> branchPos;
    private Branch previousBranch;
    private Random rand;
    private Thread thread;

    public Simulator() {
        initComponents();
        puncta = 2000;
        punctaSize = 10;
        removeRadius = 20;
        numberRoots = 1;
        maxChildren = 3;
        maxBranches = 100;
        averageLength = 20;
        lineWidth = 3;
        delay = 5;
        suspended = false;
        drawCH = true;
        drawEC = true;
        rand = new Random();

        jSpinner1.setModel(new SpinnerNumberModel(lineWidth, 1, 100, 1));
        JSpinner.DefaultEditor ed = ((JSpinner.DefaultEditor)jSpinner1.getEditor());
        ed.getTextField().setHorizontalAlignment(JTextField.LEFT);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        display = new neuronbuilder.PunctaDisplay();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        startB = new javax.swing.JButton();
        resetB = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        pauseB = new javax.swing.JToggleButton();
        CHCheckBox = new javax.swing.JCheckBox();
        ECCheckBox = new javax.swing.JCheckBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Puncta");
        setResizable(true);

        javax.swing.GroupLayout displayLayout = new javax.swing.GroupLayout(display);
        display.setLayout(displayLayout);
        displayLayout.setHorizontalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        displayLayout.setVerticalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        jLabel1.setText("Puncta");

        jTextField1.setText("2000");

        jLabel2.setText("Puncta Size");

        jTextField2.setText("10");

        jLabel3.setText("Remove Radius");

        jTextField3.setText("20");

        jLabel4.setText("Number Roots");

        jTextField4.setText("1");

        jLabel5.setText("Children");

        jLabel6.setText("Branches");

        jLabel7.setText("Length");

        jLabel8.setText("Line Width");

        jTextField5.setText("3");

        jTextField6.setText("100");

        jTextField7.setText("20");

        jSpinner1.setValue(3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner1)
                    .addComponent(jTextField7)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        startB.setMnemonic(java.awt.event.KeyEvent.VK_S);
        startB.setText("Start");
        startB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBActionPerformed(evt);
            }
        });

        resetB.setMnemonic(java.awt.event.KeyEvent.VK_R);
        resetB.setText("Reset");
        resetB.setPreferredSize(new java.awt.Dimension(60, 23));
        resetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBActionPerformed(evt);
            }
        });

        jLabel9.setText("0/100");

        jLabel10.setText("Delay (ms)");

        jTextField8.setText("5");

        pauseB.setMnemonic(java.awt.event.KeyEvent.VK_P);
        pauseB.setText("Pause");
        pauseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseBActionPerformed(evt);
            }
        });

        CHCheckBox.setMnemonic(java.awt.event.KeyEvent.VK_C);
        CHCheckBox.setSelected(true);
        CHCheckBox.setText("Convex Hull");
        CHCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHCheckBoxActionPerformed(evt);
            }
        });

        ECCheckBox.setMnemonic(java.awt.event.KeyEvent.VK_M);
        ECCheckBox.setSelected(true);
        ECCheckBox.setText("MEC");
        ECCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ECCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startB, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(CHCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pauseB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ECCheckBox))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CHCheckBox)
                    .addComponent(ECCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resetB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseB)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(display, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBActionPerformed
        puncta = Integer.parseInt(jTextField1.getText());
        punctaSize = Integer.parseInt(jTextField2.getText());
        removeRadius = Integer.parseInt(jTextField3.getText());
        numberRoots = Integer.parseInt(jTextField4.getText());
        maxChildren = Integer.parseInt(jTextField5.getText());
        maxBranches = Integer.parseInt(jTextField6.getText());
        averageLength = Integer.parseInt(jTextField7.getText());
        lineWidth = (Integer)jSpinner1.getValue();
        delay = Integer.parseInt(jTextField8.getText());
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jTextField7.setEnabled(false);
        jTextField8.setEnabled(false);
        jSpinner1.setEnabled(false);
        startB.setEnabled(false);
        CHCheckBox.setEnabled(false);
        ECCheckBox.setEnabled(false);
        display.setParameters(punctaSize, removeRadius, lineWidth);
        thread = new Thread(this);
        thread.start();
}//GEN-LAST:event_startBActionPerformed

    private void resetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBActionPerformed
        if (thread != null) thread.interrupt();
        getContentPane().removeAll();
        initComponents();
        jTextField1.setText("" + puncta);
        jTextField2.setText("" + punctaSize);
        jTextField3.setText("" + removeRadius);
        jTextField4.setText("" + numberRoots);
        jTextField5.setText("" + maxChildren);
        jTextField6.setText("" + maxBranches);
        jTextField7.setText("" + averageLength);
        jTextField8.setText("" + delay);
        jLabel9.setText("0/" + maxBranches);
        jSpinner1.setModel(new SpinnerNumberModel(lineWidth,1,100,1));
        JSpinner.DefaultEditor ed = ((JSpinner.DefaultEditor)jSpinner1.getEditor());
        ed.getTextField().setHorizontalAlignment(JTextField.LEFT);
        CHCheckBox.setSelected(drawCH);
        ECCheckBox.setSelected(drawEC);
        rand = new Random();
        suspended = false;
}//GEN-LAST:event_resetBActionPerformed

    private void pauseBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseBActionPerformed
        pause();
    }//GEN-LAST:event_pauseBActionPerformed

    private void CHCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHCheckBoxActionPerformed
        drawCH = CHCheckBox.isSelected();
    }//GEN-LAST:event_CHCheckBoxActionPerformed

    private void ECCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ECCheckBoxActionPerformed
        drawEC = ECCheckBox.isSelected();
    }//GEN-LAST:event_ECCheckBoxActionPerformed

    private synchronized void pause() {
        suspended = !suspended;
        jTextField8.setEnabled(suspended);
        if (! suspended)
        {
            delay = Integer.parseInt(jTextField8.getText());
            notify();
        }
    }

    private double sum(ArrayList<Branch> bran) {
        double S = 0;
        for (Branch b : bran)
            S += b.length();
        return S;
    }
    /**
     * build the basics for every run (incl. puncta, roots)
     * @param addPos a reference for the arbor ArrayList to which the roots are added
     */
    private void build(ArrayList<Arbor> addPos) {
        punctaPos = new Point[puncta];
        for (int i = 0; i < puncta; i++)
            punctaPos[i] = new Point(punctaSize + rand.nextInt(display.getWidth() - 2*punctaSize),
                    punctaSize + rand.nextInt(display.getHeight() - 2*punctaSize));

        branchPos = new ArrayList<Branch>(maxBranches);
        occupiedPunctaPos = new ArrayList<Point>();
        availablePunctaPos = new ArrayList<Point>(puncta);
        for (int i = 0; i < puncta; i++)
          availablePunctaPos.add(punctaPos[i]);

        rootPos = new Root[numberRoots];
        for (int i = 0; i < numberRoots; i++)
        {
            int x, y;
            if (numberRoots == 1)
            {
                x = display.getWidth() / 2;
                y = display.getHeight()/ 2;
            }
            else
            {
                x = rand.nextInt(display.getWidth());
                y = display.getHeight();//rand.nextInt(display.getHeight());
            }
            rootPos[i] = new Root(x, y, punctaSize, colors[i%colors.length]);
            addPos.add(rootPos[i]);
        }
        display.drawBase(punctaSize, punctaPos, availablePunctaPos, rootPos);
    }
    /**
     *
     * @param arb The base arbor for the trial branch
     * @param x2 The new end x for the trial branch
     * @param y2 The new end y for the trial branch
     * @param addPos The reference to the arbor ArrayList to which new arbors are added
     * @return YES if the branch is valid, NO if the branch isn't, and INTERRUPTED if the drawing process is interrupted
     */
    private int branchTrial(Arbor arb, int x2, int y2, ArrayList<Arbor> addPos) {
        int x1 = arb.x, y1 = arb.y;
        boolean found = false; // whether the branch is valid
        ArrayList<Integer> punctaToRemove = new ArrayList<Integer>();
        ArrayList<Point> newOccupied = new ArrayList<Point>();
        for (Point avail : availablePunctaPos)
        {
            int px = avail.x;
            int py = avail.y;
            double m = ((double)y2 - y1)/(x2 - x1);
            double b = (double)y1 - m * x1;
            double r = Math.abs(m * px - py + b) / Math.sqrt(m * m + 1);
            if (r < punctaSize / 2)
            {
                double left = (px - x1) * (px - x1) + (py - y1) * (py - y1);
                double right = (px - x2) * (px - x2) + (py - y2) * (py - y2);
                double lengthSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (left + lengthSq > right && right + lengthSq > left)
                {
                    found = true;
                    newOccupied.add(new Point(px, py));
                    int j = 0;
                    for (Point avail2 : availablePunctaPos)
                    {
                        double length = Math.hypot(avail2.x - px, avail2.y - py);
                        double z = length/(removeRadius); // the remove radius is the StDev
                        if (z < 2 && z <= Math.abs(rand.nextGaussian())) // truncating at 2 StDev
                            punctaToRemove.add(j);
                        j++;
                    }
                }
            }
        }

        Branch revert = previousBranch; // in case the branch isn't valid
        previousBranch = new Branch(x1, y1, x2, y2, arb);
        branchPos.add(previousBranch);
        display.update(occupiedPunctaPos, availablePunctaPos, branchPos);
        if (delay > 0)
            try {Thread.sleep(1);}
            catch (InterruptedException e){return INTERRUPTED;}
        if (! found)
        {
            if (! branchPos.remove(previousBranch))
                throw new RuntimeException("Failure to remove last branch");
            previousBranch = revert;
            return NO;
        }

        occupiedPunctaPos.addAll(newOccupied);
        addPos.addAll(previousBranch.adjacentArbors(newOccupied));

        java.util.Collections.sort(punctaToRemove);
        int oldVal = -1;
        for (int i = punctaToRemove.size() - 1; i >= 0; i--)
            if (oldVal != punctaToRemove.get(i))
            {
                availablePunctaPos.remove((int)punctaToRemove.get(i));
                oldVal = punctaToRemove.get(i);
            }
        return YES;
    }

    public void run() {
        long mill = System.currentTimeMillis();
        ArrayList<Arbor> addPos = new ArrayList<Arbor>();
        build(addPos);

        int index, endX, endY;
        double radius;
        double angle = 0;
        boolean moreSpace = true;
        boolean previousAngleWorks = false;
        Arbor arb;

        loop:
        while(moreSpace)
        {
            while (true) {
                try {
                Thread.sleep(delay);
                if (suspended)
                    synchronized (this) {
                        while (suspended) wait();
                    }
                } catch (InterruptedException exp) {
                    break loop;
                }
                if (! previousAngleWorks) // then try growing branch in new direction
                {
                    // trim only the last branch of a series of growth in the same direction
                    if (previousBranch != null)
                        previousBranch.trim();
                    previousBranch = null;
                    Arbor arb2, root2;
                    do {
                        index = rand.nextInt(addPos.size());
                        arb2 = addPos.get(index);
                        root2 = addPos.get(0);
                    } while (exp(-sqrt((arb2.x-root2.x)^2 + (arb2.y-root2.y)^2)/averageLength) < rand.nextDouble());
                    angle = 2D * Math.PI * rand.nextDouble();
                }
                else // use the end arbor from the last branch to grow in the same direction
                {
                    index = addPos.size() - 1;
                }
                arb = addPos.get(index);
                //radius = averageLength;
                do{
                    radius = averageLength*Math.abs(rand.nextGaussian());//averageLength + averageLength / 3D * rand.nextGaussian();
                } while(radius > 2.*averageLength);
                endX = arb.x + (int)(radius * Math.cos(angle) + .5);
                endY = arb.y + (int)(radius * Math.sin(angle) + .5);
                int trialResult = branchTrial(arb, endX, endY, addPos);
                if (trialResult == INTERRUPTED)
                    break loop;
                else if (trialResult == YES)
                {
                    previousAngleWorks = true;
                    break;
                }
                previousAngleWorks = false;
            }

            if (drawCH || drawEC)
            {
                ArrayList<int[]> hull = Enclosure.getConvexHull(display, branchPos);
                if (drawCH) Enclosure.convexHull(display, hull, true);
                if (drawEC) Enclosure.enclosingCircle(display, hull, true);
            }

            display.update(occupiedPunctaPos, availablePunctaPos, branchPos);

            jLabel9.setText(branchPos.size()+"/"+maxBranches);

            addPos.get(index).children++;
            if (addPos.get(index).children >= maxChildren)
                addPos.remove(index);

            if (addPos.isEmpty() || branchPos.size() >= maxBranches ||
                    availablePunctaPos.isEmpty())
                moreSpace = false;
        }

        double lengths = averageLength * branchPos.size();
        double trueLengths = sum(branchPos);
        ArrayList<int[]> hull = Enclosure.getConvexHull(display, branchPos);
        double area = Enclosure.convexHull(display, hull, drawCH);
        System.out.println("#AddPts: " + addPos.size() +
                " #Branches: " + branchPos.size() +
                " #Puncta: " + availablePunctaPos.size() +
                " #Occupied: " + occupiedPunctaPos.size());
        System.out.println("CH     " + Math.log(lengths)/Math.log(area));
        System.out.println("TrueCH " + Math.log(trueLengths)/Math.log(area) + " " + lengths/trueLengths);
        System.out.println("EC     " + Math.log(lengths)/Math.log(Enclosure.enclosingCircle(display,hull,drawEC)));
        System.out.println("RT     " + Math.log(lengths)/Math.log(Enclosure.rectangle(display,branchPos)));
        System.out.println("Run time: " + (System.currentTimeMillis() - mill));
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CHCheckBox;
    private javax.swing.JCheckBox ECCheckBox;
    private neuronbuilder.PunctaDisplay display;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JToggleButton pauseB;
    private javax.swing.JButton resetB;
    private javax.swing.JButton startB;
    // End of variables declaration//GEN-END:variables
}
