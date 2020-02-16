/**
 * This class is used as a link between mySQL DB and the tawelib. All interaction with the database is
 * processed through this class
 *
 * @author Eduard Zakarian
 */

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataAdapter {

    /**
     * Gets the current UNIX timestamp.
     *
     * @return Long containing the timestamp.
     */
    public static long getCurrentDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * Converts UNIX timestamp to String with date (dd/mm/yyyy). Time is set to 00:00:00.
     *
     * @param unixTime Timestamp to convert.
     * @return A String with date (dd/mm/yyyy).
     */
    public static String longToString(long unixTime) {
        Date javaDate = new java.util.Date(unixTime * 1000L);
        SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        timeFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        return timeFormat.format(javaDate);
    }

    /**
     * Creates a Statement and a Connections needed for a DB interaction.
     *
     * @param user   MySQL username.
     * @param pass   MySQL password.
     * @param server Ip of a server.
     * @return DataSource object is returned to create a connection.
     * @throws SQLException Exception is being thrown if the connection cannot be established.
     */
    private static DataSource establishConnection(String user, String pass, String server) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(server);
        return dataSource;
    }

    /**
     * Identifies parsed object and uses one of the private method to insert values into the DB.
     *
     * @param o Object to be inserted.
     * @throws Exception Throws exception for any unexpected instance.
     */
    public static void writeData(Object o) throws Exception {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        switch (o.getClass().getName()) {
            case "User":
                writeUser(o, stmt);
                break;
            case "Librarian":
                writeLibrarian(o, stmt);
                break;
            case "Book":
                writeBook(o, stmt);
                break;
            case "DVD":
                writeDVD(o, stmt);
                break;
            case "Laptop":
                writeLaptop(o, stmt);
                break;
            case "Copy":
                writeCopy(o, stmt);
                break;
            default:
                throw new Exception("Unrecognised Class");
        }
        stmt.close();
        con.close();
    }

    /**
     * Adds a new user to a DB.
     *
     * @param u    User object.
     * @param stmt Conncetion to a db.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeUser(Object u, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        User u1 = (User) u;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `users` " +
                        "(`username`, `fname`, `lname`, `mobile`, `address`, `image`, " +
                        "`isLibrarian`, `staffNo`, `empDate`, `initBalance`)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '0', NULL, NULL, '%s');",
                u1.getUsername(), u1.getFirstName(), u1.getLastName(), u1.getPhoneNumber(), u1.getAddress(),
                u1.getImgPath(), u1.getBalance()));
    }

    /**
     * Add a new Copy to a database.
     *
     * @param u    Copy to be added.
     * @param stmt Conncetion to a db.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeCopy(Object u, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        Copy c1 = (Copy) u;
        stmt.executeQuery("USE tawelib;");
        //Inserting a copy into the DB
        try {
            stmt.executeUpdate(String.format
                    ("INSERT INTO `copy` (`uid`,`rid`) " +
                                    "VALUES ('%d','%s')",
                            c1.getUniqueID(), c1.getResourceID()));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a new Librarian to a DB.
     *
     * @param l    User object.
     * @param stmt Conncetion to a db.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeLibrarian(Object l, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        Librarian l1 = (Librarian) l;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `users` " +
                        "(`username`, `fname`, `lname`, `mobile`, `address`, `image`, " +
                        "`isLibrarian`, `staffNo`, `empDate`, `initBalance`)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '1', '%d', '%s', NULL);",
                l1.getUsername(), l1.getFirstName(), l1.getLastName(), l1.getPhoneNumber(), l1.getAddress(),
                l1.getImgPath(), l1.getStaffNumber(), l1.getEmploymentDate()));
    }

    /**
     * Adds a new book to a DB.
     *
     * @param b    Book to be added.
     * @param stmt Conncetion to a db.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeBook(Object b, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        Book b1 = (Book) b;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `book` " +
                        "( `rid`, `author`, `publisher`, `genre`, `isbn`, `language`)" +
                        " VALUES ('%s', '%s', '%s', '%s','%s','%s');", b1.getUniqueId(), b1.getAuthor(),
                b1.getPublisher(), b1.getGenre(), b1.getIsbn(), b1.getLanguage()));
    }

    /**
     * Adds a new DVD to a DB.
     *
     * @param d    DVD to be added.
     * @param stmt Conncetion to a DB.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeDVD(Object d, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        DVD d1 = (DVD) d;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `dvd` " +
                        "(`rid`, `director`, `runtime`, `language`, `subLang`) " +
                        "VALUES ('%s', '%s', '%d', '%s', '%s');",
                d1.getUniqueId(), d1.getDirector(), d1.getRuntime(), d1.getLanguage(), d1.getSubtitle()));
    }

    /**
     * Adds a new Laptop to a DB.
     *
     * @param l    Laptop to be added.
     * @param stmt Connection to a DB.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeLaptop(Object l, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        Laptop l1 = (Laptop) l;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `laptop` (`rid`, `manufacturer`, `model`, `installedOS`) " +
                        "VALUES ('%s', '%s', '%s', '%s');", l1.getUniqueId(), l1.getManufacturer(), l1.getModel(),
                l1.getOperatingSystem()));
    }

    /**
     * Adds a new Resource to a DB.
     *
     * @param r    A Resource to be added.
     * @param stmt Connection to a DB.
     * @throws SQLException Throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static void writeResource(Object r, Statement stmt) throws SQLException {
        //Casting needed to get access to setters & getters
        Resource r1 = (Resource) r;
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `resource` (`rid`, `title`, `year`, `imagePath`, `numCopies`) " +
                        "VALUES ('%s', '%s', '%d', '%s', '%d');",
                r1.getUniqueId(), r1.getTitle(), r1.getYear(), r1.getThumbnailPath(), r1.getNumCopies()));
    }

    /**
     * Finds an account with the provided username in the DB
     *
     * @param username the user which we want to find
     * @return Account object containing the information about this user
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB
     */

    public static Account readAccount(String username) throws SQLException {
        //Establishing a connection with the local DB
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");

        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.users WHERE username = '" + username + "'");
        Account result = null;
        //Resulting cells are stored in rs so we need a loop to retrieve each of them
        while (rs.next()) {
            //String uName = rs.getString("username");
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String mob = rs.getString("mobile");
            String address = rs.getString("address");
            String imagePath = rs.getString("image");
            int isLibrarian = rs.getInt("isLibrarian");
            if (isLibrarian == 1) {
                int staffNum = rs.getInt("staffNo");
                long empDate = rs.getLong("empDate");
                result = new Librarian(username, fName, lName, mob, address, imagePath, staffNum, empDate);
            } else {
                double balance = rs.getDouble("initBalance");
                result = new User(username, fName, lName, mob, address, imagePath, balance);
            }
        }
        //Handling an exception where incorrect username is parsed
        if (result == null) {
            throw new IllegalArgumentException("No such an Account found in the DB! ");
        }
        rs.close();
        stmt.close();
        con.close();
        return result;
    }


    /**
     * Finds a Resource  with the provided unique ID in the DB.
     *
     * @param uID the unique identifier of the resource.
     * @return Resource object containing the information about this item.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */

    public static Resource readResource(String uID) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");
        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.resource WHERE rid = '" + uID + "'");
        Resource result = null;
        while (rs.next()) {
            String resID = rs.getString("rid");
            String title = rs.getString("title");
            int year = rs.getInt("year");
            String imagePath = rs.getString("imagePath");
            int numCopies = rs.getInt("numCopies");
            //System.out.println(uName + " " + fName + " " + lName + " " + mob + " " + address + " " + imagePath);
            result = new Resource(resID, title, year, imagePath, numCopies);

        }
        //Handling an exception where incorrect uid is parsed
        if (result == null) {
            throw new IllegalArgumentException("No such a Resource found in the DB! ");
        }
        rs.close();
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Extracts the Copy from the DB and creates an object with retrieved information.
     *
     * @param copyID a unique identifier of a Copy.
     * @return a Copy object with all data from the DB.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */
    public static Copy readCopy(int copyID) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");
        Copy c = null;
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM copy " +
                "WHERE copyID = %d", copyID));
        while (rs.next()) {
            String rid = rs.getString("rid");
            int available = rs.getInt("available");
            int dur = rs.getInt("duration");
            c = new Copy(copyID, rid, dur, available);
        }
        stmt.close();
        con.close();
        return c;
    }

    /**
     * Extracts the Book object from a DB given a Resource info.
     *
     * @param uID       a unique ID of a Resource.
     * @param title     the title of the Book.
     * @param year      the year when the book was published.
     * @param imgPath   a path to a thumbnail image.
     * @param numCopies the number of copies which will exist for this Resource.
     * @return Book object with all the data from a DB.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static Book readBook(String uID, String title, int year, String imgPath, int numCopies) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");
        Book b = null;
        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.book WHERE rid = '" + uID + "'");
        while (rs.next()) {
            String author = rs.getString("author");
            String pub = rs.getString("publisher");
            String genre = rs.getString("genre");
            String isbn = rs.getString("isbn");
            String lan = rs.getString("language");
            b = new Book(uID, title, year, imgPath, author, pub, genre, isbn, lan, numCopies);
        }
        return b;
    }

    /**
     * Extrats the Laptop from a DB given a Resource info.
     *
     * @param uID       a unique ID of a Resource.
     * @param title     the title of the Laptop.
     * @param year      the year when the Laptop was published.
     * @param imgPath   a path to a thumbnail image.
     * @param numCopies the number of copies which will exist for this Resource.
     * @return Laptop object with all the data from a DB.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static Laptop readLaptop(String uID, String title, int year, String imgPath, int numCopies) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");
        Laptop l = null;
        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.laptop WHERE rid = '" + uID + "'");
        while (rs.next()) {
            String man = rs.getString("manufacturer");
            String mod = rs.getString("model");
            String os = rs.getString("installedOS");
            l = new Laptop(uID, title, year, imgPath, numCopies, man, mod, os);
        }
        return l;
    }

    /**
     * Extrats the DVD from a DB given a Resource info.
     *
     * @param uID       a unique ID of a Resource.
     * @param title     the title of the DVD.
     * @param year      the year when the DVD was published.
     * @param imgPath   a path to a thumbnail image.
     * @param numCopies the number of copies which will exist for this Resource.
     * @return DVD object with all the data from a DB.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */
    private static DVD readDVD(String uID, String title, int year, String imgPath, int numCopies) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib;");
        DVD d = null;
        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.dvd WHERE rid = '" + uID + "'");
        while (rs.next()) {
            String director = rs.getString("director");
            int runtime = rs.getInt("runtime");
            String language = rs.getString("language");
            String sub = rs.getString("subLang");
            d = new DVD(uID, title, year, imgPath, numCopies, director, runtime, language, sub);
        }
        return d;
    }

    /**
     * This method changes the path to a profile picture of a user.
     *
     * @param user       the username of a person, whose avatar should change.
     * @param pathToFile a path to an avatar.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the .
     */
    public static void changeAvatar(String user, String pathToFile) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        checkExistence("users", "username", user);
        String query = String.format("UPDATE `users` SET `image` = '%s' " +
                "WHERE `users`.`username` = '%s'", pathToFile, user);
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

        stmt.close();
        con.close();
    }

    /**
     * Get all resources which are stored in tawelib.resource table.
     *
     * @return ArrayList of resources stored in the DB.
     * @throws SQLException throws exception in case connection can't be established or value will not exist in the DB.
     */
    public static ArrayList<Resource> getAllResources() throws SQLException {
        ArrayList<Resource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM tawelib.resource");
        while (rs.next()) {
            String rid = rs.getString("rid");
            String title = rs.getString("title");
            int year = rs.getInt("year");
            String imgPath = rs.getString("imagePath");
            int numCopies = rs.getInt("numCopies");

            switch (rid.charAt(0)) {
                case 'B':
                    result.add(DataAdapter.readBook(rid, title, year, imgPath, numCopies));
                    break;
                case 'L':
                    result.add(DataAdapter.readLaptop(rid, title, year, imgPath, numCopies));
                    break;
                case 'D':
                    result.add(DataAdapter.readDVD(rid, title, year, imgPath, numCopies));
                    break;
                default:
                    System.out.println("Something is wrong with the input!");
            }
        }
        return result;
    }

    /**
     * Checks for existence of a value based on table, unique attribute and a value itself.
     *
     * @param table            a table in a DB where the search needs to be done.
     * @param unique_attribute a column which contains unique information.
     * @param value            a value which we want to find.
     * @throws SQLException throws exception in case connection can't be established.
     */
    public static void checkExistence(String table, String unique_attribute, String value) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        //Checking if a user exists in a database
        String query1 = String.format
                ("SELECT EXISTS(SELECT * FROM %s WHERE %s = '%s')", table, unique_attribute, value);
        ResultSet rs = stmt.executeQuery(query1);
        //initialising a trigger. It will change its value.
        int trigger = -1;
        while (rs.next()) {
            trigger = rs.getInt(1);
        }
        rs.close();
        if (trigger == 0) {
            throw new IllegalArgumentException("No such a value found in a DB");
        }
    }


    /**
     * Updates the user table in the DB by analizing the provided user.
     *
     * @param u Users object with new info.
     * @throws SQLException throws an exception if connection cannot be established or a user doesn't exists in a DB.
     */
    public static void updateUser(User u) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        checkExistence("users", "username", u.getUsername());
        stmt.executeUpdate(String.format("UPDATE `users` " +
                        "SET " +
                        "`username` = '%s'," +
                        "`fname` = '%s'," +
                        "`lname` = '%s'," +
                        "`mobile` = '%s'," +
                        "`address` = '%s'," +
                        "`image` = '%s'," +
                        "`initBalance` = '%s'" +
                        "WHERE `users`.`username` = '%s';",
                u.getUsername(), u.getFirstName(), u.getLastName(), u.getPhoneNumber(),
                u.getAddress(), u.getImgPath(), u.getBalance(), u.getUsername()));
        stmt.close();
        con.close();
    }

    /**
     * Checks the availability of a Copy.
     *
     * @param r a Resource a Copy is related to.
     * @return 1 if available, 0 if not, -1 if there is an error.
     * @throws SQLException throws an exception if connection cannot be established or a user doesn't exists in a DB.
     */
    public static int available(Resource r) throws SQLException {
        int result = -1;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT copyID FROM copy " +
                "WHERE rid = '%s'" +
                "AND copy.available = '1'" +
                "LIMIT 1;", r.getUniqueId()));
        while (rs.next()) {
            result = rs.getInt("copyID");
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Marks a Resource as reserved for a user.
     *
     * @param u The user wishing to reserve a copy of a resource.
     * @param r The resource being reserved.
     * @param c The copy of the resource being reserved.
     * @throws SQLException
     */
    public static void reserveResource(User u, Resource r, Copy c) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("INSERT INTO `borrowedresources` " +
                        "(`username`, `rid`, `copyID`, `dateOut`, `due`)" +
                        " VALUES ('%s', '%s', '%d', NULL, NULL)",
                u.getUsername(), r.getUniqueId(), c.getUniqueID()));
        markCopyAvailability(r, c.getUniqueID(), false);
        stmt.close();
        con.close();
    }

    /**
     * Marks a resource as borrowed by a user for when they have reserved a copy and have come to collect it.
     *
     * @param u   The user collecting the resource.
     * @param r   The resource being collected.
     * @param cid The unique identifier of the copy of the resource being collected.
     * @throws SQLException
     */
    public static void collectResource(User u, Resource r, int cid) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("INSERT INTO `borrowedresources` " +
                "(`username`, `rid`, `copyID`, `dateOut`, `due`) " +
                "VALUES ('%s', '%s',%d , %d, NULL)", u.getUsername(), r.getUniqueId(), cid, getCurrentDate()));
        markCopyAvailability(r, cid, false);
        stmt.close();
        con.close();
    }

    /**
     * Adds an entry to the copy table of a resource.
     *
     * @param r
     * @param duration
     * @throws SQLException
     */
    private static void onlyAddCopy(Resource r, int duration) throws SQLException {
        int maxId = 0;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT MAX(copyID) FROM `copy`");
        while (rs.next()) { //searches to get the latest ID
            maxId = rs.getInt("MAX(copyID)"); //gets the last unique ID
        }
        stmt.executeUpdate(String.format("INSERT INTO `copy` (`copyID`, `rid`, `available`, `duration`) " +
                "VALUES ('%d', '%s', '1', '%d')", maxId + 1, r.getUniqueId(), duration)); //gets the next unique id
    }

    /**
     * Adds a copy to the copy table if the resource already exists. If not, will create a resource and adds a copy.
     *
     * @param r
     * @param duration
     * @throws SQLException
     */
    public static void addCopy(Resource r, int duration) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        try {
            checkExistence("resource", "rid", r.getUniqueId());
            onlyAddCopy(r, duration);
            stmt.close();
            con.close();
        } catch (IllegalArgumentException e) {
            writeResource(r, stmt);
            onlyAddCopy(r, duration);
            stmt.close();
            con.close();
        }
    }

    /**
     * Adds a requested resource for a user.
     *
     * @param u The user requesting a resource.
     * @param r The resource being requested.
     * @throws SQLException
     */
    public static void addRequestedResource(User u, Resource r) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("INSERT INTO `requestedresources` (`username`, `rid`, `date`) " +
                "VALUES ('%s', '%s', '%d')", u.getUsername(), r.getUniqueId(), getCurrentDate()));
        stmt.close();
        con.close();
    }

    /**
     * Deletes an instance of a borrowed resource to indicate that the resource is no longer being borrowed.
     *
     * @param u The user that borrowed the resource.
     * @param r The resource being borrowed.
     * @param c The copy of the resource being borrowed.
     * @throws SQLException
     */
    public static void deleteBorrowedResource(User u, Resource r, Copy c) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("DELETE FROM borrowedresources " +
                        "WHERE username = '%s' AND rid = '%s' AND copyID = '%d'",
                u.getUsername(), r.getUniqueId(), c.getUniqueID()));
    }

    /**
     * Deletes an instance of a requested resource.
     *
     * @param u The user that requested the resource.
     * @param r The resource that had been requested.
     * @throws SQLException
     */
    public static void deleteRequestedResource(User u, Resource r) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("DELETE FROM requestedresources " +
                "WHERE username = '%s' AND rid = '%s'", u.getUsername(), r.getUniqueId()));
        stmt.close();
        con.close();
    }

    /**
     * Gets all borrowed resources from the database for a certain user.
     *
     * @param u The user.
     * @return An ArrayList of BorrowedResources that have been borrowed by the user.
     * @throws SQLException
     */
    public static ArrayList<BorrowedResource> getBorrowedResources(User u) throws SQLException {
        ArrayList<BorrowedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM borrowedresources " +
                "WHERE username = '%s'", u.getUsername()));
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            int cid = rs.getInt("copyID");
            long dateOut = rs.getLong("dateOut");
            long dueDate = rs.getLong("due");
            BorrowedResource b = new BorrowedResource(uname, rid, cid, dateOut, dueDate);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Gets all requested resources for a certain user.
     *
     * @param u The user.
     * @return An ArrayList of RequestedResources requested by the user.
     * @throws SQLException
     */
    public static ArrayList<RequestedResource> getRequestedResources(User u) throws SQLException {
        ArrayList<RequestedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM requestedresources " +
                "WHERE username = '%s'", u.getUsername()));
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            long date = rs.getLong("date");
            RequestedResource b = new RequestedResource(uname, rid, date);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Gets the loan duration of a copy of a resource.
     *
     * @param r The resource.
     * @param c The copy of the resource.
     * @return The loan duration of the copy.
     * @throws SQLException
     */
    public static int getCopyDuration(String r, int c) throws SQLException {
        int res = -1; //error value
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT duration " +
                "FROM copy " +
                "WHERE rid = '%s' AND copyID = '%d'", r, c));
        while (rs.next()) {
            res = rs.getInt("duration");
        }
        stmt.close();
        con.close();
        return res;
    }

    /**
     * Gets all resource requests for a certain resource.
     *
     * @param r The resource.
     * @return An ArrayList of all of the RequestedResources for the resource.
     * @throws SQLException
     */
    public static ArrayList<RequestedResource> getAllResourceRequests(Resource r) throws SQLException {
        ArrayList<RequestedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM requestedresources " +
                "WHERE rid = '%s'", r.getUniqueId()));
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            long date = rs.getLong("date");
            RequestedResource b = new RequestedResource(uname, rid, date);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;

    }

    /**
     * Gets all borrowed copies of a resource.
     *
     * @param r The resource borrowed.
     * @return An ArrayList of BorrowedResources.
     * @throws SQLException
     */
    public static ArrayList<BorrowedResource> getAllBorrowedResources(Resource r) throws SQLException {
        ArrayList<BorrowedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM borrowedresources " +
                "WHERE rid = '%s'", r.getUniqueId()));
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            int copyId = rs.getInt("copyID");
            long dateOut = rs.getLong("dateOut");
            long due = rs.getLong("due");
            BorrowedResource b = new BorrowedResource(uname, rid, copyId, dateOut, due);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Gets all borrowed copies of resources.
     *
     * @return An ArrayList of all BorrowedResources in the borrowedresources table.
     * @throws SQLException
     */
    public static ArrayList<BorrowedResource> getAllBorrowedResources() throws SQLException {
        ArrayList<BorrowedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM borrowedresources");
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            int copyId = rs.getInt("copyID");
            long dateOut = rs.getLong("dateOut");
            long due = rs.getLong("due");
            BorrowedResource b = new BorrowedResource(uname, rid, copyId, dateOut, due);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;

    }

    /**
     * Sets the due date for a specific copy of a resource.
     *
     * @param r   The resource.
     * @param u   The user borrowing the copy.
     * @param cid The unique identifier for the copy.
     * @param due The due date for the copy.
     * @throws SQLException
     */
    public static void setDueDate(Resource r, User u, int cid, long due) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("UPDATE borrowedresources " +
                        "SET due = '%d' " +
                        "WHERE rid = '%s' AND username = '%s' AND copyID = %d",
                due, r.getUniqueId(), u.getUsername(), cid));
        stmt.close();
        con.close();
    }

    /**
     * Gets all copies of a resource.
     *
     * @param r The resource.
     * @return An ArrayList of all the copies.
     * @throws SQLException
     */
    public static ArrayList<Copy> getAllCopies(Resource r) throws SQLException {
        ArrayList<Copy> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * " +
                "FROM copy " +
                "WHERE rid = '%s'", r.getUniqueId()));
        while (rs.next()) {
            int copyID = rs.getInt("copyID");
            String rid = rs.getString("rid");
            int available = rs.getInt("available");
            int dur = rs.getInt("duration");
            Copy c = new Copy(copyID, rid, dur, available);
            result.add(c);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Checks if a copy is overdue.
     *
     * @param u The user borrowing the resource.
     * @param r The resource.
     * @param c The copy of the resource.
     * @return
     * @throws SQLException
     */
    public static boolean isResourceOverdue(User u, Resource r, Copy c) throws SQLException {
        long due = -1; //error value
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT due " +
                        "FROM borrowedresources " +
                        "WHERE username = '%s' AND rid = '%s' AND copyID = %d",
                u.getUsername(), r.getUniqueId(), c.getUniqueID()));
        while (rs.next()) {
            due = rs.getInt("due");
        }
        if (due == 0) {
            return false;
        } else if (due < getCurrentDate()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the number of resources in the table by the type
     *
     * @param numeralType The type of resource (based on numbers).
     * @return The number of resources.
     * @throws SQLException
     */
    public static int getNumberOfResources(int numeralType) throws SQLException {
        //type: 1 - DVD, 2 - Laptop, 3 - Book
        int result = -1; //Error value
        String type = "";
        switch (numeralType) {
            case 1:
                type = "dvd";
                break;
            case 2:
                type = "laptop";
                break;
            case 3:
                type = "book";
                break;
            default:
                throw new IllegalArgumentException("The type is WRONG!");
        }
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT count(*) " +
                "FROM %s", type));
        while (rs.next()) {
            result = rs.getInt("count(*)");
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Will return all data that the borrowedresources stored as a BorrowedResource object.
     *
     * @param u The user borrowing the resource.
     * @param r The resource being borrowed.
     * @param c the copy of the resource.
     * @throws SQLException
     */
    public static BorrowedResource getBorrowedResourceInfo(User u, Resource r, Copy c) throws SQLException {
        BorrowedResource result = null;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM borrowedresources " +
                        "WHERE username = '%s' AND rid = '%s' AND copyID = %d ",
                u.getUsername(), r.getUniqueId(), c.getUniqueID()));
        while (rs.next()) {
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            int copyId = rs.getInt("copyID");
            long dateOut = rs.getLong("dateOut");
            long due = rs.getLong("due");
            result = new BorrowedResource(uname, rid, copyId, dateOut, due);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Will mark whether a copy of a resource is available.
     *
     * @param r           The resource being borrowed.
     * @param cid         The unique identifier of the copy of the resource.
     * @param isAvailable Whether the copy is available or not.
     * @throws SQLException
     */
    public static void markCopyAvailability(Resource r, int cid, boolean isAvailable) throws SQLException {
        int flag = -1; //Error code
        if (isAvailable) {
            flag = 1;
        } else {
            flag = 0;
        }
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("UPDATE copy " +
                "SET available = %d " +
                "WHERE copyID = %d AND rid = '%s'", flag, cid, r.getUniqueId()));
        stmt.close();
        con.close();
    }

    /**
     * Checks if a resource has been requested by a user.
     *
     * @param r The resource.
     * @return Whether the resource has been requested or not.
     * @throws SQLException
     */
    public static boolean isResourceRequested(Resource r) throws SQLException {
        int resourceCounter = -1; //Error code
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT count(*) " +
                "FROM requestedresources " +
                "WHERE rid = '%s'", r.getUniqueId()));
        while (rs.next()) {
            resourceCounter = rs.getInt("count(*)");
        }
        stmt.close();
        con.close();
        if (resourceCounter > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Adds a fine to the fine table.
     *
     * @param f The fine.
     * @throws SQLException
     */
    public static void addFine(Fine f) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(
                String.format("INSERT INTO `fine` (`fid`, `username`, `rid`, `cid`, `setDate`, `amountDue`) " +
                                "VALUES (%d, '%s', '%s', %d, %d, %d)",
                        genFineNum(), f.getUserId(), f.getRId(), f.getCId(), getCurrentDate(), calculateFine(f)));
        User u = (User) DataAdapter.readAccount(f.getUserId());
        addTransaction(u, calculateFine(f), f);
        stmt.close();
        con.close();
    }

    /**
     * Adds a transaction to the transaction table.
     *
     * @param u      The user that the transaction applies to.
     * @param amount The amount of the transaction.
     * @param f      The fine that this relates to.
     * @throws SQLException
     */
    public static void addTransaction(User u, int amount, Fine f) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        if (f == null) {
            stmt.executeUpdate(
                    String.format("INSERT INTO `transactions` (`fid`, `date`, `amount`, `username`, `endDate`)" +
                            " VALUES (NULL, %d, %d, '%s', NULL)", getCurrentDate(), amount, u.getUsername()));
        } else {
            stmt.executeUpdate(
                    String.format("INSERT INTO `transactions` (`fid`, `date`, `amount`, `username`, `endDate`)" +
                            " VALUES (%d, %d, %d, '%s', NULL)", f.getFId(), getCurrentDate(), -amount, u.getUsername()));
        }
        stmt.close();
        con.close();
    }

    /**
     * Gets all the fines for a certain user.
     *
     * @param u The user.
     * @return The fines that a certain user has.
     * @throws SQLException
     */
    public static ArrayList<Fine> getAllUserFines(User u) throws SQLException {
        ArrayList<Fine> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM fine WHERE username = '%s'", u.getUsername()));
        while (rs.next()) {
            int fid = rs.getInt("fid");
            String uname = rs.getString("username");
            String rid = rs.getString("rid");
            long setDate = rs.getLong("setDate");
            int amount = rs.getInt("amountDue");
            int cid = rs.getInt("cid");
            Fine f = new Fine(fid, uname, rid, cid, setDate, amount);
            result.add(f);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Updating the amount due for each entry in the database which is present in an arraylist.
     *
     * @param in An ArrayList of fines.
     * @throws SQLException
     */
    public static void setAllUserFineAmounts(ArrayList<Fine> in) throws SQLException {
        String username = in.get(0).getUserId(); //All username here will be the same so take the first one
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        for (Fine f : in) {
            stmt.executeUpdate(String.format("UPDATE fine " +
                    "SET amountDue = %d " +
                    "WHERE fid = %d", f.getAmountDue(), f.getFId()));
        }
        stmt.close();
        con.close();
    }

    /**
     * Gets all transactions for a user.
     *
     * @param u The user.
     * @return The transactions.
     * @throws SQLException
     */
    public static ArrayList<Transaction> getAllUserTransactions(User u) throws SQLException {
        ArrayList<Transaction> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM transactions " +
                "WHERE username = '%s'", u.getUsername()));
        while (rs.next()) {
            int tid = rs.getInt("tid");
            int fid = rs.getInt("fid");
            long date = rs.getLong("date");
            int amount = rs.getInt("amount");
            String username = rs.getString("username");
            long endDate = rs.getLong("endDate");
            Transaction t = new Transaction(tid, fid, date, amount, username, endDate);
            result.add(t);
        }
        return result;
    }

    /**
     * Sets a fine as paid in the database.
     *
     * @param fid The identifier for the fine.
     * @throws SQLException
     */
    //Matches fid in transactions table and sets endDate to current datetime in UNIX
    public static void setFineAsPayed(int fid) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("UPDATE transactions " +
                "SET endDate = %d " +
                "WHERE fid = %d", getCurrentDate(), fid));
        stmt.close();
        con.close();
    }

    /**
     * Gets the unpaid balance of the user.
     *
     * @param user The user.
     * @return The balance  for the user.
     * @throws SQLException
     */
    public static double getUserUnpaidBalance(User user) throws SQLException {
        int userTotalBalance;

        ArrayList<Fine> fineList = DataAdapter.getAllUserFines(user);

        double balance = 0;
        for (Fine currentfine : fineList) {
            balance += currentfine.getAmountDue();

        }

        return balance;

    }

    /**
     * Gets the amount of days between two dates (in unix time stamp).
     *
     * @param setDate The first date.
     * @param endDate The last date.
     * @return The amount of days.
     */
    public static int getDuration(long setDate, long endDate) {
        int duration = (int) (endDate - setDate);
        int rDuration = (int) (duration / (8.64 * Math.pow(10, 7)));
        return rDuration;

    }

    /**
     * Gets the number of fines that exist.
     *
     * @return The number of fines.
     * @throws SQLException
     */
    public static int getNumberOfFines() throws SQLException {
        int result = -1; //Error Code
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM fine");
        while (rs.next()) {
            result = rs.getInt("count(*)");
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Returns the number of fines plus 1 for unique identifier generation.
     *
     * @return The number of fines plus 1
     * @throws SQLException
     */
    public static int genFineNum() throws SQLException {
        return getNumberOfFines() + 1;
    }

    /**
     * Calculates the fine that a user needs to pay by accepting a fine to find the resource it is associated with
     * to calculate the fine amount.
     *
     * @param fine A fine.
     * @return An integer of how much the fine is.
     * @throws SQLException
     */
    public static int calculateFine(Fine fine) throws SQLException {
        User u = (User) DataAdapter.readAccount(fine.getUserId());
        Resource r = DataAdapter.readResource(fine.getRId());
        Copy c = DataAdapter.readCopy(fine.getCId());
        BorrowedResource br = getBorrowedResourceInfo(u, r, c);
        int numDaysLate = getDuration(br.getDateOut(), br.getDateDue());
        int fineAmount;
        switch (br.getrID().charAt(0)) {
            case 'L':
                fineAmount = numDaysLate * 1000;
                if (fineAmount > 10000) {
                    fineAmount = 10000;
                }
                break;
            default:
                fineAmount = numDaysLate * 200;
                if (fineAmount > 2500) {
                    fineAmount = 2500;
                }
                break;
        }
        return fineAmount;
    }

    /**
     * Checks if a user exists in the database.
     *
     * @param username       The user being checked
     * @param librarianOrNot Whether the user is a librarian or not.
     * @return A boolean indicating whether the user exists.
     * @throws SQLException
     */
    //Checks if a user exists in the DB and that he or she is not a Librarian
    public static boolean checkUser(String username, int librarianOrNot) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        //Checking if a user exists in a database
        String query1 = String.format
                ("SELECT EXISTS(SELECT * FROM users WHERE username = '%s' AND isLibrarian = %d)",
                        username, librarianOrNot);
        ResultSet rs = stmt.executeQuery(query1);
        //initialising a trigger. It will change its value.
        int trigger = -1;
        while (rs.next()) {
            trigger = rs.getInt(1);
        }
        rs.close();
        if (trigger == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds data to the CopyHistory table to track the history of borrowings for a certain copy.
     *
     * @param c The copyhistory.
     * @throws SQLException
     */
    public static void addCopyHistory(CopyHistory c) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("INSERT INTO `copyhistory` (`rid`, `cid`, `username`, `dateout`, `datein`) " +
                "VALUES ('%s',%d, '%s', %d, %d)", c.getRid(), c.getCid(), c.getUsername(), c.getDateout(), c.getDatein()));
        stmt.close();
        con.close();
    }

    /**
     * Gets the CopyHistory for a copy.
     *
     * @param cid The unique identifier for the copy.
     * @return The history of what has happened to the copy in an ArrayList of CopyHistory.
     * @throws SQLException
     */
    public static ArrayList<CopyHistory> getCopyHistory(int cid) throws SQLException {
        ArrayList<CopyHistory> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM copyhistory " +
                "WHERE cid = '%s'", cid));
        while (rs.next()) {
            int chid = rs.getInt("chid");
            String rid = rs.getString("rid");
            String username = rs.getString("username");
            long dateout = rs.getLong("dateout");
            long datein = rs.getInt("datein");
            CopyHistory c = new CopyHistory(chid, rid, cid, username, dateout, datein);
            result.add(c);
        }
        return result;
    }

    /**
     * Sets the datein attribute for copyhistory.
     *
     * @param ch The copy history.
     * @throws SQLException
     */
    public static void setCHDateIn(CopyHistory ch) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("UPDATE copyhistory " +
                "SET datein = %d " +
                "WHERE rid = '%s' AND " +
                "cid = %d AND " +
                "dateout = %d", getCurrentDate(), ch.getRid(), ch.getCid(), ch.getDateout()));
        stmt.close();
        con.close();
    }

    /**
     * Updates the resource with new values (aside from the unique identifier).
     *
     * @param b The book resource.
     * @throws SQLException
     */
    public static void updateBook(Book b) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");

        stmt.executeUpdate(String.format(String.format("UPDATE `resource` " +
                        "SET `title` = '%s'," +
                        " `year` = %d, " +
                        "`imagePath` = '%s', " +
                        "`numCopies` = %d " +
                        "WHERE `resource`.`rid` = '%s' ", b.getTitle(), b.getYear(), b.getThumbnailPath(),
                b.getNumCopies(), b.getUniqueId())));

        stmt.executeUpdate(String.format("UPDATE `book` " +
                "SET `author` = '%s', " +
                "`publisher` = '%s', " +
                "`genre` = '%s', " +
                "`isbn` = '%s', " +
                "`language` = '%s' " +
                "WHERE `book`.`rid` = '%s' ", b.getAuthor(), b.getPublisher(), b.getGenre(), b.getIsbn(), b.getLanguage(), b.getUniqueId()));
        stmt.close();
        con.close();
    }

    /**
     * Updates the resource with new values (aside from the unique identifier).
     *
     * @param l The Laptop resource.
     * @throws SQLException
     */
    public static void updateLaptop(Laptop l) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");

        stmt.executeUpdate(String.format(String.format("UPDATE `resource` " +
                        "SET `title` = '%s'," +
                        " `year` = %d, " +
                        "`imagePath` = '%s', " +
                        "`numCopies` = %d " +
                        "WHERE `resource`.`rid` = '%s' ", l.getTitle(), l.getYear(), l.getThumbnailPath(),
                l.getNumCopies(), l.getUniqueId())));

        stmt.executeUpdate(String.format("UPDATE `laptop` " +
                "SET `manufacturer` = '%s', " +
                "`model` = '%s', " +
                "`installedOS` = '%s' " +
                "WHERE `laptop`.`rid` = '%s' ", l.getManufacturer(), l.getModel(), l.getOperatingSystem(), l.getUniqueId()));
        stmt.close();
        con.close();
    }

    /**
     * Updates the resource with new values (aside from the unique identifier).
     *
     * @param d The DVD resource.
     * @throws SQLException
     */
    public static void updateDvd(DVD d) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");

        stmt.executeUpdate(String.format(String.format("UPDATE `resource` " +
                        "SET `title` = '%s'," +
                        " `year` = %d, " +
                        "`imagePath` = '%s', " +
                        "`numCopies` = %d " +
                        "WHERE `resource`.`rid` = '%s' ", d.getTitle(), d.getYear(), d.getThumbnailPath(),
                d.getNumCopies(), d.getUniqueId())));

        stmt.executeUpdate(String.format("UPDATE `dvd` " +
                        "SET `director` = '%s', " +
                        "`runtime` = %d, " +
                        "`language` = '%s', " +
                        "`subLang` = '%s' " +
                        "WHERE `dvd`.`rid` = '%s' ",
                d.getDirector(), d.getRuntime(), d.getLanguage(), d.getSubtitle(), d.getUniqueId()));
        stmt.close();
        con.close();
    }

    /**
     * Returns the book associated with the resource id.
     *
     * @param rid The resource id of the book.
     * @return The book.
     * @throws SQLException
     */
    public static Book readBook(String rid) throws SQLException {
        Book b = null;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM book WHERE rid = '%s'", rid));
        while (rs.next()) {
            Resource br = DataAdapter.readResource(rid);
            String author = rs.getString("author");
            String pub = rs.getString("publisher");
            String genre = rs.getString("genre");
            String isbn = rs.getString("isbn");
            String lan = rs.getString("language");
            b = new Book(rid, br.getTitle(), br.getYear(), br.getThumbnailPath(), author, pub, genre, isbn, lan, br.getNumCopies());
        }
        stmt.close();
        con.close();
        return b;
    }

    /**
     * Returns the laptop associated with the resource id.
     *
     * @param rid The resource id of the laptop.
     * @return The laptop.
     * @throws SQLException
     */
    public static Laptop readLaptop(String rid) throws SQLException {
        Laptop l = null;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM laptop WHERE rid = '%s'", rid));
        while (rs.next()) {
            Resource br = DataAdapter.readResource(rid);
            String man = rs.getString("manufacturer");
            String mod = rs.getString("model");
            String os = rs.getString("installedOS");
            l = new Laptop(rid, br.getTitle(), br.getYear(), br.getThumbnailPath(), br.getNumCopies(), man, mod, os);
        }
        stmt.close();
        con.close();
        return l;
    }

    /**
     * Returns the DVD associated with the resource id.
     *
     * @param rid The resource id of the DVD.
     * @return The DVD.
     * @throws SQLException
     */
    public static DVD readDvd(String rid) throws SQLException {
        DVD d = null;
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM dvd WHERE rid = '%s'", rid));
        while (rs.next()) {
            Resource br = DataAdapter.readResource(rid);
            String dir = rs.getString("director");
            int run = rs.getInt("runtime");
            String lan = rs.getString("language");
            String sub = rs.getString("subLang");
            d = new DVD(br.getUniqueId(), br.getTitle(), br.getYear(), br.getThumbnailPath(), br.getNumCopies(), dir, run, lan, sub);
        }
        stmt.close();
        con.close();
        return d;
    }

    /**
     * Returns all books in the database.
     *
     * @return ArrayList of books.
     * @throws SQLException
     */
    public static ArrayList<Book> readAllBooks() throws SQLException {
        ArrayList<Book> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM resource WHERE rid LIKE '%B%'");
        while (rs.next()) {
            String rid = rs.getString("rid");
            Book b = DataAdapter.readBook(rid);
            result.add(b);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Returns all laptops in the database.
     *
     * @return ArrayList of laptops.
     * @throws SQLException
     */
    public static ArrayList<Laptop> readAllLaptops() throws SQLException {
        ArrayList<Laptop> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM resource WHERE rid LIKE '%L%'");
        while (rs.next()) {
            String rid = rs.getString("rid");
            Laptop l = DataAdapter.readLaptop(rid);
            result.add(l);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Returns all DVDs in the database.
     *
     * @return ArrayList of DVDs.
     * @throws SQLException
     */
    public static ArrayList<DVD> readAllDvds() throws SQLException {
        ArrayList<DVD> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM resource WHERE rid LIKE '%D%'");
        while (rs.next()) {
            String rid = rs.getString("rid");
            DVD d = DataAdapter.readDvd(rid);
            result.add(d);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Returns all users that are not staff.
     *
     * @return ArrayList of Users.
     * @throws SQLException
     */
    public static ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE staffNo IS NULL AND empDate IS NULL");
        while (rs.next()) {
            String username = rs.getString("username");
            String fn = rs.getString("fname");
            String ln = rs.getString("lname");
            String mob = rs.getString("mobile");
            String address = rs.getString("address");
            String img = rs.getString("image");
            double balance = rs.getInt("initBalance");
            User u = new User(username, fn, ln, mob, address, img, balance);
            result.add(u);
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Adds a date when a borrowedresource has been collected.
     *
     * @param username The username of the user that collected the resource.
     * @param rid      The resource identifier.
     * @param cid      The copy identifier.
     * @throws SQLException
     */
    public static void addCollectionDate(String username, String rid, int cid) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        stmt.executeUpdate(String.format("UPDATE borrowedresources " +
                "SET dateOut = %d " +
                "WHERE username = '%s' AND " +
                "rid = '%s' AND " +
                "copyID = %d", getCurrentDate(), username, rid, cid));
        stmt.close();
        con.close();
    }

    /**
     * Writes a resource into the database.
     *
     * @param r1 The resource to be written.
     * @throws SQLException
     */
    public static void writeResource(Resource r1) throws SQLException {
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        //Casting needed to get access to setters & getters
        stmt.executeQuery("USE tawelib;");
        //Inserting a user into the DB
        stmt.executeUpdate(String.format("INSERT INTO `resource` (`rid`, `title`, `year`, `imagePath`, `numCopies`) " +
                        "VALUES ('%s', '%s', '%d', '%s', '%d');",
                r1.getUniqueId(), r1.getTitle(), r1.getYear(), r1.getThumbnailPath(), r1.getNumCopies()));
    }

    /**
     * Gets all RequestedRecources for a specific resource.
     *
     * @param rid The resource's identifier.
     * @return An ArrayList of the RequestedResources.
     * @throws SQLException
     */
    public static ArrayList<RequestedResource> getAllRequested(String rid) throws SQLException {
        ArrayList<RequestedResource> result = new ArrayList<>();
        Connection con = establishConnection("root", "", "127.0.0.1").getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE tawelib");
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM requestedresources WHERE rid = '%s'", rid));
        while (rs.next()) {
            String username = rs.getString("username");
            long date = rs.getLong("date");
            RequestedResource r = new RequestedResource(username, rid, date);
            result.add(r);
        }
        stmt.close();
        con.close();
        return result;
    }
}