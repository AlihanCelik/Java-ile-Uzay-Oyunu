
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

class Ateş {
    private int x;
    private int y;

    public Ateş(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     * @return int return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return int return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}

public class Oyun extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(4, this);
    private int geçen_süre = 0;
    private int harcanan_ateş = 0;
    private BufferedImage image;
    private ArrayList<Ateş> ateşler = new ArrayList<Ateş>();
    private int ateştirY = 3;
    private int topX = 0;
    private int topdirX = 4;
    private int UzayGemisiX = 0;
    private int dirUzayX = 20;

    public boolean kontrolEt() {
        for (Ateş ateş : ateşler) {

            if (new Rectangle(ateş.getX(), ateş.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20))) {
                return true;
            }
        }
        return false;

    }

    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setBackground(Color.BLACK);
        timer.start();

    }

    @Override

    public void paint(Graphics g) {
        super.paint(g);
        geçen_süre += 5;
        g.setColor(Color.blue);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image, UzayGemisiX, 490, image.getWidth() / 10, image.getHeight() / 10, this);

        for (Ateş ateş : ateşler) {
            if (ateş.getY() < 0) {
                ateşler.remove(ateş);
            }

        }
        g.setColor(Color.red);
        for (Ateş ateş : ateşler) {
            g.fillRect(ateş.getX(), ateş.getY(), 10, 20);
        }

        if (kontrolEt()) {
            timer.stop();
            String mesaj = "Kazandınız \n " + "Harcanan ateş : " + harcanan_ateş + "\n" + "Geçen süre : "
                    + geçen_süre / 1000.0 + "\n";
            JOptionPane.showMessageDialog(this, mesaj);
            System.exit(0);
        }
    }

    public void repaint() {
        super.repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (UzayGemisiX <= 0) {
                UzayGemisiX = 0;
            } else {
                UzayGemisiX -= dirUzayX;
            }

        } else if (c == KeyEvent.VK_RIGHT) {
            if (UzayGemisiX >= 750) {
                UzayGemisiX = 750;

            } else {
                UzayGemisiX += dirUzayX;
            }
        } else if (c == KeyEvent.VK_CONTROL) {
            ateşler.add(new Ateş(UzayGemisiX + 15, 490));
            harcanan_ateş++;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        for (Ateş ateş : ateşler) {
            ateş.setY(ateş.getY() - ateştirY);
        }
        topX += topdirX;
        if (topX >= 750) {
            topdirX = -topdirX;
        }
        if (topX <= 0) {
            topdirX = -topdirX;
        }
        repaint();
    }

}
