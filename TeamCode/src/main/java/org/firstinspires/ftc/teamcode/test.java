package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//@Disabled
//uncomment line 7 to make the program not show up on the DS.
@TeleOp
public class test extends OpMode {

    @Override
    public void init()
    {
        telemetry.addData("Hello", "World");
    }

    public void loop()
    {

    }
    //single line comment

    /*
    block
     comment
     */
}
