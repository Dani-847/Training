package org.drk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static int[] cadena = {0, 0, 0, 0};

    public static void main(String[] args) {
        Map<String, Set<String>> combinacionesQueDan10 = new TreeMap<>();

        for (int i = 0; i < 9999; i++) {
            cadena[3] += 1;
            for (int j = cadena.length - 1; j >= 0; j--) {
                if (cadena[j] > 9) {
                    cadena[j] = 0;
                    if (j > 0) {
                        cadena[j - 1] += 1;
                    }
                }
            }

            Set<String> soluciones = findExpressions(cadena);
            if (!soluciones.isEmpty()) {
                String cs = cadenaString(cadena);
                System.out.println(cs + " -> " + soluciones);
                combinacionesQueDan10.putIfAbsent(cs, new TreeSet<>());
                combinacionesQueDan10.get(cs).addAll(soluciones);
            }
        }

        System.out.println("Combinaciones que dan 10 (" + combinacionesQueDan10.size() + "):");
        for (Map.Entry<String, Set<String>> e : combinacionesQueDan10.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    public static String cadenaString(int[] cadena) {
        StringBuilder cadenaString = new StringBuilder();
        for (int j : cadena) {
            cadenaString.append(j);
        }
        return cadenaString.toString();
    }

    // Busca expresiones que evalúen a 10 con las reglas pedidas
    public static Set<String> findExpressions(int[] nums) {
        Set<String> results = new HashSet<>();
        List<int[]> permutations = new ArrayList<>();
        permute(nums, new boolean[4], new int[4], 0, permutations);

        char[] ops = new char[]{'+', '-', '*', '/'};

        for (int[] perm : permutations) {
            // todas las combinaciones de signos unarios (negación) -> 0..15
            for (int signMask = 0; signMask < (1 << 4); signMask++) {
                double[] vals = new double[4];
                String[] strs = new String[4];
                for (int k = 0; k < 4; k++) {
                    int v = perm[k];
                    if (((signMask >> k) & 1) == 1) v = -v;
                    vals[k] = v;
                    strs[k] = (v < 0) ? "(" + v + ")" : Integer.toString(v);
                }

                for (char op0 : ops) for (char op1 : ops) for (char op2 : ops) {
                    // 5 parentetizaciones posibles
                    checkPattern1(vals, strs, new char[]{op0, op1, op2}, results);
                    checkPattern2(vals, strs, new char[]{op0, op1, op2}, results);
                    checkPattern3(vals, strs, new char[]{op0, op1, op2}, results);
                    checkPattern4(vals, strs, new char[]{op0, op1, op2}, results);
                    checkPattern5(vals, strs, new char[]{op0, op1, op2}, results);
                }
            }
        }
        return results;
    }

    // Permutaciones simples de los 4 números
    private static void permute(int[] nums, boolean[] used, int[] current, int depth, List<int[]> out) {
        if (depth == 4) {
            out.add(current.clone());
            return;
        }
        for (int i = 0; i < 4; i++) if (!used[i]) {
            used[i] = true;
            current[depth] = nums[i];
            permute(nums, used, current, depth + 1, out);
            used[i] = false;
        }
    }

    // Aplicar operador con manejo de división por cero. Devuelve null si inválido.
    private static Double applyOp(Double a, Double b, char op) {
        double eps = 1e-9;
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (Math.abs(b) < eps) return null; else return a / b;
            default: return null;
        }
    }

    private static boolean isTen(Double v) {
        if (v == null) return false;
        return Math.abs(v - 10.0) < 1e-9;
    }

    // Las 5 formas de parentetizar y construir la expresión textual
    private static void checkPattern1(double[] vals, String[] strs, char[] ops, Set<String> out) {
        // ((a op b) op c) op d
        Double t1 = applyOp(vals[0], vals[1], ops[0]);
        if (t1 == null) return;
        Double t2 = applyOp(t1, vals[2], ops[1]);
        if (t2 == null) return;
        Double t3 = applyOp(t2, vals[3], ops[2]);
        if (isTen(t3)) {
            String expr = "((" + strs[0] + " " + ops[0] + " " + strs[1] + ") " + ops[1] + " " + strs[2] + ") " + ops[2] + " " + strs[3];
            out.add(expr);
        }
    }

    private static void checkPattern2(double[] vals, String[] strs, char[] ops, Set<String> out) {
        // (a op (b op c)) op d
        Double t1 = applyOp(vals[1], vals[2], ops[1]);
        if (t1 == null) return;
        Double t2 = applyOp(vals[0], t1, ops[0]);
        if (t2 == null) return;
        Double t3 = applyOp(t2, vals[3], ops[2]);
        if (isTen(t3)) {
            String expr = "(" + strs[0] + " " + ops[0] + " (" + strs[1] + " " + ops[1] + " " + strs[2] + ")) " + ops[2] + " " + strs[3];
            out.add(expr);
        }
    }

    private static void checkPattern3(double[] vals, String[] strs, char[] ops, Set<String> out) {
        // a op ((b op c) op d)
        Double t1 = applyOp(vals[1], vals[2], ops[1]);
        if (t1 == null) return;
        Double t2 = applyOp(t1, vals[3], ops[2]);
        if (t2 == null) return;
        Double t3 = applyOp(vals[0], t2, ops[0]);
        if (isTen(t3)) {
            String expr = strs[0] + " " + ops[0] + " ((" + strs[1] + " " + ops[1] + " " + strs[2] + ") " + ops[2] + " " + strs[3] + ")";
            out.add(expr);
        }
    }

    private static void checkPattern4(double[] vals, String[] strs, char[] ops, Set<String> out) {
        // a op (b op (c op d))
        Double t1 = applyOp(vals[2], vals[3], ops[2]);
        if (t1 == null) return;
        Double t2 = applyOp(vals[1], t1, ops[1]);
        if (t2 == null) return;
        Double t3 = applyOp(vals[0], t2, ops[0]);
        if (isTen(t3)) {
            String expr = strs[0] + " " + ops[0] + " (" + strs[1] + " " + ops[1] + " (" + strs[2] + " " + ops[2] + " " + strs[3] + "))";
            out.add(expr);
        }
    }

    private static void checkPattern5(double[] vals, String[] strs, char[] ops, Set<String> out) {
        // (a op b) op (c op d)
        Double t1 = applyOp(vals[0], vals[1], ops[0]);
        if (t1 == null) return;
        Double t2 = applyOp(vals[2], vals[3], ops[2]);
        if (t2 == null) return;
        Double t3 = applyOp(t1, t2, ops[1]);
        if (isTen(t3)) {
            String expr = "(" + strs[0] + " " + ops[0] + " " + strs[1] + ") " + ops[1] + " (" + strs[2] + " " + ops[2] + " " + strs[3] + ")";
            out.add(expr);
        }
    }
}
