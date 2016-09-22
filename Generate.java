package autop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Generate {

    static PreparedStatement pst;
    static ResultSet rs;
    static Connection conn = Database.makeConnection();

    static int bottom;

    public static ArrayList<Integer> numbs = new ArrayList();
    public static ArrayList<Integer> missing = new ArrayList();

    public static void gatherColumnValues() {
        String qry = "select id from gen";
        try {
            pst = conn.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                numbs.add(Integer.parseInt(rs.getString("id").split("Std")[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Method handled");
        System.out.println(numbs);

        getMissingNumbers(numbs);
    }

    private static void getMissingNumbers(ArrayList<Integer> x) {
        for (int i = 1; i < x.size(); i++) {
            for (int j = 1 + x.get(i - 1); j < x.get(i); j++) {
                missing.add(j);
            }
        }
        if (missing.isEmpty()) {
            generateNumberDb();
            formatt();
        } else {
            populateUsingMissing();
        }
    }

    public static void formatt() {
        String fmt = "Std";
        String qry = "insert into gen(id) values(?)";
        try {
            pst = conn.prepareStatement(qry);
            if (bottom < 10) {
                String fm1 = fmt + "00" + (bottom);
                pst.setString(1, fm1);
                pst.execute();
                pst.close();
            }
            if (bottom > 9 && bottom < 100) {
                String fm1 = fmt + "0" + (bottom);
                pst.setString(1, fm1);
                pst.execute();
                pst.close();
            }
            if (bottom > 99 && bottom < 1000) {
                String fm1 = fmt + "" + (bottom);
                pst.setString(1, fm1);
                pst.execute();
                pst.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateNumberDb() {
        String qry = "select count(*) from gen";
        try {
            pst = conn.prepareStatement(qry);
            rs = pst.executeQuery();
            if (rs.last()) {
                bottom = rs.getInt(1) + 1;
                System.out.println(bottom);
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateUsingMissing() {
        System.out.println("Missing: "+missing);
        Random r = new Random();
        bottom = missing.get(r.nextInt(missing.size()));
        System.out.println("Now bottom = " + bottom);
        formatt();
        missing.remove(missing.indexOf(bottom));
        System.out.println(bottom+" removed");
        System.out.println("Missing: "+missing);
    }

}
