/* CommandManager.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

import java.util.ArrayList;

public class CommandManager
{
    /* Cursor movement */
    public static final String CMD_HOME = "^h"; /* move the cursor to row 0, column 0; the image on the screen is not changed */
    public static final String CMD_BEGIN = "^b"; /* move the cursor to the beginning of the current line; the cursor row does not change */
    public static final String CMD_DOWN = "^d"; /* move the cursor down one row if possible; the cursor column does not change */
    public static final String CMD_UP = "^u"; /* 	move the cursor up one row, if possible; the cursor column does not change */
    public static final String CMD_LEFT = "^l"; /* move the cursor left one column, if possible; the cursor row does not change */
    public static final String CMD_RIGHT = "^r"; /* move the cursor right one column, if possible; the cursor row does not change */
    public static final String CMD_JUMP_TO = "^DD"; /* move the cursor to the row and column specified; each D represents a decimal digit; the first D represents the new row number, and the second D represents the new column number */

    /* Output manipulation */
    public static final String CMD_CLEAR = "^c"; /* clear the entire screen; the cursor row and column do not change */
    public static final String CMD_DROP = "^e"; /* erase characters to the right of, and including, the cursor column on the cursor's row; the cursor row and column do not change */
    public static final String CMD_INSERT_MODE = "^i"; /* enter insert mode */
    public static final String CMD_OVERWITE_MODE = "^o"; /* enter overwrite mode */
    public static final String CMD_WRITE_CIRCUMFLEX = "^^"; /* write a circumflex (^) at the current cursor location, exactly as if it was not a special character; this is subject to the actions of the current mode (insert or overwrite) */

    public static ArrayList<String> listOfCommands;

    public CommandManager()
    {
        listOfCommands = new ArrayList<>();
        listOfCommands.add(CMD_CLEAR);
        listOfCommands.add(CMD_HOME);
        listOfCommands.add(CMD_BEGIN);
        listOfCommands.add(CMD_DOWN);
        listOfCommands.add(CMD_UP);
        listOfCommands.add(CMD_LEFT);
        listOfCommands.add(CMD_RIGHT);
        listOfCommands.add(CMD_DROP);
        listOfCommands.add(CMD_INSERT_MODE);
        listOfCommands.add(CMD_OVERWITE_MODE);
        listOfCommands.add(CMD_WRITE_CIRCUMFLEX);
        listOfCommands.add(CMD_JUMP_TO);
    }

    public int getNumberOfCommands()
    {
        return listOfCommands.size();
    }

    public ArrayList<String> getCommands()
    {
        ArrayList<String> list = listOfCommands;
        return list;
    }
}
