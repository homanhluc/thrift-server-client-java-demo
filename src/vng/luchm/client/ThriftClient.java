/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.client;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import vng.luchm.thrift.Calculator;
import vng.luchm.thrift.Operation;

/**
 *
 * @author luchm
 */
public class ThriftClient {

    public static void main(String[] args) {

        try {
            final TTransport transport = new TSocket("localhost", 9000);
            final TProtocol protocol = new TBinaryProtocol(transport);
            final Calculator.Client client = new Calculator.Client(protocol);
            transport.open();
            calculator(client);
        } catch (TTransportException ex) {
            Logger.getLogger(ThriftClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TException ex) {
            Logger.getLogger(ThriftClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void println(String string) {
        System.out.println(string);
    }

    private static void calculator(Calculator.Client client) throws TException {
        new Calculator();
        Scanner sc = new Scanner(System.in);

        int choose = 0;
        boolean kt = true;
        do {
            println("--Chọn phép tính--");
            println("1. Cộng");
            println("2. Trừ");
            println("3. Nhân");
            println("4. Chia");
            println("Chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            println("Nhập số a: ");
            int a = sc.nextInt();
            println("Nhập số b: ");
            int b = sc.nextInt();
            switch (choose) {
                case 1: {
                    println("=> Kết quả: " + client.calculate(a, b, Operation.ADD));
                    break;
                }
                case 2: {
                    println("=> Kết quả: " + client.calculate(a, b, Operation.SUBTRACT));
                    break;
                }
                case 3: {
                    println("=> Kết quả: " + client.calculate(a, b, Operation.MULTIPLY));
                    break;
                }
                case 4: {
                    println("=> Kết quả: " + client.calculate(a, b, Operation.DIVIDE));
                    break;
                }

                default:
                    println("Sai Cu Phap!");
                    break;
            }
        } while (kt);
        sc.close();
    }
}
