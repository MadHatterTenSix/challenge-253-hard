/* Terminal.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

public class Terminal
{
    public static enum WriteMode
    {
        INSERT,
        OVERWRITE
    }

    private static WriteMode _writeMode;
    private static Cursor _cursor;
    private static Screen _screen;

    public Terminal()
    {
        _screen = new Screen();
        _writeMode = WriteMode.OVERWRITE;

        execute();
    }

    private static void execute()
    {
//        processPastedInput("^h^c^iDDD^r^rPPPP^d^bD^r^rD^rP^19P^d^bD^r^rD^rPPPP^d^bD^r^rD^rP^d^bDDD^r^rP");
//        processPastedInput("^h^c^i^04^^^13/ \^d^b  /   \^u^d^d^l^l^l^l^l^l^l^l^l^r^r^l^l^d<^48>^l^l^d/^b^o \^d^r^r^66/^b  \^b^d   \ /^d^l^lv^d^b===========^i^94O123456789^94A=======^u^u^u^u^u^u^l^l\^o^b^r/");
//        processPastedInput("^h^c^i^04^^^13/ \\^d^b  /   \\^u^d^d^l^l^l^l^l^l^l^l^l^r^r^l^l^d<^48>^l^l^d/^b^o \\^d^r^r^66/^b  \\^b^d   \\ /^d^l^lv^d^b===========^i^94O123456789^94A=======^u^u^u^u^u^u^l^l\\^o^b^r/");
    }

    public static WriteMode getWriteMode()
    {
        return _writeMode;
    }

    public static void setWriteMode(WriteMode mode)
    {
        _writeMode = mode;
    }

    public static void setText(String s)
    {
        Main.textArea.setText(s);
    }

    /**
     * Paste original syntax input into program and translate it.
     *
     * Originally, {'^h', '^c', '^^', ...} was the syntax.
     * Now, it is converted to {'CTRL+H', 'CTRL+C', 'CTRL+6', ...} because
     * of the keyboard implementation. This function will allow the user
     * to paste a series of input for the program to process.
     */
    public static void processPastedInput(String s)
    {
        int i = 0;
        int len = s.length();
        char c;

        while (i < len)
        {
            c = s.charAt(i);
            if (c == '^')
            {
                i++;
                c = s.charAt(i);
                if (c == 'c')
                    Screen.clear();
                else if (c == 'h')
                    Cursor.home();
                else if (c == 'b')
                    Cursor.begin();
                else if (c == 'd')
                    Cursor.down();
                else if (c == 'u')
                    Cursor.up();
                else if (c == 'l')
                    Cursor.left();
                else if (c == 'r')
                    Cursor.right();
                else if (c == 'e')
                    Screen.eraseRight();
                else if (c == 'i')
                    Terminal.setWriteMode(Terminal.WriteMode.INSERT);
                else if (c == 'o')
                    Terminal.setWriteMode(Terminal.WriteMode.OVERWRITE);
                else if (c == '^')
                    Screen.write('^');
                else if (c >= '0' && c <= '9')
                {
                    int row = charToInt(c);
                    i++;
                    c = s.charAt(i);
                    int col = charToInt(c);
                    Cursor.set(row, col);
                }
                else
                    Screen.write(c);
            }
            else
                Screen.write(c);

            i++;
        }
        Screen.updateDisplay();
    }

    public static int charToInt(char c)
    {
        return (int)(c - '0');
    }
}
