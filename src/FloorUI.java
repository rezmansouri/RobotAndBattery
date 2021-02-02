
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.JSONArray;

public class FloorUI extends javax.swing.JFrame {

    UITile start;
    UITile goal;
    static UITile[][] tiles;
    BufferedImage robotImage = null;
    static ImageIcon robotIcon = null;
    BufferedImage batteryImage = null;
    static ImageIcon batteryIcon = null;

    public FloorUI() {
        initComponents();
        try {
            robotImage = ImageIO.read(getClass().getResource("robot.png"));
            robotIcon = new ImageIcon(robotImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
            batteryImage = ImageIO.read(getClass().getResource("battery.png"));
            batteryIcon = new ImageIcon(batteryImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            Logger.getLogger(FloorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        tiles = new UITile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new UITile(i, j, this);
                jPanel1.add(tiles[i][j].contentLabel);
                jPanel1.add(tiles[i][j].upWallLabel);
                jPanel1.add(tiles[i][j].rightWallLabel);
                jPanel1.add(tiles[i][j].leftWallLabel);
                jPanel1.add(tiles[i][j].downWallLabel);
                tiles[i][j].contentLabel.setBounds(10 + j * 70, 10 + i * 70, 60, 60);
                tiles[i][j].upWallLabel.setBounds(10 + j * 70, i * 70, 60, 10);
                tiles[i][j].leftWallLabel.setBounds(j * 70, 10 + i * 70, 10, 60);
                tiles[i][j].rightWallLabel.setBounds(70 + j * 70, 10 + i * 70, 10, 60);
                tiles[i][j].downWallLabel.setBounds(10 + j * 70, 70 + i * 70, 60, 10);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j].up = i == 0 ? null : tiles[i - 1][j];
                tiles[i][j].left = j == 0 ? null : tiles[i][j - 1];
                tiles[i][j].right = j == 9 ? null : tiles[i][j + 1];
                tiles[i][j].down = i == 9 ? null : tiles[i + 1][j];
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze with A Star");
        setBackground(new java.awt.Color(153, 255, 153));
        setForeground(java.awt.Color.lightGray);
        setMinimumSize(new java.awt.Dimension(900, 900));
        setPreferredSize(new java.awt.Dimension(900, 900));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 900));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 204, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(710, 710));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(90, 60, 710, 710);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        searchButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        searchButton.setText("Search");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel2.add(searchButton);
        searchButton.setBounds(390, 810, 120, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 900, 900);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j].contentLabel.setBackground(Color.black);
            }
        }
        JSONArray walls = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONArray walls_i = new JSONArray();
            for (int j = 0; j < 10; j++) {
                walls_i.put(tiles[i][j].walls());
            }
            walls.put(walls_i);
        }
        if (goal == null || start == null) {
            System.out.println("Set Goal and Start First");
            return;
        }
        try {
            Run.run(walls, start.x, start.y, goal.x, goal.y);
        } catch (IOException ex) {
            Logger.getLogger(FloorUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Floor.FloorException ex) {
            Logger.getLogger(FloorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FloorUI().setVisible(true);
            }
        });
    }

    public static void printPath(ArrayList<Tile> path) {
        for (int i = 1; i < path.size(); i++) {
            tiles[path.get(i).x][path.get(i).y].contentLabel.setBackground(Color.GREEN);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables

}
