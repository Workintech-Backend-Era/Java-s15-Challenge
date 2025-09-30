package service;

public class NotificationService {
    public void notifyMember(int memberId, String message){
         System.out.println("[NOTIFICATION] Member#" + memberId + ": " + message);
    }
}
