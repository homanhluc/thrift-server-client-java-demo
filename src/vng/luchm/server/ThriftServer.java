/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import vng.luchm.service.CalculatorServiceHandler;
import vng.luchm.thrift.Calculator;

/**
 *
 * @author luchm
 */
public class ThriftServer {

    public static CalculatorServiceHandler handler;
    public static Calculator.Processor processor;

    public static void main(String[] args) {
        handler = new CalculatorServiceHandler();
        processor = new Calculator.Processor(handler);
        Runnable threadServerStart = () -> {
            serverStart(processor);
        };
        new Thread(threadServerStart).start();
    }

    public static void serverStart(Calculator.Processor processor) {
        try {
            final TServerTransport serverTransport = new TServerSocket(9000);
            final TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the thread pool server...");
            server.serve();
        } catch (TTransportException ex) {
            Logger.getLogger(ThriftServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
