/* Main.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 * Version 0.1
 * 2016-02-13
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
    public static final int WIDTH = 300;
    public static final int HEIGHT = 250;
    public static final int FONT_SIZE = 14;

    private JScrollPane scrollPane;
    public static JTextArea textArea;

    public static Terminal terminal;

    public Main()
    {
        initComponents();

        terminal = new Terminal();
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
        catch (Exception e)
        {
            /* Do nothing. */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main().setVisible(true);
            }
        });
    }
    //</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc=" public static boolean isFontInstalled(String fontName) ... ">
    public static boolean isFontInstalled(String fontName)
    {
        GraphicsEnvironment g;
        String[] fonts;
        int len;

        g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = g.getAvailableFontFamilyNames();
        len = fonts.length;

        for (String font : fonts)
            if (font.equalsIgnoreCase(fontName))
                return true;
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" private void initComponents() ... ">
    private void initComponents()
    {
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        textArea = new JTextArea();
        textArea.setEditable(false);
//        textArea.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.green);
        textArea.addKeyListener(new KeyHandler());

        if (isFontInstalled("Consolas"))
            textArea.setFont(new Font("Consolas", Font.BOLD, FONT_SIZE));
        else if (isFontInstalled("DejaVu Sans Mono"))
            textArea.setFont(new Font("DejaVu Sans Mono", Font.BOLD, FONT_SIZE));
        else if (isFontInstalled("Courier New"))
            textArea.setFont(new Font("Courier New", Font.BOLD, FONT_SIZE));
        else if (isFontInstalled("Courier"))
            textArea.setFont(new Font("Courier", Font.BOLD, FONT_SIZE));

        scrollPane.setViewportView(textArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                layout.createSequentialGroup()
//                .addGap(73, 73, 73)
//                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(scrollPane)
//                .addContainerGap(89, Short.MAX_VALUE)
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                layout.createSequentialGroup()
//                .addGap(75, 75, 75)
//                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(scrollPane)
//                .addContainerGap(127, Short.MAX_VALUE)
            )
        );

        pack();

        setLocationRelativeTo(null);

        while (textArea == null) {}
    }
    //</editor-fold>
}
