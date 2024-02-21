package app.freesounds.sounds;

import app.freesounds.sounds.Sound;

import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface SoundService {
    String API_TOKEN = "mFLvJ7f8PKEOoMtouW0KuUhZP9nPFQ3vbbpmMGlR";

    @GET("sounds/?token=" + API_TOKEN)
    List<Sound> getSounds();


}
