public class Main {
    public static void main(String[] args) {
        Game g = new Game();

        try {
            System.out.println(g.setBombAt(1,1));
            System.out.println(g.setBombAt(2,2));
            System.out.println(g.setBombAt(0,8));
            System.out.println(g.setBombAt(4,6));
            System.out.println(g.setBombAt(6,4));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        g.setBombOnGrid();

        g.setAround();

        try {
            System.out.println(g.fireAt(4,3));
            System.out.println(g.fireAt(5,5));
            System.out.println(g.fireAt(0,8));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println(g.toString());
    }
}
