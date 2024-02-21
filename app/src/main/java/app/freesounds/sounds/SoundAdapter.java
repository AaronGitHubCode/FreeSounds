package app.freesounds.sounds;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import app.freesounds.R;

import android.widget.TextView;
import android.widget.ImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class SoundAdapter extends Adapter<SoundViewHolder> {
    private SoundRepository repository;

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
        return null;
    }

    @Override
    public int getItemCount() {
        return repository.getSize();
    }

    @Override
    public void onBindViewHolder(@NonNull SoundViewHolder holder, int position) {
        final var view = holder.getView();

        view.setOnClickListener(v -> {
            onSoundSelected.onSelect(repository.getSound(position));
        });
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
