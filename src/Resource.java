/**
 * This class creates a resource object.
 *
 * @author Luke Burtonwood
 * @version v1.3.0
 * - no copyright
 */

import java.sql.SQLException;


public class Resource {
    private String uniqueId;
    private String title;
    private int year;
    private String thumbnailPath;
    private int type;
    private int numCopies;


    public Resource(String title, int year, String thumbnailPath, int numCopies) throws SQLException {
        this.title = title;
        this.year = year;
        this.thumbnailPath = thumbnailPath;
        this.numCopies = numCopies;
    }


    public Resource(String uniqueId, String title, int year, String thumbnailPath, int numCopies) {
        this.uniqueId = uniqueId;
        this.title = title;
        this.year = year;
        this.thumbnailPath = thumbnailPath;
        this.numCopies = numCopies;
    }


    /*
     * @return unique ID of the resource
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /*
     * Will create a unique ID for a resource which is incremental
     * of the previous unique ID of the same resource type.
     */
    public void setUniqueId() throws Exception {
        String rType = "";
        if (this instanceof DVD) {
            rType = "D";
        } else if (this instanceof Book) {
            rType = "B";
        } else if (this instanceof Laptop) {
            rType = "L";
        } else {
            throw new Exception("Cannot resolve a type");
        }
        uniqueId = rType + String.valueOf(DataAdapter.getNumberOfResources(type) + 1);
    }


    /*
     * @return title of the resource
     */
    public String getTitle() {
        return title;
    }

    /*
     * @param title changes the title of the resource
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
     * @return year in which resource created
     */
    public int getYear() {
        return year;
    }

    /*
     * @param year changes the year of the resource
     */
    public void setYear(int year) {
        this.year = year;
    }

    /*
     * @return thumbnail path of the resource
     */
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    /*
     * @param thumbailPath changes the thumbnail path of the resource
     */
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    /*
     * @return type of resource (1=DVD, 2=Laptop, 3=DVD)
     */
    public int getType() {
        return type;
    }

    /*
     * @param type determines the type of resource created
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return number of copies of a certain resource
     */
    public int getNumCopies() {
        return numCopies;
    }

    /**
     * @param numCopies changes the amount of copies of that certain resource
     */
    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
}

