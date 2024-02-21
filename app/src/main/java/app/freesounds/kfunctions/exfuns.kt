package app.freesounds.kfunctions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.commit(transaction: FragmentTransaction.() -> Unit) {
    val mTransaction = beginTransaction()
    mTransaction.commit()
}