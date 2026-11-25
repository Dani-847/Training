
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String original = "cba";
        String cifrado = cifrar(original);
        String descifrado = descifrar(cifrado);

        System.out.println("Original: " + original);
        System.out.println("Cifrado : " + cifrado);
        System.out.println("Descifrado: " + descifrado);
    }

    public static String cifrar(String mensaje) {
        if (mensaje == null || mensaje.isEmpty()) return "";

        // 1) parsear a int sumando los códigos de los caracteres
        int sum = 0;
        for (int i = 0; i < mensaje.length(); i++) {
            sum += mensaje.charAt(i);
        }
        if (sum <= 0) sum = 1;

        // 2) ln(sum)
        double lnSum = Math.log(sum);

        // 3) sumarle pi
        double a = lnSum + Math.PI;

        // 4) integral definida entre 0 y a de f(x)=-(1/2)cos(pi/2 * x)
        // Integral analítica: -(1/(2k)) * sin(k * a) con k = pi/2 => -(1/pi) * sin((pi/2)*a)
        double integral = - (1.0 / Math.PI) * Math.sin((Math.PI / 2.0) * a);

        // 5) controlar decimales con BigDecimal (precisión de salida controlada)
        BigDecimal bd = BigDecimal.valueOf(integral).setScale(15, RoundingMode.HALF_UP);
        String integralStr = bd.toPlainString();

        // 6) generar clave XOR derivada del valor de la integral
        long keyLong = Math.abs(bd.movePointRight(6).longValue()) + 1L; // usar 6 decimales como factor
        byte[] keyBytes = ByteBuffer.allocate(Long.BYTES).putLong(keyLong).array();

        // 7) aplicar XOR a los bytes del mensaje
        byte[] plain = mensaje.getBytes(StandardCharsets.UTF_8);
        byte[] cipherBytes = new byte[plain.length];
        for (int i = 0; i < plain.length; i++) {
            cipherBytes[i] = (byte) (plain[i] ^ keyBytes[i % keyBytes.length]);
        }

        // 8) construir texto cifrado: integralStr:base64(cipherBytes)
        String body = Base64.getEncoder().encodeToString(cipherBytes);
        return integralStr + ":" + body;
    }

    // Descifra el formato producido por cifrar
    public static String descifrar(String mensaje) {
        if (mensaje == null || mensaje.isEmpty()) return "";

        int sep = mensaje.indexOf(':');
        if (sep <= 0) return "";

        String integralStr = mensaje.substring(0, sep);
        String body = mensaje.substring(sep + 1);

        // parsear integral con la misma precisión
        BigDecimal bd;
        try {
            bd = new BigDecimal(integralStr);
        } catch (Exception e) {
            return "";
        }

        long keyLong = Math.abs(bd.movePointRight(6).longValue()) + 1L;
        byte[] keyBytes = ByteBuffer.allocate(Long.BYTES).putLong(keyLong).array();

        byte[] cipherBytes;
        try {
            cipherBytes = Base64.getDecoder().decode(body);
        } catch (IllegalArgumentException e) {
            return "";
        }

        byte[] plain = new byte[cipherBytes.length];
        for (int i = 0; i < cipherBytes.length; i++) {
            plain[i] = (byte) (cipherBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(plain, StandardCharsets.UTF_8);
    }
}
