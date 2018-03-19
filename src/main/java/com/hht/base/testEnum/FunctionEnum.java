package com.hht.base.testEnum;

public enum FunctionEnum implements InterfaceEnum{

    PrintOne {
        @Override
        public void print() {
            System.out.println(1);
        }
    },

    PrintTwo {
        @Override
        public void print() {
            System.out.println(2);
        }
    };

    public static void main(String[] args) {
        FunctionEnum.PrintOne.print();
        FunctionEnum.PrintTwo.print();
    }
}
