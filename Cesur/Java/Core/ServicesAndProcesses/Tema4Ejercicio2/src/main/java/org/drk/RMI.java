package org.drk;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    float librasEuros(float cantidad) throws RemoteException;
    float eurosLibras(float cantidad) throws RemoteException;
    float dolaresEuros(float cantidad) throws RemoteException;
    float eurosDolares(float cantidad) throws RemoteException;
}
