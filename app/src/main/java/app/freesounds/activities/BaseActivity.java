package app.freesounds.activities;

import android.app.NotificationManager;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

abstract class BaseActivity extends AppCompatActivity
implements INotification {
    private NotificationManager notificationManager;

    BaseActivity() {
        super();
    }

    BaseActivity(int layout) {
        super(layout);
    }

    NotificationManager getNotificationManager() {
        return notificationManager;
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
