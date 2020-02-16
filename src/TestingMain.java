import java.util.ArrayList;

public class TestingMain {
    public static void main(String args[]) throws Exception {
        User test = (User) DataAdapter.readAccount("user1");
        // Resource r = DataAdapter.readResource("B001");
        Copy c = DataAdapter.readCopy(1);

        Book b = new Book("B001", "Tiee", 2000, "pathpath", "a", "grefd", "btef", "gevf", "gg", 5);
        DataAdapter.updateBook(b);
        Fine f = new Fine(DataAdapter.genFineNum(), "user1", "D005", 8, 100, 10);
        Librarian l = new Librarian("alpha", "lib1", "grfv", "+123", "asdf", "fv", 8451, 8451);
        Laptop l3 = new Laptop("L001", "Title", 2000, "pathL", 23, "man", "model", "os");

        DVD d3 = new DVD("D25001", "dvdr", 1999, "pgrfvc", 6, "direc", 544, "lang", "sub");
        // DataAdapter.writeData(d3);


        ArrayList<User> aaaa = DataAdapter.getAllUsers();
        System.out.println(DataAdapter.getAllRequested("B1").size());
        //l.returnResource(test,r,c);
        //l.borrowResource(test,r);
        //DataAdapter.changeAvatar("user1","test");
        //CopyHistory ch = new CopyHistory(0,"B001",1,"user1",984561,9845612);
        //DataAdapter.setCHDateIn(ch);

        //System.out.println(DataAdapter.getAllSpecificResources("Laptop").size());

        //DataAdapter.updateUser("user1",new User("abc","d","g","fg","g","fg",56));

    }

}
