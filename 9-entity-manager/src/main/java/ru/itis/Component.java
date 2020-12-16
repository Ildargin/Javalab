package ru.itis;

/**
 * 02.11.2020
 * 10. Reflection
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Component {
    public int publicField;
    private int privateField;
    public final static String staticField = "FINAL";

    public Component() {
        this.privateField = 10;
        this.publicField = 10;
    }

    public Component(int publicField, int privateField) {
        this.publicField = publicField;
        this.privateField = privateField;
    }

    public int getPublicField() {
        return publicField;
    }

    public int getPrivateField() {
        return privateField;
    }

    public void publicMethod() {
        System.out.println(privateField + " " + publicField);
    }

    private boolean privateMethod() {
        return this.privateField == this.publicField;
    }

    public int getSumOfFields(int a) {
        return a + this.privateField + this.publicField;
    }

    public static int methodWithArgs(int a, int b, int c) {
        return a + b + c;
    }
}
