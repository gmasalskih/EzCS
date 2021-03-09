package ru.gmasalskikh.ezcs.providers.service_provider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import ru.gmasalskikh.ezcs.data.app_entity.*
import ru.gmasalskikh.ezcs.data.firestore_entities.*
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import kotlin.coroutines.coroutineContext

class Mapper(
    private val cs: CoroutineScope,
    private val contentRepository: ContentRepository
) {
    val competitive: suspend (CompetitiveFirestoreEntity) -> Competitive = { firestoreEntity ->
        Competitive(
            name = firestoreEntity.name,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            order = firestoreEntity.order
        )
    }

    val dangerZone: suspend (DangerZoneFirestoreEntity) -> DangerZone = { firestoreEntity ->
        DangerZone(
            name = firestoreEntity.name,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            order = firestoreEntity.order
        )
    }

    val mapHolder: suspend (MapHolderFirestoreEntity) -> MapHolder = { firestoreEntity ->
        MapHolder(
            isCompetitive = firestoreEntity.isCompetitive,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            mapDescription = firestoreEntity.map,
            mapDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.map
                )
            },
            wallpaperDescription = firestoreEntity.wallpaper,
            wallpaperDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.wallpaper
                )
            }
        )
    }

    val mapPoint: suspend (MapPointFirestoreEntity) -> MapPoint = { firestoreEntity ->
        MapPoint(
            name = firestoreEntity.name,
            mapDocumentName = firestoreEntity.mapDocumentName,
            grenadeType = firestoreEntity.grenadeType,
            tickrateTypes = firestoreEntity.tickrateTypes,
            previewStartDescription = firestoreEntity.previewStart,
            previewStartDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.previewStart
                )
            },
            previewEndDescription = firestoreEntity.previewEnd,
            previewEndDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.previewEnd
                )
            },
            contentImages = firestoreEntity.contentImages.map { img ->
                cs.async(coroutineContext.job) {
                    contentRepository.getFile(
                        pathToFolder = firestoreEntity.getDocumentName(),
                        fileName = img
                    )
                }
            }.toList(),
            contentVideos = firestoreEntity.contentVideos.map { video ->
                cs.async(coroutineContext.job) {
                    contentRepository.getFileLink(
                        pathToFolder = firestoreEntity.getDocumentName(),
                        fileName = video
                    )
                }
            }.toList()
        )
    }

    val profileRank: suspend (ProfileRankFirestoreEntity) -> ProfileRank = { firestoreEntity ->
        ProfileRank(
            name = firestoreEntity.name,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            order = firestoreEntity.order,
            xp = firestoreEntity.xp
        )
    }

    val weapon: suspend (WeaponFirestoreEntity) -> Weapon = { firestoreEntity ->
        Weapon(
            externalId = firestoreEntity.externalId,
            weaponType = firestoreEntity.weaponType,
            teamTypes = firestoreEntity.teamTypes,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            sprayDescription = firestoreEntity.spray,
            sprayDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.spray
                )
            },
            recoilDescription = firestoreEntity.recoil,
            recoilDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.recoil
                )
            },
            armorRatio = firestoreEntity.armorRatio,
            cycleTime = firestoreEntity.cycleTime,
            damage = firestoreEntity.damage,
            inGamePrice = firestoreEntity.inGamePrice,
            inaccuracyCrouch = firestoreEntity.inaccuracyCrouch,
            inaccuracyCrouchAlt = firestoreEntity.inaccuracyCrouchAlt,
            inaccuracyMove = firestoreEntity.inaccuracyMove,
            inaccuracyMoveAlt = firestoreEntity.inaccuracyMoveAlt,
            inaccuracyStand = firestoreEntity.inaccuracyStand,
            inaccuracyStandAlt = firestoreEntity.inaccuracyStandAlt,
            killAward = firestoreEntity.killAward,
            maxPlayerSpeed = firestoreEntity.maxPlayerSpeed,
            penetration = firestoreEntity.penetration,
            primaryClipSize = firestoreEntity.primaryClipSize,
            primaryReserveAmmoMax = firestoreEntity.primaryReserveAmmoMax,
            rangeModifier = firestoreEntity.rangeModifier,
            recoilAngleVariance = firestoreEntity.recoilAngleVariance,
            recoilAngleVarianceAlt = firestoreEntity.recoilAngleVarianceAlt,
            recoilMagnitude = firestoreEntity.recoilMagnitude,
            recoilMagnitudeAlt = firestoreEntity.recoilMagnitudeAlt,
            recoilMagnitudeVariance = firestoreEntity.recoilMagnitudeVariance,
            recoilMagnitudeVarianceAlt = firestoreEntity.recoilMagnitudeVarianceAlt,
            recoveryTimeCrouchFinal = firestoreEntity.recoveryTimeCrouchFinal,
            recoveryTimeStandFinal = firestoreEntity.recoveryTimeStandFinal,
            spread = firestoreEntity.spread,
            spreadAlt = firestoreEntity.spreadAlt
        )
    }

    val wingman: suspend (WingmanFirestoreEntity) -> Wingman = { firestoreEntity ->
        Wingman(
            name = firestoreEntity.name,
            logoDescription = firestoreEntity.logo,
            logoDeferred = cs.async(coroutineContext.job) {
                contentRepository.getFile(
                    pathToFolder = firestoreEntity.getDocumentName(),
                    fileName = firestoreEntity.logo
                )
            },
            order = firestoreEntity.order
        )
    }
}