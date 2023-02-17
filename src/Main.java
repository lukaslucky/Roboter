import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RoboterClient rc = new RoboterClient();
        Scanner in = new Scanner(System.in);

        // Name des Teilnehmers einlesen
        System.out.println("Roboter aktiviert! \nRoboter-Nummer eingeben:");
        String name = in.nextLine();

        // ChatClient starten
        rc.startActionB(name);
        rc.startActionP(name);

        // Wenn man möchte, dass sich der Client beim Beenden des Servers auch
        // beendet muss die Benutzereingabe aus einem Deamon-Thread heraus erfolgen

        //	Thread t = new Thread(new Runnable() {
        //		public void run() {
        // Konsoleneingaben einlesen und an Server senden
        String s;
        while ((s = in.nextLine()) != null) {
            if (s.equalsIgnoreCase("exit")) {
                rc.stopActionB();
                rc.stopActionP();
                break;
            } else {
                rc.sendActionP(s);
                rc.sendActionB(s);
            }
        }
        //		}
        //	});
        //	t.setDaemon(true); // Anwendung endet sobald Receiver-Thread terminiert
        //	t.start();
        System.out.println("main ende");
        in.close();
    }

}