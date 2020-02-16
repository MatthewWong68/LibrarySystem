/**
 * This class creates a Laptop object inherited from the Resource class.
 * Matthew have added toString method(needed in Edit Laptop Page).
 *
 * @author Luke Burtonwood
 * @version v1.3.0
 * - no copyright
 */

public class Laptop extends Resource {
    private String manufacturer;
    private String model;
    private String operatingSystem;

    /**
     * Creates a Laptop resource
     *
     * @param title           name designated to a certain Laptop
     * @param year            year the Laptop was created
     * @param thumbnailPath   path to an image to easily recognise Laptop
     * @param manufacturer    company that manufactured Laptop (HP, ASUS, LENOVO etc.)
     * @param model           model of the Laptop
     * @param operatingSystem operating system in which the Laptop runs
     */
    public Laptop(String title, int year, String thumbnailPath, int numCopies, String manufacturer, String model,
                  String operatingSystem) throws Exception {
        super(title, year, thumbnailPath, numCopies);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setOperatingSystem(operatingSystem);
        super.setType(2);
        super.setUniqueId();
    }

    public Laptop(String uid, String title, int year, String thumbnailPath, int numCopies, String manufacturer, String model,
                  String operatingSystem) {
        super(uid, title, year, thumbnailPath, numCopies);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setOperatingSystem(operatingSystem);
        super.setType(2);
    }

    /*
     * @return manufacturer of the Laptop
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /*
     * @param manufacturer changes who manufactured the Laptop
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /*
     * @return model of the Laptop
     */
    public String getModel() {
        return model;
    }

    /*
     * @param model changes the model of the Laptop
     */
    public void setModel(String model) {
        this.model = model;
    }

    /*
     * @return operating system which runs on Laptop
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /*
     * @param operatingSystem changes the operating system which runs on the Laptop
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * Get a short description of the laptop that is suitable for use in a ListView.
     *
     * @return A short description of the laptop.
     */
    public String toString() {
        return getTitle() + " - " + getYear() + " - " + getThumbnailPath() + " - " + getNumCopies()
                + " - " + manufacturer + " - " + model + " - " + operatingSystem;
    }
}
