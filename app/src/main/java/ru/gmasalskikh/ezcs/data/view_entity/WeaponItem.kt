package ru.gmasalskikh.ezcs.data.view_entity

import android.graphics.Bitmap
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.data.app_entity.AppEntity
import ru.gmasalskikh.ezcs.data.type.TeamType

class WeaponItem(
    val logoDeferred: Deferred<Bitmap>,
    val teamTypes: List<TeamType>,
    val sprayDeferred: Deferred<Bitmap>,
    val recoilDeferred: Deferred<Bitmap>,
    val listDetails: MutableList<Pair<String, String>>
) : AppEntity