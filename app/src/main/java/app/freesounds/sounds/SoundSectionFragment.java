package app.freesounds.sounds;

import app.freesounds.R;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Supplier;

public final class SoundSectionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sound_section_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final var context = view.getContext();
        final var recyclerView = (RecyclerView) view.findViewById(R.id.sound_recycler_view);
        final var fragmentManager = getParentFragmentManager();

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(((Supplier<SoundAdapter>) () -> {
            final var soundAdapter = new SoundAdapter();

            soundAdapter.setOnSoundSelected(sound -> {
                final var fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.fragment_container, new SoundDetailFragment(sound), "sound_detail_fragment");
                fragmentTransaction.addToBackStack("sound_detail_fragment");

                fragmentTransaction.commit();
            });

            return soundAdapter;
        }).get());
    }
}
