package app.freesounds.activities;

import android.app.DownloadManager;

public abstract class BaseActivityWithDownloadCapability extends BaseActivity
implements IDownload {
    private DownloadManager downloadManager;

    DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public BaseActivityWithDownloadCapability() {
        super();
    }

    public BaseActivityWithDownloadCapability(int layout) {
        super(layout);
    }


}
