
package pos.Model;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class FileImageModel {

    public Icon getIcon() {
        return icon;
    }

   
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

   
    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public FileImageModel(File path) {
        this.path = path;
    }

    public FileImageModel(byte [] bytes) {
        if (bytes!=null) {
            icon = new ImageIcon(bytes);
            
        }
    }

    public FileImageModel(Icon icon) {
        this.icon = icon;
    }

    public FileImageModel() {
    }
 
    
    private Icon icon;
    private File path;
}
