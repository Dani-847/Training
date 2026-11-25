package org.drk;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI extends UnicastRemoteObject implements RMI {
    protected ServerRMI() throws RemoteException {
        super();
    }

    @Override
    public int sumar(int n1, int n2) throws RemoteException {
        return n1 + n2;
    }

    @Override
    public int resta(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int multiplica(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0.0d) {
            throw new ArithmeticException("División por cero");
        }
        return a / b;
    }

    @Override
    public double porcentaje(double x, double y) throws RemoteException {
        return (x / 100.0d) * y;
    }

    @Override
    public int resto(int a, int b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("División por cero en resto");
        }
        return a % b;
    }

    @Override
    public double media(double[] x) throws RemoteException {
        if (x == null || x.length == 0) {
            throw new IllegalArgumentException("Array nulo o vacío");
        }
        double suma = 0.0d;
        for (double v : x) {
            suma += v;
        }
        return suma / x.length;
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("RemotoRMI", new ServerRMI());
            System.out.println("Servidor activo");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
