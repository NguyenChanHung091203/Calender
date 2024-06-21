package vn.edu.tlu.nhom7.calendar.activity.task;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import vn.edu.tlu.nhom7.calendar.R;
import vn.edu.tlu.nhom7.calendar.model.Task;

public class ShowTaskActivity extends AppCompatActivity {
    private TextView show_taskName, show_startTime, show_endTime, show_date, show_alarmTime,
            show_taskDescription, show_location;
    private ImageView show_imageColor;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_task);

        initUi();
        getTask();

    }

    public void initUi() {
        show_taskName = findViewById(R.id.show_taskName);
        show_startTime = findViewById(R.id.show_startTime);
        show_endTime = findViewById(R.id.show_endTime);
        show_date = findViewById(R.id.show_date);
        show_alarmTime = findViewById(R.id.show_alarmTime);
        show_taskDescription = findViewById(R.id.show_taskDescription);
        show_location = findViewById(R.id.show_location);
        show_imageColor = findViewById(R.id.show_imageColor);
    }

    public void getTask() {
        Task task = (Task) getIntent().getExtras().get("object_task");
        if (task != null) {
            id = task.getId();
            show_taskName.setText(task.getTaskName());
            show_startTime.setText(task.getStartTime());
            show_endTime.setText(task.getEndTime());
            show_date.setText(task.getDate());
            show_alarmTime.setText(task.getAlarmTime());
            show_taskDescription.setText(task.getTaskDescription());
            if (task.getLocation().isEmpty()) {
                show_location.setText("...");
            } else {
                show_location.setText(task.getLocation());
            }

            String[] colors = {"Xanh dương", "Xanh lục", "Vàng", "Đỏ"};
            int[] colorDrawables = {
                    R.drawable.ic_task_cl_blue,
                    R.drawable.ic_task_cl_green,
                    R.drawable.ic_task_cl_yellow,
                    R.drawable.ic_task_cl_red
            };
            for (int i = 0; i < colors.length; i++) {
                if (colors[i].equals(task.getColor())) {
                    show_imageColor.setImageResource(colorDrawables[i]);
                    break;
                }
            }
        }
    }
}