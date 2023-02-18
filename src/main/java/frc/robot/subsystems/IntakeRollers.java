// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.Constants.IntakeRollersMotorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class IntakeRollers extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANSparkMax intakeRollerMotor;
    private static IntakeRollers intakeRollers;
    private SparkMaxPIDController pidController;
    private RelativeEncoder encoder;
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
    *
    */
    private IntakeRollers() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intakeRollerMotor = new CANSparkMax(IntakeRollersMotorConstants.CAN_ID, MotorType.kBrushless);
        intakeRollerMotor.restoreFactoryDefaults();
        pidController = intakeRollerMotor.getPIDController();
        encoder = intakeRollerMotor.getEncoder();
        initializePidController();
        initializeSmartDashboard();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    public static synchronized IntakeRollers getInstance() {
        if (null == intakeRollers) {
            intakeRollers = new IntakeRollers();
        }
        return intakeRollers;
    }

    private void initializePidController() {
        // PID coefficients
        kP = IntakeRollersMotorConstants.kP;
        kI = IntakeRollersMotorConstants.kI;
        kD = IntakeRollersMotorConstants.kD;
        kIz = IntakeRollersMotorConstants.kIz;
        kFF = IntakeRollersMotorConstants.kFF;
        kMaxOutput = IntakeRollersMotorConstants.MAX_POWER_OUTPUT;
        kMinOutput = IntakeRollersMotorConstants.MIN_POWER_OUTPUT;

        // set PID coefficients
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    private void initializeSmartDashboard() {
        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber("IntakeRollers/P Gain", kP);
        SmartDashboard.putNumber("IntakeRollers/I Gain", kI);
        SmartDashboard.putNumber("IntakeRollers/D Gain", kD);
        SmartDashboard.putNumber("IntakeRollers/I Zone", kIz);
        SmartDashboard.putNumber("IntakeRollers/Feed Forward", kFF);
        SmartDashboard.putNumber("IntakeRollers/Max Output", kMaxOutput);
        SmartDashboard.putNumber("IntakeRollers/Min Output", kMinOutput);
    }
    
    @Override
    public void periodic() {
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber("IntakeRollers/P Gain", 0);
        double i = SmartDashboard.getNumber("IntakeRollers/I Gain", 0);
        double d = SmartDashboard.getNumber("IntakeRollers/D Gain", 0);
        double iz = SmartDashboard.getNumber("IntakeRollers/I Zone", 0);
        double ff = SmartDashboard.getNumber("IntakeRollers/Feed Forward", 0);
        double max = SmartDashboard.getNumber("IntakeRollers/Max Output", 0);
        double min = SmartDashboard.getNumber("IntakeRollers/Min Output", 0);

        // if PID coefficients on SmartDashboard have changed, write new values to
        // controller
        if ((p != kP)) {
            pidController.setP(p);
            kP = p;
        }
        if ((i != kI)) {
            pidController.setI(i);
            kI = i;
        }
        if ((d != kD)) {
            pidController.setD(d);
            kD = d;
        }
        if ((iz != kIz)) {
            pidController.setIZone(iz);
            kIz = iz;
        }
        if ((ff != kFF)) {
            pidController.setFF(ff);
            kFF = ff;
        }
        if ((max != kMaxOutput) || (min != kMinOutput)) {
            pidController.setOutputRange(min, max);
            kMinOutput = min;
            kMaxOutput = max;
        }
    }

    public void setVelocity(double velocity) {
        if (velocity > IntakeRollersMotorConstants.MAX_RPM) {
            velocity = IntakeRollersMotorConstants.MAX_RPM;
        } else if (velocity < -IntakeRollersMotorConstants.MAX_RPM) {
            velocity = -IntakeRollersMotorConstants.MAX_RPM;
        }
        pidController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
        SmartDashboard.putNumber("IntakeRollers/Current Velocity", encoder.getVelocity());
    }

    public void stop() {
        intakeRollerMotor.stopMotor();
        SmartDashboard.putNumber("IntakeRollers/Current Velocity", 0.0);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }


}
