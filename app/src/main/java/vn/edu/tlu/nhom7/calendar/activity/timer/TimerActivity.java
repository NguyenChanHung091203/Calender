package vn.edu.tlu.nhom7.calendar.activity.timer;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import vn.edu.tlu.nhom7.calendar.R;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {
    private static final long DEFAULT_TIME_IN_MILLIS = 60000; // 60 giây

    private TextView tvTimer;
    private Button btnSetTime, btnStartReset;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private long timeLeftInMillis = DEFAULT_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTimer = findViewById(R.id.tv_timer);
        btnSetTime = findViewById(R.id.btn_set_time);
        btnStartReset = findViewById(R.id.btn_start_reset);

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog();
            }
        });

        btnStartReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    resetTimer();
                } else {
                    startTimer();
                }
            }
        });

        updateTimerUI();
    }

    private void openTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeLeftInMillis = (hourOfDay * 3600 + minute * 60) * 1000;
                        updateTimerUI();
                    }
                },
                0, 1, true // Giờ mặc định là 00:01 (1 phút)
        );
        timePickerDialog.show();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerUI();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                btnStartReset.setText("Đặt lại");
            }
        }.start();

        isRunning = true;
        btnStartReset.setText("Dừng");
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timeLeftInMillis = DEFAULT_TIME_IN_MILLIS;
        updateTimerUI();
        isRunning = false;
        btnStartReset.setText("Bắt đầu");
    }

    private void updateTimerUI() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        tvTimer.setText(timeFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
