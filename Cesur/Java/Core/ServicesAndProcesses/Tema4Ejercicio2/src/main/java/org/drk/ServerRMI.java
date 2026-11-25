package org.drk;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI extends UnicastRemoteObject implements RMI {
    private static final float LIBRA_A_EURO = 1.16f;
    private static final float EURO_A_LIBRA = 0.87f;
    private static final float DOLAR_A_EURO = 0.86f;
    private static final float EURO_A_DOLAR = 1.16f;

    protected ServerRMI() throws RemoteException {
        super();
    }

    @Override
    public float librasEuros(float cantidad) throws RemoteException {
        return cantidad * LIBRA_A_EURO;
    }

    @Override
    public float eurosLibras(float cantidad) throws RemoteException {
        return cantidad * EURO_A_LIBRA;
    }

    @Override
    public float dolaresEuros(float cantidad) throws RemoteException {
        return cantidad * DOLAR_A_EURO;
    }

    @Override
    public float eurosDolares(float cantidad) throws RemoteException {
        return cantidad * EURO_A_DOLAR;
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("ConversionMoneda", new ServerRMI());
            System.out.println("Servidor de conversión de monedas activo");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
