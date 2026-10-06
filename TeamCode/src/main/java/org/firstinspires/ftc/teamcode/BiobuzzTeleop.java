package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class BiobuzzTeleop extends OpMode {
    DcMotor FR; //port 0
    DcMotor BR; //port 1
    DcMotor FL; //port 2
    DcMotor BL; //port 3
    DcMotor intake; //EH port 0
    @Override
    public void init()
    {
        FR = hardwareMap.get(DcMotor.class, "FR");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BL = hardwareMap.get(DcMotor.class, "BL");
        intake = hardwareMap.get(DcMotor.class, "intake");
    }

    public void loop()
    {
        double x = -gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        FL.setPower(y - x - rx);
        BL.setPower(y + x - rx);
        FR.setPower(y + x + rx);
        BR.setPower(y - x + rx);
        intake.setPower(-0.5);
    }
}
