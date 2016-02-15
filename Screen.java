/* Screen.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

import terminal.Cursor.CursorPosition;

public class Screen
{
    public static final int MAX_ROWS = 10;
    public static final int MAX_COLS = 10;
    public static final String BLOCK = "\u2588";
    public static final String BLANK = " ";
    public static final String NEWLINE = System.lineSeparator();

    private static String[][] _text;
    private static String[][] _textWithCursor;

    public Screen()
    {
        init();
    }

    private static void init()
    {
        if (_text == null || _textWithCursor == null)
        {
            int row;
            int col;
            _text = new String[MAX_ROWS][MAX_COLS];
            _textWithCursor = new String[MAX_ROWS][MAX_COLS];
            for (row = 0; row < MAX_COLS; row++)
            {
                for (col = 0; col < MAX_ROWS; col++)
                {
                    _text[row][col] = BLANK;
                    _textWithCursor[row][col] = BLANK;
                }
            }
        }
    }

    /**
     * Public function that attempts to update display.
     */
    public static void updateDisplay()
    {
        if (_text == null || _textWithCursor == null)
            init();

        if (!Main.copyStringArray2D(_text, _textWithCursor))
            return;

        int row = Cursor.getRow();
        int col = Cursor.getCol();
        _textWithCursor[row][col] = BLOCK;
        updateDisplay(_textWithCursor);
    }

    /**
     * Private function that sets the final text for the display.
     */
    private static void updateDisplay(String[][] displayArray)
    {
        if (displayArray == null)
            return;

        StringBuilder sb = new StringBuilder();
        int row;
        int col;

        sb.append("");
        for (row = 0; row < MAX_ROWS; row++)
        {
            for (col = 0; col < MAX_COLS; col++)
                sb.append(displayArray[row][col]);
            sb.append(NEWLINE);
        }

        Main.terminal.setText(sb.toString());
    }

    /**
     * Write to terminal screen.
     */
    public static void write(String s)
    {
        if (Terminal.getWriteMode() == Terminal.WriteMode.INSERT)
            insert(s);
        else if (Terminal.getWriteMode() == Terminal.WriteMode.OVERWRITE)
            overwrite(s);

        Cursor.right();
        updateDisplay();
    }

    public static void write(char c)
    {
        String s = "" + c;
        write(s);
    }

    /**
     * Overwrite text at current cursor position.
     */
    private static void overwrite(String s)
    {
        CursorPosition cp = Cursor.getPosition();
        _text[cp.row][cp.col] = s;
    }

    /**
     * Insert text at current cursor position.
     */
    private static void insert(String s)
    {
        int row = Cursor.getRow();
        int col = Cursor.getCol();
        int tmpCol;

        /* Shift contents to the right of cursor position to make room.*/
        tmpCol = MAX_COLS - 1;
        while (tmpCol > col)
        {
            _text[row][tmpCol] = _text[row][tmpCol-1];
            tmpCol--;
        }

        /* Insert. */
        _text[row][col] = s;
    }

    public static void eraseRight()
    {
        int row = Cursor.getRow();
        int col = Cursor.getCol();

        while (col < MAX_COLS)
        {
            _text[row][col++] = BLANK;
        }
    }

    public static void clear()
    {
        int row;
        int col;

        for (row = 0; row < MAX_ROWS; row++)
            for (col = 0; col < MAX_COLS; col++)
                _text[row][col] = BLANK;
    }
}
