/* Main.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 250;
    public static final int FONT_SIZE = 14;
    public static final Color FOREGROUND_COLOR = Color.green;
    public static final Color BACKGROUND_COLOR = Color.black;
    public static final String[] PROGRAM_FONTS = {
        "Consolas",
        "DejaVu Sans Mono",
        "Courier New",
        "Courier"
    };
    public static final String APP_NAME = "__MadHatter's Terminal";
    public static final String APP_VER = "0.2";
    public static final String APP_DATE = "2016/02/15";
    public static final String APP_TITLE = APP_NAME + " v" + APP_VER;

    private JScrollPane scrollPane;
    public static JTextArea textArea;
//    public static JTextField textField;

    public static Terminal terminal;

    public Main()
    {
        initComponents();

        terminal = new Terminal();
    }

    public static boolean copyStringArray2D(String[][] src, String[][] dest)
    {
        if (src == null || dest == null)
            return false;

        int row;
        int col;
        int rowLen = src[0].length;
        int colLen = src[1].length;

        for (row = 0; row < rowLen; row++)
        {
            for (col = 0; col < colLen; col++)
            {
                dest[row][col] = src[row][col];
            }
        }

        return true;
    }

    public static boolean isFontInstalled(String fontName)
    {
        GraphicsEnvironment g;
        String[] fonts;

        g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = g.getAvailableFontFamilyNames();

        for (String font : fonts)
            if (font.equalsIgnoreCase(fontName))
                return true;
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc=" public static void main(String[] args) ... ">
    public static void main(String[] args)
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        catch (Exception e)
        {
            /* Do nothing. */
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Main().setVisible(true);
            }
        });
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" private void initComponents() ... ">
    private void initComponents()
    {
        setTitle(APP_TITLE);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        textArea = new JTextArea();
        textArea.setText(Screen.BLOCK);
        textArea.setEditable(false);
        textArea.setBackground(BACKGROUND_COLOR);
        textArea.setForeground(FOREGROUND_COLOR);
        textArea.addKeyListener(new KeyHandler());

        for (String font : PROGRAM_FONTS)
        {
            if (isFontInstalled(font))
            {
                textArea.setFont(new Font(font, Font.BOLD, FONT_SIZE));
                break;
            }
        }

        scrollPane.setViewportView(textArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(scrollPane))
            .addGroup(layout.createSequentialGroup())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(scrollPane))
        );

        pack();
        setLocationRelativeTo(null);
    }
    //</editor-fold>
}
