// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.*;

// Evan added some imports
import java.util.function.BooleanSupplier;
import frc.robot.subsystems.IntakeRollers;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

  private static RobotContainer robotContainer = new RobotContainer();
   private Conveyor conveyor;
   //private IntakeRollers intakeRollers;
  

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  // The robot's subsystems
  public final Drive drive = new Drive();
  public final boolean isPrecisionOn = false;
  public final PrecisionToggle precision = new PrecisionToggle(drive, isPrecisionOn);

  // Joysticks
  private final CommandXboxController operatorController = new CommandXboxController(1);
  private final CommandXboxController driveController = new CommandXboxController(0);

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  // A chooser for autonomous commands
  SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private RobotContainer() {

    initializeSubsystems();
  
  }

  private void initializeSubsystems() {
    conveyor = Conveyor.getInstance();
    //intakeRollers = IntakeRollers.getInstance();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems

    initializePneumatics();

    SmartDashboard.putData(IntakeArms.getInstance());

    SmartDashboard.putData(drive);

    // SmartDashboard Buttons
    SmartDashboard.putData("AutonomousCommand", new AutonomousCommand());
    SmartDashboard.putData("DriveCommand",
        new DriveCommand(() -> DriveConstants.DRIVE_FULL_SPEED, () -> DriveConstants.TURN_RADIUS));
    SmartDashboard.putData("RaiseIntakeCommand", new RaiseIntakeCommand());
    SmartDashboard.putData("LowerIntakeCommand", new LowerIntakeCommand());
    SmartDashboard.putData("ForwardConveyorCommand", new ForwardConveyorCommand());
    SmartDashboard.putData("ReverseConveyorCommand", new ReverseConveyorCommand());
    // SmartDashboard.putData("ForwardIntakeRollersCommand", new ForwardIntakeRollersCommand());
    // SmartDashboard.putData("ReverseIntakeRollersCommand", new ReverseIntakeRollersCommand());
    //SmartDashboard.putData("IntakeCommand", new IntakeCommand(intakeRollers));
    SmartDashboard.putData("PrecisionToggle", new PrecisionToggle(drive, isPrecisionOn));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    drive.setDefaultCommand(new DriveCommand(() -> DriveConstants.DRIVE_FULL_SPEED, () -> DriveConstants.TURN_RADIUS));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", chooser);
  }

  // Used to start compressor
  private void initializePneumatics() {
    try (Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM)) {
      pcmCompressor.enableDigital();
      System.out.println("Compressor Initialized? " + pcmCompressor.isEnabled());
    }
  }

  public static RobotContainer getInstance() {
    return robotContainer;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS

    // Create some buttons
   operatorController.povUp().onTrue(new IntakeCommand());
   operatorController.povDown().onTrue(new UntakeCommand());
    // operatorController.rightBumper().whileTrue(new ForwardIntakeRollersCommand());
    // operatorController.leftBumper().whileTrue(new ReverseIntakeRollersCommand());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
  public CommandXboxController getDriveController() {
    return driveController;
  }

  public CommandXboxController getoperatorController() {
    return operatorController;
  }

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return chooser.getSelected();
  }

}
