/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.service;

import org.apache.thrift.TException;
import vng.luchm.thrift.Calculator;
import vng.luchm.thrift.Operation;

/**
 *
 * @author luchm
 */
public class CalculatorServiceHandler implements Calculator.Iface {

    @Override
    public int calculate(int num1, int num2, Operation op) throws TException {
        switch (op) {
            case ADD:
                return num1 + num2;
            case SUBTRACT:
                return num1 - num2;
            case MULTIPLY:
                return num1 * num2;
            case DIVIDE:
                return num1 / num2;
            default:
                throw new TException("Unknown operation " + op);
        }
    }
}
