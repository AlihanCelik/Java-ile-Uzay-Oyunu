import javax.swing.JFrame;

public class oyunekranı extends JFrame {
    public oyunekranı(String title) {
        super(title);
    }

    public static void main(String[] args) {
        oyunekranı ekran = new oyunekranı("Uzay Oyunu");
        ekran.setResizable(false);
        ekran.setFocusable(false);

        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oyun oyun = new Oyun();
        oyun.requestFocus(); // klavyeden işlmeleri algılmasını sağlar // yerler önemli kodların
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setVisible(true);
    }
}