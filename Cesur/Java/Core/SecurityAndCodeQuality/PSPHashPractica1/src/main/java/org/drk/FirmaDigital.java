package org.drk;

public class FirmaDigital {
    private final HashUtil hashUtil = new HashUtil();

    public String firmar(String mensaje, String clavePrivada) {
        return hashUtil.hash(mensaje + clavePrivada);
    }

    public boolean verificar(String mensaje, String firma, String clavePublica) {
        String firmaEsperada = hashUtil.hash(mensaje + clavePublica.replace("PUB-", "PRIV-"));
        return firma.equals(firmaEsperada);
    }
}
