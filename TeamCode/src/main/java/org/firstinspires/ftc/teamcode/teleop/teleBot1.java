package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.TankDrive;

@TeleOp(name = "TeleBot1")
public class teleBot1 extends OpMode
{
    TankDrive drive = new TankDrive();

    // Motors
    DcMotor vertMotor;
    DcMotor horiMotor;

    // Servos
    Servo armServo;
    Servo vertServo;
    Servo horiServo;
    CRServo hori2Servo;

    double armPosition = 0.5;
    double horiServoPosition = 0.5;
    double vertServoPosition = 0.5;
    final double DEGREE_TO_POSITION = 1.0;

    boolean aPressed = false;
    boolean bPressed = false;
    boolean xPressed = false;
    boolean yPressed = false;

    @Override
    public void init()
    {
        // Motors
        vertMotor = hardwareMap.dcMotor.get("vertMotor");
        horiMotor = hardwareMap.dcMotor.get("horiMotor");

        // Motor modes
        vertMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Servos
        armServo = hardwareMap.servo.get("armServo");
        vertServo = hardwareMap.servo.get("vertServo");
        horiServo = hardwareMap.servo.get("horiServo");
        hori2Servo = hardwareMap.crservo.get("secondHoriServo");

        // Servo modes

        // Drive
        drive.init(hardwareMap);
    }

    @Override
    public void loop()
    {
        motorHandler();
        motorDriveHandler();
        angServoHandler();
        contServoHandler();
    }
    private void angServoHandler()
    {
        // Servo 1: Angular
        if (gamepad1.dpad_up)
        {
            armPosition = 1;
        }
        else if (gamepad1.dpad_down)
        {
            armPosition = 0;
        }
        armServo.setPosition(armPosition);

        // Servo 2: Angular
        if (gamepad1.a)
        {
            horiServoPosition = 1;
        }
        else if (gamepad1.b)
        {
            horiServoPosition = 0;
        }
        horiServo.setPosition(horiServoPosition);

        // Servo 3: Angular
        if (gamepad1.x)
        {
            vertServoPosition = 1;
        }
        else if (gamepad1.y)
        {
            vertServoPosition = 0;
        }
        vertServo.setPosition(vertServoPosition);
    }
    private void contServoHandler()
    {
        if (gamepad1.dpad_left)
        {
            hori2Servo.setPower(0.2);
        }
        else if (gamepad1.dpad_right)
        {
            hori2Servo.setPower(-0.2);
        }
        else
        {
            hori2Servo.setPower(0);
        }
    }
    private void motorHandler()
    {
        horiMotor.setPower(gamepad1.left_stick_x*0.7);
        vertMotor.setPower(gamepad1.right_stick_x);
    }
    private void motorDriveHandler()
    {
        double forward = gamepad2.left_stick_y * 0.5f;
        double turn = gamepad2.left_stick_x * 0.75f;

        drive.setMotorDrivePowers(forward - turn, forward + turn);
    }
}