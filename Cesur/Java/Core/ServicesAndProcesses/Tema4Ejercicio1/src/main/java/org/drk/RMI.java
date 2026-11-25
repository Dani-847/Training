package org.drk;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    int sumar(int a, int b) throws RemoteException;
    int resta(int a, int b) throws RemoteException;
    int multiplica(int a, int b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    double porcentaje(double x, double y) throws RemoteException;
    int resto(int a, int b) throws RemoteException;
    double media(double[] x) throws RemoteException;
}
