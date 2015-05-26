package com.example.gul.tweening;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
    private ViewFlipper viewFlipper;
    private static int MCQnum;
    private float lastX;
    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MCQnum=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        text1=(TextView)findViewById(R.id.textView);
        text2=(TextView)findViewById(R.id.textView2);

    }

    // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent touchevent) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(MCQnum);

        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                // Handling left to right screen swap.
                if ((lastX+200) < currentX) {
                    if(MCQnum==0)//so that after toggling down to first MCQ user can't move back more
                        break;
                    MCQnum--;

                    String strI = String.valueOf(MCQnum);
                    text2.setText("MCQ 2");

                    // Next screen comes in from left.

                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);

                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Display next screen.
                    viewFlipper.showNext();
                }

                // Handling right to left screen swap.
                if ((lastX-200) > currentX) {
                      MCQnum++;


                    text1.setText("MCQ 1");

                    // If there is a child (to the left),
                    //if (viewFlipper.getDisplayedChild() == 1)
                      //  break;

                    // Next screen comes in from right.

                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);

                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Display previous screen.
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}
