package org.firstinspires.ftc.teamcode.drive;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Gyro;

public class TankDrive
{
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public void init(@NonNull HardwareMap hardwareMap)
    {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setMotorDrivePowers(double leftPower, double rightPower)
    {
        // Ensure motor values are within [-1, 1]
        double max = 1.0f;
        max = Math.max(max, Math.abs(leftPower));
        max = Math.max(max, Math.abs(rightPower));

        leftMotor.setPower(leftPower / max);
        rightMotor.setPower(rightPower / max);
    }
}
