public class SearchJava {

    public static void main(String[] args) {

        stringSearch("omg", "wowomgzomg");
    }

    private static void stringSearch(String key, String text) {
        boolean good;
        int ans = 0;

        for (int txtIt = 0; txtIt < text.length()-key.length()+1; txtIt++) {

            good = true;
            int keyIt = 0;

            while (keyIt < key.length() && good) {
                if (text.charAt(txtIt+keyIt) != key.charAt(keyIt)) good = false;
                keyIt++;
            }

            ans += good ? 1 : 0;
        }

        System.out.println(ans + " occurences");
    }
}
