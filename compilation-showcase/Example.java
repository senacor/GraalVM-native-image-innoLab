public class Example {
    public static void main(String[] args) {
        String str = args[0];
        System.out.println("Reversing '" + str + "'");
        String reversed = reverseString(str);
        System.out.println(reversed);
    }

    public static String reverseString(String str) {
        if (str.isEmpty()) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }
}
