import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        RedBlackTree bst = new RedBlackTree();
        Scanner sc=new Scanner(System.in);
        System.out.println("RED-BLACK TREE INSERT");
        System.out.print("Number of key:");
        int n = sc.nextInt();
        while (n != 0) {
            System.out.print("Enter key:");
            int key = sc.nextInt();
            bst.insert(key);
            n--;
        }
        bst.print(bst.getRoot());

        System.out.println("RED-BLACK TREE DELETE");
        System.out.print("Enter key:");
        int Dkey = sc.nextInt();
        bst.delete(Dkey);
        bst.print(bst.getRoot());
    }
}
