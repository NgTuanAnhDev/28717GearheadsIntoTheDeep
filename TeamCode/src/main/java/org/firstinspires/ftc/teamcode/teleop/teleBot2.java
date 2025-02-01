package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.TankDrive;

import java.util.Objects;

@TeleOp(name = "teleBot2")
public class teleBot2 extends OpMode
{
    DcMotor horizontalSlideMotor;
    DcMotor verticalSlideMotor;

    Servo verticalArmServo;
    Servo horizontalArmServo;

    Servo horizontalClawServo;
    Servo verticalClawServo;

    int verticalSlidePosition;
    int horizontalSlidePosition;

    int verticalArmPositionIndex = 0;

    final double verticalMotorSpeed = 1.0;
    final double horizontalMotorSpeed = 1.0;

    final int verticalSlideUpperLimit = 3000;
    final int verticalSlideLowerLimit = 100;
    final int horizontalSlideUpperLimit = 600;
    final int horizontalSlideLowerLimit = 50;

    boolean horizontalClawToggle = false;
    boolean verticalClawToggle = false;

    boolean aButtonPressed = false;
    boolean bButtonPressed = false;

    double[] verticalArmPositions = {1, 0.85, 0};
    double horizontalArmPosition = 1;

    TankDrive drive = new TankDrive();
    String drivingJoystick;

    @Override
    public void init()
    {
        horizontalSlideMotor = hardwareMap.dcMotor.get("horizontalSlide");
        verticalSlideMotor = hardwareMap.dcMotor.get("verticalSlide");

        horizontalSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        verticalArmServo = hardwareMap.servo.get("verticalArm");
        horizontalArmServo = hardwareMap.servo.get("horizontalArm");

        horizontalClawServo = hardwareMap.servo.get("horizontalClaw");
        verticalClawServo = hardwareMap.servo.get("verticalClaw");

        drive.init(hardwareMap);
    }
    @Override
    public void loop()
    {
        slidesHandler();
        clawsHandler();
        armHandler();
        driveHandler();
        telemtryHandler();
    }
    private void telemtryHandler()
    {
        telemetry.addData("Vertical slide position: ", verticalSlidePosition);
        telemetry.addData("Horizontal slide position: ", horizontalSlidePosition);
        telemetry.addData("Vertical arm position: ", verticalArmPositions[verticalArmPositionIndex]);
        telemetry.addData("Horizontal arm position: ", horizontalArmPosition);
        telemetry.addData("Vertical claw position: ", verticalClawServo.getPosition());
        telemetry.addData("Horizontal claw position: ", horizontalClawServo.getPosition());
        telemetry.addData("Driving joystick: ", drivingJoystick);
        telemetry.update();
    }
    private void driveHandler()
    {
        switchHandler();

        double forward = 0;
        double turn = 0;
        if (Objects.equals(drivingJoystick, "left"))
        {
            forward = gamepad1.left_stick_y;
            turn = gamepad1.left_stick_x * 0.5f;
        }
        else if (Objects.equals(drivingJoystick, "right"))
        {
            forward = gamepad1.right_stick_y;
            turn = gamepad1.right_stick_x * 0.5f;
        }
        drive.setMotorDrivePowers(forward - turn, forward + turn);
    }
    private void switchHandler()
    {
        if (gamepad1.left_stick_button)
        {
            drivingJoystick = "left";
        }
        else if (gamepad1.right_stick_button)
        {
            drivingJoystick = "right";
        }
    }
    private void armHandler()
    {
        verticalArmHandler();
        horizontalArmHandler();
    }
    private void verticalArmHandler()
    {
        if (gamepad1.a && !aButtonPressed) {
            if (verticalArmPositionIndex > 0) {
                verticalArmPositionIndex--; // Increase the value
            }
            aButtonPressed = true; // Set the flag to indicate button A is pressed
        } else if (!gamepad1.a) {
            aButtonPressed = false; // Reset the flag when the button is released
        }

        // Check if button B is pressed to decrease the value
        if (gamepad1.b && !bButtonPressed) {
            if (verticalArmPositionIndex < 2) {
                verticalArmPositionIndex++; // Decrease the value
            }
            bButtonPressed = true; // Set the flag to indicate button B is pressed
        } else if (!gamepad1.b) {
            bButtonPressed = false; // Reset the flag when the button is released
        }

        verticalArmServo.setPosition(verticalArmPositions[verticalArmPositionIndex]);
    }
    private void horizontalArmHandler()
    {
        if (gamepad1.dpad_down)
        {
            horizontalArmPosition = 0;
        }
        else if (gamepad1.dpad_up)
        {
            horizontalArmPosition = 1;
        }
        else if (gamepad1.dpad_left || gamepad1.dpad_right)
        {
            horizontalArmPosition = 0.2;
        }
        horizontalArmServo.setPosition(horizontalArmPosition);
    }
    private void clawsHandler()
    {
        horizontalClawHandler();
        verticalClawHandler();
    }
    private void horizontalClawHandler()
    {
        if (gamepad1.x && !horizontalClawToggle)
        {
            if (horizontalClawServo.getPosition() == 0)
                horizontalClawServo.setPosition(0.75);
            else horizontalClawServo.setPosition(0);
            horizontalClawToggle = true;
        }
        else if (!gamepad1.x)
        {
            horizontalClawToggle = false;
        }
    }
    private void verticalClawHandler()
    {
        if (gamepad1.y && !verticalClawToggle)
        {
            if (verticalClawServo.getPosition() == 0)
                verticalClawServo.setPosition(0.75);
            else verticalClawServo.setPosition(0);
            verticalClawToggle = true;
        }
        else if (!gamepad1.y)
        {
            verticalClawToggle = false;
        }
    }
    private void slidesHandler()
    {
        verticalSlideHandler();
        horizontalSlideHandler();
    }
    private void verticalSlideHandler()
    {
        final double triggerThreshold = 0.1;

        verticalSlidePosition = -verticalSlideMotor.getCurrentPosition();

        double power = 0;

        if (gamepad1.left_bumper && gamepad1.left_trigger <= triggerThreshold)
        {
            power = -verticalMotorSpeed;
        }
        else if (gamepad1.left_trigger > triggerThreshold && !gamepad1.left_bumper)
        {
            power = verticalMotorSpeed;
        }
        verticalSlideMotor.setPower(power);
    }
    private void horizontalSlideHandler()
    {
        final double triggerThreshold = 0.1;

        horizontalSlidePosition = -horizontalSlideMotor.getCurrentPosition();

        double power = 0;

        if (gamepad1.right_bumper && gamepad1.right_trigger <= triggerThreshold)
        {
            if (horizontalSlidePosition > horizontalSlideLowerLimit)
            {
                power = horizontalMotorSpeed;
            }
        }
        else if (gamepad1.right_trigger > triggerThreshold && !gamepad1.right_bumper)
        {
            if (horizontalSlidePosition < horizontalSlideUpperLimit)
            {
                power = -horizontalMotorSpeed;
            }
        }
        horizontalSlideMotor.setPower(power);
    }
}