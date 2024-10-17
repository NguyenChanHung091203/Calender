package vn.edu.tlu.nhom7.calendar.activity.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;
import vn.edu.tlu.nhom7.calendar.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Phát âm thanh báo thức
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
        mediaPlayer.start();

        // Giải phóng tài nguyên khi âm thanh đã phát xong
        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release(); // Giải phóng tài nguyên MediaPlayer
        });

        Toast.makeText(context, "Báo thức kêu!", Toast.LENGTH_SHORT).show();
    }
}
