/**
 * This class creates a Book object inherited from the Resource class.
 * <p>
 * Matthew have added toString method(needed in Edit Book Page).
 *
 * @author Luke Burtonwood
 * @version v1.3.0
 * - no copyright
 */

public class Book extends Resource {
    private String author;
    private String publisher;
    private String genre;
    private String isbn;
    private String language;

    /**
     * Creates a Book resource
     *
     * @param title         Title of the Book
     * @param year          Year the Book was written
     * @param thumbnailPath Path to an image to easily recognise Book
     * @param author        Author of the Book
     * @param publisher     Publisher of the Book
     * @param genre         Genre of the book
     * @param isbn          Long String of characters that identifies the Book
     * @param language      Language in which Book is written
     */
    public Book(String title, int year, String thumbnailPath, String author, String publisher, String genre,
                String isbn, String language, int numCopies) throws Exception {
        super(title, year, thumbnailPath, numCopies);
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.language = language;
        super.setType(3);
        super.setUniqueId();
    }

    /**
     * Constructor that accepts unique id.
     *
     * @param uid           Unique ID.
     * @param title         Title of the Book
     * @param year          Year the Book was written
     * @param thumbnailPath Path to an image to easily recognise Book
     * @param author        Author of the Book
     * @param publisher     Publisher of the Book
     * @param genre         Genre of the book
     * @param isbn          Long String of characters that identifies the Book
     * @param language      Language in which Book is written
     * @param numCopies     Number of copies of the resource.
     */
    public Book(String uid, String title, int year, String thumbnailPath, String author, String publisher, String genre,
                String isbn, String language, int numCopies) {
        super(uid, title, year, thumbnailPath, numCopies);
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.language = language;
        super.setType(3);

    }


    /*
     * @return author of the Book
     */
    public String getAuthor() {
        return author;
    }

    /*
     * @param author changes the author of the Book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /*
     * @return publisher of the Book
     */
    public String getPublisher() {
        return publisher;
    }

    /*
     * @param publisher changes the publisher of the Book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /*
     * @return genre of the Book
     */
    public String getGenre() {
        return genre;
    }

    /*
     * @param genre changes the genre of the Book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /*
     * @return ISBN of the Book
     */
    public String getIsbn() {
        return isbn;
    }

    /*
     * @param isbn changes the ISBN of the Book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /*
     * @return language of the Book
     */
    public String getLanguage() {
        return language;
    }

    /*
     * @param language changes the language of the Book
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get a short description of the book that is suitable for use in a ListView.
     *
     * @return A short description of the book.
     */
    public String toString() {
        return getTitle() + " - " + getYear() + " - " + getThumbnailPath() + " - " + getNumCopies()
                + " - " + author + " - " + publisher + " - " + genre + " - " + isbn + " - " + language;
    }
}
