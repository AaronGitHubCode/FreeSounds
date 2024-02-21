package app.freesounds.sounds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class SoundViewModel : ViewModel() {
    private var mutableSoundData: MutableLiveData<MutableList<Sound>> = MutableLiveData(
        mutableListOf()
    )

    val soundData: LiveData<MutableList<Sound>> = mutableSoundData

    val size = soundData.value!!.size

    private fun updateData(): Unit {
        val mutableSoundData = mutableSoundData
        this.mutableSoundData = mutableSoundData
    }
}