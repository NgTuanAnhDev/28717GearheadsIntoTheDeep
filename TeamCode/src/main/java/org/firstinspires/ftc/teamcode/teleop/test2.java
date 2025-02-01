package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "teleOpTest2")
public class test2 extends LinearOpMode
{
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    double servo1Position = 0.5;
    double servo2Position = 0.5;
    final double DEGREE_TO_POSITION = 1.0;

    boolean aPressed = false;
    boolean bPressed = false;
    boolean xPressed = false;
    boolean yPressed = false;
    boolean dpadUpPressed = false;
    boolean dpadDownPressed = false;
    @Override
    public void runOpMode() {
        // Initialize motors and servos
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");

        // Set motor directions if necessary
        rightMotor.setDirection(DcMotor.Direction.REVERSE); // Reverse the right motor if needed

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Control motors with left and right joysticks
            leftMotor.setPower(gamepad1.left_stick_y); // Left joystick controls left motor
            rightMotor.setPower(gamepad1.right_stick_y); // Right joystick controls right motor

            // Control servos with button

            // Servo 1: Angular
            if (gamepad1.x && !xPressed) // Check if x is pressed and was not pressed before
            {
                servo1Position += DEGREE_TO_POSITION; // Move 10 degrees
                servo1Position = Math.min(1.0, servo1Position); // Clamp to max position
                servo1.setPosition(servo1Position);
                xPressed = true; // Update the button state
            }
            else if (!gamepad1.x) // Reset the button state when released
            {
                xPressed = false;
            }

            if (gamepad1.y && !yPressed) // Check if y is pressed and was not pressed before
            {
                servo1Position -= DEGREE_TO_POSITION; // Move -10 degrees
                servo1Position = Math.max(0.0, servo1Position); // Clamp to min position
                servo1.setPosition(servo1Position);
                yPressed = true; // Update the button state
            }
            else if (!gamepad1.y) // Reset the button state when released
            {
                yPressed = false;
            }

            // Servo 2: Angular
            if (gamepad1.a && !aPressed) // Check if a is pressed and was not pressed before
            {
                servo2Position += DEGREE_TO_POSITION; // Move 10 degrees
                servo2Position = Math.min(1.0, servo2Position); // Clamp to max position
                servo2.setPosition(servo2Position);
                aPressed = true; // Update the button state
            }
            else if (!gamepad1.a) // Reset the button state when released
            {
                aPressed = false;
            }
            if (gamepad1.b && !bPressed) // Check if y is pressed and was not pressed before
            {
                servo2Position -= DEGREE_TO_POSITION; // Move -10 degrees
                servo2Position = Math.max(0.0, servo2Position); // Clamp to min position
                servo2.setPosition(servo2Position);
                bPressed = true; // Update the button state
            }
            else if (!gamepad1.b) // Reset the button state when released
            {
                bPressed = false;
            }
            // Servo 3: Continuous
            if (gamepad1.dpad_up)
            {
                servo3.setPosition(1.0);
            }
            else if (gamepad1.dpad_down)
            {
                servo3.setPosition(0.0);
            }
            else
            {
                servo3.setPosition(0.5);
            }
            // Add telemetry for debugging
            telemetry.addData("Left Motor Power", leftMotor.getPower());
            telemetry.addData("Right Motor Power", rightMotor.getPower());
            telemetry.addData("Servo 1 Position", servo1.getPosition());
            telemetry.addData("Servo 2 Position", servo2.getPosition());
            telemetry.addData("Servo 3 Position", servo3.getPosition());
            telemetry.update();
        }
    }

}

