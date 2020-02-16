/**
 * This class creates a DVD object inherited from the Resource class.
 * <p>
 * Matthew have added toString method(needed in Edit DVD Page).
 *
 * @author Luke Burtonwood
 * @version v1.3.0
 * - no copyright
 * - Last modification date: 4/12/2018
 * - Creation date: 18/11/2018
 */

public class DVD extends Resource {
    private String director;
    private int runtime;
    private String language; //
    private String subtitle; //possibly an array of multiple languages?

    /**
     * Creates a DVD resource
     *
     * @param title         title of the DVD
     * @param year          year the DVD was created
     * @param thumbnailPath path to an image to easily recognise DVD
     * @param director      director of the DVD
     * @param runtime       length of the DVD (in minutes)
     * @param language      language the DVD is played in
     * @param subtitle      language in which the subtitles for DVD are
     */
    public DVD(String title, int year, String thumbnailPath, int numCopies, String director, int runtime, String language,
               String subtitle) throws Exception {
        super(title, year, thumbnailPath, numCopies);
        this.setDirector(director);
        this.setRuntime(runtime);
        this.setLanguage(language);
        this.setSubtitle(subtitle);
        super.setType(1);
        super.setUniqueId();
    }

    /**
     * Additional constructor for the unique ID.
     *
     * @param uid           Unique ID.
     * @param title         of the DVD.
     * @param year          year the DVD was created.
     * @param thumbnailPath path to an image to easily recognise DVD.
     * @param director      director of the DVD.
     * @param runtime       length of the DVD (in minutes).
     * @param language      language the DVD is played in.
     * @param subtitle      language in which the subtitles for DVD are.
     */
    public DVD(String uid, String title, int year, String thumbnailPath, int numCopies, String director, int runtime, String language,
               String subtitle) {
        super(uid, title, year, thumbnailPath, numCopies);
        this.setDirector(director);
        this.setRuntime(runtime);
        this.setLanguage(language);
        this.setSubtitle(subtitle);
        super.setType(1);
    }

    /*
     * @return director of the DVD
     */
    public String getDirector() {
        return director;
    }

    /*
     * @param director changes the director of the DVD
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /*
     * @return runtime of the DVD (in minutes)
     */
    public int getRuntime() {
        return runtime;
    }

    /*
     * @param runtime changes the runtime of the DVD (in minutes)
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /*
     * @return Language of the DVD
     */
    public String getLanguage() {
        return language;
    }

    /*
     * @param language changes the language of the DVD
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /*
     * @return Language of subtitles available on DVD
     */
    public String getSubtitle() {
        return subtitle;
    }

    /*
     * @param subtitle changes the language of subtitles available
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Get a short description of the DVD that is suitable for use in a ListView.
     *
     * @return A short description of the DVD.
     */
    public String toString() {
        return getTitle() + " - " + getYear() + " - " + getThumbnailPath() + " - " + getNumCopies()
                + " - " + director + " - " + runtime + " - " + language + " - " + subtitle;
    }
}
