package com.example.example

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//@Parcelize
// extend parcelable when ready to parcelize

@Entity(
  tableName = "artworks"
)
@Parcelize
 data class ArtworkObject (

  @SerializedName("_score"               ) val score               : Double?  = null,
  @SerializedName("is_public_domain"     ) val isPublicDomain      : Boolean? = null,
  @SerializedName("medium_display"       ) val mediumDisplay       : String?  = null,
  @SerializedName("colorfulness"         ) val colorfulness        : Double?  = null,
  @SerializedName("inscriptions"         ) val inscriptions        : String?  = null,
  @SerializedName("latitude"             ) val latitude            : String?  = null,
  @SerializedName("artist_display"       ) val artistDisplay       : String?  = null,
  @SerializedName("artist_title"         ) val artistTitle         : String?  = null,
  @SerializedName("provenance_text"      ) val provenanceText      : String?  = null,
  @SerializedName("title"                ) val title               : String?  = null,
  @SerializedName("place_of_origin"      ) val placeOfOrigin       : String?  = null,
  @SerializedName("credit_line"          ) val creditLine          : String?  = null,
  @PrimaryKey(autoGenerate = false)
  @SerializedName("id"                   ) val id                  : Int?     = null,
  @SerializedName("image_id"             ) val imageId             : String?  = null,
  @SerializedName("classification_title" ) val classificationTitle : String?  = null,
  @SerializedName("style_title"          ) val styleTitle          : String? = null,
  @SerializedName("longitude"            ) val longitude           : String?  = null
): Parcelable


{
  //until access to Coinfig...
  fun getArtImageUrl(): String {
    val artUrlToDisplay = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg"
    return artUrlToDisplay
  }
  fun getOtherImgUrl(): String {
    val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
    return urlForDisplay
  }

}