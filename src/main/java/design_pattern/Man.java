package design_pattern;

public class Man {
    private String name;
    private int age;

    private boolean hasBrother;
    private boolean hasSister;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean hasBrother() {
        return hasBrother;
    }

    public boolean hasSister() {
        return hasSister;
    }

    private Man(){}

    private Man(ManBuilder mb){
        this.name = mb.name;
        this.age = mb.age;
        this.hasBrother = mb.hasBrother;
        this.hasSister = mb.hasSister;
    }

    public static class ManBuilder{
        private String name;
        private int age;

        private boolean hasBrother;
        private boolean hasSister;

        public ManBuilder(String name,int age){
            this.name = name;
            this.age = age;
        }

        public ManBuilder setHasBrother(boolean tf){
            this.hasBrother = tf;
            return this;
        }

        public ManBuilder setHasSister(boolean tf){
            this.hasSister = tf;
            return this;
        }

        public Man build(){
            return new Man(this);
        }
    }
}
