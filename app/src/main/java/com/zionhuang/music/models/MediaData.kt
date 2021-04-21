package com.zionhuang.music.models

import android.os.Parcelable
import android.support.v4.media.MediaMetadataCompat
import com.zionhuang.music.constants.MediaConstants.ArtworkType
import com.zionhuang.music.constants.MediaConstants.EXTRA_ARTWORK_TYPE
import com.zionhuang.music.constants.MediaConstants.TYPE_SQUARE
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaData(
        var id: String? = null,
        var title: String? = null,
        var artistName: String? = null,
        var artwork: String? = null,
        var duration: Int? = null,
        @ArtworkType var artworkType: Int = TYPE_SQUARE,
) : Parcelable {
    fun pullMediaMetadata(mediaMetadata: MediaMetadataCompat): MediaData = apply {
        id = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)
        title = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE)
        artistName = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE)
        artwork = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI)
        duration = (mediaMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION) / 1000).toInt()
        artworkType = mediaMetadata.getLong(EXTRA_ARTWORK_TYPE).toInt()
    }
}

fun MediaMetadataCompat.toMediaData(): MediaData = MediaData().pullMediaMetadata(this)