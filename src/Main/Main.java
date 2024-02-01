package Main;

import com.example.kütüphane.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Librarian librarian = new Librarian(1, "Emre");
        Library library = new Library(1);
        library.addLibrarian(librarian);

        // Yazarlar
        Author author1 = new Author(1, "Peyami Safa");
        Author author2 = new Author(2, "Ömer Seyfettin");
        Author author3 = new Author(3, "Kemal Tahir");
        Author author4 = new Author(4, "Sait Faik Abasıyanık");
        Author author5 = new Author(5, "Sabahattin Ali");
        Author author6 = new Author(6, "Ahmet Hamdi Tanpınar");

        // Katagoriler
        Category category1 = new Category(1, "Hikaye");
        Category category2 = new Category(2, "Roman");
        Category category3 = new Category(3, "Deneme");
        Category category4 = new Category(4, "Eliştiri");
        Category category5 = new Category(5, "Tiyatro");
        Category category6 = new Category(6, "Öykü");


        //Kullanıcılar
        User user1 = new User(1 ,"suat");
        User user2 = new User(2 , "fatih");
        User user3 = new User(3 , "mehmet");

        addUser(library, user1);
        addUser(library, user2);
        addUser(library, user3);

        //Kitaplar
        Book book1 = new Book(1, "Bahar ve Kelebekler" ,author2 ,category1 , true , 3);
        Book book2 = new Book(2, "Cingöz Recai" ,author1 , category2 , false , 5);
        Book book3 = new Book(3, "Müfettişler Müfettişi" , author6 , category3 , false ,4);
        Book book4 = new Book(4, "Semaver" , author4 , category6 , true,5);
        Book book5 = new Book(5, "Beş Şehir" , author6 , category4 , false ,2);
        Book book6 = new Book(6, "Huzur Bulvarı" , author6 , category5 , false ,1);

        addBook(library, book1);
        addBook(library, book2);
        addBook(library, book3);
        addBook(library, book4);
        addBook(library, book5);
        addBook(library, book6);


        //Arayüz

        Scanner scanner = new Scanner(System.in);;

        System.out.print("Kullanıcı: ");
        String username = scanner.nextLine();

        User currentUser = null;

        for (User user : library.getUsers()) {
            if (user.getName().equals(username)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Kullanıcı bulunamadı.");
            return;
        }

        System.out.println("Hoş geldin, " + currentUser.getName()+ "." +" Lütfen yapmak istediğin işlemi seç.");

        while (true) {
            System.out.println("1. Kitapları Listele");
            System.out.println("2. Kitap Al");
            System.out.println("3. Kitap İade Et");
            System.out.println("4. Kitap Bilgilerini Güncelle");
            System.out.println("5. Yeni Kullanıcı Kaydet");
            System.out.println("6. Çıkış");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listBooks(library);
                    break;
                case 2:
                    borrowBook(currentUser, library, scanner);
                    break;
                case 3:
                    returnBook(currentUser, library, scanner);
                    break;
                case 4:
                    updateBook(currentUser, library, scanner);
                    break;
                case 5:
                    addUserFromInput( library, scanner);
                    break;
                case 6:
                    System.out.println("Çıkış yapılıyor.");
                    return;
                default:
                    System.out.println("Geçersiz seçenek.");
            }
        }
    }

    private static void borrowBook(User user, Library library, Scanner scanner) {
        System.out.print("Almak istediğiniz kitap ID'sini giriniz: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        Book book = library.getBookById(bookID);

        if (book != null) {
            if (user.canBorrow()) {
                user.borrowBook(book);
                user.borrowedItems.add(book);
                library.removeBook(book);
                System.out.println(book.getName() + " ödünç alındı.");
            } else {
                System.out.println("Ödünç alınamadı. Limitiniz dolu.");
            }
        } else {
            System.out.println("Geçersiz ID.");
        }
    }

    private static void returnBook(User user, Library library, Scanner scanner) {
        System.out.println("İade etmek istediğiniz kitaplar:");
        ArrayList borrowedItems = new ArrayList(user.getBorrowedBooks());


        for (int i = 0; i < borrowedItems.size(); i++) {
            Object item = borrowedItems.get(i);
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println((i + 1) + ". " + book.getName());
            }
        }

        System.out.print("İade etmek istediğiniz kitap ID'sini girin: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();

        if (bookNumber >= 1 && bookNumber <= borrowedItems.size()) {
            Book book = (Book) borrowedItems.get(bookNumber - 1);

        } else {
            System.out.println("Geçersiz kitap ID.");
        }
    }


    private static void updateBook(User user, Library library, Scanner scanner) {
        System.out.println("Güncellemek istediğiniz kitabın ID'sini girin: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        Book book = library.getBooks().get(bookID);
        if (book instanceof Book) {
            if (user.bookHasBorrowed(book)) {
                System.out.println("Bu kitabı güncelleyemezsiniz. Kitap ödünç alınmış durumda.");
            } else {
                System.out.print("Kitap ismi: ");
                String newTitle = scanner.nextLine();

                System.out.print("Yeni yazar adı: ");
                String authorName = scanner.next();
                scanner.nextLine();
                Author newAuthor = library.getAuthorByAuthorName(authorName);

                Random random = new Random();
                int randomNumber = random.nextInt(100);

                System.out.print("Yeni kategori adı: ");
                String newCategoryName = scanner.nextLine();
                Category newCategory = new Category(randomNumber, newCategoryName);

                book.updateBookInfo(newTitle, newAuthor, newCategory);
                System.out.println("Kitap bilgileri güncellendi.");
            }
        } else {
            System.out.println("Geçersiz ID.");
        }
    }



    private static void listBooks(Library library) {
        System.out.println("Kütüphanedeki kitaplar:");
        for (Book book : library.getBooks()) {
            System.out.println(book.toString());
        }
    }

    public static void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println(book.getName() + " kütüphaneye eklendi.");
    }


    private static void addUserFromInput(Library library, Scanner scanner) {
        System.out.print("Kullanıcı adı: ");
        String userName = scanner.nextLine();
        User newUser = new User(library.generateUserId(), userName);
        addUser(library, newUser);
    }

    public static void addUser(Library library, User user) {
        library.addUser(user);
        System.out.println(user.getName() + " kütüphane üyesi olarak kaydedildi.");
    }
}
