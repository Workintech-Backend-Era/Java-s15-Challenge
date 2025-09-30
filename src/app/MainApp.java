package app;

import model.*;
import repository.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        BookRepository bookRepo = new BookRepository();
        UserRepository userRepo = new UserRepository();
        LoanRepository loanRepo = new LoanRepository();
        BillingService billing = new BillingService();
        NotificationService notify = new NotificationService();
        LibraryService lib = new LibraryService(bookRepo, userRepo, loanRepo, billing, notify);

        // örnek başlangıç verisi
        Author a1 = new Author(1, "Yalçın Küçük");
        Category c1 = new Category(1, "Araştırma");
        Book bk1 = lib.addBook("Türkiye Üzerine Tezler", a1, c1, 50.0);
        Member m1 = lib.addMember("Lucy", "lucy@example.com");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== KÜTÜPHANE OTOMASYONU ===");
            System.out.println("1) Kitap ekle");
            System.out.println("2) Kitap ara (başlığa göre)");
            System.out.println("3) Kitap güncelle");
            System.out.println("4) Kitap sil");
            System.out.println("5) Üye ekle");
            System.out.println("6) Ödünç al");
            System.out.println("7) İade et");
            System.out.println("8) Üyenin kitaplarını göster");
            System.out.println("9) Tüm kitapları listele");
            System.out.println("0) Çıkış");
            System.out.print("Seçim: ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Başlık: "); String title = sc.nextLine();
                        System.out.print("Yazar adı: "); String aname = sc.nextLine();
                        System.out.print("Kategori: "); String cname = sc.nextLine();
                        System.out.print("Fiyat: "); double price = Double.parseDouble(sc.nextLine());
                        Author author = new Author( IdGeneratorForDemo.next(), aname );
                        Category cat = new Category(IdGeneratorForDemo.next(), cname);
                        Book nb = lib.addBook(title, author, cat, price);
                        System.out.println("Eklendi: " + nb);
                        break;
                    case "2":
                        System.out.print("Başlık arama: ");
                        String q = sc.nextLine();
                        List<Book> found = lib.searchByTitle(q);
                        System.out.println("Bulundu: ");
                        for (Book b : found) System.out.println(b);
                        break;
                    case "3":
                        System.out.print("Güncellenecek kitap ID: "); int uid = Integer.parseInt(sc.nextLine());
                        System.out.print("Yeni başlık (boşsa enter): "); String nt = sc.nextLine();
                        boolean ok = lib.updateBook(uid, nt.isEmpty()?null:nt, null, null, null);
                        System.out.println(ok? "Güncellendi" : "Kitap bulunamadı");
                        break;
                    case "4":
                        System.out.print("Silinecek kitap ID: "); int rid = Integer.parseInt(sc.nextLine());
                        boolean rem = lib.removeBook(rid);
                        System.out.println(rem? "Silindi" : "Silinemedi (muhtemelen ödünçte veya yok)");
                        break;
                    case "5":
                        System.out.print("Üye adı: "); String uname = sc.nextLine();
                        System.out.print("E-mail: "); String uemail = sc.nextLine();
                        Member nm = lib.addMember(uname, uemail);
                        System.out.println("Üye eklendi: id=" + nm.getId());
                        break;
                    case "6":
                        System.out.print("Üye ID: "); int memId = Integer.parseInt(sc.nextLine());
                        System.out.print("Kitap ID: "); int bookId = Integer.parseInt(sc.nextLine());
                        System.out.println(lib.borrowBook(memId, bookId));
                        break;
                    case "7":
                        System.out.print("Üye ID: "); int memIdR = Integer.parseInt(sc.nextLine());
                        System.out.print("Kitap ID: "); int bookIdR = Integer.parseInt(sc.nextLine());
                        System.out.println(lib.returnBook(memIdR, bookIdR));
                        break;
                    case "8":
                        System.out.print("Üye ID: "); int showId = Integer.parseInt(sc.nextLine());
                        Member mm = userRepo.getById(showId);
                        if (mm == null) System.out.println("Üye yok");
                        else System.out.println("Borrowed: " + mm.getBorrowedBookIds());
                        break;
                    case "9":
                        for (Book b : bookRepo.getAll()) System.out.println(b);
                        break;
                    case "0":
                        System.out.println("Çıkış...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Geçersiz seçim.");
                }
            } catch (Exception ex) {
                System.out.println("Hata: " + ex.getMessage());
            }
        }
    }

    // Hızlı demo id üretimi (sadece main içi demo için)
    static class IdGeneratorForDemo {
        private static int id = 2000;
        public static int next() { return id++; }
    }
}
