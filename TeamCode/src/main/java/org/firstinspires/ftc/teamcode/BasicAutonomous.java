package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
@Disabled
public class BasicAutonomous extends OpMode {
    DcMotor FR; //port 0
    DcMotor BR; //port 1
    DcMotor FL; //port 2
    DcMotor BL; //port 3
    DcMotor intake; //EH port 0
    DcMotor flywheel; //EH port 1
    private ElapsedTime timer = new ElapsedTime();
    @Override
    public void init()
    {
        FR = hardwareMap.get(DcMotor.class, "FR");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BL = hardwareMap.get(DcMotor.class, "BL");
        intake = hardwareMap.get(DcMotor.class, "intake");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
    }
    public void start()
    {
        timer.reset();
    }
    public void loop()
    {

        double y = 0; //forward/back speed
        double x = 0; //strafe speed
        double rx = 0; //rotational speed
        FL.setPower(y - x - rx);
        BL.setPower(y + x - rx);
        FR.setPower(y + x + rx);
        BR.setPower(y - x + rx);
        if(timer.seconds() > 25.0 && timer.seconds() < 26.0)
        {
            y = 1;
        }
        else
        {
            y = 0;
        }
    }
}
