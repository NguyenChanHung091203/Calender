package vn.edu.tlu.nhom7.calendar.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
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
    private long selectedTimeInMillis = DEFAULT_TIME_IN_MILLIS; // Lưu thời gian đã chọn

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
        // Tạo một dialog tùy chỉnh
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_set_time);

        NumberPicker numberPickerHours = dialog.findViewById(R.id.number_picker_hours);
        NumberPicker numberPickerMinutes = dialog.findViewById(R.id.number_picker_minutes);
        NumberPicker numberPickerSeconds = dialog.findViewById(R.id.number_picker_seconds);

        // Thiết lập giá trị cho các NumberPicker
        numberPickerHours.setMinValue(0);
        numberPickerHours.setMaxValue(23);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(59);
        numberPickerSeconds.setMinValue(0);
        numberPickerSeconds.setMaxValue(59);

        // Thêm nút xác nhận trong dialog
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hours = numberPickerHours.getValue();
                int minutes = numberPickerMinutes.getValue();
                int seconds = numberPickerSeconds.getValue();
                // Cập nhật thời gian đã chọn
                selectedTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;
                timeLeftInMillis = selectedTimeInMillis; // Cập nhật timeLeftInMillis
                updateTimerUI();
                dialog.dismiss(); // Đóng dialog
            }
        });

        dialog.show(); // Hiện dialog
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
                Toast.makeText(TimerActivity.this, "Thời gian đã hết!", Toast.LENGTH_SHORT).show(); // Hiển thị thông báo Toast
                timeLeftInMillis = selectedTimeInMillis; // Đặt lại thời gian về lựa chọn cũ
                updateTimerUI(); // Cập nhật UI để hiển thị lại thời gian đã chọn
                btnSetTime.setEnabled(true); // Kích hoạt lại nút Đặt thời gian
            }
        }.start();

        isRunning = true;
        btnStartReset.setText("Dừng");
        btnSetTime.setEnabled(false); // Vô hiệu hóa nút Đặt thời gian
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timeLeftInMillis = selectedTimeInMillis; // Đặt lại thời gian về lựa chọn cũ
        updateTimerUI();
        isRunning = false;
        btnStartReset.setText("Bắt đầu");
        btnSetTime.setEnabled(true); // Kích hoạt lại nút Đặt thời gian
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
