package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class selection extends OpMode {
    @Override
    public void init(){

    }
    @Override
    public void loop(){
        boolean aButton = gamepad1.a;
        boolean bButton = gamepad1.b;
        boolean xButton = gamepad1.x;
        if(aButton && bButton){
            telemetry.addData("A+B buttons", "Pressed!");
        }
        else if (aButton){
            telemetry.addData("A button", "Pressed!");
        }
        else if (bButton){
            telemetry.addData("B button", "Pressed!");
        }
        else{
            telemetry.addData("No button", "Pressed :(");
        }
    }
}
/*
every conditional operator / combination
== - equal to
!= - not equal to
< - less than
> - greater than
<= - less than or equal to
>= - greater than or equal to
&& - AND
|| - OR
! - NOT
^ - XOR (exclusive or)
 */