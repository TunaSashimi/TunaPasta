package current;

public enum EnumTest {
    MR("Mr."), MRS("Mrs."), MS("Ms.");
    private final String title;

    private EnumTest(String t) {
        title = t;
    }

    public String format(String last, String first) {
        return title + " " + first + " " + last;
    }

    public static void main(String[] args) {

        System.out.println(EnumTest.MR.format("Doe", "John"));
        System.out.println(3 - -1);
    }
}
