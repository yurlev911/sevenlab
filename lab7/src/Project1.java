import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Project1{
    public static void main(String[] args) throws IOException{
        try{
            File f1 = new File("C:\\Users\\kedua\\Учеба\\3 семестр\\Алгоритмика\\Лаба 7\\My\\Project1.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();

            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            long fSize = rf.length();

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.println("Введите количество людей для записи в файл " + "=> ");
            int N = sc.nextInt();
            sc.nextLine();

            // Запись инфы о людях
            String last_name;
            String name;
            int year;
            int month;
            for (int i = 0; i < N; i++){
                System.out.print("Введите фамилию " + (i + 1) + " человека => ");
                last_name = sc.nextLine();
                rf.seek(rf.length());
                rf.writeUTF(last_name);
                for (int j = 0; j < 20 - last_name.length(); j++){
                    rf.writeByte(1);
                }

                System.out.print("Введите имя человека => ");
                name = sc.nextLine();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++){
                    rf.writeByte(1);
                }

                System.out.print("Введите год рождения человека => ");
                year = sc.nextInt();
                sc.nextLine();
                rf.writeInt(year);

                System.out.print("Введите месяц рождения (от 1 до 12)=> ");
                month = sc.nextInt();
                sc.nextLine();
                rf.writeInt(month);
            }
            rf.close();

            // Чтение инфы о людях
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            System.out.println("Информация о людях");
            System.out.println("Фамилия \t\t имя \t\t год рождения \t\t месяц");

            for (int i = 0; i < N; i++){
                last_name = rf.readUTF();
                for (int j = 0; j < 20 - last_name.length(); j++){
                    rf.readByte();
                }

                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++){
                    rf.readByte();
                }
                year = rf.readInt();
                month = rf.readInt();
                if (month == 1){
                    System.out.println(last_name  +  "\t\t\t" + name + "\t\t\t" + year + "\t\t\t" + "january");
                }
            }
            rf.close();
        }
        catch (IOException e){
            System.out.println("End of file " + e);
        }
    }
}

