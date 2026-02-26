package gamecontentlogic;
/*
 * Author: Eibon
 * added in class of 24/2/2026 at 1056 from New Cinerea Netbeans Project
 * Resume: Handles Abstract Target logic, an extension from Target Class
 * status: requires heavy modifications and simplification
 * */

public class AbstractTarget implements Target {
    private String name;
    public AbstractTarget(String name) { this.name = name; }
    @Override
    public String getName() { return name; }
    @Override
    public void applyEffect(String effect) {
        System.out.println("The concept of " + name + " cannot be affected by " + effect);
    }
}