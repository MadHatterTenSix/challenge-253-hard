/* KeyHandler.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    /* Variables to track cursor jump. */
    private int jumpNumber1 = -1; /* row */
    private int jumpNumber2 = -1; /* column */

    public KeyHandler()
    {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        /* Clear screen */
        if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_C)
        {
            Screen.clear();
        }
        /* Cursor up */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_U)
        {
            Cursor.up();
        }
        /* Cursor down */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_D)
        {
            Cursor.down();
        }
        /* Cursor left */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_L)
        {
            Cursor.left();
        }
        /* Cursor right */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_R)
        {
            Cursor.right();
        }
        /* Erase chars to the right including current position. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_E)
        {
            Screen.eraseRight();
        }
        /* Cursor home */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_H)
        {
            Cursor.home();
        }
        /* Cursor beginning */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_B)
        {
            Cursor.begin();
        }
        /* Insert circumflex. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_6)
        {
            Screen.write("^");
        }
        /* Insert '<' */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_COMMA)
        {
            Screen.write("<");
        }
        /* Insert '>' */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_PERIOD)
        {
            Screen.write(">");
        }
        /* Change write mode for inserting. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_I)
        {
            Terminal.setWriteMode(Terminal.WriteMode.INSERT);
        }
        /* Change write mode for overwriting. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_O)
        {
            Terminal.setWriteMode(Terminal.WriteMode.OVERWRITE);
        }
        /* Cursor jump */
        else if (isAltPressed(e) && isNumberPressed(e))
        {
            if (jumpNumber1 < 0)
            {
                jumpNumber1 = e.getKeyCode();
            }
            else if (jumpNumber2 < 0)
            {
                jumpNumber2 = e.getKeyCode();

                Cursor.set(keyCodeToNumber(jumpNumber1), keyCodeToNumber(jumpNumber2));

                jumpNumber1 = -1;
                jumpNumber2 = -1;
            }
        }
        /* User pastes input. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_V)
        {
            try
            {
                String s = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                Terminal.processPastedInput(s);
            } catch (Exception ex)
            {
                /* Do nothing. */
            }
        }
        /* Something other key is pressed. */
        else if (!isCtrlPressed(e) && !isAltPressed(e))
        {
            Screen.write(keyToString(e));
        }

        Screen.updateDisplay();
    }

    private boolean isCtrlPressed(KeyEvent e)
    {
        return ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0);
    }

    private boolean isAltPressed(KeyEvent e)
    {
        return ((e.getModifiers() & KeyEvent.ALT_MASK) != 0);
    }

    private boolean isNumberPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch (keyCode)
        {
            case KeyEvent.VK_1: return true;
            case KeyEvent.VK_2: return true;
            case KeyEvent.VK_3: return true;
            case KeyEvent.VK_4: return true;
            case KeyEvent.VK_5: return true;
            case KeyEvent.VK_6: return true;
            case KeyEvent.VK_7: return true;
            case KeyEvent.VK_8: return true;
            case KeyEvent.VK_9: return true;
            case KeyEvent.VK_0: return true;
        }
        return false;
    }

    /**
     * Convert specified KeyEvent into a string from 'KeyEvent.getKeyCode()'.
     */
    private String keyToString(KeyEvent e)
    {
        String s = "" + (char)e.getKeyCode();
        return s;
    }

    private int keyCodeToNumber(int keyCode)
    {
        switch (keyCode)
        {
            case KeyEvent.VK_0: return 0;
            case KeyEvent.VK_1: return 1;
            case KeyEvent.VK_2: return 2;
            case KeyEvent.VK_3: return 3;
            case KeyEvent.VK_4: return 4;
            case KeyEvent.VK_5: return 5;
            case KeyEvent.VK_6: return 6;
            case KeyEvent.VK_7: return 7;
            case KeyEvent.VK_8: return 8;
            case KeyEvent.VK_9: return 9;
        }
        return -1;
    }

    //<editor-fold defaultstate="collapsed" desc=" Unused abstract methods ... ">
    //////////////////////////////////////////////////////////////////////
    /* keyPressed() is most likely the only abstract method to use right now.
    /* All other methods can be placed here to be hidden. */
    /* These functions are included because the compiler complains about them not being overidden. */
    //////////////////////////////////////////////////////////////////////

    @Override
    public void keyReleased(KeyEvent e)
    {}

    @Override
    public void keyTyped(KeyEvent e)
    {}

    //////////////////////////////////////////////////////////////////////

    //</editor-fold>
}
