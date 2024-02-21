package app.freesounds.sounds;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import app.freesounds.R;

import android.widget.ImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.net.Uri;

import androidx.annotation.NonNull;

public class SoundAdapter extends Adapter<SoundViewHolder> {
    private final SoundRepository repository;

    @FunctionalInterface
    interface OnSoundSelected {
        void onSelect(Sound sound);
    }

    private OnSoundSelected onSoundSelected;

    public SoundAdapter() {
        repository = SoundRepository.getInstance();
    }

    public void setOnSoundSelected(OnSoundSelected onSoundSelected) {
        this.onSoundSelected = onSoundSelected;
    }

    @NonNull
    @Override
    public SoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoundViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_section_row_layout, parent, false));
    }

    @Override
    public int getItemCount() {
        return repository.getSize();
    }

    @Override
    public void onBindViewHolder(@NonNull SoundViewHolder holder, int position) {
        final var soundIcon = (ImageView) holder.getView().findViewById(R.id.sound_icon);

        soundIcon.setImageURI(Uri.parse(""));
        soundIcon.setOnClickListener(v -> onSoundSelected.onSelect(repository.getSound(position)));
    }
}

class SoundViewHolder extends ViewHolder {
    private View view;

    public SoundViewHolder(View view) {
        super(view);
    }

    public View getView() {
        return view;
    }
}
