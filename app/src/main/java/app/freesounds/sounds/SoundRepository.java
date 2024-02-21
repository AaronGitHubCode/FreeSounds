package app.freesounds.sounds;

import app.freesounds.annotations.Singleton;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public final class SoundRepository {
    @Singleton private static SoundRepository repository;

    private List<Sound> soundList;

    static {
        repository = new SoundRepository();
    }

    private SoundRepository() {
        soundList = new ArrayList<>();
    }

    public Sound getSound(final int index) {
        return soundList.get(index);
    }

    public void addSound(final Sound... sound) {
        soundList.addAll(Arrays.asList(sound));
    }

    public void removeSound(final Sound sound) {
        soundList.removeAll(Arrays.asList(sound));
    }

    public int getSize() {
        return soundList.size();
    }

    public static SoundRepository getInstance() {
        return repository;
    }
}
