package org.drk;

public class Main {
    public static void main(String[] args) {
        // Ana genera su par de claves
        FakeKeyPair clavesAna = new FakeKeyPair();
        String clavePublicaAna = clavesAna.getPublicKey();
        String clavePrivadaAna = clavesAna.getPrivateKey();

        System.out.println("Clave pública de Ana: " + clavePublicaAna);
        System.out.println("Clave privada de Ana: " + clavePrivadaAna);

        String mensajeOriginal = "Paga 100 euros a Bernardo";
        System.out.println("\nMensaje original: " + mensajeOriginal);

        FirmaDigital firmaDigital = new FirmaDigital();

        // Ana firma el mensaje con su clave privada
        String firma = firmaDigital.firmar(mensajeOriginal, clavePrivadaAna);
        System.out.println("Firma generada por Ana: " + firma);

        // Bernardo recibe mensaje + firma + clave pública de Ana
        System.out.println("\n --- Verificacion correcta --- ");
        boolean ok = firmaDigital.verificar(mensajeOriginal, firma, clavePublicaAna);
        System.out.println("¿La firma es válida? " + ok);

        // Modificamos el mensaje en tránsito (ataque a la integridad)
        String mensajeModificado = "Paga 1000 euros a Bernardo";
        System.out.println("\nMensaje modificado: " + mensajeModificado);

        System.out.println("\n --- Verificación tras modificación --- ");
        boolean ok2 = firmaDigital.verificar(mensajeModificado, firma, clavePublicaAna);
        System.out.println("¿La firma es válida tras modificar el mensaje? " + ok2);

    }
}