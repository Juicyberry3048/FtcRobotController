package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp
public class TeamMemberPractice extends OpMode {
    boolean initDone;

    @Override
    public void init()
    {
        telemetry.addData("Init", initDone);
        initDone = true;
    }
    //first double is return data type, second is input type
    //public means that all classes can see the method, private means that none can, and undefined means that only programs in the organization class can (org.firstinspires.ftc.teamcode)
    double squareInputWithSign(double input){
        double output = input * input;

        if (input < 0)
        {
            output *= -1;
        }
        return output;
    }

    @Override
    public void loop()
    {
        telemetry.addData("Init", initDone);

        double yAxis = gamepad1.left_stick_y;

        telemetry.addData("Left Stick Normal", yAxis);

        yAxis = squareInputWithSign(yAxis);
        telemetry.addData("Left Stick Modified", yAxis);

    }
}
