
package workwithtrees;

/**
 * The abstract class of the menu entry.
 * @author DantalioNxxi
 * @version 1.3
 * @since 1.0
 * @see MenuMain
 * @see BSTreeMenu
 * @see AVLTreeMenu
 */
abstract class MenuEntry {
    /**The name is identifies the menu entry into the code.*/
    private final String title;

    /**
     * Constructor of the Menu Entry.
     * @param title identifies the menu entry into the code
     */
    public MenuEntry(String title) {
        this.title = title;
    }

    /**
     * Is launches the menu entry.
     */
    public abstract void run();
}
