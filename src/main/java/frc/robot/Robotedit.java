/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DMC60;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.cscore.UsbCamera;
// import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.networktables.NetworkTableInstance;




/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robotedit extends TimedRobot {
  // public static Servo miniMotor = new Servo(0);
  public static Servo cameraOneServo = new Servo(3);
  public static Servo cameraTwoServo = new Servo(4);
  public static DMC60 intake = new DMC60(2);
  public static Spark rightSide = new Spark(0); 
  public static Spark leftSide = new Spark(1); 
  public static Solenoid armSolenoidIn = new Solenoid(0,2);
  public static Solenoid armSolenoidOut = new Solenoid(0,4);
  public static Solenoid tailSolenoidIn = new Solenoid(0,0);
  public static Solenoid tailSolenoidOut = new Solenoid(0,3);
  public static Solenoid hatchSolenoidIn = new Solenoid(0,5);
  public static Solenoid hatchSolenoidOut = new Solenoid(0,6);
  public static boolean cam_1;

  public UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
  public UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);

  DifferentialDrive robot = new DifferentialDrive(leftSide, rightSide); 

  Joystick control = new Joystick(0);
  double y;
  double x;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    camera2.setResolution(320, 240);
    camera2.setFPS(15);
  
    camera.setResolution(320, 240);
    camera.setFPS(15);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    y = control.getRawAxis(5) * control.getRawAxis(3) * 0.5;
    x = control.getRawAxis(4) * control.getRawAxis(3) * 0.5;
    robot.arcadeDrive(y, x);

    if (control.getRawButton(5)) {
      intake.set(-1);
    } else if (control.getRawButton(6)) {
      intake.set(1);
    } else {
      intake.set(0);
    }
    
    if (control.getRawButton(4)) {
      armSolenoidIn.set(true);
      armSolenoidOut.set(false);
    }
    if (control.getRawButton(1)) {
      armSolenoidOut.set(true);
      armSolenoidIn.set(false);
    } 
    if (control.getRawButton(3)) {
      tailSolenoidIn.set(true);
      tailSolenoidOut.set(false);
    }
    if (control.getRawButton(2)) {
      tailSolenoidOut.set(true);
      tailSolenoidIn.set(false);
    } 
    if (control.getRawButton(7)) {
      hatchSolenoidIn.set(true);
      hatchSolenoidOut.set(false);
    } else {
      hatchSolenoidOut.set(true);
      hatchSolenoidIn.set(false);
    } 
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    y = control.getRawAxis(5) * control.getRawAxis(3);
    x = control.getRawAxis(4) * control.getRawAxis(3);
    robot.arcadeDrive(y, x);

    if (control.getRawButton(5)) {
      intake.set(-1);
    } else if (control.getRawButton(6)) {
      intake.set(1);
    } else {
      intake.set(0);
    }
    
    if (control.getRawButton(4)) {
      armSolenoidIn.set(true);
      armSolenoidOut.set(false);
    }
    if (control.getRawButton(1)) {
      armSolenoidOut.set(true);
      armSolenoidIn.set(false);
    } 
    if (control.getRawButton(2)) {
      tailSolenoidIn.set(true);
      tailSolenoidOut.set(false);
    }
    if (control.getRawButton(3)) {
      tailSolenoidOut.set(true);
      tailSolenoidIn.set(false);
    } 
    if (control.getRawButton(7)) {
      hatchSolenoidIn.set(true);
      hatchSolenoidOut.set(false);
    } else {
      hatchSolenoidOut.set(true);
      hatchSolenoidIn.set(false);
    } 
    // miniMotor.set(control.getRawAxis(2));
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
