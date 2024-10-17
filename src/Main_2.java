class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String OS;
    private String cooling;
    private String powerSupply;

    public void setCPU(String CPU) { this.CPU = CPU; }
    public void setRAM(String RAM) { this.RAM = RAM; }
    public void setStorage(String storage) { this.storage = storage; }
    public void setGPU(String GPU) { this.GPU = GPU; }
    public void setOS(String OS) { this.OS = OS; }
    public void setCooling(String cooling) { this.cooling = cooling; }
    public void setPowerSupply(String powerSupply) { this.powerSupply = powerSupply; }

    @Override
    public String toString() {
        return "Компьютер: CPU - " + CPU + ", RAM - " + RAM + ", Накопитель - " + storage +
                ", GPU - " + GPU + ", ОС - " + OS + ", Охлаждение - " + cooling + ", Блок питания - " + powerSupply;
    }
}

interface IComputerBuilder {
    void setCPU();
    void setRAM();
    void setStorage();
    void setGPU();
    void setOS();
    void setCooling();
    void setPowerSupply();
    Computer getComputer();
}

class OfficeComputerBuilder implements IComputerBuilder {
    private Computer computer;

    public OfficeComputerBuilder() {
        this.computer = new Computer();
    }
    @Override
    public void setCPU() { computer.setCPU("Intel i3"); }
    @Override
    public void setRAM() { computer.setRAM("8GB"); }
    @Override
    public void setStorage() { computer.setStorage("1TB HDD"); }
    @Override
    public void setGPU() { computer.setGPU("Integrated"); }
    @Override
    public void setOS() { computer.setOS("Windows 10"); }
    @Override
    public void setCooling() { computer.setCooling("Standard Cooling"); }
    @Override
    public void setPowerSupply() { computer.setPowerSupply("400W"); }
    @Override
    public Computer getComputer() { return this.computer; }
}

class GamingComputerBuilder implements IComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void setCPU() { computer.setCPU("Intel i9"); }

    @Override
    public void setRAM() { computer.setRAM("32GB"); }

    @Override
    public void setStorage() { computer.setStorage("1TB SSD"); }

    @Override
    public void setGPU() { computer.setGPU("NVIDIA RTX 3080"); }

    @Override
    public void setOS() { computer.setOS("Windows 11"); }

    @Override
    public void setCooling() { computer.setCooling("Liquid Cooling"); }

    @Override
    public void setPowerSupply() { computer.setPowerSupply("750W"); }

    @Override
    public Computer getComputer() { return this.computer; }
}
 class ComputerDirector {
    private IComputerBuilder builder;

    public ComputerDirector(IComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.setCPU();
        builder.setRAM();
        builder.setStorage();
        builder.setGPU();
        builder.setOS();
        builder.setCooling();
        builder.setPowerSupply();
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}
public class Main_2 {
    public static void main(String[] args) {
        IComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector director = new ComputerDirector(officeBuilder);
        director.constructComputer();
        Computer officeComputer = director.getComputer();
        System.out.println(officeComputer);

        IComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director = new ComputerDirector(gamingBuilder);
        director.constructComputer();
        Computer gamingComputer = director.getComputer();
        System.out.println(gamingComputer);
    }
}
