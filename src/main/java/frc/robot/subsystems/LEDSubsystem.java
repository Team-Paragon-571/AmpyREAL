package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private static int ledPort = 1;
    private static int ledLength = 170;
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;

    public LEDSubsystem() {
        led = new AddressableLED(ledPort);
        ledBuffer = new AddressableLEDBuffer(ledLength);
        led.setLength(ledBuffer.getLength());
        led.start();

        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 255, 255);
        }

        led.setData(ledBuffer);
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("LED Color", ledBuffer.getLED(0).toString());
    }

    public void setLedColor(int red, int green, int blue) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, red, green, blue);
        }
        led.setData(ledBuffer);
    }

}
