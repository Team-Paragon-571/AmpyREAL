
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
    private static Conveyor conveyor;

    private String name = "Index";
    private WPI_TalonFX conveyorMotor;
    private DigitalInput frontInput;
    private DigitalInput backInput;

    public Conveyor() {
        conveyorMotor = new WPI_TalonFX(Constants.MotorConstants.kLeftIndexMotor);
        addChild("IndexMotor", conveyorMotor);
        conveyorMotor.setInverted(false);
        conveyorMotor.setNeutralMode(NeutralMode.Brake);

        frontInput = new DigitalInput(Constants.DigitalConstants.DIGITAL_FRONT_INPUT);
        backInput = new DigitalInput(Constants.DigitalConstants.DIGITAL_BACK_INPUT);
    }

    public static synchronized Conveyor getInstance() {
        if (conveyor == null) {
            conveyor = new Conveyor();
        }
        return conveyor;
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }

    public void outputTelemetry() {
        SmartDashboard.putBoolean(getName() + "/Front Input", frontInput.get());
        SmartDashboard.putBoolean(getName() + "/Back Input", backInput.get());

    }

    @Override
    public String getName() {
        return name;
    }

    public void stop() {
        conveyorMotor.stopMotor();

    }

    public boolean getFrontInput() {
        return !frontInput.get();
    }

    public boolean getBackInput() {
        return !backInput.get();
    }

    public void turn() {
        conveyorMotor.set(0.5);
    }

    public void reverse() {
        conveyorMotor.set(-0.5);
    }
}