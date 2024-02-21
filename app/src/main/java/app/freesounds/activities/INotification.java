package app.freesounds.activities;

import androidx.annotation.NonNull;

interface INotification {
    void onNotify(@NonNull String title, @NonNull String content, @NonNull int smallIcon, @NonNull int largeIcon);
}
