package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp

public class idk extends OpMode {
    private DcMotor wheel = null;


    @Override
    public void init()
    {
        wheel  = hardwareMap.get(DcMotor.class, "wheel");
        telemetry.addData("Hello", "World");
    }
    @Override
    public void loop()
    {
        telemetry.addData("the program is running", "now");
        wheel.setPower(0.5);
    }

}
