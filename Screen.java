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

    public static void overwrite(String s)
    {
        int row = Cursor.getRow();
        int col = Cursor.getCol();

        _text[row][col] = s;

        update(_textWithCursor);
    }

    public static void eraseToEnd()
    {
        int row = Cursor.getRow();
        int col = Cursor.getCol();

        while (col < MAX_COLS)
            _text[row][col++] = BLANK;

        update(_textWithCursor);
    }

    public static void clear()
    {
        int row;
        int col;

        for (row = 0; row < MAX_ROWS; row++)
        {
            for (col = 0; col < MAX_COLS; col++)
            {
                _text[row][col] = BLANK;
            }
        }
        updateCursorPos();
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

    public static void updateCursorPos()
    {
        if (_text == null || _textWithCursor == null)
            init();

        if (!Main.copyStringArray2D(_text, _textWithCursor))
            return;

        int row = Cursor.getRow();
        int col = Cursor.getCol();

        _textWithCursor[row][col] = BLOCK;

        update(_textWithCursor);
    }

    public static void update(String[][] text)
    {
        StringBuilder sb = new StringBuilder();
        int row;
        int col;

        sb.append("");

        for (row = 0; row < MAX_ROWS; row++)
        {
            for (col = 0; col < MAX_COLS; col++)
            {
                sb.append(text[row][col]);
            }
            sb.append(NEWLINE);
        }

        Main.terminal.setText(sb.toString());
    }
}
