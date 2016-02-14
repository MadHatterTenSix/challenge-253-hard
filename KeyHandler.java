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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private int number1 = -1;
    private int number2 = -1;

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
            Screen.updateCursorPos();
        }
        /* Cursor down */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_D)
        {
            Cursor.down();
            Screen.updateCursorPos();
        }
        /* Cursor left */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_L)
        {
            Cursor.left();
            Screen.updateCursorPos();
        }
        /* Cursor right */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_R)
        {
            Cursor.right();
            Screen.updateCursorPos();
        }
        /* Insert circumflex. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_6)
        {
            Screen.overwrite("^");
            Cursor.right();
            Screen.updateCursorPos();
        }
        /* Insert '<' */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_COMMA)
        {
            Screen.overwrite("<");
            Cursor.right();
            Screen.updateCursorPos();
        }
        /* Insert '>' */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_PERIOD)
        {
            Screen.overwrite(">");
            Cursor.right();
            Screen.updateCursorPos();
        }
        /* Erase chars to the right including current position. */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_E)
        {
            Screen.eraseToEnd();
            Screen.updateCursorPos();
        }
        /* Cursor home */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_H)
        {
            Cursor.home();
            Screen.updateCursorPos();
        }
        /* Cursor beginning */
        else if (isCtrlPressed(e) && e.getKeyCode() == KeyEvent.VK_B)
        {
            Cursor.begin();
            Screen.updateCursorPos();
        }
        /* Regular number pressed. */
        else if (isAltPressed(e) && isNumberPressed(e))
        {
            char c = (char)e.getKeyCode();
            String s = "" + c;
            Screen.overwrite(s);
            Cursor.right();
            Screen.updateCursorPos();
        }
        /* Cursor jump */
        else if (isNumberPressed(e))
        {
            if (number1 < 0)
            {
                number1 = e.getKeyCode();
            }
            else if (number2 < 0)
            {
                number2 = e.getKeyCode();

                Cursor.set(keyCodeToNumber(number1), keyCodeToNumber(number2));

                number1 = -1;
                number2 = -1;
            }
            Screen.updateCursorPos();
        }
        /* Something else is pressed. */
        else if (!isCtrlPressed(e) && !isAltPressed(e))
        {
            char c = (char)e.getKeyCode();
            String s = "" + c;
            Screen.overwrite(s);
            Cursor.right();
            Screen.updateCursorPos();
        }
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

    private boolean isCtrlPressed(KeyEvent e)
    {
        return ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0);
    }

    private boolean isAltPressed(KeyEvent e)
    {
        return ((e.getModifiers() & KeyEvent.ALT_MASK) != 0);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {}

    @Override
    public void keyTyped(KeyEvent e)
    {}
}
