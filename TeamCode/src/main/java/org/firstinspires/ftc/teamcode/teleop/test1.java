package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.TankDrive;

@TeleOp(name = "teleOpTest1")
public class test1 extends OpMode
{
    TankDrive drive = new TankDrive();

    DcMotor motor2 = null;
    Servo servo;

    @Override
    public void init()
    {
        drive.init(hardwareMap);
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        servo = hardwareMap.get(Servo.class, "servo1");
    }

    @Override
    public void loop()
    {
        motorDriveHandler();
        motorControllingByButtonHandler();
        servoControllerByDpadHandler();
    }
    private void motorDriveHandler()
    {
        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x; // Reduce the turning speed

        drive.setMotorDrivePowers(forward + turn, forward - turn);
    }
    private void motorControllingByButtonHandler()
    {
        if (gamepad1.y)
        {
            motor2.setPower(1);
        }
        else if (gamepad1.a)
        {
            motor2.setPower(-1);
        }
        else if (gamepad1.x || gamepad1.b)
        {
            motor2.setPower(0);
        }
    }
    private void servoControllerByDpadHandler()
    {
        if (gamepad1.dpad_right)
        {
            servo.setPosition(1);
        }
        else if (gamepad1.dpad_up || gamepad1.dpad_down)
        {
            servo.setPosition(0.5);
        }
        else if (gamepad1.dpad_left)
        {
            servo.setPosition(0);
        }
    }
}