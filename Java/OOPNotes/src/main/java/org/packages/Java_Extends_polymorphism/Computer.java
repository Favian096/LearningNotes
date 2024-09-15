package org.packages.Java_Extends_polymorphism;

public class Computer {
    private String computerName;

    public Computer(String computerName) {
        this.computerName = computerName;
    }

    void start() {
        System.out.println(this.computerName + "已开机");
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public void installUSB(USB u) {
        u.connect();
        if (u instanceof Keyboard) {
            Keyboard k = (Keyboard) u;
            k.words();
        } else if (u instanceof Mouse) {
            Mouse m = (Mouse) u;
            m.click();
        }
        u.unconnect();
    }
}
